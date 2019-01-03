/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.fgs.v1.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.fgs.v1.domain.triggersEventDatas.EventData;
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
public class FunctionTrigger implements ModelEntity {

    /**
     *
     */
    private static final long serialVersionUID = 3624993008807730558L;

    @JsonProperty("trigger_id")
    private String triggerId;

    @JsonProperty("trigger_type_code")
    private String triggerTypeCode;

    @JsonProperty("event_type_code")
    private String eventTypeCode;

    @JsonProperty("trigger_status")
    private String triggerStatus;

    @JsonProperty("event_data")
    private EventData eventData;

    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;

    @JsonProperty("created_time")
    private String createdTime;

}
