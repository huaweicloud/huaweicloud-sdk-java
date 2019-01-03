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
public class VolumeBackup implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6218624660584730143L;
	/**
	 *平均速率
	 */
	@JsonProperty("average_speed")
	private Integer averageSpeed;
	/**
	 *是否系统盘 
	 */
	@JsonProperty("bootable")
	private Boolean bootable;
	/**
	 *cinder backup id 
	 */
	@JsonProperty("id")
	private String id;
	/**
	 *备份集类型，备份 
	 */
	@JsonProperty("image_type")
	private String imageType;
	/**
	 *是否增备 
	 */
	@JsonProperty("incremental")
	private Boolean incremental;
	/**
	 *卷备份名称 
	 */
	@JsonProperty("name")
	private String name;
	/**
	 *累计备份大小，单位是MB 
	 */
	@JsonProperty("size")
	private Integer size;
	/**
	 *源卷id 
	 */
	@JsonProperty("source_volume_id")
	private String sourceVolumeId;
	/**
	 *源卷大小（GB） 
	 */
	@JsonProperty("source_volume_size")
	private Integer sourceVolumeSize;
	/**
	 *空间节省率 
	 */
	@JsonProperty("space_saving_ratio")
	private Integer spaceSavingRatio;
	/**
	 *状态 
	 */
	@JsonProperty("status")
	private String status;
	/**
	 *源卷名称 
	 */
	@JsonProperty("source_volume_name")
	private String sourceVolumeName;
	/**
	 *生成备份的快照id 
	 */
	@JsonProperty("snapshot_id")
	private String snapshotId;
	/**
	 * 源卷类型，当前支持EVS、DSS
	 */
	@JsonProperty("source_volume_service_type")
	private String sourceVolumeType;
	/**
	 * 累计专属备份大小，单位是MB
	 */
	@JsonProperty("dec_size")
	private String decSize;
	
}
