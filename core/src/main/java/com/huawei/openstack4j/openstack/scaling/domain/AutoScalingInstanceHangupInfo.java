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
public class AutoScalingInstanceHangupInfo  implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3556907326104568870L;

	/**
	 * { 
	    "instance_hanging_info": [ 
	        { 
	            "instance_id": "b25c1589-c96c-465b-9fef-d06540d1945c", 
	            "scaling_group_id": "e5d27f5c-dd76-4a61-b4bc-a67c5686719a", 
	            "lifecycle_hook_name": "hook-test", 
	            "lifecycle_action_key": "6ebe6e72-4b09-4adb-ae4a-a91dc0560069", 
	            "default_result": "ABANDON", 
	            "timeout": "2016-11-15T06:43:41Z", 
	            "lifecycle_hook_status": "HANGING" 
	        } 
	    ] 
	}*/
	@JsonProperty("instance_id")
	private String instanceId;
	
	@JsonProperty("scaling_group_id")
	private String scalingGroupId;
	
	@JsonProperty("lifecycle_hook_name")
	private String lifecycleHookName;
	
	@JsonProperty("lifecycle_action_key")
	private String lifecycleActionKey;
	
	@JsonProperty("default_result")
	private String defaultResult;
	
	@JsonProperty("timeout")
	private String timeout;
	
	@JsonProperty("lifecycle_hook_status")
	private String lifecycleHookStatus;
	
	
	public static class AutoScalingInstanceHangupInfos extends ListResult<AutoScalingInstanceHangupInfo> {

		private static final long serialVersionUID = -589481269362039859L;
		
		@JsonProperty("instance_hanging_info")
		private List<AutoScalingInstanceHangupInfo> infos;

		@Override
		protected List<AutoScalingInstanceHangupInfo> value() {
			return infos;
		}
	}
	
}
