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
@JsonRootName("source")
public class Source implements ModelEntity{

	private static final long serialVersionUID = 1L;

	@JsonProperty("domain_id")
	private String domainId;
	
	@JsonProperty("ip_or_domain")
	private String ipOrDomain;
	
	@JsonProperty("origin_type")
	private String originType;
	
	@JsonProperty("active_standby")
	private Integer activeStandby;
	
	public static class Sources extends ListResult<Source> {
		private static final long serialVersionUID = 7666104777418585874L;

		@JsonProperty("sources")
		List<Source> sources;

		@Override
		protected List<Source> value() {
			return sources;
		}

	}
	
	@Builder(toBuilder = true)
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonRootName("origin")
	public static class Origin{

		/**
		 * 
		 */
		@JsonProperty("sources")
		List<Source> sources;

		public List<Source> getSources() {
			return sources;
		}
		
	}
}
