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
public class CustomerOrderForOrderList implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Order ID
     */
    @JsonProperty("orderId")
    private String orderId;

    /**
     * Parent order ID (order ID before splitting).
     */
    @JsonProperty("baseOrderId")
    private String baseOrderId;

    /**
     * HUAWEI CLOUD operation entity ID.
     */
    @JsonProperty("beId")
    private String beId;

    /**
     * Customer ID.
     */
    @JsonProperty("customerId")
    private String customerId;

    /**
     * Customer order source type.
     */
    @JsonProperty("sourceType")
    private Integer sourceType;

    /**
     * Order status.
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * Order type.
     */
    @JsonProperty("orderType")
    private Integer orderType;

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
     * Order amount unit.
     */
    @JsonProperty("measureId")
    private Integer measureId;

    /**
     * Amount unit name.
     */
    @JsonProperty("measureName")
    private String measureName;

    /**
     * Creation time.
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * Payment time.
     */
    @JsonProperty("paymentTime")
    private String paymentTime;

    /**
     * Last status update time.
     */
    @JsonProperty("lastUpdateTime")
    private String lastUpdateTime;

    /**
     * Requiring approval or not.
     */
    @JsonProperty("needAudit")
    private Boolean needAudit;

    /**
     * Currency code.
     */
    @JsonProperty("currencyType")
    private String currencyType;

    /**
     * Contract ID.
     */
    @JsonProperty("contractId")
    private String contractId;

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
     * Cloud service type code.
     */
    @JsonProperty("serviceType")
    private String serviceType;
}
