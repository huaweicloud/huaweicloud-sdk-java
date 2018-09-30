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

import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.cloudeye.MetricDataService;
import com.huawei.openstack4j.model.cloudeye.*;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricAggregation;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;


public class CloudEyeMetricDataServiceImpl extends BaseCloudEyeServices
		implements MetricDataService {

	@Override
	public MetricAggregation get(String namespace, String metric_name, Date from, Date to, Period period, Filter filter, String[] dimValues) {
		checkNotNull(namespace);
		checkNotNull(metric_name);
		checkNotNull(from);
		checkNotNull(to);
		checkNotNull(period);
		checkNotNull(filter);
		checkNotNull(dimValues);
		Invocation<CloudEyeMetricAggregation> invocation = get(CloudEyeMetricAggregation.class, uri(PATH_METRIC_DATAS));
		Map<String, Object> filters = new HashMap<>();
		filters.put("namespace", namespace);
		filters.put("metric_name", metric_name);
		filters.put("from", from.getTime());
		filters.put("to", to.getTime());
		filters.put("period", period.getCode());
		filters.put("filter", filter.value());
		for (int i = 0; i < dimValues.length; i++) {
			filters.put("dim." + i, dimValues[i]);
		}
		invocation.params(filters);
		return invocation.execute();
	}

	@Override
	public ActionResponse add(List<? extends MetricData> metrics) {
		checkNotNull(metrics);
		return postWithResponse(PATH_METRIC_DATAS).entity(metrics).execute();
	}
}
