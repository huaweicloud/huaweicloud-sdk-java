/*******************************************************************************
 * 	Copyright 2018 HuaWei Tld                                     
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
package com.huawei.openstack4j.api.vpc.v1;

import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.FixedIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Port;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PortCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PortUpdate;

/**
 * Test The implementation of manipulation of Port
 * 
 * @author ChangjunZhao
 * @date 2018-03-25
 */
@Test(suiteName = "Vpc/Port", enabled = true)
public class PortTest extends AbstractTest {

	@Test
	public void testDeletePort() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().vpc().ports().delete("13551d6b-755d-4757-b956-536f674975c0");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/ports/13551d6b-755d-4757-b956-536f674975c0");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(response.getCode(), 200);
	}

	@Test
	public void testListPorts() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_port_response.json");

		List<? extends Port> list = osv3().vpc().ports().list();

		Port port = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/ports");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(port.getId(), "d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(port.getFixedIps().get(0).getIpAddress(), "10.128.1.10");
		Assert.assertEquals(port.getSecurityGroups().get(0), "02b4e8ee-74fa-4a31-802e-5490df11245e");
	}

	@Test
	public void testListPortsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_port_response.json");

		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("name", "test");
		List<? extends Port> list = osv3().vpc().ports().list(filteringParams);

		Port port = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/ports?name=test");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(port.getId(), "d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(port.getFixedIps().get(0).getIpAddress(), "10.128.1.10");
		Assert.assertEquals(port.getSecurityGroups().get(0), "02b4e8ee-74fa-4a31-802e-5490df11245e");
	}

	@Test
	public void testCreatePort() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_port_response.json");

		FixedIp ip = FixedIp.builder().ipAddress("10.128.1.10").subnetId("70f2e74b-e660-410a-b754-0ca46744348a")
				.build();

		ArrayList<FixedIp> list = new ArrayList<FixedIp>();
		list.add(ip);

		PortCreate portCreate = PortCreate.builder().adminStateUp(true).name("test").networkId("5b808927-13c9-4e60-a4f4-ed6ffe225167")
				.tenantId("43f2d1cca56a40729dcb17212482f34d").fixedIps(list).build();

		Port port = osv3().vpc().ports().create(portCreate);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/ports");
		Assert.assertEquals(request.getMethod(), "POST");
		Assert.assertEquals(port.getId(), "d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(port.getFixedIps().get(0).getIpAddress(), "10.128.1.10");
		Assert.assertEquals(port.getDnsAssignment().size(), 1);
		Assert.assertEquals(port.getSecurityGroups().get(0), "02b4e8ee-74fa-4a31-802e-5490df11245e");
	}

	@Test
	public void testGetPort() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_port_response.json");

		Port port = osv3().vpc().ports().get("d00f9c13-412f-4855-8af3-de5d8c24cd60");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/ports/d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(port.getId(), "d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(port.getFixedIps().get(0).getIpAddress(), "10.128.1.10");
		Assert.assertEquals(port.getSecurityGroups().get(0), "02b4e8ee-74fa-4a31-802e-5490df11245e");
	}

	@Test
	public void testUpdatePort() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_port_response.json");

		PortUpdate portUpdate = PortUpdate.builder().name("test").build();

		Port port = osv3().vpc().ports().update("d00f9c13-412f-4855-8af3-de5d8c24cd60",portUpdate);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/ports/d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(request.getMethod(), "PUT");
		Assert.assertEquals(port.getId(), "d00f9c13-412f-4855-8af3-de5d8c24cd60");
		Assert.assertEquals(port.getFixedIps().get(0).getIpAddress(), "10.128.1.10");
		Assert.assertEquals(port.getSecurityGroups().get(0), "02b4e8ee-74fa-4a31-802e-5490df11245e");
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
