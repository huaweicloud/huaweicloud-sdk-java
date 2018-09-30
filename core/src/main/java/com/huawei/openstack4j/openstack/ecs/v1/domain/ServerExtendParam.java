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

	@java.beans.ConstructorProperties({ "chargingMode", "regionId", "autoRecovery" })
	public ServerExtendParam(ServerChargingMode chargingMode, String regionId, Boolean autoRecovery) {
		this.chargingMode = chargingMode;
		this.regionId = regionId;
		this.autoRecovery = autoRecovery;
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

	@Override
	public String toString() {
		return "ServerExtendParam(chargingMode=" + this.getChargingMode() + ", regionId=" + this.getRegionId()
				+ ", autoRecovery=" + this.getAutoRecovery() + ")";
	}

	public ServerExtendParamBuilder toBuilder() {
		return new ServerExtendParamBuilder().chargingMode(this.chargingMode).regionId(this.regionId)
				.autoRecovery(this.autoRecovery);
	}

	public static class ServerExtendParamBuilder {
		private ServerChargingMode chargingMode;
		private String regionId;
		private Boolean autoRecovery;

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

		public ServerExtendParam build() {
			return new ServerExtendParam(chargingMode, regionId, autoRecovery);
		}

		@Override
		public String toString() {
			return "ServerExtendParam.ServerExtendParamBuilder(chargingMode=" + this.chargingMode + ", regionId="
					+ this.regionId + ", autoRecovery=" + this.autoRecovery + ")";
		}
	}
}
