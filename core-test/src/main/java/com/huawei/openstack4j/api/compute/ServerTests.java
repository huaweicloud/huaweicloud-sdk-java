/*******************************************************************************
 * 	Copyright 2018 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.compute;

import okhttp3.mockwebserver.RecordedRequest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.ServerPassword;
import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.model.compute.actions.EvacuateOptions;

/**
 * Test cases for Server based Services
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName = "Servers")
public class ServerTests extends AbstractTest {

	private static final String JSON_SERVERS = "/compute/servers.json";
	private static final String JSON_SERVER_CREATE = "/compute/server_create.json";
	private static final String JSON_SERVER_EVACUATE = "/compute/server_evacuate.json";
	private static final String JSON_SERVER_CONSOLE_OUTPUT = "/compute/server_console_output.json";
	private static final String JSON_SERVER_ATTACHED_VOLUMES_LIST_OUTPUT = "/compute/server_attached_volumes_list_output.json";

	@Test
	public void listServer() throws Exception {
		respondWith(JSON_SERVERS);

		List<? extends Server> servers = osv3().compute().servers().list();
		assertEquals(1, servers.size());

		takeRequest();

		Server s = servers.get(0);
		assertEquals(1, s.getAddresses().getAddresses("private").size());
		assertEquals("192.168.0.3", s.getAddresses().getAddresses("private").get(0).getAddr());
		assertEquals(Status.ACTIVE, s.getStatus());
		assertEquals("new-server-test", s.getName());
	}
	
//	@Test
//	public void getMetadataItem() throws Exception{
//		respondWith(200,"{\"meta\": {\"org\": \"huawei\"}}");
//		Map<String,String> metadataItem = osv3().compute().servers().getMetadataItem("kdsf-cj", "org");
//		assertEquals("huawei", metadataItem.get("org"));
//	}
//
//	@Test
//	public void setMetadataItem() throws Exception{
//		respondWith(200,"{\"meta\": {\"org\": \"huawei1\"}}");
//		Map<String,String> metadataItem = osv3().compute().servers().setMetadataItem("kdsf-cj", "org", "huawei1");
//		assertEquals("huawei1", metadataItem.get("org"));
//	}
//
//	@Test
//	public void listAttachedVolumes() throws Exception{
//		respondWith(JSON_SERVER_ATTACHED_VOLUMES_LIST_OUTPUT);
//		List<? extends VolumeAttachment> attachedVolumes = osv3().compute().servers().listAttachedVolumes("4d8c3732-a248-40ed-bebc-539a6ffd25c0");
//		assertEquals(attachedVolumes.size(),2);
//		assertEquals(attachedVolumes.get(0).getDevice(),"/dev/sdd");
//	}
//
//	@Test
//	public void getAttachVolume() throws Exception{
//		respondWith(200,"{\"volumeAttachment\": "
//				+ "{\"device\": \"/dev/sdd\","
//				+ "\"id\": \"a26887c6-c47b-4654-abb5-dfadf7d3f803\","
//				+ "\"serverId\": \"4d8c3732-a248-40ed-bebc-539a6ffd25c0\","
//				+ "\"volumeId\": \"a26887c6-c47b-4654-abb5-dfadf7d3f803\"}}");
//		VolumeAttachment volumeAttachment = osv3().compute().servers().getAttachVolume("4d8c3732-a248-40ed-bebc-539a6ffd25c0", "a26887c6-c47b-4654-abb5-dfadf7d3f803");
//		assertEquals(volumeAttachment.getDevice(),"/dev/sdd");
//	}

	@Test(expectedExceptions = ServerResponseException.class, invocationCount = 10)
	public void serverError() throws Exception {
		String jsonResponse = "{\"computeFault\": {"
				+ "\"message\": \"The server has either erred or is incapable of performing the requested operation.\", "
				+ "\"code\": 500}}";

		respondWith(500, jsonResponse);
		osv3().compute().servers().get("05184ba3-00ba-4fbc-b7a2-03b62b884931");
		Assert.fail("Exception should have been thrown.");

		takeRequest();
	}

	@Test
	public void createServer() throws Exception {
		respondWith(JSON_SERVER_CREATE);

		ServerCreate build = Builders.server().name("server-test-1").min(2).max(3)
				.networks(Lists.newArrayList("network-1")).configDrive(true).addMetadataItem("key", "value").build();
		Server created = osv3().compute().servers().boot(build);
		assertEquals("server-test-1", created.getName());
		assertEquals(2, created.getMinCount().intValue());
		assertEquals(3, created.getMaxCount().intValue());
		

		RecordedRequest request = takeRequest();
		String body = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/server_create_request.json");
		Assert.assertEquals(body, expectBody);
		// JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		// JsonNode server = node.get("server");
		// assertEquals("server-test-1", server.get("name").asText());
		// assertTrue(server.get("min_count").isInt());
		// assertEquals(2, server.get("min_count").asInt());
		// assertTrue(server.get("max_count").isInt());
		// assertEquals(3, server.get("max_count").asInt());
		// assertEquals(false, server.get("return_reservation_id").asBoolean());
	}

	/**
	@Test
	public void createServerAndReturnReservationId() throws Exception {
		respondWith("/compute/server_create_and_return_reservation_id.json");
	
		ServerCreate build = Builders.server().name("server-test-1").min(2).max(3).build();
		String reservationId = osv3().compute().servers().bootAndReturnReservationId(build);
		assertEquals("r-3fhpjulh", reservationId);
	
		RecordedRequest request = takeRequest();
	
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		JsonNode server = node.get("server");
		assertEquals("server-test-1", server.get("name").asText());
		assertTrue(server.get("min_count").isInt());
		assertEquals(2, server.get("min_count").asInt());
		assertTrue(server.get("max_count").isInt());
		assertEquals(3, server.get("max_count").asInt());
		assertEquals(true, server.get("return_reservation_id").asBoolean());
	}*/

	@Override
	protected Service service() {
		return Service.COMPUTE;
	}

	@Test
	public void evacuateServer() throws Exception {
		respondWith(JSON_SERVER_EVACUATE);

		ServerPassword password = osv3().compute().servers().evacuate("e565cbdb-8e74-4044-ba6e-0155500b2c46",
				EvacuateOptions.create().host("server-test-1").onSharedStorage(false));
		assertEquals(password.getPassword(), "MySecretPass");

		takeRequest();
	}

	@Test
	public void getServerConsoleOutput() throws Exception {
		// Get console output with explicit length
		int length = 50;
		respondWith(JSON_SERVER_CONSOLE_OUTPUT);
		String console = osv3().compute().servers().getConsoleOutput("existing-uuid", length);

		// Check that the request is the one we expect
		RecordedRequest request = server.takeRequest();

		assertNotNull(console);
		assertTrue(console.length() > 0);

		String requestBody = request.getBody().readUtf8();
		//assertTrue(requestBody.contains("\"os-getConsoleOutput\" : {"));
		//assertTrue(requestBody.contains("\"length\" : " + length));

		// Get full console output
		respondWith(JSON_SERVER_CONSOLE_OUTPUT);
		console = osv3().compute().servers().getConsoleOutput("existing-uuid", 0);

		// Check that the request is the one we expect
		request = takeRequest();

		assertNotNull(console);
		assertTrue(console.length() > 0);

		requestBody = request.getBody().readUtf8();
		assertFalse(requestBody.contains("\"length\""));
	}

	@Test
	public void getServerConsoleOutputNonExistingServer() throws Exception {
		respondWith(404);

		String console = osv3().compute().servers().getConsoleOutput("non-existing-uuid", 0);
		assertNull(console);

		takeRequest();
	}

	@Test
	public void stopServer() throws Exception {
		respondWith(202);
		ActionResponse stopResp = osv3().compute().servers().stop("server_uuid", StopType.HARD);
		assertTrue(stopResp.isSuccess());

		takeRequest();
	}

	@Test
	public void stopServerNonExistingServer() throws Exception {
		String jsonResponse = "{\"itemNotFound\": {"
				+ "\"message\": \"Instance 1ef755e8-2a64-496a-bfd3-c178e023d9646 could not be found.\", "
				+ "\"code\": 404}}";
		respondWith(404, jsonResponse);
		ActionResponse stopResp = osv3().compute().servers().stop("1ef755e8-2a64-496a-bfd3-c178e023d9646",
				StopType.SOFT);
		assertFalse(stopResp.isSuccess());
		assertEquals(404, stopResp.getCode());
		assertEquals("[404] Instance 1ef755e8-2a64-496a-bfd3-c178e023d9646 could not be found.", stopResp.getFault());

		takeRequest();
	}

}
