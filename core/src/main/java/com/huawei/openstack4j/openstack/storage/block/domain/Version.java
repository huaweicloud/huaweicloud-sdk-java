/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.                                          
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
package com.huawei.openstack4j.openstack.storage.block.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Volume version model
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Version implements ModelEntity {

	private static final long serialVersionUID = 8734320711608276592L;

	/**
	 * The minimum API version.
	 */
	@JsonProperty("min_version")
	private String minVersion;

	/**
	 * The request message type of the API version.
	 */
	@JsonProperty("media-types")
	private List<MediaType> mediaTypes;

	/**
	 * The URI of the API version.
	 */
	private List<VersionLink> links;

	/**
	 * The ID of the API version.
	 */
	private String id;

	/**
	 * The last time when the API version was updated.
	 */
	private String updated;

	/**
	 * The subversion of the API version.
	 */
	private String version;

	/**
	 * The API version status.
	 */
	private String status;

	/**
	 * List of version
	 * 
	 * @author bill
	 *
	 */
	@Getter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Versions extends ListResult<Version> {

		private static final long serialVersionUID = -1125141687235153543L;

		private List<Version> versions;

		@Override
		protected List<Version> value() {
			return versions;
		}
	}
}
