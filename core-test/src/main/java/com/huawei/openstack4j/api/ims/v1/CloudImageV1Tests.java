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
package com.huawei.openstack4j.api.ims.v1;

import static org.testng.AssertJUnit.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.ims.v1.domain.ExportImage;
import com.huawei.openstack4j.openstack.ims.v1.domain.ImageCreateByBackup;
import com.huawei.openstack4j.openstack.ims.v1.domain.ImageCreateByInstance;
import com.huawei.openstack4j.openstack.ims.v1.domain.ImageCreateByOBS;
import com.huawei.openstack4j.openstack.ims.v1.domain.RegistImage;
@Test
public class CloudImageV1Tests extends AbstractTest{

	@Test
	public void createByInstanceTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		List<String> tags = new ArrayList<String>();
		tags.add("******");
		ImageCreateByInstance build = ImageCreateByInstance.builder()
				.name("name")
				.instanceId("instanceId")
				.tags(tags)
				.imageTags(Collections.EMPTY_LIST)
				.enterpriseProjectId("******")
				.maxRam(Integer.valueOf(1))
				.minRam(Integer.valueOf(1))
				.description("******")
				.build();
		String jobId = osv3().ims().images().create(build);
		assertNotNull(jobId);
	}
	
	@Test
	public void createByBackupTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		ImageCreateByBackup build = ImageCreateByBackup.builder()
				.name("name")
				.backupId("backupid")
				.description("******")
				.tags(Collections.EMPTY_LIST)
				.enterpriseProjectId("******")
				.maxRam(Integer.valueOf(1))
				.minRam(Integer.valueOf(1))
				.build();
		String jobId = osv3().ims().images().create(build);
		assertNotNull(jobId);
	}
	
	@Test
	public void createByOBSTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		List<String> tags = new ArrayList<String>();
		tags.add("aaa.111");
		tags.add("bbb.222");
		int minDisk = 40;
		ImageCreateByOBS imageCreateByOBS = ImageCreateByOBS.builder()
				.name("name")
				.imageUrl("imageUrl")
				.minDisk(minDisk)
				.description("description")
				.cmkId("******")
				.imageTags(Collections.EMPTY_LIST)
				.enterpriseProjectId("******")
				.osType("osType")
				.tags(tags)
				.build();
		String jobId = osv3().ims().images().create(imageCreateByOBS);
		assertNotNull(jobId);
	}
	
	@Test
	public void registTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		RegistImage registImage = RegistImage.builder()
				.imageUrl("imageUrl")
				.build();
			String registImageId = "******";
		String jobId = osv3().ims().images().regist(registImage, registImageId);
		assertNotNull(jobId);
	}
	
	@Test
	public void exportTest(){
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		ExportImage uploadImage = ExportImage.builder()
				.fileFormat("fileFormat")
				.bucketUrl("bucketUrl")
				.build();
			String imageId = "******";
		String jobId = osv3().ims().images().export(uploadImage, imageId);
		assertNotNull(jobId);
	}
	
	
	
	@Override
	protected Service service() {
		return Service.IMS;
	}

}
