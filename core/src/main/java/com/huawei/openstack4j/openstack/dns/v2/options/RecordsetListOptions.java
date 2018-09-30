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
package com.huawei.openstack4j.openstack.dns.v2.options;

import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.model.dns.v2.ZoneType;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class RecordsetListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	/**
	 * setup pagination limit filter option
	 * 
	 * @param limit limit value
	 * @return
	 */
	public RecordsetListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	/**
	 * setup pagination marker of last page
	 * 
	 * @param offset offset value
	 * @return
	 */
	public RecordsetListOptions marker(String marker) {
		return add("marker", marker);
	}
	
	public RecordsetListOptions zoneType(ZoneType zoneType) {
		return add("zone_type", zoneType.value());
	}

	private RecordsetListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static RecordsetListOptions create() {
		return new RecordsetListOptions();
	}
	
}
