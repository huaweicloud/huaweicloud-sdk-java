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
package com.huawei.openstack4j.openstack.networking.domain.ext;

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
@JsonRootName("l7policy")
public class NeutronL7Policy implements ModelEntity {
	
	private static final long serialVersionUID = -926545211246868042L;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("admin_state_up")
	private Boolean adminStateUp;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("listener_id")
	private String listenerId;
	
	@JsonProperty("action")
	private String action;
	
	@JsonProperty("redirect_pool_id")
	private String redirectPoolId;

	@JsonProperty("redirect_listener_id")
	private String redirectListenerId;
	
	@JsonProperty("redirect_url")
	private String redirectUrl;
	
	@JsonProperty("rules")
	private List<NeutronRule> rules;
	
	@JsonProperty("position")
	private Integer position;
	
	
	
	
	public  static class NeutronL7Policies extends ListResult<NeutronL7Policy>{
		
		private static final long serialVersionUID = 926442541146868042L;
		
		@JsonProperty("l7policies")
		private List<NeutronL7Policy> L7Policies;

		@Override
		protected List<NeutronL7Policy> value() {
			return L7Policies;
		}
		
	}
}
