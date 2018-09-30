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

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleHook;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleInstanceCallback;
import com.huawei.openstack4j.openstack.scaling.domain.AutoScalingInstanceHangupInfo;
import com.huawei.openstack4j.openstack.scaling.options.ScalingInstanceOptions;

public interface AutoScalingLifecycleHookService extends RestService {
	
	/**
	 * 创建生命周期挂钩，可为伸缩组添加一个或多个生命周期挂钩，最多添加5个。
	 * @param lifecycleHook
	 * @return
	 */
	ASAutoScalingLifecycleHook create(ASAutoScalingLifecycleHook lifecycleHook , String groupId);
	
	/**
	 * 查询生命周期挂钩列表。
	 * @param groupId
	 * @return
	 */
	List<? extends ASAutoScalingLifecycleHook> list(String groupId);
	
	/**
	 * 查询一个指定生命周期挂钩详情。
	 * @param groupId
	 * @param lifecycleHookName
	 * @return
	 */
	ASAutoScalingLifecycleHook list(String groupId , String lifecycleHookName);
	
	/**
	 * 删除一个指定生命周期挂钩。
	 * @param groupId
	 * @param lifecycleHookName
	 * @return
	 */
	ActionResponse delete(String groupId , String lifecycleHookName);
	
	/**
	 * 修改一个指定生命周期挂钩中的信息。
	 * @param groupId
	 * @param lifecycleHookName
	 * @return
	 */
	ASAutoScalingLifecycleHook update(String groupId , String lifecycleHookName , ASAutoScalingLifecycleHook lifecycleHook);
	
	/**
	 * 添加生命周期挂钩后，当伸缩组进行伸缩活动时，实例将被挂钩挂起并置于等待状态，根据输入条件过滤查询弹性伸缩组中伸缩实例的挂起信息。
	 * @param groupId
	 * @param instanceId
	 * @return
	 */
	List<? extends AutoScalingInstanceHangupInfo>  scalingInstanceHangup(String groupId , ScalingInstanceOptions instanceId);
	
	/**
	 * 添加生命周期挂钩后，当伸缩组进行伸缩活动时，实例将被挂钩挂起并置于等待状态，根据输入条件过滤查询弹性伸缩组中伸缩实例的挂起信息。
	 * @param groupId
	 * @param instanceId
	 * @return
	 */
	List<? extends AutoScalingInstanceHangupInfo>  scalingInstanceHangup(String groupId);
	
	/**
	 * 通过生命周期操作令牌或者通过实例ID和生命周期挂钩名称对伸缩实例指定的挂钩进行回调操作。
	 * @param groupId
	 * @param lifecycleInstanceCallback
	 * @return
	 */
	ActionResponse scalingInstanceHookCallback(String groupId , ASAutoScalingLifecycleInstanceCallback lifecycleInstanceCallback);
	
	
}
