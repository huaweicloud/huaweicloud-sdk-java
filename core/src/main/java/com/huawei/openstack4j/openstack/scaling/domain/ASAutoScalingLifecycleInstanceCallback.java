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


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingLifecycleInstanceCallback implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7635315613194755810L;
/**
 * { 
    "lifecycle_action_result": "ABANDON", 
    "lifecycle_action_key":"23880867-6288-4470-98a8-f8bda096b6c4" 
}

 */
	@JsonProperty("lifecycle_action_key")
	private String lifecycleActionKey;
	
	@JsonProperty("instance_id")
	private String instanceId;
	
	@JsonProperty("lifecycle_hook_name")
	private String lifecycleHookName;
	
	@JsonProperty("lifecycle_action_result")
	private ASAutoScalingLifecycleActionResult lifecycleActionResult;
	
}
