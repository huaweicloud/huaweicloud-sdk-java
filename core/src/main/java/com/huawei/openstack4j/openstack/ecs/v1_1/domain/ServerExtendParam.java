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

import com.huawei.openstack4j.openstack.ecs.v1_1.contants.InterruptionPolicyEnum;
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
	 * 是否配置虚拟机自动恢复的功能。
		“true”：配置该功能
		“false”：不配置该功能
	 */
	@JsonProperty("support_auto_recovery")
	Boolean autoRecovery;
	
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

	/**
	 * 企业项目ID
	 * 约束：
	 * 如果设置企业项目ID，那么需要确保创建弹性云服务器使用的其他资源都共属于同一个企业项目ID底下。
	 */
	@JsonProperty("enterprise_project_id")
	String enterpriseProjectId;

	/**
	 * 建竞价实例时，需指定该参数的值为“spot”。
	 * 约束：
	 * 当chargingMode=0时且marketType=spot时此参数生效。
	 */
	@JsonProperty("marketType")
	String marketType;


	/**
	 * 用户愿意为竞价实例每小时支付的最高价格。
	 * 约束：
	 * 仅chargingMode=0且marketType=spot时，该参数设置后生效。
	 * 当chargingMode=0且marketType=spot时，如果不传递spotPrice，默认使用按需购买的价格作为竞价。
	 */
	@JsonProperty("spotPrice")
	String spotPrice;

	/**
	 * 购买的竞价实例时长。
	 * 约束：
	 * 仅interruption_policy=immediate 时该字段有效 。
	 * spot_duration_hours大于0。最大值由预测系统给出可以从flavor的extra_specs的cond:spot_block:operation:longest_duration_hours字段中查询。
	 */
	@JsonProperty("spot_duration_hours")
	Integer spotDurationHours;

	/**
	 * 表示购买的“竞价实例时长”的个数
	 * 约束：
	 * 仅spot_duration_hours>0 时该字段有效。
	 * spot_duration_hours小于6时，spot_duration_count值必须为1。
	 * spot_duration_hours等于6时，spot_duration_count大于等于1。
	 * spot_duration_count的最大值由预测系统给出可以从flavor的extra_specs的cond:spot_block:operation:longest_duration_count字段中查询。
	 */
	@JsonProperty("spot_duration_count")
	Integer spotDurationCount;

	/**
	 * 竞价实例中断策略，当前支持immediate。
	 * 约束：
	 * 当interruption_policy=immediate时表示释放策略为立即释放。
	 */
	@JsonProperty("interruption_policy")
	InterruptionPolicyEnum interruptionPolicy;
}
