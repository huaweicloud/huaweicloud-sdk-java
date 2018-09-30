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
package com.huawei.openstack4j.openstack.nat.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("dnat_rule")
public class DnatRule implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6147665528641871236L;
	
	private String id;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("nat_gateway_id")
	private String natGatewayId;
	
	@JsonProperty("port_id")
	private String portId;
	
	@JsonProperty("internal_service_port")
	private Integer internalServicePort;
	
	@JsonProperty("floating_ip_id")
	private String floatingIpId;
	
	@JsonProperty("external_service_port")
	private Integer externalServicePort;
	
	@JsonProperty("floating_ip_address")
	private String floatingIpAddress;
	
	@JsonProperty("protocol")
	private String protocol;
	
	@JsonProperty("status")
	private Status status;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("admin_state_up")
	private boolean adminStateUp;
	
	
	public  static class DNatRules extends ListResult<DnatRule>{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = -7607242585768409486L;
		
		@JsonProperty("dnat_rules")
		private List<DnatRule> dNatRules;
		
		@Override
		protected List<DnatRule> value() {
			return dNatRules;
		}
		
		
	}
}
