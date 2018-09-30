package com.huawei.openstack4j.openstack.map.reduce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
  @JsonProperty("key")
 
   String tagKey;

  @JsonProperty("value")
 
   String tagValue;

  @JsonProperty("cluster_id")
   String clusterId;

  @JsonProperty("last_update_time")
   long lastUpdateTime;

  @JsonProperty("create_time")
   long createTime;


 
}
