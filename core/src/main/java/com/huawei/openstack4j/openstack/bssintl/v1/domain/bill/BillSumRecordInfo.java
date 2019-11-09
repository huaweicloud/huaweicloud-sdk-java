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
public class BillSumRecordInfo implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327227225L;

    /**
     * Customer ID.
     */
    @JsonProperty("customer_id")
    private String customerId;

    /**
     * Resource type code.
     */
    @JsonProperty("resource_type_code")
    private String resourceTypeCode;

    /**
     *
     * Cloud service region.
     */
    @JsonProperty("region_code")
    private String regionCode;

    /**
     * Cloud service type code. For example, the cloud service type code of ECS is hws.service.type.ec2.
     */
    @JsonProperty("cloud_service_type_code")
    private String cloudServiceTypeCode;

    /**
     * Expenditure data collection period. The value is in "YYYY-MM" format.
     */
    @JsonProperty("consume_time")
    private String consumeTime;

    /**
     * Expenditure type.
     */
    @JsonProperty("pay_method")
    private String payMethod;

    /**
     * Consumption amount, that is, the amount deducted from the customer's account. It includes the amount paid using cash coupons.
     */
    @JsonProperty("consume_amount")
    private BigDecimal consumeAmount;

    /**
     * Outstanding amount, that is, the amount due generated when fees are deducted from the customer account and the account balance is insufficient.
     */
    @JsonProperty("debt")
    private BigDecimal debt;

    /**
     * Discounted amount.
     */
    @JsonProperty("discount")
    private BigDecimal discount;

    /**
     * Unit.
     */
    @JsonProperty("measure_id")
    private Integer measureId;

    /**
     * Expenditure type.
     */
    @JsonProperty("bill_type")
    private Integer billType;

    /**
     * Total payment amount distinguished by expenditure type and payment method of an account.
     */
    @JsonProperty("account_details")
    private List<BalanceTypePay> accountDetails;

    /**
     * Discounted amount details.
     */
    @JsonProperty("discount_detail_infos")
    private List<DiscountDetailInfo> discountDetailInfos;

    /**
     * Enterprise project ID.
     */
    @JsonProperty("enterpriseProjectId")
    private String enterpriseProjectId;
}
