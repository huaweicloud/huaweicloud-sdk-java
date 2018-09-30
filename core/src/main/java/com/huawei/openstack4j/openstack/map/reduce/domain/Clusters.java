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
import com.huawei.openstack4j.model.ModelEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clusters implements ModelEntity {

	private static final long serialVersionUID = -8073930146185322114L;

	private String clusterId; // 集群 ID

	private String clusterName; // 集群名称

	private String masterNodeNum; // 主节点数量

	private String coreNodeNum; // 核心节点数量

	private String clusterState; // 集群状态

	private String stageDesc;

	private boolean isMrsManagerFinish;

	private String createAt; // 创建时间

	private String updateAt; // 更新时间

	private String chargingStartTime; // 计费开始时间

	private String billingType; // 付费方式

	private String dataCenter; // 数据中心

	private String vpc; // 虚拟私有网

	private String vpcId;

	private String duration;

	private String fee;

	private String hadoopVersion;

	private String masterNodeSize;

	private String coreNodeSize;

	private List<MapReduceComponent> componentList;

	private String externalIp; // 外网 IP

	private String externalAlternateIp; // 外网 备用IP

	private String internalIp; // 内网 IP

	private String deploymentId; // 部署 ID（用于删除集群）

	private String remark; // 备注

	private String orderId; // 订单号

	private String azId; // Available Zone ID

	private String masterNodeProductId;

	private String masterNodeSpecId;

	private String coreNodeProductId;

	private String coreNodeSpecId;

	private String azName; // 可用区域名称

	private String instanceId;

	private String vnc;

	private String tenantId; // 租户ID

	private int volumeSize;

	private String volumeType;

	private String subnetId;

	private String subnetName;

	private String securityGroupsId;

	private String slaveSecurityGroupsId;

	private BootstrapScript[] bootstrapScripts;

	private Integer safeMode;

	private String clusterVersion;

	private String nodePublicCertName;

	private String masterNodeIp = "unknown";

	private String privateIpFirst;

	private String errorInfo;

	private String tags;

	private Integer clusterType;

	private Integer logCollection;

	private List<TaskNodeGroup> taskNodeGroups;

	private String masterDataVolumeType;

	private int masterDataVolumeSize;

	private int masterDataVolumeCount;

	private String coreDataVolumeType;

	private int coreDataVolumeSize;

	private int coreDataVolumeCount;

	private Integer periodType;

	private String purchaseMode;

	private String scale;

	private String clusterProductId;

	private String clusterSpecId;

	private String clusterResourceId;

}
