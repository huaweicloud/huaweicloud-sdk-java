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
package com.huawei.openstack4j.api.scaling;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceTag.ASAutoScalingResourceTags;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingTag.ASAutoScalingTags;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingTagActionType;

@Test
public class AutoScalingTagServiceV1Tests extends AbstractTest {
	
	private static final String JSON_SCALING_RESOURCE_TYPE_TAG_LIST = "/scaling/as_scaling_resource_type_tag_list.json";
	private static final String JSON_SCALING_RESOURCE_TAG_LIST = "/scaling/as_scaling_resource_tag_list.json";
	
	public void  testAutoScalingTagServiceGet() throws IOException{		
		respondWith(JSON_SCALING_RESOURCE_TYPE_TAG_LIST);
		ASAutoScalingTags asAutoScalingTags = osv3().autoScaling().tags().get(ASAutoScalingResourceType.scaling_group_tag);
		assertTrue(asAutoScalingTags.getList().size()==5);
		
	
	}
	
	public void testATSGetResourceTag() throws IOException{
		respondWith(JSON_SCALING_RESOURCE_TAG_LIST);
		String resource_id = "89431";
		ASAutoScalingResourceTags resourceTag = osv3().autoScaling().tags().get(ASAutoScalingResourceType.scaling_group_tag, resource_id);
		assertTrue(resourceTag.getList().size()==3);
	}
	
	public void testATSUpdateOrDelete() throws IOException{
		respondWith(200);
		String resource_id = "89431";
		ASAutoScalingResourceTags tags = ASAutoScalingResourceTags.builder().action(ASAutoScalingTagActionType.delete).build();
		ActionResponse res = osv3().autoScaling().tags().updateOrDelete(ASAutoScalingResourceType.scaling_group_tag, resource_id, tags);
		assertTrue(res.getCode()==200);
	}

	@Override
	protected Service service() {		
		return Service.AUTO_SCALING;
	}
}
