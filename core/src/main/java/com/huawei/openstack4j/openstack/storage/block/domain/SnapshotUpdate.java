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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * Volume snapshot update model
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("snapshot")
public class SnapshotUpdate implements ModelEntity {

	private static final long serialVersionUID = -8178863629502081753L;

	/**
	 * The name of the EVS snapshot. The value can contain a maximum of
	 * 255 bytes.
	 */
	private String name;

	/**
	 * The description of the EVS snapshot. The value can contain a
	 * maximum of 255 bytes.
	 */
	private String description;

	/**
	 * Specifies also the disk name. You can specify either parameter name or
	 * display_name. If both parameters are specified, the name value is used. The
	 * value can contain a maximum of 255 bytes.
	 */
	@JsonProperty("display_name")
	private String displayName;

	/**
	 * Specifies also the description of the EVS snapshot. You can specify either
	 * parameter description or display_description. The value can contain a maximum
	 * of 255 bytes.
	 */
	@JsonProperty("display_description")
	private String displayDescription;
}
