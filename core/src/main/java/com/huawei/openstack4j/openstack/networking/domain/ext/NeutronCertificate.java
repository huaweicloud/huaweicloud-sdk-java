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
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NeutronCertificate implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1626695303826619328L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("type")
	@Builder.Default
	private String type = "server";
	
	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("private_key")
	private String privateKey;
	
	@JsonProperty("certificate")
	private String certificate;
	
	@JsonProperty("createTime")
	private String create_time;
	
	@JsonProperty("updateTime")
	private String update_time;
	
	public  static class NeutronCertificates extends ListResult<NeutronCertificate>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 7825693830214509937L;
		
		@JsonProperty("certificates")
		private List<NeutronCertificate> certificates;

		@Override
		protected List<NeutronCertificate> value() {
			return certificates;
		}
	}
}
