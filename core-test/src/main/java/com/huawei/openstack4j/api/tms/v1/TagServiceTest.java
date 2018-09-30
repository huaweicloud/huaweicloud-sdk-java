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
package com.huawei.openstack4j.api.tms.v1;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
	
import com.huawei.openstack4j.openstack.tms.v1.contants.Action;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTagRequest;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTags;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTagsAction;
import com.huawei.openstack4j.openstack.tms.v1.internal.TagFilterOption;
@Test(suiteName = "TagManagementService")
public class TagServiceTest  extends AbstractTest {
	
	private static final String JSON_TAGS = "/tms/v1/tags.json";
	
	@Override
	protected Service service() {
		return Service.TAG_MANAGEMENT;
	}
	@Test
	public void getTagList() throws Exception{
		respondWith(JSON_TAGS);
		PredefineTags preTags = osv3().tms().tags().list();
		assertNotNull(preTags);
	}
	@Test
	public void getTagListWithOption() throws IOException{
		respondWith(JSON_TAGS);
		PredefineTags preTags = osv3().tms().tags().list(TagFilterOption.create().key("123").limit(2));
		assertNotNull(preTags);
	}
	@Test
	public void createTagTest(){
//		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
//		PredefineTagRequest re1 = PredefineTagRequest.builder().key("test_wxc_2018041107").value("test_wxc_2018041107").build();
//		List<PredefineTagRequest> tagList1 = new ArrayList<PredefineTagRequest>();
//		tagList1.add(re1);
//		PredefineTagsAction tagsAtion = PredefineTagsAction.builder().action(Action.Create).tagList(tagList1).build();
//		String result = osv3().tms().tags().create(tagsAtion);
//		assertNotNull(result);
	}
	
	
	
	
}