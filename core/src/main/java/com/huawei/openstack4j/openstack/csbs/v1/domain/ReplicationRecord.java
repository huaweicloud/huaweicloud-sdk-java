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
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplicationRecord implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -761458888649148349L;
	/**
	 *复制记录ID 
	 */
	private String id;
	/**
	 *复制的源区域 
	 */
	@JsonProperty("source_region")
	private String sourceRegion;
	/**
	 *复制的源项目ID 
	 */
	@JsonProperty("source_project_id")
	private String sourceProjectId;
	/**
	 *复制的源备份记录ID 
	 */
	@JsonProperty("source_checkpoint_id")
	private String sourceCheckpointId;
	/**
	 *复制的源备份ID 
	 */
	@JsonProperty("source_checkpoint_item_id")
	private String sourceCheckpointItemId;
	/**
	 *复制的目标区域 
	 */
	@JsonProperty("destination_region")
	private String destinationRegion;
	/**
	 *复制的目标项目ID 
	 */
	@JsonProperty("destination_project_id")
	private String destinationProjectId;
	/**
	 *复制的目的备份记录ID 
	 */
	@JsonProperty("destination_checkpoint_id")
	private String destinationCheckpointId;
	/**
	 *复制的目的备份ID 
	 */
	@JsonProperty("destination_checkpoint_item_id")
	private String destinationCheckpointItemId;
	/**
	 *复制的状态。包含replicating（复制中）、success（成功）、fail（失败）、skip（跳过）、
	 *waiting_replicate(排队中)。 
	 */
	private String status;
	/**
	 *产生复制的策略ID 
	 */
	@JsonProperty("plan_id")
	private String planId;
	/**
	 *复制开始的时间 
	 */
	@JsonProperty("created_at")
	private String createdAt;
	/**
	 *复制的附加信息 
	 */
	@JsonProperty("extra_info")
	private ReplicationExtraInfo extraInfo;
	
	public  static class ReplicationRecords extends ListResult<ReplicationRecord>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7607242585768409486L;
		
		@JsonProperty("replication_records")
		private List<ReplicationRecord> replicationRecords;
		
		public List<ReplicationRecord> getReplicationRecords() {
			return replicationRecords;
		}
		
		public int getCount() {
			return count;
		}
		
		private Integer count;
		@Override
		protected List<ReplicationRecord> value() {
			return replicationRecords;
		}
		
		
	}
}
