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
public class OperationDefinition implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3468524840433876154L;

	/**
	 * 单个备份对象自动备份的最大备份数
	 */
	@JsonProperty("max_backups")
	private Integer maxBackups;
	
	/**
	 * 备份保留时长，单位天
	 */
	@JsonProperty("retention_duration_days")
	private Integer retentionDurationDays;
	
	/**
	 * 是否永久保留
	 */
	private Boolean permanent;
	
	/**
	 * 备份策略id
	 */
	@JsonProperty("plan_id")
	private String planId;
	
	/**
	 * 备份提供商id，当前取值固定值：fc4d5750-22e7-4798-8a46-f48f62c4c1da。
	 */
	@JsonProperty("provider_id")
	private String providerId;
	
	/**
	 * 复制的目标区域，仅在跨区域复制时才会使用并且必须指定。
	 */
	@JsonProperty("destination_region")
	private String destinationRegion;
	
	/**
	 * 复制的目标项目id，仅在跨区域复制时才会使用并且必须指定。
	 */
	@JsonProperty("destination_project_id")
	private String destinationProjectId;
	
	/**
	 * 跨区域复制时，是否启用加速从而缩减复制的时间，如果不指定，默认不启用加速
	 */
	@JsonProperty("enable_acceleration")
	private Boolean enableAcceleration;
	
}
