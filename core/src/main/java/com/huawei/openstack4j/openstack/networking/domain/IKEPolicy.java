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
@JsonRootName("ikepolicy")
public class IKEPolicy implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5424079089711176060L;

	@JsonProperty
	private String name;
	@JsonProperty("auth_algorithm")
	private String authAlgorithm;	
	@JsonProperty
	private String description;
	@JsonProperty("encryption_algorithm")
	private String encryptionAlgorithm;
	@JsonProperty("ike_version")
	@Builder.Default
	private IkeVersion ikeVersion = IkeVersion.V1;
	@JsonProperty
	private LifeTime lifetime;
	@JsonProperty
	@Builder.Default
	private Pfs pfs = Pfs.group5;
	@JsonProperty("phase1_negotiation_mode")
	private String phase1NegotiationMode;	
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty
	private String value;
	@JsonProperty
	private String units;
	@JsonProperty("project_id")
	private String projectId;
	@JsonProperty
	private String id;
	
	public  static class IKEPolicies extends ListResult<IKEPolicy>{
		/**
		 * 
		 */
		private static final long serialVersionUID = -38195172842707313L;
		@JsonProperty
		private List<IKEPolicy>	ikePolicies;
		@Override
		protected List<IKEPolicy> value() {
			return ikePolicies;
		}
		
		
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonRootName("lifeTime")
	public static class LifeTime{
		
		private String units;
		private Integer value;
			
		public String getUnits() {
			return units;
		}

		public void setUnits(String units) {
			this.units = units;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		
		
		
	}
	
	public enum IkeVersion {

		V1,V2;
		
		 @JsonValue
		    public String value() {
		        return name().toLowerCase();
		    }

		    @JsonCreator
		    public static IkeVersion value(String v)
		    {
		        try {
		            return valueOf(v.toLowerCase());
		        } catch (IllegalArgumentException e) {
		            return null;
		        }
		    }
	}
	
	public enum Pfs {

		group2,group5,group14;
		
		 @JsonValue
		    public String value() {
		        return name().toLowerCase();
		    }

		    @JsonCreator
		    public static Pfs value(String v)
		    {
		        try {
		            return valueOf(v.toLowerCase());
		        } catch (IllegalArgumentException e) {
		            return null;
		        }
		    }
	}
	

}
