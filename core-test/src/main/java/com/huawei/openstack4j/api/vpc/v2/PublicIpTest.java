 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.api.vpc.v2;

import static org.testng.Assert.assertEquals;
import okhttp3.mockwebserver.RecordedRequest;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.vpc.v2.contants.IpType;
import com.huawei.openstack4j.openstack.vpc.v2.contants.ShareType;
import com.huawei.openstack4j.openstack.vpc.v2.domain.AsyncPublicipRespEntity;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidth;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualPublicIpType;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualPublicIps;

@Test(suiteName = "VPC/PublicIp")
public class PublicIpTest extends AbstractTest{

	@Override
	protected Service service() {
		return Service.VPC2;
	}

	
	public void applyTest() throws InterruptedException{
		respondWith(200, "{\"order_id\": \"this-is-a-order-id\"}");
		VirtualPublicIps publicIps = VirtualPublicIps.builder()
				.virtualBandwidth(
				VirtualBandWidth.builder().size(10).name("bandwidth123").shareType(ShareType.PER).build())
				.virtualPublicIp(VirtualPublicIpType.builder().type(IpType.BGP).build()).build();
		AsyncPublicipRespEntity respEntity = osv3().vpcV2().publicips().apply(publicIps);
		
		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v2.0/project-id/publicips");
		assertEquals(request.getMethod(), "POST");
		assertEquals(respEntity.getOrderId(), "this-is-a-order-id");
		
	}
}
