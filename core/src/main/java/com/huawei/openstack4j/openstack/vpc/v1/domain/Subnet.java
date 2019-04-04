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
package com.huawei.openstack4j.openstack.vpc.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of vpc subnet
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("subnet")
public class Subnet implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * vpc name
	 */
	private String name;
	
	/**
	 * vpc subnet cidr
	 */
	private String cidr;
	
	/**
	 * Subnet gateway
	 */
	@JsonProperty("gateway_ip")
	private String gatewayIp;
	
	/**
	 * Whether the dhcp function is enabled on the subnet
	 */
	@JsonProperty("dhcp_enable")
	private Boolean dhcpEnable;
	
	/**
	 * Subnet dns server address 1
	 */
	@JsonProperty("primary_dns")
	private String primaryDns;
	
	/**
	 * Subnet dns server address 2
	 */
	@JsonProperty("secondary_dns")
	private String secondaryDns;
	
	/**
	 * Subnet dns server address list set
	 */
	private List<String> dnsList;
	
	/**
	 * Available partition ID where the subnet is located
	 */
	@JsonProperty("availability_zone")
	private String availabilityZone;
	
	/**
	 * The VPC ID of the subnet
	 */
	@JsonProperty("vpc_id")
	private String vpcId;

	/**
	 * vpc status
	 */
	private String status;
	
	/**
	 *  network (Openstack neutron) id
	 */
	@JsonProperty("neutron_network_id")
	private String neutronNetworkId;
	
	/**
	 *  subnet (Openstack neutron) id 
	 */
	@JsonProperty("neutron_subnet_id")
	private String neutronSubnetId;
	
	public static class Subnets extends ListResult<Subnet> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("subnets")
		private List<Subnet> subnets;

		public List<Subnet> value() {
			return subnets;
		}

	}

}
