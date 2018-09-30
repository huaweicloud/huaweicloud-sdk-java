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
@JsonRootName("nat_gateway")
public class NatGateWay implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733482289711340339L;
	
	private String id;
	
	private String name;
	
	private String description;

	@JsonProperty("router_id")
	private String routerId;
	
	@JsonProperty("internal_network_id")
	private String internalNetworkId;
	
	private Status status;
	
	private String spec;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("admin_state_up")
	private boolean adminStateUp;
	

	
	public  static class NatGateWays extends ListResult<NatGateWay>{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = -7607242585768409486L;
		
		@JsonProperty("nat_gateways")
		private List<NatGateWay> natGateways;
		
		@Override
		protected List<NatGateWay> value() {
			return natGateways;
		}
		
		
	}
}
