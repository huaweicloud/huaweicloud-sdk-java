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
package com.huawei.openstack4j.openstack.fgs.v1.domain.triggersEventDatas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
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
@JsonInclude
public class EventData implements ModelEntity {

    /**
     *
     */
    private static final long serialVersionUID = -4718519746088818991L;


    //OBS
    @JsonProperty("bucket")
    private String bucket;

    @JsonProperty("events")
    private List<String> events;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("suffix")
    private String suffix;

    //cts
    @JsonProperty("name")
    private String name;

    @JsonProperty("operations")
    private List<String> operations;

    //TIMER
    @JsonProperty("schedule_type")
    private String scheduleType;

    @JsonProperty("schedule")
    private String schedule;

    @JsonProperty("user_event")
    private String userEvent;

    //smnsend
    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("remark")
    private String remark;

    //SMN
    @JsonProperty("topic_urn")
    private String topicURN;

    //LTS
    @JsonProperty("log_group_id")
    private String logGroupId;

    @JsonProperty("log_topic_id")
    private String logTopicId;

    @JsonProperty("log_group_name")
    private String logGroupName;

    @JsonProperty("log_topic_name")
    private String logTopicNmae;

    //dms
    @JsonProperty("queue_id")
    private String queueId;

    @JsonProperty("consumer_group_id")
    private String consumerGroupId;

    @JsonProperty("polling_interval")
    private int pollingInterval;

    //dis
    @JsonProperty("stream_name")
    private String streamName;


    @JsonProperty("shard_iterator_type")
    private String shardIteratorType;

    @JsonProperty("batch_size")
    private int batchSize;

    //apig
    @JsonProperty("group_id")
    private String groupID;

    @JsonProperty("env_id")
    private String envID;

    @JsonProperty("env_name")
    private String envName;

    @JsonProperty("path")
    private String path;

    @JsonProperty("auth")
    private String auth;

    @JsonProperty("match_mode")
    private String matchMode;

    @JsonProperty("req_method")
    private String reqMethod;

    @JsonProperty("backend_type")
    private String backendType;

    @JsonProperty("sl_domain")
    private String slDomain;

    @JsonProperty("type")
    private int type;

    @JsonProperty("group_name")
    private String groupName;


}

