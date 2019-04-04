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
package com.huawei.openstack4j.openstack.dss.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

 @Getter
 @ToString
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 public class Pool implements ModelEntity{
     /**
      *
      */
     private static final long serialVersionUID = -3974566408735909574L;

     /**
      * {
      *    "name" : "dedicatedStorage01",
      *    "id" : "c950ee97-587c-4f24-8a74-3367e3da570f",
      *    "project_id" : "63d910f2705a487ebe4e1c274748d9e1",
      *    "capacity" : 100,
      *    "type" : "SATA",
      *    "availability_zone" : "AZ1",
      *    "status" : "available",
      *    "created_at" : "2014-12-18T15:57:56.299000",
      *    "total_capacity_gb": 1000,
      *    "used_capacity_gb": 300,
      *    "provisioned_capacity_gb":700,
      *    "max_over_subscription_ratio": 1.0
      * }
      *
      */

     /**
      * 专属分布式存储池名称。
      */
     @JsonProperty("name")
     private String name;

     /**
      * 专属分布式存储池归属的project_id。
      */
     @JsonProperty("project_id")
     private String projectId;

     /**
      * 专属分布式存储池ID。
      */
     @JsonProperty("id")
     private String id;

     /**
      * 申请的专属分布式存储池容量，单位GB。
      */
     @JsonProperty("capacity")
     private Integer capacity;

     /**
      * 专属分布式存储池的存储类型。
      */
     @JsonProperty("type")
     private String type;

     /**
      * 专属分布式存储池所在AZ。
      */
     @JsonProperty("availability_zone")
     private String availabilityZone;

     /**
      * 专属分布式存储池的状态。
      */
     @JsonProperty("status")
     private String status;

     /**
      * 专属分布式存储池的创建时间。
      */
     @JsonProperty("created_at")
     private String createdAt;

     /**
      * 总容量(专属分布式存储池状态为deploying时，无该字段)。
      */
     @JsonProperty("total_capacity_gb")
     private Integer totalCapacityGb;

     /**
      * 已使用容量(专属分布式存储池状态为deploying时，无该字段)。
      */
     @JsonProperty("used_capacity_gb")
     private Integer usedCapacityGb;

     /**
      * 已分配容量(专属分布式存储池状态为deploying时，无该字段)。
      */
     @JsonProperty("provisioned_capacity_gb")
     private Integer provisionedCapacityGb;

     /**
      * 超分比(专属分布式存储池状态为deploying时，无该字段)。
      */
     @JsonProperty("max_over_subscription_ratio")
     private Float maxOverSubscriptionRatio;

 }
