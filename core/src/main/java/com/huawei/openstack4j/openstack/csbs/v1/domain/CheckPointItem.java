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
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("checkpoint_item")
public class CheckPointItem implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536332474143067502L;

	/**
	 * 备份记录id
	 */
	@JsonProperty("checkpoint_id")
	private String checkpointId;
	
	/**
	 * 创建时间
	 */
	@JsonProperty("created_at")
	private String createdAt;
	
	/**
	 * 扩展信息
	 */
	@JsonProperty("extend_info")
	private ExtendInfo extendInfo;
	
	/**
	 * 备份id
	 */
	@JsonProperty("id")
	private String id;
	
	/**
	 * 备份名称
	 */
	@JsonProperty("name")
	private String name;
	
	/**
	 * 备份对象id
	 */
	@JsonProperty("resource_id")
	private String resourceId;
	
	/**
	 * 备份状态
	 */
	@JsonProperty("status")
	private String status;
	
	/**
	 * 修改时间
	 */
	@JsonProperty("updated_at")
	private String updatedAt;
	
	/**
	 * 虚拟机元数据
	 */
	@JsonProperty("backup_data")
	private BackupData backupData;
	
	/**
	 * 备份描述信息
	 */
	@JsonProperty("description")
	private String description;
	
	/**
	 * 备份对象的类型
	 */
	@JsonProperty("resource_type")
	private String resourceType;
	
	/**
	 * 复制记录列表
	 */
	@JsonProperty("replication_records")
	private ReplicationRecord replicationRecords;
	
	/**
	 * 备份数据时间点
	 */
	@JsonProperty("protected_at")
	private String protectedAt;
	
	public  static class CheckPointItems extends ListResult<CheckPointItem>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7607242585768409486L;
		
		@JsonProperty("checkpoint_items")
		private List<CheckPointItem> checkpointItems;
		
		@Override
		protected List<CheckPointItem> value() {
			return checkpointItems;
		}
		
		
	}
}
