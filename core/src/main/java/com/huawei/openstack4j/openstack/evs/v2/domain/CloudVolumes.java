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
package com.huawei.openstack4j.openstack.evs.v2.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;

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
@JsonRootName("volume")
public class CloudVolumes implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3974566408735909574L;

	/**
	 * {
		    "volume": {
		        "backup_id": null, 
		        "count": 1, 
		        "availability_zone": "az1.dc1", 
		        "description": "test_volume_1", 
		        "size": 120, 
		        "name": "test_volume_1", 
		        "imageRef": null, 
		        "volume_type": "SSD", 
		        "metadata": {
		            "__system__encrypted": "0", 
		            "__system__cmkid": null
		        }
		    }
		}
	 */
	/**
	 * 备份ID，从备份创建云硬盘时为必选。
	 */
	@JsonProperty("backup_id")
	private String backupId;
	
	/**
	 * 指定要创建云硬盘的AZ。
		若指定的AZ不存在或指定的AZ和备份所在的AZ不同，则创云硬盘失败。
	 */
	@JsonProperty("availability_zone")
	private String availabilityZone;
	
	/**
	 * 云硬盘的描述。最大支持255个字节。
	 */
	@JsonProperty("description")
	private String description;
	
	/**
	 * 云硬盘大小，单位为GB，其限制如下：
		系统盘：1GB-1024GB
		数据盘：10GB-32768GB
		创建空白云硬盘和从 镜像/快照 创建云硬盘时，size为必选，且云硬盘大小不能小于 镜像/快照 大小。
		从备份创建云硬盘时，size为可选，不指定size时，云硬盘大小和备份大小一致。
		说明：
		如果发送请求时，将参数值设置为小数，则默认取小数点前的整数。
	 */
	@JsonProperty("size")
	private Integer size;
	
	/**
	 * 云硬盘名称。
		如果为批量创建云磁盘，name为云硬盘名称前缀，不同的云磁盘以“-”开头，以四位数字编号，如“shan-0001”。最大支持250个字节。
		如果为创建单个云硬盘，name为云硬盘名称。最大支持255个字节。
	 */
	@JsonProperty("name")
	private String name;
	
	/**
	 * 快照ID，指定该参数表示创建云硬盘方式为从快照创建云硬盘。
	 */
	@JsonProperty("snapshot_id")
	private String snapshotId;
	
	/**
	 * 	
		IMS中镜像ID，指定该参数表示创建云硬盘方式为从镜像创建云硬盘。
		说明：
		不支持通过BMS的镜像创建BMS系统盘
	 */
	@JsonProperty("imageRef")
	private String imageRef;
	
	/**
	 * 	
		云硬盘类型。
		目前支持“SSD”，“SAS”和“SATA”三种。
		当指定的云硬盘类型在avaliability_zone内不存在时，则创建云硬盘失败。
		说明：
		从快照创建云硬盘时，volume_type字段必须和快照源云硬盘保持一致。
	 */
	@JsonProperty("volume_type")
	private VolumeType volumeType;
	
	/**
	 * 	
		批量创云硬盘的个数。如果无该参数，表明只创建1个云硬盘，目前最多支持批量创建100个。
		从备份创建云硬盘时，不支持批量创建，数量只能为“1”。
		说明：
		如果发送请求时，将参数值设置为小数，则默认取小数点前的整数。
	 */
	@JsonProperty("count")
	private Integer count;
	
	/**
	 * 是否为共享云硬盘。true为共享盘，false为普通云硬盘。
		说明：
		该字段已经废弃，请使用multiattach。
	 */
	@JsonProperty("shareable")
	private String shareable;
	
	/**
	 * 	创建云硬盘的metadata信息，metadata中的key和value长度不大于255个字节。
	 */
	@JsonProperty("metadata")
	private Metadata metadata;
	
	/**
	 * 创建共享云硬盘的信息。
	 */
	@JsonProperty("multiattach")
	private Boolean multiattach;
	
	/**
	 * 创建云硬盘的时候，给云硬盘绑定标签。
	 */
	@JsonProperty("tags")
	private Map<String,String> tags;
	
	/**
	 * 企业项目ID。创建云硬盘时，给云硬盘绑定企业项目ID。
	 */
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
}
