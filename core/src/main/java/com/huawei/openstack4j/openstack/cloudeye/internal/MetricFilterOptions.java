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
package com.huawei.openstack4j.openstack.cloudeye.internal;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.model.cloudeye.OrderType;

/**
 * Created by coa.ke on 6/24/17.
 */
public class MetricFilterOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private MetricFilterOptions() {
	}

	public static MetricFilterOptions create() {
		return new MetricFilterOptions();
	}

	public MetricFilterOptions limit(Integer limit) {
		return add("limit", limit);
	}

	public MetricFilterOptions metricName(String metricName) {
		return add("metric_name", metricName);
	}

	/**
	 * @param start The paging start value in the format: namespace.metric_name.key: value
	 * @return
	 */
	public MetricFilterOptions start(String start) {
		return add("start", start);
	}

	public MetricFilterOptions namespace(String namespace) {
		return add("namespace", namespace);
	}

	public MetricFilterOptions order(OrderType orderType) {
		return add("order", orderType.value());
	}

	public MetricFilterOptions dim(String[] dimValues) {
		if (dimValues != null) {
			Map<String, String> dimsMap = new HashMap<>();
			for (int i = 0; i < dimValues.length; i++) {
				dimsMap.put("dim." + i, dimValues[i]);
			}
			return addAll(dimsMap);
		}
		return this;
	}

	private MetricFilterOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	private MetricFilterOptions addAll(Map<String, String> queryValues) {
		if (queryValues != null) {
			this.queryParams.putAll(queryValues);
		}
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}