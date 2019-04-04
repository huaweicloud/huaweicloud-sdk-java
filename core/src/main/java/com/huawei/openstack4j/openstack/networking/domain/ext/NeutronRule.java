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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("rule")
public class NeutronRule implements ModelEntity  {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("admin_state_up")
	@Builder.Default
	private Boolean adminStateUp = true;
	
	@JsonProperty("type")
	private Type type;
	
	@JsonProperty("compare_type")
	private String compareType;
	
	@JsonProperty("invert")
	@Builder.Default
	private	 Boolean invert = false;
	
	@JsonProperty("key")
	private String key;
	
	@JsonProperty("value")
	private String value;
	
	public  static class NeutronRules extends ListResult<NeutronRule>{
		
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("rules")
		private List<NeutronRule> rules;
		
		@Override
		protected List<NeutronRule> value() {
			return rules;
		}
		
		
	}
	
	public enum Type {

		HOST_NAME,PATH;

		    @JsonCreator
		    public static Type value(String v)
		    {
		        try {
		            return valueOf(v);
		        } catch (IllegalArgumentException e) {
		            return null;
		        }
		    }
	}
}
