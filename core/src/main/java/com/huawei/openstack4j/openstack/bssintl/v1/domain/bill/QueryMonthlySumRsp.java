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
public class QueryMonthlySumRsp implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327227225L;

    /**
     * Error code.
     */
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * Error description.
     */
    @JsonProperty("error_msg")
    private String errorMsg;

    /**
     * Currency.
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * Number of the total records.
     */
    @JsonProperty("total_count")
    private Integer totalCount;

    /**
     * Total amount (tax included).
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    /**
     * Total debt.
     */
    @JsonProperty("debt_amount")
    private BigDecimal debtAmount;

    /**
     * Cash coupon amount.
     */
    @JsonProperty("coupon_amount")
    private BigDecimal couponAmount;

    /**
     * Flexi-purchase coupon amount (reserved).
     */
    @JsonProperty("cashcoupon_amount")
    private BigDecimal cashcouponAmount;

    /**
     * Stored-value card amount (reserved).
     */
    @JsonProperty("storedcard_amount")
    private BigDecimal storedcardAmount;

    /**
     * Balance in the cash account.
     */
    @JsonProperty("debit_amount")
    private BigDecimal debitAmount;

    /**
     * Balance in the credit account.
     */
    @JsonProperty("credit_amount")
    private BigDecimal creditAmount;

    /**
     * Unit.
     * 1: Yuan/Dollar
     * 2: Jiao/Dime
     * 3: Fen/Cent
     */
    @JsonProperty("measure_id")
    private Integer measureId;

    /**
     * Record information.
     */
    @JsonProperty("bill_sums")
    private List<BillSumRecordInfo> billSums;
}
