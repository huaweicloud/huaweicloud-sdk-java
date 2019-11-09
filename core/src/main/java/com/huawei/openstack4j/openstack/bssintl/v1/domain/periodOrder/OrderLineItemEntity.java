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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.periodOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemEntity implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Order ID.
     */
    @JsonProperty("orderLineItemId")
    private String orderLineItemId;

    /**
     * Cloud service type code. For example, the cloud service type code of ECS is hws.service.type.ec2.
     */
    @JsonProperty("cloudServiceType")
    private String cloudServiceType;

    /**
     * Product ID.
     */
    @JsonProperty("productId")
    private String productId;

    /**
     * Product specification description.
     */
    @JsonProperty("productSpecDesc")
    private String productSpecDesc;

    /**
     * Product catalog code.
     */
    @JsonProperty("categoryCode")
    private String categoryCode;

    /**
     * Period type.
     */
    @JsonProperty("periodType")
    private Integer periodType;

    /**
     * Number of periods.
     */
    @JsonProperty("periodNum")
    private Integer periodNum;

    /**
     * Effective time.
     */
    @JsonProperty("validTime")
    private String validTime;

    /**
     * Expiration time.
     */
    @JsonProperty("expireTime")
    private String expireTime;

    /**
     * Number of subscriptions.
     */
    @JsonProperty("subscriptionNum")
    private Integer subscriptionNum;

    /**
     * Order amount (original price).
     */
    @JsonProperty("currency")
    private Double currency;

    /**
     * Order amount after the discount (excluding the vouchers or cards).
     */
    @JsonProperty("currencyAfterDiscount")
    private Double currencyAfterDiscount;

    /**
     * Order amount (list price).
     */
    @JsonProperty("currencyOfficial")
    private Double currencyOfficial;

    /**
     * Order details
     */
    @JsonProperty("amountInfo")
    private AmountInfo amountInfo;

    /**
     * Currency code.
     */
    @JsonProperty("currencyType")
    private String currencyType;
}
