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
package com.huawei.openstack4j.openstack.ecs.v1_1.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerHints {
	
	/**
	 * 云服务器组ID，UUID格式。
	 */
	@JsonProperty("group")
	private String group;
	
	/**
	 * 专属主机的ID。
	 */
	@JsonProperty("dedicated_host_id")
	private String dedicatedHostId;
	
	/**
	 * 在指定的专属主机或者共享主机上创建弹性云服务器。
	 */
	@JsonProperty("tenancy")
	private List<String> tenancy;
	
}
