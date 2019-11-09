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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResource  implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Customer resource ID.
     */
    @JsonProperty("customerResourceId")
    private String customerResourceId;

    /**
     * Customer ID.
     */
    @JsonProperty("customerId")
    private String customerId;

    /**
     * Cloud service region code, for example, cn-north-1.
     */
    @JsonProperty("regionCode")
    private String regionCode;

    /**
     * AZ code.
     */
    @JsonProperty("azCode")
    private String azCode;

    /**
     * Cloud service type code.
     */
    @JsonProperty("cloudServiceTypeCode")
    private String cloudServiceTypeCode;

    /**
     * Resource type code.
     */
    @JsonProperty("resourceTypeCode")
    private String resourceTypeCode;

    /**
     * Resource ID.
     */
    @JsonProperty("resourceId")
    private String resourceId;

    /**
     * Resource instance name.
     */
    @JsonProperty("resourceName")
    private String resourceName;

    /**
     * Effective time.
     */
    @JsonProperty("startTime")
    private String startTime;

    /**
     * Expiration time.
     */
    @JsonProperty("endTime")
    private String endTime;

    /**
     * Resource status.
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * Specifications code of the pay-per-use resource.
     */
    @JsonProperty("resourceSpecCode")
    private String resourceSpecCode;

    /**
     * Resource capacity.
     */
    @JsonProperty("resourceInfo")
    private String resourceInfo;

    /**
     * Whether the billing mode can be changed from pay-per-use to yearly/monthly.
     */
    @JsonProperty("chargingModeChangeFlag")
    private String chargingModeChangeFlag;

    /**
     * Account type of the last deduction for the resource.
     */
    @JsonProperty("lastDeductType")
    private String lastDeductType;
}
