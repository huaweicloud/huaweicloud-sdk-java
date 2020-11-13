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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.openstack.ecs.v1.contants.InterruptionPolicyEnum;
import com.huawei.openstack4j.openstack.ecs.v1.contants.ServerChargingMode;

@JsonRootName("server")
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

	@java.beans.ConstructorProperties({ "chargingMode", "regionId", "autoRecovery", "enterpriseProjectId"})
	public ServerExtendParam(ServerChargingMode chargingMode, String regionId, Boolean autoRecovery, String enterpriseProjectId) {
		this.chargingMode = chargingMode;
		this.regionId = regionId;
		this.autoRecovery = autoRecovery;
		this.enterpriseProjectId = enterpriseProjectId;
	}

	@java.beans.ConstructorProperties({"chargingMode", "regionId", "autoRecovery", "enterpriseProjectId", "marketType", "spotPrice"})
	public ServerExtendParam(ServerChargingMode chargingMode, String regionId, Boolean autoRecovery, String enterpriseProjectId, String marketType, String spotPrice) {
		this.chargingMode = chargingMode;
		this.regionId = regionId;
		this.autoRecovery = autoRecovery;
		this.enterpriseProjectId = enterpriseProjectId;
		this.marketType = marketType;
		this.spotPrice = spotPrice;
	}

	@java.beans.ConstructorProperties({"chargingMode", "regionId", "autoRecovery", "enterpriseProjectId", "marketType", "spotPrice", "spotDurationHours", "spotDurationCount", "interruptionPolicy"})
	public ServerExtendParam(ServerChargingMode chargingMode, String regionId, Boolean autoRecovery, String enterpriseProjectId,
							 String marketType, String spotPrice, Integer spotDurationHours, Integer spotDurationCount, InterruptionPolicyEnum interruptionPolicy) {
		this.chargingMode = chargingMode;
		this.regionId = regionId;
		this.autoRecovery = autoRecovery;
		this.enterpriseProjectId = enterpriseProjectId;
		this.marketType = marketType;
		this.spotPrice = spotPrice;
		this.spotDurationHours = spotDurationHours;
		this.spotDurationCount = spotDurationCount;
		this.interruptionPolicy = interruptionPolicy;
	}

	public ServerExtendParam() {
	}

	public static ServerExtendParamBuilder builder() {
		return new ServerExtendParamBuilder();
	}

	public ServerChargingMode getChargingMode() {
		return this.chargingMode;
	}

	public String getRegionId() {
		return this.regionId;
	}

	public Boolean getAutoRecovery() {
		return this.autoRecovery;
	}

	public String getEnterpriseProjectId(){
		return this.enterpriseProjectId;
	}

	public String getMarketType() {
		return marketType;
	}

	public String getSpotPrice() {
		return spotPrice;
	}

	public Integer getSpotDurationHours() {
		return spotDurationHours;
	}

	public Integer getSpotDurationCount() {
		return spotDurationCount;
	}

	public InterruptionPolicyEnum getInterruptionPolicy() {
		return interruptionPolicy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerExtendParam(chargingMode=");
		builder.append(this.getChargingMode());
		builder.append(", regionId=");
		builder.append(this.getRegionId());
		builder.append(", autoRecovery=");
		builder.append(this.getAutoRecovery());
		builder.append(", enterpriseProjectId=");
		builder.append(this.getEnterpriseProjectId());
		builder.append(", marketType=");
		builder.append(this.getMarketType());
		builder.append(", spotPrice=");
		builder.append(this.getSpotPrice());
		builder.append(", spotDurationHours=");
		builder.append(this.getSpotDurationHours());
		builder.append(", spotDurationCount=");
		builder.append(this.getSpotDurationCount());
		builder.append(", interruptionPolicy=");
		builder.append(this.getInterruptionPolicy());
		builder.append(")");
		return builder.toString();
	}

	public ServerExtendParamBuilder toBuilder() {
		return new ServerExtendParamBuilder().chargingMode(this.chargingMode).regionId(this.regionId)
				.autoRecovery(this.autoRecovery);
	}

	public static class ServerExtendParamBuilder {
		private ServerChargingMode chargingMode;
		private String regionId;
		private Boolean autoRecovery;
		private String enterpriseProjectId;
		private String marketType;
		private String spotPrice;
		Integer spotDurationHours;
		Integer spotDurationCount;
		InterruptionPolicyEnum interruptionPolicy;

		ServerExtendParamBuilder() {
		}

		public ServerExtendParam.ServerExtendParamBuilder chargingMode(ServerChargingMode chargingMode) {
			this.chargingMode = chargingMode;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder regionId(String regionId) {
			this.regionId = regionId;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder autoRecovery(Boolean autoRecovery) {
			this.autoRecovery = autoRecovery;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder enterpriseProjectId(String enterpriseProjectId){
			this.enterpriseProjectId = enterpriseProjectId;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder marketType(String marketType) {
			this.marketType = marketType;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder spotPrice(String spotPrice) {
			this.spotPrice = spotPrice;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder spotDurationHours(Integer spotDurationHours) {
			this.spotDurationHours = spotDurationHours;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder spotDurationCount(Integer spotDurationCount) {
			this.spotDurationCount = spotDurationCount;
			return this;
		}

		public ServerExtendParam.ServerExtendParamBuilder interruptionPolicy(InterruptionPolicyEnum interruptionPolicy) {
			this.interruptionPolicy = interruptionPolicy;
			return this;
		}

		public ServerExtendParam build() {
			if (null != marketType) {
				return new ServerExtendParam(chargingMode, regionId, autoRecovery, enterpriseProjectId,
						marketType, spotPrice, spotDurationHours, spotDurationCount, interruptionPolicy);
			} else {
				return new ServerExtendParam(chargingMode, regionId, autoRecovery, enterpriseProjectId);
			}

		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ServerExtendParam.ServerExtendParamBuilder(chargingMode=");
			builder.append(this.chargingMode);
			builder.append(", regionId=");
			builder.append(this.regionId);
			builder.append(", autoRecovery=");
			builder.append(this.autoRecovery);
			builder.append(", enterpriseProjectId=");
			builder.append(this.enterpriseProjectId);
			builder.append(", marketType=");
			builder.append(this.marketType);
			builder.append(", spotPrice=");
			builder.append(this.spotPrice);
			builder.append(", spotDurationHours=");
			builder.append(this.spotDurationHours);
			builder.append(", spotDurationCount=");
			builder.append(this.spotDurationCount);
			builder.append(", interruptionPolicy=");
			builder.append(this.interruptionPolicy);
			builder.append(")");
			return builder.toString();
		}
	}
}
