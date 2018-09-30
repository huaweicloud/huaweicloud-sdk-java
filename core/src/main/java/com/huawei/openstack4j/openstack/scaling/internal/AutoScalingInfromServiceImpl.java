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
package com.huawei.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.scaling.AutoScalingInformService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform.ASAutoScalingTopics;

public class AutoScalingInfromServiceImpl extends BaseAutoScalingServices implements AutoScalingInformService {

	@Override
	public ASAutoScalingInform deploy(String groupId ,ASAutoScalingInform info) {
		checkArgument(!Strings.isNullOrEmpty(groupId),"parameter `groupId` should not be empty");
		return put(ASAutoScalingInform.class, uri("/scaling_notification/%s",groupId)).entity(info).execute();
	}

	@Override
	public ASAutoScalingTopics list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId),"parameter `groupId` should not be empty");
		return  get(ASAutoScalingTopics.class, uri("/scaling_notification/%s",groupId)).execute();
	}

	@Override
	public ActionResponse delete(String groupId, String topicUrn) {
		checkArgument(!Strings.isNullOrEmpty(groupId),"parameter `groupId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(topicUrn),"parameter `topicUrn` should not be empty");
		return  ToActionResponseFunction.INSTANCE
					.apply(delete(Void.class, uri("/scaling_notification/%s", groupId+"/"+topicUrn)).executeWithResponse());
	}

}
