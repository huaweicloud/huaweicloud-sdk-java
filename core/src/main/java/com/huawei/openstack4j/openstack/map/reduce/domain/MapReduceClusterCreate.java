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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapReduceClusterCreate implements ModelEntity {
	private static final long serialVersionUID = -4868885128978653274L;

	/**
	 * 集群版本号MRS + 版本号的形式例如 : MRS 1.7.2  、MRS 1.7.1 
	 * 
	 */
	@JsonProperty("cluster_version")
	String version;

	@JsonProperty("cluster_name")

	String name; // 集群名称

	String clusterId;

	@JsonProperty("master_node_num")

	int masterNodeNum; // 主节点数量

	@JsonProperty("core_node_num")

	int coreNodeNum; // 核心节点数量

	@JsonProperty("billing_type")

	Integer billingType; // 付费方式；

	String strBillingType; // 付费方式;用于校验是否为整型

	@JsonProperty("data_center")

	String dataCenter; // 数据中心

	@JsonProperty("vpc")
	String vpc; // 虚拟私有网

	@JsonProperty("master_node_size")

	String masterNodeSize;

	@JsonProperty("core_node_size")

	// CoreNodeSize不能为空！
	String coreNodeSize;

	@JsonProperty("component_list")

	List<MapReduceComponent> components; // 组件列表

	@JsonProperty("master_node_size_id")
	String masterNodeSizeId;

	@JsonProperty("core_node_size_id")
	String coreNodeSizeId;

	@JsonProperty("available_zone_id")

	String availablilityZoneId;

	@JsonProperty("master_node_spec_id")
	String masterNodeSpecId;

	@JsonProperty("core_node_spec_id")
	String coreNodeSpecId;

	@JsonProperty("vpc_id")
	String vpcId; // 子网所在VPC ID

	@JsonProperty("subnet_id")
	String subnetId; // 子网ID

	@JsonProperty("subnet_name")
	String subnetName; // 子网名称

	@JsonProperty("security_groups_id")
	String securityGroupsId; // 安全组ID

	@JsonProperty("available_zone_name")
	// 可用区域名称不能为空！
	String availableZoneName; // 可用区域名称

	@JsonProperty("available_zone_code")
	// 可用区域代码不能为空！
	String availableZoneCode; // 可用区域代码

	@JsonProperty("add_jobs")

	List<MapReduceJobExeCreate> jobs;

	@JsonProperty("volume_size")
	int volumeSize;

	@JsonProperty("volume_type")
	String volumeType;

	String masterRootVolumeType;

	int masterRootVolumeSize;

	String masterRootVolumeProductId;

	@JsonProperty("master_data_volume_type")
	String masterDataVolumeType;

	@JsonProperty("master_data_volume_size")
	int masterDataVolumeSize;

	@JsonProperty("master_data_volume_count")
	int masterDataVolumeCount;

	String masterDataVolumeProductId;

	String coreRootVolumeType;

	int coreRootVolumeSize;

	String coreRootVolumeProductId;

	String masterVmProductId;

	String coreVmProductId;

	@JsonProperty("core_data_volume_type")
	String coreDataVolumeType;

	@JsonProperty("core_data_volume_size")
	int coreDataVolumeSize;

	@JsonProperty("core_data_volume_count")
	int coreDataVolumeCount;

	String coreDataVolumeProductId;

	@JsonProperty("task_node_groups")

	List<TaskNodeGroup> taskNodeGroups;

	@JsonProperty("bootstrap_scripts")

	BootstrapScript[] bootstrapScripts;

	@JsonProperty("node_public_cert_name")
	String keypair;

	@JsonProperty("cluster_admin_secret")
	String clusterAdminSecret;

	@JsonProperty("cluster_master_secret")
	String clusterMasterSecret;

	@JsonProperty("safe_mode")

	Integer safeMode;

	String strMasterNodeNum; // 主节点数量--字符类型

	String strCoreNodeNum; // 核心节点数量--字符类型

	String strVolumeSize; // 卷大小 --字符类型

	@JsonProperty("cluster_type")
	Integer type;

	String strClusterType; // 集群类型;用于校验是否为整型

	@JsonProperty("log_collection")
	Integer logCollection;

	String strLogCollection;

	@JsonProperty("tags")
	Tag[] tags;

	@JsonProperty("login_mode")
	Integer loginMode;
	
}
