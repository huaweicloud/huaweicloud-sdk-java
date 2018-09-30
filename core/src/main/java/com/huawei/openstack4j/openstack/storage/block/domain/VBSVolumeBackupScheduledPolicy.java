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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huawei.openstack4j.model.common.serializer.YNBooleanDeserializer;
import com.huawei.openstack4j.model.common.serializer.YNBooleanSerializer;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupPolicyStatus;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupScheduledPolicy;

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
public class VBSVolumeBackupScheduledPolicy implements VolumeBackupScheduledPolicy {

	private static final long serialVersionUID = -5660930519824771L;
	
	@JsonProperty("rentention_num")
	Integer maxBackupAmount;

	Integer frequency;

	@JsonSerialize(using = YNBooleanSerializer.class)
	@JsonDeserialize(using = YNBooleanDeserializer.class)
	@JsonProperty("remain_first_backup_of_curMonth")
	Boolean retainFirstBackupOfCurrentMonth;

	@JsonProperty("start_time")
	String startTime;
	
	VolumeBackupPolicyStatus status;

}
