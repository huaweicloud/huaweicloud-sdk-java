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
package com.huawei.openstack4j.openstack.vpc.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.vpc.v2.contants.VirtualChargingMode;
import com.huawei.openstack4j.openstack.vpc.v2.contants.ShareType;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonRootName("server")
public class VirtualBandWidth implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 1、功能说明：带宽名称
		2、取值范围：1-64，支持数字、字母、中文、_(下划线)、-（中划线）
		3、约束：
		如果share_type是PER，该字段是必选。
		如果bandwidth对象的id有值，该字段被忽略。
	 */
	@JsonProperty("name")
	private String name;
	
	/**
	 * 带宽（Mbit/s），取值范围为[1,300]。
	 */
	@JsonProperty("size")
	private Integer size;

	/**
	 * 使用已有的共享带宽创建IP
	 */
	@JsonProperty("id")
	private String id;
	
	/**
	 * 	带宽的共享类型。 共享类型枚举：PER，表示独享。 目前只支持独享。
	 */
	@JsonProperty("share_type")
	private ShareType shareType;

	/**
	 * 带宽的计费类型。
		未传该字段，表示按带宽计费。
		字段值为空，表示按带宽计费。
		字段值为“traffic”，表示按流量计费。
		字段为其它值，会导致创建云服务器失败。
	 */
	@JsonProperty("charge_mode")
	private VirtualChargingMode chargeMode;

}
