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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.enquiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryRatingReq implements ModelEntity
{
   private static final long serialVersionUID = -6881267823327224028L;

   /**
    * Project ID.
    */
   @JsonProperty("tenantId")
   private String tenantId;

   /**
    * Region ID.
    */
   @JsonProperty("regionId")
   private String regionId;

   /**
    * AZ ID.
    */
   @JsonProperty("avaliableZoneId")
   private String avaliableZoneId;

   /**
    * Billing mode.
    */
   @JsonProperty("chargingMode")
   private Integer chargingMode;

   /**
    * Order period type.
    */
   @JsonProperty("periodType")
   private Integer periodType;

   /**
    * Number of subscription periods.
    */
   @JsonProperty("periodNum")
   private Integer periodNum;

   /**
    * Expiration date.
    */
   @JsonProperty("periodEndDate")
   private String periodEndDate;

   /**
    * Associated resource ID.
    */
   @JsonProperty("relativeResourceId")
   private String relativeResourceId;

   /**
    * Period type of the associated resource.
    */
   @JsonProperty("relativeResourcePeriodType")
   private Integer relativeResourcePeriodType;

   /**
    * Number of subscriptions.
    */
   @JsonProperty("subscriptionNum")
   private Integer subscriptionNum;

   /**
    * Product information..
    */
   @JsonProperty("productInfos")
   private List<ProductInfoForQueryRating> productInfos;

   /**
    * Inquiry date.UTC time in "yyyy-MM-dd'T'HH:mm:ss'Z'" format, such as 2019-05-06T08:05:01Z.
    */
   @JsonProperty("inquiryTime")
   private String inquiryTime;
}


