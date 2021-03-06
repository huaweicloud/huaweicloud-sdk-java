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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.cloudeye.Condition;

import lombok.*;


@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeAlarmCondition implements Condition {
    private static final long serialVersionUID = 5719142668031530589L;
    Integer period;
    String filter;
    @JsonProperty("comparison_operator")
    String comparisonOperator;
    Number value;
    String unit;
    Integer count;

    public CloudEyeAlarmCondition (Integer period, String filter, String comparisonOperator, Number value, String unit, Integer count) {
        this.period = period;
        this.filter = filter;
        this.comparisonOperator = comparisonOperator;
        this.value = value;
        this.unit = unit;
        this.count = count;
    }
}

