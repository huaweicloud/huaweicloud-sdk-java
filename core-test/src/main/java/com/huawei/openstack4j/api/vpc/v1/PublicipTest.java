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
import com.huawei.openstack4j.openstack.vpc.v1.contants.IpType;
import com.huawei.openstack4j.openstack.vpc.v1.contants.ShareType;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIpUpdate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualBandWidth;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIpType;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIps;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIpsResp;

/**
 * Test The implementation of manipulation of PublicIp
 * @author ChangjunZhao
 * @date   2018-03-26
 */
@Test(suiteName = "Vpc/PublicIp", enabled = true)
public class PublicipTest extends AbstractTest {

	@Test
	public void testDeletePublicip() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().vpc().publicips().delete("13551d6b-755d-4757-b956-536f674975c0");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/publicips/13551d6b-755d-4757-b956-536f674975c0");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(response.getCode(), 200);
	}
	
	@Test
	public void testListPublicips() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_publicip_response.json");

		List<? extends PublicIp> list = osv3().vpc().publicips().list();
		
		PublicIp publicip = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/publicips");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(publicip.getId(), "6285e7be-fd9f-497c-bc2d-dd0bdea6efe0");
		Assert.assertEquals(publicip.getBandwidthName(),"bandwidth-test");
		Assert.assertEquals(publicip.getBandwidthSize().intValue(),5);
	}
	
	@Test
	public void testListPublicipsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_publicip_response.json");

		Map<String,String> filteringParams = new HashMap<String,String>();
		filteringParams.put("limit", "2");
		filteringParams.put("marker", "6285e7be-fd9f-497c-bc2d-dd0bdea6efe0");
		List<? extends PublicIp> list = osv3().vpc().publicips().list(filteringParams);
		
		PublicIp publicip = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/publicips?marker=6285e7be-fd9f-497c-bc2d-dd0bdea6efe0&limit=2");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(publicip.getId(), "6285e7be-fd9f-497c-bc2d-dd0bdea6efe0");
		Assert.assertEquals(publicip.getBandwidthName(),"bandwidth-test");
		Assert.assertEquals(publicip.getBandwidthSize().intValue(),5);
	}
	
	@Test
	public void testApplyPublicip() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_publicip_response.json");

		VirtualPublicIpType publicipnew = VirtualPublicIpType.builder().type(IpType.BGP).build();

		VirtualBandWidth bw = VirtualBandWidth.builder().shareType(ShareType.PER).size(10).build();

		VirtualPublicIps apply = VirtualPublicIps.builder().virtualPublicIp(publicipnew).virtualBandwidth(bw).build();
		VirtualPublicIpsResp publicip = osv3().vpc().publicips().apply(apply);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/publicips");
		Assert.assertEquals(request.getMethod(), "POST");
		Assert.assertEquals(publicip.getId(), "2ec9b78d-9368-46f3-8f29-d1a95622a568");
		Assert.assertEquals(publicip.getBandwidthSize().intValue(),10);
		Assert.assertEquals(publicip.getType(),"5_bgp");
	}
	
	@Test
	public void testGetPublicip() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_publicip_response.json");

		VirtualPublicIp publicip = osv3().vpc().publicips().get("2ec9b78d-9368-46f3-8f29-d1a95622a568");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/publicips/2ec9b78d-9368-46f3-8f29-d1a95622a568");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(publicip.getId(), "2ec9b78d-9368-46f3-8f29-d1a95622a568");
		Assert.assertEquals(publicip.getBandwidthName(),"bandwidth-test");
		Assert.assertEquals(publicip.getType(),"5_bgp");
		Assert.assertEquals(publicip.getBandwidthShareType(),"PER");
	}
	
	@Test
	public void testUpdatePublicip() throws IOException, InterruptedException {
		respondWith("/vpc/v1/create_get_update_publicip_response.json");
		
		PublicIpUpdate publicIpUpdate = PublicIpUpdate.builder().portId("f588ccfa-8750-4d7c-bf5d-2ede24414706").build();

		PublicIp publicip = osv3().vpc().publicips().update("2ec9b78d-9368-46f3-8f29-d1a95622a568",publicIpUpdate);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/publicips/2ec9b78d-9368-46f3-8f29-d1a95622a568");
		Assert.assertEquals(request.getMethod(), "PUT");
		Assert.assertEquals(publicip.getId(), "2ec9b78d-9368-46f3-8f29-d1a95622a568");
		Assert.assertEquals(publicip.getBandwidthName(),"bandwidth-test");
		Assert.assertEquals(publicip.getBandwidthSize().intValue(),10);
		Assert.assertEquals(publicip.getType(),"5_bgp");
		Assert.assertEquals(publicip.getPortId(),"f588ccfa-8750-4d7c-bf5d-2ede24414706");
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
