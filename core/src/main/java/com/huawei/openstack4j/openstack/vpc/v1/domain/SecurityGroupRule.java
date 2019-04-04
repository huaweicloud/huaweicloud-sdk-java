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
 * Model represent attributes of security group rule
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("security_group_rule")
public class SecurityGroupRule implements ModelEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String description;
	/**
	 * security group id
	 */
	@JsonProperty("security_group_id")
	private String securityGroupId;
	
	/**
	 * direction, egress or ingress
	 */
	private String direction;
	
	/**
	 * ethertype，IPv4 or IPv6
	 */
	private String ethertype;
	
	/**
	 * protocol，contains icmp，tcp，udp，and so on
	 */
	private String protocol;
	
	/**
	 * port range min， 1~65535
	 */
	@JsonProperty("port_range_min")
	private Integer portRangeMin;
	
	/**
	 * port range max，1~65535
	 */
	@JsonProperty("port_range_max")
	private Integer portRangeMax;
	
	/**
	 * remote ip prefix
	 */
	@JsonProperty("remote_ip_prefix")
	private String remoteIpPrefix;
	
	/**
	 * remote group id
	 */
	@JsonProperty("remote_group_id")
	private String remoteGroupId;
	
	public static class SecurityGroupRules extends ListResult<SecurityGroupRule> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("security_group_rules")
		private List<SecurityGroupRule> securityGroupRules;

		public List<SecurityGroupRule> value() {
			return securityGroupRules;
		}

	}

}
