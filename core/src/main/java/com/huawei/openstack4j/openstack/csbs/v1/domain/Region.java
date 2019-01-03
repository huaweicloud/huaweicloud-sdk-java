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
package com.huawei.openstack4j.openstack.csbs.v1.domain;

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
public class Region implements ModelEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3721428916609749033L;
	/**
	 * 云服务所在的区域
	 */
	private String name;
	/**
	 * 支持复制的目标区域列表
	 */
	@JsonProperty("replication_destinations")
	private List<String> destinations;

	public static class Regions extends ListResult<Region> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7607242585768409486L;

		@JsonProperty("regions")
		private List<Region> regions;

		public List<Region> getRegions() {
			return regions;
		}
		@Override
		protected List<Region> value() {
			return regions;
		}

	}
}
