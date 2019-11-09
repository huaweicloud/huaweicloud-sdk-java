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
package com.huawei.openstack4j.openstack.bss.v1.domain.periodOrder;

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
public class AmountInfo implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Item
     */
    @JsonProperty("discountList")
    private List<DiscountItem> discountList;

    /**
     * Flexi-purchase coupon amount (reserved).
     */
    @JsonProperty("cashcouponAmount")
    private Double cashcouponAmount;

    /**
     * Cash coupon amount.
     */
    @JsonProperty("couponAmount")
    private Double couponAmount;

    /**
     * Stored-value card amount (reserved).
     */
    @JsonProperty("cardAmount")
    private Double cardAmount;

    /**
     * Handling fee (only for unsubscription orders).
     */
    @JsonProperty("commissionAmount")
    private Double commissionAmount;

    /**
     * Consumptions (only for unsubscription orders).
     */
    @JsonProperty("consumedAmount")
    private Double consumedAmount;
}
