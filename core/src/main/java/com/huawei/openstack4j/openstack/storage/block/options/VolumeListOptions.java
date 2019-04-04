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
package com.huawei.openstack4j.openstack.storage.block.options;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import com.google.common.collect.Maps;
import com.huawei.openstack4j.openstack.storage.block.domain.Sort;

public class VolumeListOptions {
	private Map<String, Object> options = Maps.newHashMap();

	private VolumeListOptions() {
	}

	private VolumeListOptions add(String key, Object value) {
		if (value != null)
			this.options.put(key, value);
		return this;
	}

	public static VolumeListOptions create() {
		return new VolumeListOptions();
	}
	
	public Map<String, Object> getOptions() {
		return this.options;
	}

	public VolumeListOptions marker(String marker) {
		return add("marker", marker);
	}

	public VolumeListOptions name(String name) {
		return add("name", name);
	}

	public VolumeListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	public VolumeListOptions sortKey(String sortKey) {
		return add("sort_key", sortKey);
	}

	public VolumeListOptions sortDir(Sort sort) {
		checkArgument(sort != null, "`sort` is required");
		return add("sort_dir", sort.name().toLowerCase());
	}

	public VolumeListOptions offset(Integer offset) {
		return add("offset", offset);
	}

	/**
	 * @see <a href=
	 *      "https://support.huaweicloud.com/en-us/api-evs/en-us_topic_0051803385.html">https://support.huaweicloud.com/en-us/api-evs/en-us_topic_0051803385.html</a>
	 * @param status
	 * @return
	 */
	public VolumeListOptions status(String status) {
		return add("status", status);
	}

	public VolumeListOptions metadata(String metadata) {
		return add("metadata", metadata);
	}

	public VolumeListOptions availabilityZone(String az) {
		return add("availability_zone", az);
	}

	public VolumeListOptions changesSince(String since) {
		return add("changes_since", since);
	}
}
