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

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of Port
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("port")
public class Port implements ModelEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * port name
	 */
	private String name;
	
	/**
	 * network id
	 */
	@JsonProperty("network_id")
	private String networkId;
	
	/**
	 * admin state up
	 */
	@JsonProperty("admin_state_up")
	private Boolean adminStateUp;
	
	/**
	 * mac address
	 */
	@JsonProperty("mac_address")
	private String macAddress;
	
	/**
	 * fixed ips
	 */
	@JsonProperty("fixed_ips")
	private List<FixedIp> fixedIps;
	
	/**
	 * device id
	 */
	@JsonProperty("device_id")
	private String deviceId;
	
	/**
	 * device owner
	 */
	@JsonProperty("device_owner")
	private String deviceOwner;
	
	/**
	 * tenant id
	 */
	@JsonProperty("tenant_id")
	private String tenantId;

	/**
	 * status
	 */
	private String status;
	
	/**
	 * security groups
	 */
	@JsonProperty("security_groups")
	private List<String> securityGroups;
	
	/**
	 * allowed address pairs
	 */
	@JsonProperty("allowed_address_pairs")
	private List<AllowAddressPair> allowedAddressPairs;
	
	/**
	 * extra dhcp opts
	 */
	@JsonProperty("extra_dhcp_opts")
	private List<ExtraDhcpOpt> extraDhcpOpts;
	
	/**
	 * binding:vif_type
	 */
	@JsonProperty("binding:vif_type")
	private String bindingVifType;
	
	/**
	 * binding:vif_details
	 */
	@JsonProperty("binding:vif_details")
	private HashMap<String, Object> bindingVifDetails;
	
	/**
	 * binding:host_id
	 */
	@JsonProperty("binding:host_id")
	private String bindingHostId;
	
	/**
	 * binding:profile
	 */
	@JsonProperty("binding:profile")
	private HashMap<String, Object> bindingProfile;
	
	/**
	 * binding:vnic_type
	 */
	@JsonProperty("binding:vnic_type")
	private String bindingVnicType;
	
	/**
	 * dns assignment
	 */
	@JsonProperty("dns_assignment")
	private List<DnsAssignment> dnsAssignment;
	
	/**
	 * dns name
	 */
	@JsonProperty("dns_name")
	private String dnsName;

	/**
	 * 	Port security enable flag
	 */
	@JsonProperty("port_security_enabled")
	private String portSecurityEnabled;

	
	public static class Ports extends ListResult<Port> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("ports")
		private List<Port> ports;

		public List<Port> value() {
			return ports;
		}

	}

}
