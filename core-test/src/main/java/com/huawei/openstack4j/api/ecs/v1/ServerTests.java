/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.api.ecs.v1;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.NetworkChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1.contants.ShareType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudAbsoluteLimit;
import com.huawei.openstack4j.openstack.ecs.v1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Flavor;
import com.huawei.openstack4j.openstack.ecs.v1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1.domain.RootVolume;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "ECS/Servers")
public class ServerTests extends AbstractTest {

	@Override
	protected Service service() {
		return Service.ECS;
	}
	
	private static final String ecs_limit = "/ecs/absoluteLimit.json";
	private static final String ecs_specifications = "/ecs/flavorsSpecifications.json";
	@Test
	public void deleteServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().ecs().servers().delete(serverIds, false, false);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/delete");
		assertEquals(request.getMethod(), "POST");

		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_delete_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}

	@Test
	public void stopServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().ecs().servers().stop(serverIds, StopType.SOFT);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/action");
		assertEquals(request.getMethod(), "POST");

		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_stop_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}

	@Test
	public void batchRebootServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().ecs().servers().reboot(serverIds, RebootType.HARD);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/action");
		assertEquals(request.getMethod(), "POST");
		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_reboot_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}

	@Test
	public void batchStartServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().ecs().servers().start(serverIds);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/action");
		assertEquals(request.getMethod(), "POST");
		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_start_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}

	@Test
	public void createServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		
		Bandwidth bandwidth = Bandwidth.builder().size(10).shareType(ShareType.PER).chargeMode(NetworkChargingMode.BANDWIDTH).build();
		FloatingIPCreate build = FloatingIPCreate.builder().ipType(IpType.BGP).bandwidth(bandwidth ).build();
		
		ServerCreate creation = ServerCreate.builder().name("name").flavorRef("flavor-id").imageRef("image-id")
				.vpcId("vpc-id").addNetwork("network-id").availabilityZone("eu-de-01")
				.addSecurityGroup("securityGroupId")
				.addTag("key", "value")
				.publicIP(build)
				.addPersonality(Personality.builder().contents("some content").path("/etc/xxx").build())
				.rootVolume(RootVolume.builder().size(10).type(VolumeType.SSD).build())
				.addDataVolume(
						DataVolume.builder().size(100).type(VolumeType.SAS).multiAttach(true).passthrough(true).build())
				.addMetadata("mkey", "mvalue")
				.count(2).build();
		String jobId = osv3().ecs().servers().create(creation);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers");
		assertEquals(request.getMethod(), "POST");
		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_create_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}
	

	@Test
	public void limitsTest() throws Exception {
		respondWith(ecs_limit);
		CloudAbsoluteLimit limit = osv3().ecs().servers().limits();
		Assert.assertNotNull(limit);
	}
	
	@Test
	public void getSpecificationsTest() throws Exception {
		respondWith(ecs_specifications);
		List<Flavor> specifications = osv3().ecs().servers().getSpecifications(null);
		Assert.assertTrue(!specifications.isEmpty());
	}
}
