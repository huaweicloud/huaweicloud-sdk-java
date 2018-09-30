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
public class BackupData implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1861571618277871324L;

	/**
	 * 云服务器所在可用分区名称
	 */
	@JsonProperty("__openstack_region_name")
	private String openstackRegionName;
	
	/**
	 * 云服务器类型
	 */
	@JsonProperty("cloudservicetype")
	private String cloudServiceType;
	
	/**
	 * 该云服务器规格对应要求系统盘大小
	 */
	@JsonProperty("disk")
	private String disk;
	
	/**
	 * 镜像类型
	 */
	@JsonProperty("imagetype")
	private String imageType;
	
	/**
	 * 该云服务器的内存大小，单位为MB
	 */
	@JsonProperty("ram")
	private String ram;
	
	/**
	 * 该云服务器对应的cpu核数
	 */
	@JsonProperty("vcpus")
	private String vcpus;
	
	/**
	 * 云服务器弹性IP
	 */
	@JsonProperty("eip")
	private String eip;
	
	/**
	 * 云服务器内部IP
	 */
	@JsonProperty("private_ip")
	private String privateIp;
	
}
