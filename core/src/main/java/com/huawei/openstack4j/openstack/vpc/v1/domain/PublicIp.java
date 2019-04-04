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

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of publicip
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("publicip")
public class PublicIp implements ModelEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * publicip type
	 */
	private String type;
	
	/**
	 * publicip status
	 */
	private String status;
	
	/**
	 * Additional parameters, including order number, product number, etc.
	 */
	private Profile profile;
	
	/**
	 * public ip address
	 */
	@JsonProperty("public_ip_address")
	private String publicIpAddress;
	
	/**
	 * public ipv6 address
	 */
	@JsonProperty("public_ipv6_address")
	private String publicIpv6Address;
	
	/**
	 * ip version
	 */
	@JsonProperty("ip_version")
	private Integer ipVersion;
	
	/**
	 * private ip address
	 */
	@JsonProperty("private_ip_address")
	private String privateIpAddress;
	
	/**
	 * port id
	 */
	@JsonProperty("port_id")
	private String portId;
	
	/**
	 * tenant id
	 */
	@JsonProperty("tenant_id")
	private String tenantId;
	
	/**
	 * create time
	 */
	@JsonProperty("create_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date createTime;
	
	/**
	 * bandwidth id
	 */
	@JsonProperty("bandwidth_id")
	private String bandwidthId;
	
	/**
	 * bandwidth size
	 */
	@JsonProperty("bandwidth_size")
	private Integer bandwidthSize;
	
	/**
	 * bandwidth share type
	 */
	@JsonProperty("bandwidth_share_type")
	private String bandwidthShareType;
	
	/**
	 * bandwidth name
	 */
	@JsonProperty("bandwidth_name")
	private String bandwidthName;
	
	/**
	 * enterprise project id
	 */
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	
	public static class Publicips extends ListResult<PublicIp> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("publicips")
		private List<PublicIp> publicips;

		public List<PublicIp> value() {
			return publicips;
		}

	}

}
