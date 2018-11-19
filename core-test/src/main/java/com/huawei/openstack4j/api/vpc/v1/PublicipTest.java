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
package com.huawei.openstack4j.api.vpc.v1;

import static org.testng.Assert.assertNotNull;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIp;

@Test(suiteName = "VPC/Publicip")
public class PublicipTest  extends AbstractTest{

	private static final String JSON_PUBLICIP = "/vpc/v1/publicip.json";
	private String publicIp = "******";
	@Override
	protected Service service() {
		return Service.VPC;
	}
	
	@Test
	public void getPublicIpTest() throws Exception{
		respondWith(JSON_PUBLICIP);
		VirtualPublicIp resp = osv3().vpc().publicips().get(publicIp);
		assertNotNull(resp);
	}
	
	@Test
	public void deletePublicIpTest() throws Exception{
		respondWith(200);
		ActionResponse delete = osv3().vpc().publicips().delete(publicIp);
		Assert.assertEquals(delete.getCode(), 200);
	}
}
