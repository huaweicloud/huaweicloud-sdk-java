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
package com.huawei.openstack4j.openstack.bms.v1.domain;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Network;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("server")
public class ServerCreate implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6784885334550003586L;

	/**
	 * 裸金属服务器使用的镜像ID或者镜像资源的URL。
	 */
	@JsonProperty("imageRef")
	private String imageRef;
	
	/**
	 * 裸金属服务器使用的规格ID。
	 */
	@JsonProperty("flavorRef")
	private String flavorRef;
	
	/**
	 * 裸金属服务器名称。
	 */
	@JsonProperty("name")
	private String name;
	
	/**
	 * 裸金属服务器元数据，key和value的长度均不大于255字节。
	 */
	@JsonProperty("metadata")
	private Map<String, Object> metadata;
	
	/**
	 * 创建裸金属服务器过程中注入用户数据。
	 */
	@JsonProperty("user_data")
	private String userData;
	
	/**
	 * 	扩展属性，指定keypair的名称。如果需要使用SSH密钥方式登录裸金属服务器，请指定已创建密钥的名称
	 */
	@JsonProperty("key_name")
	private String keyName;
	
	/**
	 * 指定裸金属服务器的安全组
	 */
	@JsonProperty("security_groups")
	private List<IdResourceEntity> securityGroups;
	
	/**
	 * 	指定裸金属服务器的网卡信息
	 */
	@JsonProperty("nics")
	private List<Network> networks;
	
	/**
	 * 裸金属服务器对应可用分区信息，需要指定可用分区（AZ）的名称。
	 */
	@JsonProperty("availability_zone")
	private String availabilityZone;
	
	/**
	 * 创建裸金属服务器所属虚拟私有云，需要指定已创建VPC的ID，UUID格式。
	 */
	@JsonProperty("vpcid")
	private String vpcId;
	
	/**
	 * 配置裸金属服务器的弹性公网IP信息，弹性公网IP有三种配置方式。
	 */
	@JsonProperty("publicip")
	private PublicIP publicIP;
	
	/**
	 * 创建裸金属服务器数量
	 */
	@JsonProperty("count")
	private Integer count;
	
	/**
	 * 裸金属服务器对应系统盘相关配置。
	 */
	@JsonProperty("root_volume")
	private RootVolume rootVolume;
	
	/**
	 * 裸金属服务器对应数据盘相关配置
	 */
	@JsonProperty("data_volumes")
	private List<DataVolume> dataVolumes;
	
	
	/**
	 * 创建云服务器附加信息。
	 */
	@JsonProperty("extendparam")
	private ServerExtendParam extendParam;
	
	/**
	 * 云服务器调度信息。
	 */
	@JsonProperty("schedulerHints")
	private SchedulerHints schedulerHints;
}


