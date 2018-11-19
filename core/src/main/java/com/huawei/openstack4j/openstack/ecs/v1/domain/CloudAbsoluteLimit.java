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
package com.huawei.openstack4j.openstack.ecs.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("absolute")
public class CloudAbsoluteLimit implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1777179067528056831L;
	/**
	 * 可输入元数据的最大长度
	 */
	private Integer maxServerMeta;
	/**
	 * 可注入文件的最大个数
	 */
	private Integer maxPersonality;
	/**
	 * 镜像元数据最大的长度
	 */
	private Integer maxImageMeta;
	/**
	 * 注入文件内容的最大长度（单位：Byte）
	 */
	private Integer maxPersonalitySize;
	/**
	 * CPU核数最大申请数量
	 */
	private Integer maxTotalCores;
	/**
	 * 当前已使用CPU核数
	 */
	private Integer totalCoresUsed;
	/**
	 * 云服务器最大申请数量
	 */
	private Integer maxTotalInstances;
	/**
	 * 当前云服务器使用个数
	 */
	private Integer totalInstancesUsed;
	/**
	 * 内存最大申请容量（单位：MB）
	 */
	private Integer maxTotalRAMSize;
	/**
	 * 当前内存使用容量（单位：MB）
	 */
	private Integer totalRAMUsed;
	/**
	 * 安全组中安全组规则最大的配置个数
	 */
	private Integer maxSecurityGroupRules;
	/**
	 * 可以申请的SSH密钥对最大数量
	 */
	private Integer maxTotalKeypairs;
	/**
	 * 安全组最大使用个数
	 */
	private Integer maxSecurityGroups;
	/**
	 * 当前安全组使用个数
	 */
	private Integer totalSecurityGroupsUsed;
	/**
	 * 最大的浮动IP使用个数
	 */
	private Integer maxTotalFloatingIps;
	/**
	 * 当前浮动IP使用个数
	 */
	private Integer totalFloatingIpsUsed;
	/**
	 * 服务器组的最大个数
	 */
	private Integer maxServerGroups;
	/**
	 * 服务器组中的最大弹性云服务器数
	 */
	private Integer maxServerGroupMembers;
	/**
	 * 已使用的服务器组个数
	 */
	private Integer totalServerGroupsUsed;
}
