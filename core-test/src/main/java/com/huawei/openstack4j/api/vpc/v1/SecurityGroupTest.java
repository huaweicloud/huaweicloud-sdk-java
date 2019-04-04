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
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroup;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroupCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroupRule;

/**
 * Test The implementation of manipulation of SecurityGroup
 * @author ChangjunZhao
 * @date   2018-03-26
 */
@Test(suiteName = "Vpc/SecurityGroup", enabled = true)
public class SecurityGroupTest extends AbstractTest {

	@Test
	public void testListSecurityGroups() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_securitygroup_response.json");

		List<? extends SecurityGroup> list = osv3().vpc().securityGroups().list();
		
		SecurityGroup securityGroup = list.get(0);
		
		SecurityGroupRule rule = securityGroup.getSecurityGroupRules().get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/security-groups");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(securityGroup.getId(), "16b6e77a-08fa-42c7-aa8b-106c048884e6");
		Assert.assertEquals(rule.getEthertype(),"IPv4");
	}
	
	@Test
	public void testListSecurityGroupsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_securitygroup_response.json");

		Map<String,String> filteringParams = new HashMap<String,String>();
		filteringParams.put("limit", "2");
		filteringParams.put("marker", "16b6e77a-08fa-42c7-aa8b-106c048884e6");
		filteringParams.put("vpc_id", "3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85");
		List<? extends SecurityGroup> list = osv3().vpc().securityGroups().list(filteringParams);
		
		SecurityGroup securityGroup = list.get(0);
		
		SecurityGroupRule rule = securityGroup.getSecurityGroupRules().get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/security-groups?vpc_id=3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85&marker=16b6e77a-08fa-42c7-aa8b-106c048884e6&limit=2");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(securityGroup.getId(), "16b6e77a-08fa-42c7-aa8b-106c048884e6");
		Assert.assertEquals(rule.getEthertype(),"IPv4");
	}
	
	@Test
	public void testCreateSecurityGroup() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_securitygroup_response.json");
		
		SecurityGroupCreate sg = SecurityGroupCreate.builder().name("qq").vpcId("3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85").build();
		
		SecurityGroup securityGroup = osv3().vpc().securityGroups().create(sg);
		
		SecurityGroupRule rule = securityGroup.getSecurityGroupRules().get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/security-groups");
		Assert.assertEquals(request.getMethod(), "POST");
		Assert.assertEquals(securityGroup.getId(), "16b6e77a-08fa-42c7-aa8b-106c048884e6");
		Assert.assertEquals(rule.getEthertype(),"IPv4");
	}
	
	@Test
	public void testGetSecurityGroup() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_securitygroup_response.json");

		SecurityGroup securityGroup = osv3().vpc().securityGroups().get("16b6e77a-08fa-42c7-aa8b-106c048884e6");
		
		SecurityGroupRule rule = securityGroup.getSecurityGroupRules().get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/security-groups/16b6e77a-08fa-42c7-aa8b-106c048884e6");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(securityGroup.getId(), "16b6e77a-08fa-42c7-aa8b-106c048884e6");
		Assert.assertEquals(rule.getEthertype(),"IPv4");
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
