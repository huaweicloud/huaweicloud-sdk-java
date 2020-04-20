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
package com.huawei.openstack4j.openstack.bss.v1.domain.customerManagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryCustomerInfoListReq implements ModelEntity
{
   private static final long serialVersionUID = -6881267813327224783L;

   /**
    * Account name.
    */
   @JsonProperty("domainName")
   private String domainName;

   /**
    * Real-name authentication name.
    */
   @JsonProperty("name")
   private String name;

   /**
    * Page to be queried.
    */
   @JsonProperty("offset")
   private Integer offset;

   /**
    * Number of records on each page.
    */
   @JsonProperty("limit")
   private Integer limit;

   /**
    * Tag. Fuzzy search is supported.
    */
   @JsonProperty("label")
   private String label;

   /**
    * Association type.
    */
   @JsonProperty("cooperationType")
   private String cooperationType;

   /**
    * Start time of the association time range (UTC time).The value is in "yyyy-MM-dd 'T' HH:mm:ss 'Z'" format, such as 2019-05-06T08:05:01Z.
    */
   @JsonProperty("cooperationTimeStart")
   private String cooperationTimeStart;

   /**
    * End time of the association time range (UTC time). The value is in "yyyy-MM-dd 'T' HH:mm:ss 'Z'" format, such as 2019-05-06T08:05:01Z.
    */
   @JsonProperty("cooperationTimeEnd")
   private String cooperationTimeEnd;

   /**
    * Customer domain ID.
    */
   @JsonProperty("customerId")
   private String customerId;
}


