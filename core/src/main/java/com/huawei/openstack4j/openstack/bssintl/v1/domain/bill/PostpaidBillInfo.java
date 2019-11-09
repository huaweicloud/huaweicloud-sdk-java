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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostpaidBillInfo implements ModelEntity
{
   private static final long serialVersionUID = -6881267823327220457L;

   /**
    *Bill type.
    */
   @JsonProperty("billType")
   private String billType;

   /**
    * Cloud service type code. For example, the cloud service type code of ECS is hws.service.type.ec2.
    */
   @JsonProperty("cloudServiceTypeCode")
   private String cloudServiceTypeCode;

   /**
    * Resource type code. For example, the VM resource type code of ECS is hws.resource.type.vm.
    */
   @JsonProperty("resourceTypeCode")
   private String resourceTypeCode;

   /**
    * Billing mode.
    */
   @JsonProperty("payMethod")
   private String payMethod;

   /**
    * Transaction amount/unsubscription amount/refund amount of the customer, including the vouchers, flexi-purchase coupons, reserved flexi-purchase coupons, and stored-value cards.
    */
   @JsonProperty("creditDebtAmount")
   private BigDecimal creditDebtAmount;

   /**
    * Transaction amount/unsubscription amount/refund amount of the customer, not including the vouchers, flexi-purchase coupons, reserved flexi-purchase coupons, or stored-value cards.
    */
   @JsonProperty("customerAmountDue")
   private BigDecimal customerAmountDue;

   /**
    * Settlement product type.
    */
   @JsonProperty("settlementType")
   private Integer settlementType;

   /**
    * Partner discount percentage.
    */
   @JsonProperty("partnerRatio")
   private BigDecimal partnerRatio;

   /**
    * Amount that the partner needs to refund/Amount that the partner has refund
    */
   @JsonProperty("partnerAmount")
   private BigDecimal partnerAmount;

   /**
    * Yearly/monthly unit.
    */
   @JsonProperty("periodType")
   private Integer periodType;

   /**
    * Number of yearly/month periods.
    */
   @JsonProperty("periodNum")
   private Integer periodNum;

   /**
    * Product category code.
    */
   @JsonProperty("categoryCode")
   private String categoryCode;
}


