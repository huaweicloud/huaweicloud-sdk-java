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
package com.huawei.openstack4j.openstack.ims.v2.domain;

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
public class Image implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3663422920131120608L;
	
	/**
	 * 镜像文件下载和上传链接。
	 */
	private String 	file;
	/**
	 * 镜像属于哪个租户。
	 */
	private String 	owner;
	/**
	 * 镜像ID。
	 */
	private String 	id;
	/**
	 *目前暂时不使用。
	 */
	private String 	size;
	/**
	 *镜像链接信息。
	 */
	private String 	self;
	/**
	 * 镜像视图。
	 */
	private String 	schema;
	/**
	 * 镜像状态。取值如下：
	 *
	 *	queued：表示镜像元数据已经创建成功，等待上传镜像文件。
	 *	saving：表示镜像正在上传文件到后端存储。
	 *	deleted：表示镜像已经删除。
	 *	killed：表示镜像上传错误。
	 *	active：表示镜像可以正常使用。
	 */
	private String 	status;
	/**
	 * 镜像标签列表，提供用户可以自定义管理私有镜像的能力。
	 * 用户可以通过镜像标签接口为每个镜像增加不同的标签，在查询接口中可以根据标签进行过滤。
	 */
	private List<String> tags;
	/**
	 * 是否被其他租户可见，取值为private或public。
	 */
	private String 	visibility;
	/**
	 *镜像名称
	 */
	private String 	name;
	/**
	 *目前暂时不使用。
	 */
	private String 	checksum;
	/**
	 * 是否是删除的镜像
	 */
	private Boolean deleted;
	/**
	 *是否是受保护的，受保护的镜像不允许删除。取值为true或false
	 */
	@JsonProperty("protected")
	private Boolean protec;
	/**
	 * 容器类型。
	 */
	@JsonProperty("container_format")
	private String 	containerFormat;
	/**
	 *镜像运行最小内存，单位为MB。取值参考弹性云服务器规格限制，一般设置为0
	 */
	@JsonProperty("min_ram")
	private Integer minRam;
	/**
	 *更新时间。格式为UTC时间
	 */
	@JsonProperty("updated_at")
	private String 	updatedAt;
	/**
	 *操作系统位数，一般取值为“32”或者“64”。
	 */
	@JsonProperty("__os_bit")
	private String 	osBit;
	/**
	 *操作系统具体版本。
	 */
	@JsonProperty("__os_version")
	private String 	osVersion;
	/**
	 *镜像描述信息
	 */
	@JsonProperty("__description")
	private String 	description;
	/**
	 *镜像的格式，目前支持vhd，zvhd、raw，qcow2。默认值是vhd。
	 */
	@JsonProperty("disk_format")
	private String 	diskFormat;
	/**
	 * 是否是注册过的镜像，取值为“true”或者“false”。
	 */
	@JsonProperty("__isregistered")
	private String 	isRegistered;
	/**
	 *镜像平台分类，
	 *取值为Windows，Ubuntu，RedHat，SUSE，CentOS，Debian，
	 *OpenSUSE, Oracle Linux，Fedora，Other,CoreOS和EulerOS。
	 */
	@JsonProperty("__platform")
	private String 	platForm;
	/**
	 *操作系统类型，目前取值Linux， Windows，Other。
	 */
	@JsonProperty("__os_type")
	private String 	osType;
	/**
	 *镜像运行需要的最小磁盘，单位为GB 。取值为40～1024GB。
	 */
	@JsonProperty("min_disk")
	private Integer minDisk;
	/**
	 *镜像使用环境类型
	 */
	@JsonProperty("virtual_env_type")
	private String 	virtualEnvType;
	/**
	 *镜像后端存储类型，目前支持uds
	 */
	@JsonProperty("__image_source_type")
	private String 	imageSourceType;
	/**
	 *镜像类型，目前支持以下类型：
	 *
	 *	公共镜像：gold
	 *	私有镜像：private
	 *	共享镜像：shared
	 */
	@JsonProperty("__imagetype")
	private String 	imageType;
	/**
	 *创建时间。格式为UTC时间。
	 */
	@JsonProperty("created_at")
	private String 	createdAt;
	/**
	 *目前暂时不使用。
	 */
	@JsonProperty("virtual_size")
	private Integer virtualSize;
	/**
	 *删除时间。格式为UTC时间。
	 */
	@JsonProperty("deleted_at")
	private String 	deletedAt;
	/**
	 *父镜像ID
	 *公共镜像或通过文件创建的私有镜像，取值为空。
	 */
	@JsonProperty("__originalimagename")
	private String 	originalImageName;
	/**
	 *备份ID。如果是备份创建的镜像，则填写为备份的ID，否则为空。
	 */
	@JsonProperty("__backup_id")
	private String 	backupId;
	/**
	 *市场镜像的产品ID。
	 */
	@JsonProperty("__productcode")
	private String 	productCode;
	/**
	 *镜像文件的大小，单位为字节。
	 */
	@JsonProperty("__image_size")
	private String 	imageSize;
	/**
	 *镜像来源。公共镜像为空。
	 */
	@JsonProperty("__data_origin")
	private String 	dataOrigin;
	/**
	 *如果镜像支持KVM，取值为true，否则无需增加该属性。
	 */
	@JsonProperty("__support_kvm")
	private String 	supportKvm;
	/**
	 *如果镜像支持XEN，取值为true，否则无需增加该属性。
	 */
	@JsonProperty("__support_xen")
	private String 	supportXen;
	/**
	 *表示该镜像支持密集存储。如果镜像支持密集存储性能，则值为true，否则无需增加该属性。
	 */
	@JsonProperty("__support_diskintensive")
	private String 	supportDiskintensive;
	/**
	 *表示该镜像支持高计算性能。如果镜像支持高计算性能，则值为true，否则无需增加该属性。
	 */
	@JsonProperty("__support_highperformance")
	private String 	supportHighperformance;
	/**
	 *表示该镜像是支持XEN虚拟化平台下的GPU优化类型
	 */
	@JsonProperty("__support_xen_gpu_type")
	private String 	supportXenGpuType;
	/**
	 *表示当前镜像是否支持发布为市场镜像。
	 *
	 *	true表示支持
	 *	false 表示不支持
	 */
	@JsonProperty("__system_support_market")
	private Boolean systemSupportMarket;
	/**
	 * 表示当前镜像所属的企业项目
	 */
	@JsonProperty("enterprise_project_id")
	private String 	enterpriseProjectId;
	/**
	 * 表示当前镜像来源是从外部导入。取值样例：file。
	 */
	@JsonProperty("__root_origin")
	private String 	rootOrigin;
	/**
	 * 表示当前镜像对应云服务器的系统盘插槽位置。
	 */
	@JsonProperty("__sequence_num")
	private String 	sequenceNum;
	
	/**
	 * 表示当前镜像对应云服务器的系统盘插槽位置。
	 */
	@JsonProperty("max_ram")
	private String 	maxRam;
	
	public  static class Images extends ListResult<Image>{
		/**
		 * 
		 */
		private static final long serialVersionUID = 8442936931557447760L;
		@JsonProperty("images")
		private List<Image> images;
		@Override
		protected List<Image> value() {
			return images;
		}	
	}
}
