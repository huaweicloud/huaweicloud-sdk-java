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
package com.huawei.openstack4j.openstack.bss.v1.domain.enquiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoForQueryRating implements ModelEntity
{

   private static final long serialVersionUID = -6881267813327224386L;

   /**
    * ID.
    */
   @JsonProperty("id")
   private String id;

   /**
    * Cloud service type code.
    */
   @JsonProperty("cloudServiceType")
   private String cloudServiceType;

   /**
    * Resource type code.
    */
   @JsonProperty("resourceType")
   private String resourceType;

   /**
    * For example, a VM resource specification code is s2.small.1.linux.
    */
   @JsonProperty("resourceSpecCode")
   private String resourceSpecCode;

   /**
    * Resource capacity, which is used together with resouceSizeMeasureId.
    */
   @JsonProperty("resourceSize")
   private Integer resourceSize;

   /**
    * Resource capacity measurement ID.
    */
   @JsonProperty("resouceSizeMeasureId")
   private Integer resouceSizeMeasureId;

   /**
    * Usage factor.
    */
   @JsonProperty("usageFactor")
   private String usageFactor;

   /**
    * Usage value.
    */
   @JsonProperty("usageValue")
   private Double usageValue;

   /**
    * Usage measurement ID.
    */
   @JsonProperty("usageMeasureId")
   private Integer usageMeasureId;

   /**
    * Extended parameter, optional.
    */
   @JsonProperty("extendParams")
   private String extendParams;
}


