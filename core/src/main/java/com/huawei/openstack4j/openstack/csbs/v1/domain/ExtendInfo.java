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
public class ExtendInfo implements ModelEntity{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -786116132220156407L;

	/**
	 * 是否自动触发
	 */
	@JsonProperty("auto_trigger")
	private Boolean autoTrigger;
	
	/**
	 * 平均速率
	 */
	@JsonProperty("average_speed")
	private Integer averageSpeed;
	
	/**
	 * 默认为空
	 */
	@JsonProperty("copy_from")
	private String copyFrom;
	
	/**
	 * 默认为na
	 */
	@JsonProperty("copy_status")
	private String copyStatus;
	
	/**
	 * 错误码
	 */
	@JsonProperty("fail_code")
	private FailCode failCode;
	
	/**
	 * 失败的操作类型
	 */
	@JsonProperty("fail_op")
	private String failOp;
	
	/**
	 * 失败原因英文描述
	 */
	@JsonProperty("fail_reason")
	private String failReason;
	
	/**
	 * 备份类型
	 */
	@JsonProperty("image_type")
	private String imageType;
	
	/**
	 * 是否增备
	 */
	@JsonProperty("incremental")
	private Boolean incremental;
	
	/**
	 * 进度
	 */
	@JsonProperty("progress")
	private String progress;
	
	/**
	 * 	备份资源所属az
	 */
	@JsonProperty("resource_az")
	private String resourceAz;
	
	/**
	 * 备份对象名称
	 */
	@JsonProperty("resource_name")
	private String resourceName;
	
	/**
	 * 	备份对象的类型
	 */
	@JsonProperty("resource_type")
	private String resourceType;
	
	/**
	 * 备份容量
	 */
	@JsonProperty("size")
	private Integer size;
	
	/**
	 * 空间节省率
	 */
	@JsonProperty("space_saving_ratio")
	private Integer spaceSavingRatio;
	
	/**
	 * 卷备份列表
	 */
	@JsonProperty("volume_backups")
	private List<VolumeBackup> volumeBackups;
	
	/**
	 * 备份完成时间
	 */
	@JsonProperty("finished_at")
	private String finishedAt;
	
	/**
	 * 备份支持恢复的方式
	 */
	@JsonProperty("supported_restore_mode")
	private String supportedRestoreMode;
	
	/**
	 * 	镜像数据
	 */
	@JsonProperty("os_images_data")
	private List<ImageData> osImagesData;
	
	/**
	 * 是否支持lazyloading快速恢复
	 */
	@JsonProperty("support_lld")
	private Boolean supportLld;
	
	/**
	 * 复制保留时长，单位为天，如果取值为-1代表永久保留
	 */
	@JsonProperty("retention_duration")
	private Integer retentionDuration;
	
	/**
	 * 备份副本是否是专属备份
	 */
	@JsonProperty("dec")
	private Boolean dec;
	
	/**
	 * 累计专属备份大小，单位是MB
	 */
	@JsonProperty("dec_size")
	private String decSize;
	
}
