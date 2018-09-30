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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.LifeTime;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.Pfs;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("ipsecpolicy")
public class NeutronIpsecPolicy implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2420854373767223137L;

	@JsonProperty
	private String name;
	@JsonProperty
	@Builder.Default
	private Pfs pfs = Pfs.group5;
	@JsonProperty("auth_algorithm")
	private String authAlgorithm;
	@JsonProperty
	private String description;
	@JsonProperty("encapsulation_mode")
	private String encapsulationMode;
	@JsonProperty("encryption_algorithm")
	private String encryptionAlgorithm;
	@JsonProperty
	private LifeTime lifetime;
	@JsonProperty
	private String tenant_id;
	@JsonProperty("transform_protocol")
	@Builder.Default
	private String transformProtocol = "esp";
	@JsonProperty("project_id")
	private String projectId;
	/*@JsonProperty
	private Integer value;
	@JsonProperty
	private String units;*/
	
	private String id;
	
	public  static class NeutronIpsecPolicies extends ListResult<NeutronIpsecPolicy>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1484299974166197647L;
		private List<NeutronIpsecPolicy> ipsecpolicies;
		@Override
		protected List<NeutronIpsecPolicy> value() {
			return ipsecpolicies;
		}
		
		
	}
	
	
	
}
