 /*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.vpc.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

 @Getter
 @ToString
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 public class PublicIpInfo implements ModelEntity{

     private static final long serialVersionUID = 8946938923281179574L;

     /**
      * 带宽对应的弹性公网IP的唯一标识
      */
     @JsonProperty("publicip_id")
     private String publicipId;

     /**
      * 弹性公网IP的类型
      */
     @JsonProperty("publicip_type")
     private String type;

     /**
      * IPv4时是申请到的弹性公网IP地址，IPv6时为IPv6地址对应的IPv4地址
      */
     @JsonProperty("publicip_address")
     private String publicIpAddress;

     /**
      * IPv4时无此字段，IPv6时为申请到的弹性公网IP地址
      */
     @JsonProperty("publicipv6_address")
     private String publicipv6Address;

     /**
      * IP版本信息，取值范围是4和6
      */
     @JsonProperty("ip_version")
     private Integer ipVersion;

 }
