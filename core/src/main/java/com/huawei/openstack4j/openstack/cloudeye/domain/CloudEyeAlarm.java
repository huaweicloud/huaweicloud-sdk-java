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
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.cloudeye.AlarmState;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeAlarm implements Alarm {
    private static final long serialVersionUID = 4145594080416434613L;
    @JsonProperty("alarm_name")
    String alarmName;
    @JsonProperty("alarm_description")
    String alarmDescription;
    CloudEyeMetric metric;
    CloudEyeAlarmCondition condition;
    @JsonProperty("alarm_actions")
    List<CloudEyeAlarmAction> alarmActions;
    @JsonProperty("insufficientdata_actions")
    List<CloudEyeAlarmAction> insufficientdataActions;
    @JsonProperty("ok_actions")
    List<CloudEyeAlarmAction> okActions;
    @JsonProperty("alarm_enabled")
    Boolean alarmEnabled;
    @JsonProperty("alarm_action_enabled")
    Boolean alarmActionEnabled;
    @JsonProperty("alarm_id")
    String alarmId;
    @JsonProperty("update_time")
    Date updateTime;
    @JsonProperty("alarm_state")
    AlarmState alarmState;

    public static class CloudEyeAlarms extends ListResult<CloudEyeAlarm> {

        private static final long serialVersionUID = 2211086062776417518L;

        @JsonProperty("metric_alarms")
        protected List<CloudEyeAlarm> list;

        @Override
        public List<CloudEyeAlarm> value() {
            return list;
        }
    }
}
