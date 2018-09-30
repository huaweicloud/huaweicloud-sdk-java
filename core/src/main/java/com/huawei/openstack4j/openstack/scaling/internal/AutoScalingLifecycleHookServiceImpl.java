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

import java.util.List;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.scaling.AutoScalingLifecycleHookService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleHook;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleHook.ASAutoScalingLifecycleHooks;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleInstanceCallback;
import com.huawei.openstack4j.openstack.scaling.domain.AutoScalingInstanceHangupInfo;
import com.huawei.openstack4j.openstack.scaling.domain.AutoScalingInstanceHangupInfo.AutoScalingInstanceHangupInfos;
import com.huawei.openstack4j.openstack.scaling.options.ScalingInstanceOptions;

public class AutoScalingLifecycleHookServiceImpl extends BaseAutoScalingServices implements AutoScalingLifecycleHookService{
	
	
	@Override
	public ASAutoScalingLifecycleHook create( ASAutoScalingLifecycleHook lifecycleHook,String groupId) {
		checkArgument(!Strings.isNullOrEmpty(lifecycleHook.getLifecycleHookName()), "parameter `lifecycleHookName` should not be empty");
		checkArgument(!(lifecycleHook.getLifecycleHookType()==null), "parameter `lifecycleHookType` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(lifecycleHook.getNotificationTopicUrn()), "parameter `notificationTopicUrn` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		return post(ASAutoScalingLifecycleHook.class, uri("/scaling_lifecycle_hook/%s", groupId)).entity(lifecycleHook).execute();

	}

	@Override
	public List<? extends ASAutoScalingLifecycleHook> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		return get(ASAutoScalingLifecycleHooks.class, uri("/scaling_lifecycle_hook/%s", groupId+"/list")).execute().getList();
	}

	@Override
	public ASAutoScalingLifecycleHook list(String groupId,String lifecycleHookName) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(lifecycleHookName), "parameter `lifecycleHookName` should not be empty");
		return get(ASAutoScalingLifecycleHook.class, uri("/scaling_lifecycle_hook/%s", groupId+"/"+lifecycleHookName)).execute();
	}

	@Override
	public ASAutoScalingLifecycleHook update(String groupId,String lifecycleHookName,ASAutoScalingLifecycleHook lifecycleHook) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(lifecycleHookName), "parameter `lifecycleHookName` should not be empty");
		checkArgument(Strings.isNullOrEmpty(lifecycleHook.getLifecycleHookName()), "parameter `lifecycleHookName` should not be modify");
		checkArgument(Strings.isNullOrEmpty(lifecycleHook.getNotificationTopicName()), "parameter `notificationTopicName` should not be modify");
		return put(ASAutoScalingLifecycleHook.class, uri("/scaling_lifecycle_hook/%s", groupId+"/"+lifecycleHookName)).entity(lifecycleHook).execute();
	}

	@Override
	public List<? extends AutoScalingInstanceHangupInfo> scalingInstanceHangup(String groupId, ScalingInstanceOptions instanceId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		return get(AutoScalingInstanceHangupInfos.class, uri("/scaling_instance_hook/%s", groupId +"/list")).params(instanceId.getOptions()).execute().getList();
	}

	@Override
	public List<? extends AutoScalingInstanceHangupInfo>  scalingInstanceHangup(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		return get(AutoScalingInstanceHangupInfos.class, uri("/scaling_instance_hook/%s", groupId +"/list")).execute().getList();
	}

	@Override
	public ActionResponse delete(String groupId, String lifecycleHookName) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(lifecycleHookName), "parameter `lifecycleHookName` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri("/scaling_lifecycle_hook/%s", groupId+"/"+lifecycleHookName)).executeWithResponse());
	}

	@Override
	public ActionResponse scalingInstanceHookCallback(String groupId,ASAutoScalingLifecycleInstanceCallback lifecycleInstanceCallback) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "parameter `groupId` should not be empty");
		if(Strings.isNullOrEmpty(lifecycleInstanceCallback.getLifecycleActionKey())){
			checkArgument(!Strings.isNullOrEmpty(lifecycleInstanceCallback.getInstanceId()), "parameter `instanceId` should not be empty");
			checkArgument(!Strings.isNullOrEmpty(lifecycleInstanceCallback.getLifecycleHookName()), "parameter `lifecycleHookName` should not be empty");
		}
		return ToActionResponseFunction.INSTANCE
				.apply(put(Void.class, uri("/scaling_instance_hook/%s", groupId+"/callback")).entity(lifecycleInstanceCallback).executeWithResponse());
	}
	
	
	
	

	
}
