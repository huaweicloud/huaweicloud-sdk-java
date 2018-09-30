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
package com.huawei.openstack4j.openstack.networking.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("vpnservice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronVpn  implements ModelEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2332364967106563855L;
	
	@JsonProperty("project_id")
	private String projectId;
	
	@JsonProperty("subnet_id")
	private String subnetId;
	
	@JsonProperty("router_id")
	private String routerId;
	
	@JsonProperty("name")
	private String	name;
	
	@JsonProperty("admin_state_up")
	private boolean	adminStateUp;
	
	@JsonProperty("tenant_id")
	private String	tenantId;
	
	@JsonProperty("description")
	private String	description;
	
	@JsonProperty("vpnservice")
	private Object vpnservice;
	
	@JsonProperty("status")
	private Type status;
	
	@JsonProperty("external_v6_ip")
	private String externalV6Ip;
	
	@JsonProperty("external_v4_ip")
	private String externalV4Ip;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("bandwidth")
	private Integer bandwidth;
	
	public  static class NeutronVpns extends ListResult<NeutronVpn>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8105209424631380782L;
		@JsonProperty("vpnservices")
		private List<NeutronVpn> vpnservices;
		@Override
		protected List<NeutronVpn> value() {
			return vpnservices;
		}
		
		
	}
	
	public enum Type {

		ACTIVE,DOWN,BUILD, ERROR,PENDING_CREATE,PENDING_UPDATE,PENDING_DELETE;
		
		 @JsonValue
		    public String value() {
		        return name().toUpperCase();
		    }

		    @JsonCreator
		    public static Type value(String v)
		    {
		        try {
		            return valueOf(v.toUpperCase());
		        } catch (IllegalArgumentException e) {
		            return null;
		        }
		    }
	}
}
