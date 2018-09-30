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
import com.huawei.openstack4j.openstack.vpc.v2.contants.VirtualChargingType;
import com.huawei.openstack4j.openstack.vpc.v2.contants.VirtualChargingModeExtend;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualExtendParam implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7084270775989831470L;
	
	/**
	 * 付费方式（预付费、按需付费；预付费，即包周期付费）
	 */
	@JsonProperty("charge_mode")
	private VirtualChargingModeExtend chargeMode;
	
	/**
	 * 订购资源的周期类型（包年、包月等）。
	 */
	@JsonProperty("period_type")
	private VirtualChargingType periodType;
	
	/**
	 * 订购周期数
	 */
	@JsonProperty("period_num")
	private Integer periodNum;
	
	/**
	 * 是否自动续订
	 */
	@JsonProperty("is_auto_renew")
	private Boolean isAutoRenew;
	
	/**
	 * 下单订购后，是否自动从客户的账户中支付；默认是“不自动支付”
	 */
	@JsonProperty("is_auto_pay")
	private Boolean isAutoPay;

	
}
