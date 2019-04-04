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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Vpc;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VpcCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VpcUpdate;

/**
 * Test The implementation of manipulation of Vpc
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Test(suiteName = "Vpc/Vpc", enabled = true)
public class VpcTest extends AbstractTest {

	@Test
	public void testDeleteVpc() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().vpc().vpcs().delete("vpc_id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs/vpc_id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(response.getCode(), 200);
	}
	
	@Test
	public void testListVpcs() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_vpc_response.json");

		List<? extends Vpc> list = osv3().vpc().vpcs().list();
		
		Vpc vpc = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 3);
		Assert.assertEquals(vpc.getId(), "13551d6b-755d-4757-b956-536f674975c0");
		Assert.assertEquals(vpc.getCidr(),"172.16.0.0/16");
	}
	
	@Test
	public void testListVpcsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_vpc_response.json");

		Map<String,String> filteringParams = new HashMap<String,String>();
		filteringParams.put("limit", "3");
		filteringParams.put("marker", "13551d6b-755d-4757-b956-536f674975c0");
		List<? extends Vpc> list = osv3().vpc().vpcs().list(filteringParams);
		
		Vpc vpc = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs?marker=13551d6b-755d-4757-b956-536f674975c0&limit=3");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 3);
		Assert.assertEquals(vpc.getId(), "13551d6b-755d-4757-b956-536f674975c0");
		Assert.assertEquals(vpc.getCidr(),"172.16.0.0/16");
	}
	
	@Test
	public void testCreateVpc() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_vpc_response.json");

		VpcCreate creation = VpcCreate.builder().name("vpc").cidr("192.168.0.0/16").build();
		Vpc vpc = osv3().vpc().vpcs().create(creation);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs");
		Assert.assertEquals(request.getMethod(), "POST");
		Assert.assertEquals(vpc.getName(), "vpc");
		Assert.assertEquals(vpc.getCidr(),"192.168.0.0/16");
	}
	
	@Test
	public void testGetVpc() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_vpc_response.json");

		Vpc vpc = osv3().vpc().vpcs().get("99d9d709-8478-4b46-9f3f-2206b1023fd3");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs/99d9d709-8478-4b46-9f3f-2206b1023fd3");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(vpc.getId(), "99d9d709-8478-4b46-9f3f-2206b1023fd3");
		Assert.assertEquals(vpc.getName(), "vpc");
		Assert.assertEquals(vpc.getCidr(),"192.168.0.0/16");
	}
	
	@Test
	public void testUpdateVpc() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_vpc_response.json");

		VpcUpdate vpcUpdate = VpcUpdate.builder().name("vpc").cidr("192.168.0.0/16").build();
		
		Vpc vpc = osv3().vpc().vpcs().update("99d9d709-8478-4b46-9f3f-2206b1023fd3",vpcUpdate);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs/99d9d709-8478-4b46-9f3f-2206b1023fd3");
		Assert.assertEquals(request.getMethod(), "PUT");
		Assert.assertEquals(vpc.getId(), "99d9d709-8478-4b46-9f3f-2206b1023fd3");
		Assert.assertEquals(vpc.getName(), "vpc");
		Assert.assertEquals(vpc.getCidr(),"192.168.0.0/16");
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
