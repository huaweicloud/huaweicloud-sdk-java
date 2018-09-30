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
package com.huawei.openstack4j.api.cloudeye;

import static org.testng.AssertJUnit.*;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;

@Test(groups = "Cloudeye", suiteName = "Cloudeye/Quota")
public class CloudEyeQuotaServiceTest extends AbstractTest {
	private static final String JSON_QUOTA = "/cloudeye/get_quota.json";

	@Override
	protected Service service() {
		return Service.CLOUD_EYE;
	}

	public void getQuotaTest() throws Exception {
		respondWith(JSON_QUOTA);
		List<Quota> list = osv3().cloudEye().quotas().get();
		assertNotNull(list);
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getType(), ResourceType.ALARM);
	}
}
