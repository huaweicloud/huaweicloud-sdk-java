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
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInforType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform.ASAutoScalingTopics;

@Test
public class AutoScalingInformServiceV1Test  extends AbstractTest{

	private static final String JSON_SCALING_DYPLOY_INFORM_RES = "/scaling/as_scaling_deploy_inform_res.json";
	private static final String JSON_SCALING_GROUP_INFORM_LIST = "/scaling/as_scaling_group_inform_list.json";
	private static final int TOPIC_SCENE_SIZE = 5;
	private static final int GROUP_INFORM_LIST_SIZE = 2;
	private static final int STATUS_CODE = 200;
	
	public void testDeployInform() throws IOException{
		respondWith(JSON_SCALING_DYPLOY_INFORM_RES);
		String groupId = "testgroupid";
		List<ASAutoScalingInforType> list = new ArrayList<ASAutoScalingInforType>();
		list.add(ASAutoScalingInforType.SCALING_DOWN_FAIL);
		list.add(ASAutoScalingInforType.SCALING_UP);
		ASAutoScalingInform build = ASAutoScalingInform.builder().topic_scene(list).build();
		ASAutoScalingInform res = osv3().autoScaling().inform().deploy(groupId, build);
		
		assertTrue(TOPIC_SCENE_SIZE == res.getTopic_scene().size() );
	}
	
	public void testScalingGroupInformList() throws IOException{
		
		respondWith(JSON_SCALING_GROUP_INFORM_LIST);
		String groupId = "testgroupid";
		ASAutoScalingTopics res = osv3().autoScaling().inform().list(groupId);
		assertTrue(GROUP_INFORM_LIST_SIZE == res.getList().size());
		
	}
	
	public void testDelete(){
		respondWith(STATUS_CODE);
		String groupId = "testgroupid";
		String topicUrn = "7be6929";
		ActionResponse res = osv3().autoScaling().inform().delete(groupId, topicUrn);
		assertTrue(STATUS_CODE == res.getCode());
	
	}
	
	
	@Override
	protected Service service() {		
		return Service.AUTO_SCALING;
	}

}
