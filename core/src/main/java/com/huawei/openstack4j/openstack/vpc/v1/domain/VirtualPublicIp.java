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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.vpc.v1.contants.PublicIpStatus;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("publicip")
public class VirtualPublicIp implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2488535914184128625L;
	@JsonProperty("id")
	private String id;
	@JsonProperty("status")
	private PublicIpStatus status;
	@JsonProperty("profile")
	private VirtualProfile profile;
	@JsonProperty("type")
	private String type;
	@JsonProperty("public_ip_address")
	private String publicIpAddress;
	@JsonProperty("public_ipv6_address")
	private String publicIpv6Address;
	@JsonProperty("ip_version")
	private Integer ipVersion;
	@JsonProperty("private_ip_address")
	private String privateIpAddress;
	@JsonProperty("port_id")
	private String portId;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("create_time")
	private String createTime;
	@JsonProperty("bandwidth_id")
	private String bandwidthId;
	@JsonProperty("bandwidth_size")
	private Integer bandwidthSize;
	@JsonProperty("bandwidth_share_type")
	private String bandwidthShareType;
	@JsonProperty("bandwidth_name")
	private String bandwidthName;
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	
}
