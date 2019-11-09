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
import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryPostpaidBillRsp implements ModelEntity
{
   private static final long serialVersionUID = -6881267823327226830L;

   /**
    *
    */
   @JsonProperty("billCycle")
   private String billCycle;

   /**
    * Bill amount, which is calculated based on the special commercial discount of the partner.
    */
   @JsonProperty("creditDebtAmount")
   private BigDecimal creditDebtAmount;

   /**
    * Consumption amount, which is calculated based on the special commercial discount of the partner.
    */
   @JsonProperty("consumeAmount")
   private BigDecimal consumeAmount;

   /**
    * Write-off amount (negative value), which is calculated based on the special commercial discount of the partner.
    */
   @JsonProperty("writeoffdebt")
   private BigDecimal writeoffdebt;

   /**
    * Unsubscription amount (negative value), which is calculated based on the special commercial discount of the partner.
    */
   @JsonProperty("unsubscribeAmount")
   private BigDecimal unsubscribeAmount;

   /**
    *
    * Unit.
    */
   @JsonProperty("measureId")
   private Integer measureId;

   /**
    * currency
    */
   @JsonProperty("currency")
   private String currency;

   /**
    * Tax amount, which is the tax amount in the creditDebtAmount field.
    */
   @JsonProperty("taxAmount")
   private BigDecimal taxAmount;

   /**
    * Bill amount that is not settled, which is calculated based on the special commercial discount of the partner.
    */
   @JsonProperty("unclearedAmount")
   private BigDecimal unclearedAmount;

   /**
    * Due date for bills.
    */
   @JsonProperty("dueDate")
   private String dueDate;

   /**
    * Bill list.
    */
   @JsonProperty("billList")
   private List<PostpaidBillInfo> billList;
}


