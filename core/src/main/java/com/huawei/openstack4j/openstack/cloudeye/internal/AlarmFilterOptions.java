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

import com.huawei.openstack4j.model.cloudeye.OrderType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coa.ke on 6/24/17.
 */
public class AlarmFilterOptions {
    private Map<String, Object> queryParams = Maps.newHashMap();

    private AlarmFilterOptions() {
    }

    public static AlarmFilterOptions create() {
        return new AlarmFilterOptions();
    }

    public AlarmFilterOptions limit(Integer limit) {
        return add("limit", limit);
    }


    /**
     * @param start The paging start value in the format: namespace.metric_name.key: value
     * @return
     */
    public AlarmFilterOptions start(String start) {
        return add("start", start);
    }

    public AlarmFilterOptions order(OrderType orderType) {
        return add("order", orderType.value());
    }

    private AlarmFilterOptions add(String param, Object value) {
        if (value != null)
            this.queryParams.put(param, value);
        return this;
    }

    public Map<String, Object> getOptions() {
        return queryParams;
    }
}