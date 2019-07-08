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
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.ecs.v1.contants.NetworkChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1.contants.ShareType;

/**
 * A model represent Bandwidth properties for Server creation
 */
public class Bandwidth implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 带宽（Mbit/s），取值范围为[1,300]。
	 */
	@JsonProperty("size")
	private Integer size;

	/**
	 * 	带宽的共享类型。 共享类型枚举：PER，表示独享。 目前只支持独享。
	 */
	@JsonProperty("sharetype")
	private ShareType shareType;

	/**
	 * 带宽的计费类型。
		未传该字段，表示按带宽计费。
		字段值为空，表示按带宽计费。
		字段值为“traffic”，表示按流量计费。
		字段为其它值，会导致创建云服务器失败。
	 */
	@JsonProperty("chargemode")
	private NetworkChargingMode chargeMode;

	/**
	 * 带宽ID
	 * 创建WHOLE类型带宽的弹性IP时可以指定之前的共享带宽创建。
	 * 取值范围：WHOLE类型的带宽ID。
	 */
	@JsonProperty("id")
	private String id;

	@java.beans.ConstructorProperties({ "size", "shareType", "chargeMode", "id"})
	public Bandwidth(Integer size, ShareType shareType, NetworkChargingMode chargeMode, String id) {
		this.size = size;
		this.shareType = shareType;
		this.chargeMode = chargeMode;
		this.id = id;
	}

	public Bandwidth() {
	}

	public static BandwidthBuilder builder() {
		return new BandwidthBuilder();
	}

	public Integer getSize() {
		return this.size;
	}

	public ShareType getShareType() {
		return this.shareType;
	}

	public NetworkChargingMode getChargeMode() {
		return this.chargeMode;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Bandwidth(size=" + this.getSize() + ", shareType=" + this.getShareType() + ", chargeMode="
				+ this.getChargeMode() + ", id=" + this.getId() +")";
	}

	public BandwidthBuilder toBuilder() {
		return new BandwidthBuilder().size(this.size).shareType(this.shareType).chargeMode(this.chargeMode).id(this.id);
	}

	public static class BandwidthBuilder {
		private Integer size;
		private ShareType shareType;
		private NetworkChargingMode chargeMode;
		private String id;

		BandwidthBuilder() {
		}

		public Bandwidth.BandwidthBuilder size(Integer size) {
			this.size = size;
			return this;
		}

		public Bandwidth.BandwidthBuilder shareType(ShareType shareType) {
			this.shareType = shareType;
			return this;
		}

		public Bandwidth.BandwidthBuilder chargeMode(NetworkChargingMode chargeMode) {
			this.chargeMode = chargeMode;
			return this;
		}

		public Bandwidth.BandwidthBuilder id(String id){
			this.id = id;
			return this;
		}

		public Bandwidth build() {
			return new Bandwidth(size, shareType, chargeMode, id);
		}

		@Override
		public String toString() {
			return "Bandwidth.BandwidthBuilder(size=" + this.size + ", shareType=" + this.shareType + ", chargeMode="
					+ this.chargeMode + ", id=" + this.id + ")";
		}
	}
}
