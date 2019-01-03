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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Subnet;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SubnetCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SubnetUpdate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SubnetUpdateResp;

import okhttp3.mockwebserver.RecordedRequest;

/**
 * Test The implementation of manipulation of Subnet
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Test(suiteName = "Vpc/Subnet", enabled = true)
public class SubnetTest extends AbstractTest {

	@Test
	public void testDeleteSubnet() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().vpc().subnets().delete("13551d6b-755d-4757-b956-536f674975c0", "23551d6b-755d-4757-b956-536f674975c1");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs/13551d6b-755d-4757-b956-536f674975c0/subnets/23551d6b-755d-4757-b956-536f674975c1");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(response.getCode(), 200);
	}
	
	@Test
	public void testListSubnets() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_subnet_response.json");

		List<? extends Subnet> list = osv3().vpc().subnets().list();
		
		Subnet subnet = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/subnets");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(subnet.getId(), "9596613f-5d79-4a6a-8be0-ff468f388a16");
		Assert.assertEquals(subnet.getCidr(),"192.168.0.0/24");
		Assert.assertEquals(subnet.getGatewayIp(),"192.168.0.1");
		Assert.assertEquals(subnet.getDnsList().get(0),"100.125.1.250");
	}
	
	@Test
	public void testListSubnetsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_subnet_response.json");

		Map<String,String> filteringParams = new HashMap<String,String>();
		filteringParams.put("limit", "2");
		filteringParams.put("marker", "9596613f-5d79-4a6a-8be0-ff468f388a16");
		filteringParams.put("vpc_id", "b1ff6ee0-883f-47f9-8a59-6bb42dd01ff0");
		List<? extends Subnet> list = osv3().vpc().subnets().list(filteringParams);
		
		Subnet subnet = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/subnets?vpc_id=b1ff6ee0-883f-47f9-8a59-6bb42dd01ff0&marker=9596613f-5d79-4a6a-8be0-ff468f388a16&limit=2");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(subnet.getId(), "9596613f-5d79-4a6a-8be0-ff468f388a16");
		Assert.assertEquals(subnet.getCidr(),"192.168.0.0/24");
		Assert.assertEquals(subnet.getGatewayIp(),"192.168.0.1");
		Assert.assertEquals(subnet.getDnsList().get(0),"100.125.1.250");
	}
	
	@Test
	public void testCreateSubnet() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_subnet_response.json");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("114.114.114.114");
		list.add("114.114.115.115");
		
		SubnetCreate creation = SubnetCreate.builder().name("subnet").cidr("192.168.20.0/24").gatewayIp("192.168.20.1")
				.dhcpEnable(true).primaryDns("114.114.114.114").secondaryDns("114.114.115.115").dnsList(list)
				.availabilityZone("aa-bb-cc").vpcId("3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85").build();

		Subnet subnet = osv3().vpc().subnets().create(creation);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/subnets");
		Assert.assertEquals(request.getMethod(), "POST");
		Assert.assertEquals(subnet.getName(), "subnet");
		Assert.assertEquals(subnet.getCidr(),"192.168.20.0/24");
		Assert.assertEquals(subnet.getDnsList().get(0),"114.114.114.114");
	}
	
	@Test
	public void testGetSubnet() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_subnet_response.json");

		Subnet subnet = osv3().vpc().subnets().get("4779ab1c-7c1a-44b1-a02e-93dfc361b32d");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/subnets/4779ab1c-7c1a-44b1-a02e-93dfc361b32d");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(subnet.getId(), "4779ab1c-7c1a-44b1-a02e-93dfc361b32d");
		Assert.assertEquals(subnet.getName(), "subnet");
		Assert.assertEquals(subnet.getCidr(),"192.168.20.0/24");
	}
	
	@Test
	public void testUpdateSubnet() throws IOException, InterruptedException {
		respondWith("/vpc/v1/update_subnet_response.json");
		
		String subnetId = "4779ab1c-7c1a-44b1-a02e-93dfc361b32d";
		String vpcId = "3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85";
		ArrayList<String> list = new ArrayList<String>();
		list.add("114.114.114.114");
		list.add("114.114.115.115");
		SubnetUpdate subnetUpdate = SubnetUpdate.builder().name("subnet")
				.dhcpEnable(true).primaryDns("114.114.114.114").secondaryDns("114.114.115.115").dnsList(list).build();

		SubnetUpdateResp subnet = osv3().vpc().subnets().update(vpcId,subnetId,subnetUpdate);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/vpcs/3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85/subnets/4779ab1c-7c1a-44b1-a02e-93dfc361b32d");
		Assert.assertEquals(request.getMethod(), "PUT");
		Assert.assertEquals(subnet.getId(), "4779ab1c-7c1a-44b1-a02e-93dfc361b32d");
		Assert.assertEquals(subnet.getStatus(), "ACTIVE");
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
