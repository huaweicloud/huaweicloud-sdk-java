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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.openstack.ecs.v1.contants.PeriodType;
import com.huawei.openstack4j.openstack.ecs.v1_1.contants.ServerChargingMode;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonRootName("server")
public class ServerExtendParam {

	/**
	 * 计费模式：0：按需计费。
	 */
	@JsonProperty("chargingMode")
	ServerChargingMode chargingMode;

	/**
	 * 云服务器所在区域ID。
	 */
	@JsonProperty("regionID")
	String regionId;

	/**
	 * 订购周期类型。
		取值范围：
		month-月
		year-年
		说明：
		chargingMode为prePaid时生效且为必选值。
	 */
	PeriodType periodType;
	
	/**
	 * 订购周期数。
		取值范围：
		periodType=month（周期类型为月）时，取值为[1，9]。
		periodType=year（周期类型为年）时，取值为1。
		说明：
		chargingMode为prePaid时生效且为必选值。
	 */
	Integer periodNum;
	
	/**
	 * 是否自动续订。
		“true”：自动续订
		“false”：不自动续订
		说明：
		chargingMode为prePaid时生效，该值为空时默认为不自动续订。
	 */
	Boolean isAutoRenew;
	
	/**
	 * 下单订购后，是否自动从客户的账户中支付，而不需要客户手动去进行支付。
		“true”：是（自动支付）
		“false”：否（需要客户手动支付）
	 */
	Boolean isAutoPay;

}
