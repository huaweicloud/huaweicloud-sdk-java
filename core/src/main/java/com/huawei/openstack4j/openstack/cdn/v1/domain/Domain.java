/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.                                       
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
package com.huawei.openstack4j.openstack.cdn.v1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("domain")
public class Domain implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	@JsonProperty("domain_name")
	private String domainName;
	
	@JsonProperty("business_type")
	private String businessType;
	
	@JsonProperty("user_domain_id")
	private String userDomainId;
	
	@JsonProperty("domain_status")
	private String domainStatus;
	
	private String cname;
	
	private List<Source> sources;
	
	@JsonProperty("domain_origin_host")
	private DomainOriginHost  domainOriginHost;
	
	@JsonProperty("https_status")
	private Integer httpsStatus;
	
	@JsonProperty("create_time")
	private Long createTime;
	
	@JsonProperty("modify_time")
	private Long modifyTime;
	
	private Integer disabled;
	
	private Integer locked;
	
	@ToString
	public static class Domains extends ListResult<Domain> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5867110128256719487L;
		
		private Integer total;
		
		@JsonProperty("domains")
		List<Domain> domains;
		
		public Integer getTotal() {
			return total;
		}

		@Override
		protected List<Domain> value() {
			return domains;
		}

	}
}
