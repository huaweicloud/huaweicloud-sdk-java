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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("whitelist")
public class NeutronWhitelist {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("listener_id")
	private String listenerId;
	
	@JsonProperty("enable_whitelist")
	private Boolean enableWhitelist;
	
	@JsonProperty("whitelist")
	private String whitelist;
	
	public  static class NeutronWhitelists extends ListResult<NeutronWhitelist>{

		private static final long serialVersionUID = 8577126716272008979L;
		
		@JsonProperty("whitelists")
		List<NeutronWhitelist> whitelists;
		
		@Override
		protected List<NeutronWhitelist> value() {
			return whitelists;
		}
		
		
	}
}
