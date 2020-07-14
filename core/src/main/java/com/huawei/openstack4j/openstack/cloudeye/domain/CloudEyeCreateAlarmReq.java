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
 import com.huawei.openstack4j.model.ModelEntity;
 import lombok.*;

 import java.util.List;

 @Getter
 @ToString
 @Builder(toBuilder = true)
 @NoArgsConstructor
 @JsonIgnoreProperties(ignoreUnknown = true)
 public class CloudEyeCreateAlarmReq implements ModelEntity {
     private static final long serialVersionUID = -1524440551458098127L;

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

     @JsonProperty("alarm_level")
     Integer alarmLevel;

     public CloudEyeCreateAlarmReq (String alarmName, String alarmDescription, CloudEyeMetric metric, CloudEyeAlarmCondition condition, List<CloudEyeAlarmAction> alarmActions, List<CloudEyeAlarmAction> insufficientdataActions,
     List<CloudEyeAlarmAction> okActions, Boolean alarmEnabled, Boolean alarmActionEnabled, Integer alarmLevel) {
         this.alarmName = alarmName;
         this.alarmDescription = alarmDescription;
         this.metric = metric;
         this.condition = condition;
         this.alarmActions = alarmActions;
         this.insufficientdataActions = insufficientdataActions;
         this.okActions = okActions;
         this.alarmActionEnabled = alarmActionEnabled;
         this.alarmEnabled = alarmEnabled;
         this.alarmLevel = alarmLevel;
     }
 }
