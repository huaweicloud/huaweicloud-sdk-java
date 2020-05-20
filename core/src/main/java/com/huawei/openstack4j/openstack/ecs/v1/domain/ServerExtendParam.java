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

    @Override
    public String toString() {
        return "ServerExtendParam(chargingMode=" + this.getChargingMode() + ", regionId=" + this.getRegionId()
                + ", autoRecovery=" + this.getAutoRecovery()  + ", enterpriseProjectId=" + this.getEnterpriseProjectId()
                + ", marketType=" + this.getMarketType() + ", spotPrice=" + this.getSpotPrice() + ")";
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

		public ServerExtendParam build() {
			if (null != marketType) {
				return new ServerExtendParam(chargingMode, regionId, autoRecovery, enterpriseProjectId, marketType, spotPrice);
			} else {
				return new ServerExtendParam(chargingMode, regionId, autoRecovery, enterpriseProjectId);
			}

		}

		@Override
		public String toString() {
			return "ServerExtendParam.ServerExtendParamBuilder(chargingMode=" + this.chargingMode + ", regionId="
					+ this.regionId + ", autoRecovery=" + this.autoRecovery + ", enterpriseProjectId=" + this.enterpriseProjectId
					+ ", marketType=" + this.marketType + ", spotPrice=" + this.spotPrice + ")";
		}
	}
}
