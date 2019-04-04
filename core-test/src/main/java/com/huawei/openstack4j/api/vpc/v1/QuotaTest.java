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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Resource;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Resource.Quotas;

/**
 * Test The implementation of manipulation of Quota
 * @author ChangjunZhao
 * @date   2018-03-26
 */
@Test(suiteName = "Vpc/Quota", enabled = true)
public class QuotaTest extends AbstractTest {
	
	@Test
	public void testListQuotas() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_quota_response.json");

		Quotas quotas = osv3().vpc().quotas().list();
		
		Resource resource = quotas.getList().get(0);
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/quotas");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(quotas);
		Assert.assertEquals(quotas.getList().size(), 4);
		Assert.assertEquals(resource.getType(), "vpc");
		Assert.assertEquals(resource.getQuota().intValue(),150);
	}
	
	@Test
	public void testListQuotasWithType() throws IOException, InterruptedException {
		respondWith("/vpc/v1/list_quota_response.json");

		Quotas quotas = osv3().vpc().quotas().list("vpc");
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/quotas?type=vpc");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertNotNull(quotas);
	}

	@Override
	protected Service service() {
		return Service.VPC;
	}

}
