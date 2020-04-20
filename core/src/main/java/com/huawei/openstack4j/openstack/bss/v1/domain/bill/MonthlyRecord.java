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
package com.huawei.openstack4j.openstack.bss.v1.domain.bill;

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
public class MonthlyRecord implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327227225L;

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
     * Resource type code. For example, the VM resource type code of ECS is hws.resource.type.vm.
     */
    @JsonProperty("resourceTypeCode")
    private String resourceTypeCode;

    /**
     * Resource instance ID.
     */
    @JsonProperty("resInstanceId")
    private String resInstanceId;

    /**
     * Resource name.
     */
    @JsonProperty("resourceName")
    private String resourceName;

    /**
     * Resource tag.
     */
    @JsonProperty("resourceTag")
    private String resourceTag;

    /**
     * Consumption amount of a cloud service, including the amount of cash coupons.
     */
    @JsonProperty("consumeAmount")
    private BigDecimal consumeAmount;

    /**
     * Expenditure month.
     */
    @JsonProperty("cycle")
    private String cycle;

    /**
     * Unit.
     */
    @JsonProperty("measureId")
    private Integer measureId;

    /**
     * Enterprise project ID.
     */
    @JsonProperty("enterpriseProjectId")
    private String enterpriseProjectId;

    /**
     * Billing mode.
     */
    @JsonProperty("payMethod")
    private String payMethod;

    /**
     * Official amount
     */
    @JsonProperty("officialAmount")
    private BigDecimal officialAmount;
}
