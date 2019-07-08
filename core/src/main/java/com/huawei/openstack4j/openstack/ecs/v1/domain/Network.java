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

/**
 * A model represent Network properties for Server creation
 */
public class Network implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 待创建云服务器的网卡信息。
	 * 需要指定vpcid对应VPC下已创建的网络（network）的ID，UUID格式。
	 */
	@JsonProperty("subnet_id")
	private String subnetId;

	/**
	 * 待创建云服务器网卡的IP地址，IPv4格式。
	 * 
		约束：
		不填或空字符串，默认在网络（network）对应的子网（subnet）中自动分配一个未使用的IP作网卡的IP地址。
		若指定IP地址，该IP地址必须在网络（network）对应的子网的网段内，且未被使用。
	 */
	@JsonProperty("ip_address")
	private String ipAddress;

	/**
	 * 是否支持ipv6。
	 * 取值为true时，标识此网卡支持ipv6。
	 */
	@JsonProperty("ipv6_enable")
	private Boolean ipv6Enable;

	/**
	 * 绑定的共享带宽ID。
	 */
	@JsonProperty("ipv6_bandwidth")
	private Ipv6BandWidth ipv6Bandwidth;

	@java.beans.ConstructorProperties({ "subnetId", "ipAddress", "ipv6Enable", "ipv6Bandwidth" })
	public Network(String subnetId, String ipAddress, Boolean ipv6Enable, Ipv6BandWidth ipv6Bandwidth) {
		this.subnetId = subnetId;
		this.ipAddress = ipAddress;
		this.ipv6Enable = ipv6Enable;
		this.ipv6Bandwidth = ipv6Bandwidth;
	}

	public Network() {
	}

	public static NetworkBuilder builder() {
		return new NetworkBuilder();
	}

	public String getSubnetId() {
		return this.subnetId;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public Boolean getIpv6Enable() {
		return this.ipv6Enable;
	}

	public Ipv6BandWidth getIpv6Bandwidth() {
		return this.ipv6Bandwidth;
	}

	@Override
	public String toString() {
		return "Network(subnetId=" + this.getSubnetId() + ", ipAddress=" + this.getIpAddress() +
				", ipv6Enable=" + this.getIpv6Enable() + ", ipv6Bandwidth=" + this.getIpv6Bandwidth() + ")";
	}

	public NetworkBuilder toBuilder() {
		return new NetworkBuilder().subnetId(this.subnetId).ipAddress(this.ipAddress)
				.ipv6Enable(this.ipv6Enable).ipv6Bandwidth(this.ipv6Bandwidth);
	}

	public static class NetworkBuilder {
		private String subnetId;
		private String ipAddress;
		private Boolean ipv6Enable;
		private Ipv6BandWidth ipv6Bandwidth;

		NetworkBuilder() {
		}

		public Network.NetworkBuilder subnetId(String subnetId) {
			this.subnetId = subnetId;
			return this;
		}

		public Network.NetworkBuilder ipAddress(String ipAddress) {
			this.ipAddress = ipAddress;
			return this;
		}

		public Network.NetworkBuilder ipv6Enable(Boolean ipv6Enable) {
			this.ipv6Enable = ipv6Enable;
			return this;
		}

		public Network.NetworkBuilder ipv6Bandwidth(Ipv6BandWidth ipv6Bandwidth) {
			this.ipv6Bandwidth = ipv6Bandwidth;
			return this;
		}

		public Network build() {
			return new Network(subnetId, ipAddress, ipv6Enable, ipv6Bandwidth);
		}

		@Override
		public String toString() {
			return "Network.NetworkBuilder(subnetId=" + this.subnetId + ", ipAddress=" + this.ipAddress +
					", ipv6Enable=" + this.ipv6Enable + ", ipv6Bandwidth=" + this.ipv6Bandwidth + ")";
		}
	}
}
