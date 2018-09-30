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
package com.huawei.openstack4j.openstack.scaling.domain;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonRootName("lifecycle_hooks")
public class ASAutoScalingLifecycleHook implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7635315613194755810L;
/**
 * { 
    "lifecycle_hook_name": "test-hook1", 
    "default_result": "ABANDON", 
    "default_timeout": 3600, 
    "notification_topic_urn": "urn:smn:regionId:b53e5554fad0494d96206fb84296510b:gsh", 
    "notification_topic_name": "gsh", 
    "lifecycle_hook_type": "INSTANCE_LAUNCHING", 
    "notification_metadata": null 
}
 */
	@JsonProperty("lifecycle_hook_name")
	private String lifecycleHookName;
	
	@JsonProperty("lifecycle_hook_type")
	private ASAutoScalingLifecycleHookType lifecycleHookType;
	
	@JsonProperty("default_result")
	private ASAutoScalingDefaultResult defaultResult;
	
	@JsonProperty("default_timeout")
	private String defaultTimeout;
	
	@JsonProperty("notification_topic_urn")
	private String notificationTopicUrn;
	
	@JsonProperty("notification_topic_name")
	private String notificationTopicName;
	
	@JsonProperty("notification_metadata")
	private String notificationMetadata;
	
	@JsonProperty("create_time")
	private String createTime;
	
	
	
	public static class ASAutoScalingLifecycleHooks extends ListResult<ASAutoScalingLifecycleHook> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4709051567801733980L;
		
		@JsonProperty("lifecycle_hooks")
		private List<ASAutoScalingLifecycleHook> hooks;

		@Override
		protected List<ASAutoScalingLifecycleHook> value() {
			return hooks;
		}
	}
}
