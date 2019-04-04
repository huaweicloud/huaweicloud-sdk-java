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
import com.huawei.openstack4j.openstack.vpc.v1.domain.PrivateIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PrivateIp.PrivateIps;

/**
 * Test The implementation of manipulation of PrivateIp
 * 
 * @author ChangjunZhao
 * @date 2018-03-26
 */
@Test(suiteName = "Vpc/PrivateIp", enabled = true)
public class PrivateIpTest extends AbstractTest {

	@Test
	public void testDeletePrivateip() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().vpc().privateIps().delete("13551d6b-755d-4757-b956-536f674975c0");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/privateips/13551d6b-755d-4757-b956-536f674975c0");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(response.getCode(), 200);
	}

	@Test
	public void testListPrivateips() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_privateip_response.json");

		List<? extends PrivateIp> list = osv3().vpc().privateIps().list("531dec0f-3116-411b-a21b-e612e42349fd");

		PrivateIp privateip = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1/project-id/subnets/531dec0f-3116-411b-a21b-e612e42349fd/privateips");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(privateip.getId(), "d600542a-b231-45ed-af05-e9930cb14f78");
		Assert.assertEquals(privateip.getIpAddress(), "192.168.1.11");
		Assert.assertEquals(privateip.getStatus(), "DOWN");
	}

	@Test
	public void testListPrivateipsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_privateip_response.json");

		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("limit", "2");
		filteringParams.put("marker", "d600542a-b231-45ed-af05-e9930cb14f78");
		List<? extends PrivateIp> list = osv3().vpc().privateIps().list("531dec0f-3116-411b-a21b-e612e42349fd",
				filteringParams);

		PrivateIp privateip = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1/project-id/subnets/531dec0f-3116-411b-a21b-e612e42349fd/privateips?marker=d600542a-b231-45ed-af05-e9930cb14f78&limit=2");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(privateip.getId(), "d600542a-b231-45ed-af05-e9930cb14f78");
		Assert.assertEquals(privateip.getIpAddress(), "192.168.1.11");
		Assert.assertEquals(privateip.getStatus(), "DOWN");
	}

	@Test
	public void testApplyPrivateip() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_privateip_response.json");

		PrivateIp privateip1 = PrivateIp.builder().subnetId("531dec0f-3116-411b-a21b-e612e42349fd").build();
		PrivateIp privateip2 = PrivateIp.builder().subnetId("531dec0f-3116-411b-a21b-e612e42349fd")
				.ipAddress("192.168.1.17").build();

		PrivateIps privateipsApp = new PrivateIps();
		ArrayList<PrivateIp> list = new ArrayList<PrivateIp>();
		list.add(privateip1);
		list.add(privateip2);
		privateipsApp.setPrivateips(list);

		List<? extends PrivateIp> privateips = osv3().vpc().privateIps().apply(privateipsApp);
		PrivateIp privateip = privateips.get(0);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/privateips");
		Assert.assertEquals(request.getMethod(), "POST");
		Assert.assertEquals(privateip.getId(), "c60c2ce1-1e73-44bd-bf48-fd688448ff7b");
		Assert.assertEquals(privateip.getIpAddress(), "192.168.1.10");
		Assert.assertEquals(privateip.getStatus(), "DOWN");
	}

	@Test
	public void testGetPrivateip() throws IOException, InterruptedException {
		respondWith("/vpc/v1/get_privateip_response.json");

		PrivateIp privateip = osv3().vpc().privateIps().get("d600542a-b231-45ed-af05-e9930cb14f78");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/privateips/d600542a-b231-45ed-af05-e9930cb14f78");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(privateip.getId(), "d600542a-b231-45ed-af05-e9930cb14f78");
		Assert.assertEquals(privateip.getIpAddress(), "192.168.1.11");
		Assert.assertEquals(privateip.getStatus(), "DOWN");
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
