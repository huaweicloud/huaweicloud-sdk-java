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
package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-30 10:13:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupPolicyBackupTask implements VolumeBackupPolicyBackupTask {

	private static final long serialVersionUID = 948876697834568491L;
	
	@JsonProperty("job_id")
	String id;
	
	@JsonProperty("backup_name")
	String backupName;
	
	@JsonProperty("resource_id")
	String resourceId;
	
	@JsonProperty("resource_type")
	String resourceType;
	
	@JsonProperty("status")
	BackupTaskStatus status;
	
	@JsonProperty("created_at")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS_SSS)
	Date createdAt;
	
	@JsonProperty("finished_at")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS_SSS)
	Date finishedAt;
	
	public static class VolumeBackupPolicyBackupTasks extends ListResult<VBSVolumeBackupPolicyBackupTask> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("tasks")
		private List<VBSVolumeBackupPolicyBackupTask> tasks;

		@Override
		protected List<VBSVolumeBackupPolicyBackupTask> value() {
			return tasks;
		}
	}

}
