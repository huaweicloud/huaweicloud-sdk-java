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
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("restore")
public class RestoreResp implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440669213291050580L;
	/**
	 *恢复id 
	 */
	private String id;
	/**
	 *备份记录id 
	 */
	@JsonProperty("checkpoint_id")
	private String checkpointId;
	/**
	 *恢复参数 
	 */
	private RestoreParam parameters;
	/**
	 *项目ID 
	 */
	@JsonProperty("project_id")
	private String projectId;
	/**
	 *备份提供商id 
	 */
	@JsonProperty("provider_id")
	private String providerId;
	/**
	 *资源恢复失败原因 
	 */
	@JsonProperty("resources_reason")
	private Object resourcesReason;
	/**
	 *资源状态 
	 */
	@JsonProperty("resources_status")
	private Object resourcesStatus;
	/**
	 *恢复目标 
	 */
	@JsonProperty("restore_target")
	private String restoreTarget;
	/**
	 *状态 
	 */
	private String status;
}
