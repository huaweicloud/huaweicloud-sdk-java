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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:13:25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupPolicy implements VolumeBackupPolicy {

	private static final long serialVersionUID = -1027176809768928487L;

	@JsonProperty("backup_policy_id")
	String id;

	@JsonProperty("backup_policy_name")
	String name;

	@JsonProperty("scheduled_policy")
	VBSVolumeBackupScheduledPolicy scheduledPolicy;
	
	public static class VolumeBackupPolicies extends ListResult<VBSVolumeBackupPolicy> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("backup_policies")
		private List<VBSVolumeBackupPolicy> policies;

		@Override
		protected List<VBSVolumeBackupPolicy> value() {
			return policies;
		}
	}

}
