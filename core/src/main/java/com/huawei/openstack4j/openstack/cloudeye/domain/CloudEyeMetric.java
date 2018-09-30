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
package com.huawei.openstack4j.openstack.cloudeye.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.cloudeye.Metric;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeMetric implements Metric {

	private static final long serialVersionUID = -4166055531397254389L;

	String namespace;
	List<CloudEyeMetricDemension> dimensions;
	@JsonProperty("metric_name")
	String metricName;
	String unit;

	@JsonIgnore
	public String getId() {
		StringBuffer sb = new StringBuffer();
		sb.append(namespace).append(".").append(metricName);
		for (CloudEyeMetricDemension dimension : dimensions) {
			sb.append(".").append(dimension.getName()).append(":").append(dimension.getValue());
		}
		return sb.toString();
	}

	public static class CloudEyeMetrics extends ListResult<CloudEyeMetric> {

		private static final long serialVersionUID = 2211086062776417518L;

		@JsonProperty("metrics")
		protected List<CloudEyeMetric> list;
		

		@Override
		public List<CloudEyeMetric> value() {
			return list;
		}
	}
}
