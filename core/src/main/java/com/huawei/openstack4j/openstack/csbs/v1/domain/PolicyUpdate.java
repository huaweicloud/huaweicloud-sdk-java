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
package com.huawei.openstack4j.openstack.csbs.v1.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("policy")
public class PolicyUpdate implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2862115882915516523L;

	/**
	 * 备份策略描述
	 */
	private String description;
	
	/**
	 * 备份策略名称
	 */
	private String name;
	
	/**
	 * 备份参数
	 */
	private PolicyParam parameters;
	
	/**
	 * 备份提供商id
	 */
	@JsonProperty("provider_id")
	private String providerId;
	
	/**
	 * 备份对象列表
	 */
	private List<Resource> resources;
	
	/**
	 * 调度周期
	 */
	@JsonProperty("scheduled_operations")
	private List<ScheduledOperationUpdate> scheduledOperations;
	
}
