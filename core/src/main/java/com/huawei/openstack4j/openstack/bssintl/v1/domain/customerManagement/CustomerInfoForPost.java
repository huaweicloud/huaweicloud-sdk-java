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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.customerManagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoForPost implements ModelEntity
{
   private static final long serialVersionUID = -6881267813327227914L;

   /**
    * Name that has passed the real-name authentication.
    */
   @JsonProperty("name")
   private String name;

   /**
    * Account name.
    */
   @JsonProperty("domainName")
   private String domainName;

   /**
    * Customer ID.
    */
   @JsonProperty("customerId")
   private String customerId;

   /**
    * Time when a customer is associated with a partner.
    */
   @JsonProperty("cooperationTime")
   private String cooperationTime;

   /**
    * Association type.
    */
   @JsonProperty("cooperationType")
   private String cooperationType;

   /**
    * Tag. Fuzzy search is supported.
    */
   @JsonProperty("label")
   private String label;

   /**
    * Customer phone number.
    */
   @JsonProperty("telephone")
   private String telephone;

   /**
    * Real-name authentication status.
    */
   @JsonProperty("verifiedStatus")
   private String verifiedStatus;

   /**
    * Country code, which is the country code prefix of a phone number.
    */
   @JsonProperty("countryCode")
   private String countryCode;

   /**
    * Customer type.
    */
   @JsonProperty("customerType")
   private Integer customerType;

   /**
    * Whether to freeze the account.
    */
   @JsonProperty("isFrozen")
   private Integer isFrozen;
}


