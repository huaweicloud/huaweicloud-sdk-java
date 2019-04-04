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

import java.util.Map;

import com.google.common.collect.Maps;

public class SnapshotListOptions {
	private Map<String, Object> options = Maps.newHashMap();

	private SnapshotListOptions() {
	}

	private SnapshotListOptions add(String key, Object value) {
		if (value != null)
			this.options.put(key, value);
		return this;
	}

	public static SnapshotListOptions create() {
		return new SnapshotListOptions();
	}

	public Map<String, Object> getOptions() {
		return this.options;
	}

	public SnapshotListOptions name(String name) {
		return add("name", name);
	}

	public SnapshotListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	public SnapshotListOptions offset(Integer offset) {
		return add("offset", offset);
	}

	/**
	 * @see <a href=
	 *      "https://support.huaweicloud.com/en-us/api-evs/en-us_topic_0051803386.html">https://support.huaweicloud.com/en-us/api-evs/en-us_topic_0051803386.html</a>
	 * @param status
	 * @return
	 */
	public SnapshotListOptions status(String status) {
		return add("status", status);
	}

	public SnapshotListOptions volumeId(String volumeId) {
		return add("volume_id", volumeId);
	}
}
