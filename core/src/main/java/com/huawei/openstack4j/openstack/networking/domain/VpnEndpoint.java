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
@JsonRootName("endpoint_group")
public class VpnEndpoint implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -477991724233967198L;

	@JsonProperty
	private List<String> endpoints;
	@JsonProperty
	private EndpointType type;
	@JsonProperty
	private String name;
	@JsonProperty("tenantId")
	private String tenant_id;
	@JsonProperty("projectId")
	private String project_id;
	@JsonProperty
	private String description;
	@JsonProperty
	private String id;
	
	public  static class VpnEndpoints extends ListResult<VpnEndpoint>{

		@JsonProperty("endpoint_groups")
		private List<VpnEndpoint> endpointGroups;
		/**
		 * 
		 */
		private static final long serialVersionUID = 4981070310993311563L;
		
		@Override
		protected List<VpnEndpoint> value() {
			return endpointGroups;
		}
		
		
	}
	
	public enum EndpointType {

		subnet,cidr;
		
		 @JsonValue
		    public String value() {
		        return name().toLowerCase();
		    }

		    @JsonCreator
		    public static EndpointType value(String v)
		    {
		        try {
		            return valueOf(v.toLowerCase());
		        } catch (IllegalArgumentException e) {
		            return null;
		        }
		    }
	}
	
}
