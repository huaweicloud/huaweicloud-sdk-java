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
package com.huawei.openstack4j.api.ims.v2;

import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByExternalImage;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByInstance;
@Test
public class CloudImageV2Tests extends AbstractTest{
	
	@Test
	public void testCreateByInstance(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		ImageCreateByInstance build = ImageCreateByInstance.builder().name("name").instanceId("instanceId").build();
		String jobId = osv3().imsV2().images().create(build);
		assertNotNull(jobId);
	}
	
	@Test
	public void testCreateByExternalImage(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		ImageCreateByExternalImage build = ImageCreateByExternalImage.builder().name("name").imageUrl("imageUrl").minDisk(40).build();
		String jobId = osv3().imsV2().images().create(build);
		assertNotNull(jobId);
	}
	
	
	@Override
	protected Service service() {
		return Service.IMSV2;
	}

}
