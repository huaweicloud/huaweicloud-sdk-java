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
@JsonRootName("replication")
public class Replication implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3008060929998121023L;
	/**
	 *备份策略id
	 */
	@JsonProperty("policy_id")
	private String policyId;
	/**
	 *本次复制是否自动触发，默认为false，代表手动触发 
	 */
	@JsonProperty("auto_trigger")
	private Boolean autoTrigger;
	/**
	 *复制的目标区域 
	 */
	@JsonProperty("destination_region")
	private String destinationRegion;
	/**
	 *复制的目标项目id 
	 */
	@JsonProperty("destination_project_id")
	private String destinationProjectId;
	/**
	 *跨区域复制时，是否启用加速从而缩短复制的时间，如果不指定，
	 *默认不启用加速，如果启用加速，会额外收取加速的费用。 
	 */
	@JsonProperty("enable_acceleration")
	private Boolean enableAcceleration;
	/**
	 *执行复制的项目id
	 */
	@JsonProperty("project_id")
	private String projectId;
	/**
	 *备份提供商id 
	 */
	@JsonProperty("provider_id")
	private String providerId;
	/**
	 *复制的源区域 
	 */
	@JsonProperty("source_region")
	private String sourceRegion;
	/**
	 *待复制的备份列表 
	 */
	@JsonProperty("checkpoint_items")
	private List<CheckpointitemResp> checkpointItems;
	/**
	 *复制的名称 
	 */
	@JsonProperty("name")
	private String name;
	/**
	 *复制的描述 
	 */
	@JsonProperty("description")
	private String description;
	
}	
