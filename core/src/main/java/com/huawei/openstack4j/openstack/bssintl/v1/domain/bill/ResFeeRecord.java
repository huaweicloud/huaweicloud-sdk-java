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
public class ResFeeRecord implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327227225L;

    /**
     * Fee generation time.
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * Start time of using the resource corresponding to the fee.
     */
    @JsonProperty("effectiveTime")
    private String effectiveTime;

    /**
     * End time of using the resource corresponding to the fee.
     */
    @JsonProperty("expireTime")
    private String expireTime;

    /**
     * Fee record serial number.
     */
    @JsonProperty("feeId")
    private String feeId;

    /**
     * Product ID.
     */
    @JsonProperty("productId")
    private String productId;

    /**
     * Product name.
     */
    @JsonProperty("productName")
    private String productName;

    /**
     * Order ID.
     */
    @JsonProperty("orderId")
    private String orderId;

    /**
     * Consumption amount, including the amount of cash coupons.
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * Unit.
     */
    @JsonProperty("measureId")
    private Integer measureId;

    /**
     * Usage.
     */
    @JsonProperty("usageAmount")
    private BigDecimal usageAmount;

    /**
     * Usage unit.
     */
    @JsonProperty("usageMeasureId")
    private Integer usageMeasureId;

    /**
     * Package usage.
     */
    @JsonProperty("freeResourceAmount")
    private BigDecimal freeResourceAmount;

    /**
     * Unit (package usage).
     */
    @JsonProperty("freeResourceMeasureId")
    private Integer freeResourceMeasureId;

    /**
     * Cloud service type code.
     */
    @JsonProperty("cloudServiceTypeCode")
    private String cloudServiceTypeCode;

    /**
     * Cloud service region code, for example, cn-north-1.
     */
    @JsonProperty("regionCode")
    private String regionCode;

    /**
     * Resource type code.
     */
    @JsonProperty("resourceTypeCode")
    private String resourceTypeCode;

    /**
     * Payment method.
     */
    @JsonProperty("payMethod")
    private String payMethod;

    /**
     * Project ID.
     */
    @JsonProperty("projectID")
    private String projectID;

    /**
     * Project name.
     */
    @JsonProperty("projectName")
    private String projectName;

    /**
     * Resource tag.
     */
    @JsonProperty("resourcetag")
    private String resourcetag;

    /**
     * Resource name.
     */
    @JsonProperty("resourceName")
    private String resourceName;

    /**
     * Resource ID.
     */
    @JsonProperty("resourceId")
    private String resourceId;

    /**
     * Expenditure type.
     */
    @JsonProperty("feeSourceOperation")
    private Integer feeSourceOperation;

    /**
     * Period type.
     */
    @JsonProperty("periodType")
    private String periodType;

    /**
     * Enterprise project ID.
     */
    @JsonProperty("enterpriseProjectId")
    private String enterpriseProjectId;

    /**
     * Spot instance ID.
     */
    @JsonProperty("spot")
    private String spot;

    /**
     * Reserved instance usage.
     */
    @JsonProperty("rIAmount")
    private BigDecimal rIAmount;

    /**
     * Unit (reserved instance usage)
     */
    @JsonProperty("rIMeasureId")
    private Integer rIMeasureId;

    /**
     * official amount
     */
    @JsonProperty("officialAmount")
    private Integer officialAmount;
}
