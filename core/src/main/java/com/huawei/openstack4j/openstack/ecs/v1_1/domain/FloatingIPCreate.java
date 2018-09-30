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
package com.huawei.openstack4j.openstack.ecs.v1_1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;

/**
 * A model represent Network properties for Server creation
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloatingIPCreate implements ModelEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5207360450262608294L;

	/**
	 * 弹性IP地址类型。
		类型枚举值：
		5_bgp：全动态BGP
		5_sbgp：静态BGP
		5_telcom：中国电信
		5_union：中国联通
	 */
	@JsonProperty("iptype")
	IpType ipType;

	/**
	 * 弹性IP地址带宽参数
	 */
	@JsonProperty("bandwidth")
	Bandwidth bandwidth;
	
	/**
	 * 创建弹性IP的附加信息。
	 */
	@JsonProperty("extendparam")
	FloatingIPExtendParam extendparam;
	
}
