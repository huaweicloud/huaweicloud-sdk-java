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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.ims.v2.domain.Image;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByExternalImage;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByInstance;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageUpdate;
@Test
public class CloudImageV2Tests extends AbstractTest{
	private static final String IMS_LIST = "/image/v2/image_list.json";
	private static final String IMS_UPDATE = "/image/v2/image_update.json";
	@SuppressWarnings("unchecked")
	@Test
	public void createByInstanceTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		ImageCreateByInstance build = ImageCreateByInstance.builder()
				.name("name")
				.instanceId("instanceId")
				.description("******")
				.dataImages(Collections.EMPTY_LIST)
				.tags(Collections.EMPTY_LIST)
				.imageTags(Collections.EMPTY_LIST)
				.maxRam(Integer.valueOf(1))
				.minRam(Integer.valueOf(1))
				.build();
		String jobId = osv3().imsV2().images().create(build);
		assertNotNull(jobId);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void createByExternalImageTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		ImageCreateByExternalImage build = ImageCreateByExternalImage.builder()
				.name("name")
				.imageUrl("imageUrl")
				.minDisk(40)
				.description("******")
				.osVersion("******")
				.isConfig(false)
				.cmkId("******")
				.tags(Collections.EMPTY_LIST)
				.image_tags(Collections.EMPTY_LIST)
				.type("******")
				.enterpriseProjectId("******")
				.maxRam(Integer.valueOf(1))
				.minRam(Integer.valueOf(1))
				.build();
		String jobId = osv3().imsV2().images().create(build);
		assertNotNull(jobId);
	}
	

	@Test
	public void listTest() throws IOException{
		respondWith(IMS_LIST);
		List<Image> list = osv3().imsV2().images().list();
		assertNotNull(list);
	}
	
	@Test
	public void updateTest() throws IOException{
		respondWith(IMS_UPDATE);
		ImageUpdate imageUpdate = ImageUpdate.builder()
				.op("replace")
				.path("/name")
				.value("ims_test")
				.build();
		List<ImageUpdate> list = new ArrayList<ImageUpdate>();
		list.add(imageUpdate);
		String imageId = "******";
		Image image = osv3().imsV2().images().update(list, imageId);
		assertNotNull(image);
	}
	
	
	@Override
	protected Service service() {
		return Service.IMSV2;
	}

}
