/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * A model represent floating IP properties for Server creation
 */
public class PublicIP implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 待创建云服务器的网卡信息。
	 * 需要指定vpcid对应VPC下已创建的网络（network）的ID，UUID格式。
	 */
	@JsonProperty("id")
	private String floatingIP;

	/**
	 * 配置云服务器自动分配弹性IP时，创建弹性IP的配置参数。
	 */
	@JsonProperty("eip")
	private FloatingIPCreate eip;

	@java.beans.ConstructorProperties({ "floatingIP", "eip" })
	public PublicIP(String floatingIP, FloatingIPCreate eip) {
		this.floatingIP = floatingIP;
		this.eip = eip;
	}

	public PublicIP() {
	}

	public static PublicIPBuilder builder() {
		return new PublicIPBuilder();
	}

	public String getFloatingIP() {
		return this.floatingIP;
	}

	public FloatingIPCreate getEip() {
		return this.eip;
	}

	@Override
	public String toString() {
		return "PublicIP(floatingIP=" + this.getFloatingIP() + ", eip=" + this.getEip() + ")";
	}

	public PublicIPBuilder toBuilder() {
		return new PublicIPBuilder().floatingIP(this.floatingIP).eip(this.eip);
	}

	public static class PublicIPBuilder {
		private String floatingIP;
		private FloatingIPCreate eip;

		PublicIPBuilder() {
		}

		public PublicIP.PublicIPBuilder floatingIP(String floatingIP) {
			this.floatingIP = floatingIP;
			return this;
		}

		public PublicIP.PublicIPBuilder eip(FloatingIPCreate eip) {
			this.eip = eip;
			return this;
		}

		public PublicIP build() {
			return new PublicIP(floatingIP, eip);
		}

		@Override
		public String toString() {
			return "PublicIP.PublicIPBuilder(floatingIP=" + this.floatingIP + ", eip=" + this.eip + ")";
		}
	}
}
