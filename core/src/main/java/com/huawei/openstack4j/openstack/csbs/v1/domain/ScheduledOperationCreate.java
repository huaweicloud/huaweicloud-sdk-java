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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledOperationCreate implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -417210760255734494L;

	/**
	 * 调度周期描述
	 */
	private String description;
	
	/**
	 * 是否启用该调度周期，默认为true
	 */
	private Boolean enabled;
	
	/**
	 * 调度周期名称
	 */
	private String name;
	
	/**
	 * 操作类型，备份，复制
	 */
	@JsonProperty("operation_type")
	private String operationType;
	
	/**
	 * 调度周期参数
	 */
	@JsonProperty("operation_definition")
	private OperationDefinition operationDefinition;
	
	/**
	 * 调度策略
	 */
	private Trigger trigger;
	
}
