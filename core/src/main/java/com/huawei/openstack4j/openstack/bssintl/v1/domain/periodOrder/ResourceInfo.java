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

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResourceInfo implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Record ID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Resource instance ID.
     */
    @JsonProperty("resource_id")
    private String resourceId;

    /**
     * Amount.
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * Measurement unit.
     */
    @JsonProperty("measure_id")
    private String measureId;

    /**
     * Customer ID.
     */
    @JsonProperty("customer_id")
    private String customerId;

    /**
     * Resource type code. For example, the VM resource type code of ECS is hws.resource.type.vm.
     */
    @JsonProperty("resourceType")
    private String resourceType;

    /**
     * Cloud service type code. For example, the cloud service type code of ECS is hws.service.type.ec2.
     */
    @JsonProperty("cloudServiceType")
    private String cloudServiceType;

    /**
     * Cloud service region code, for example, cn-north-1.
     */
    @JsonProperty("regionCode")
    private String regionCode;

    /**
     * ID of the original order corresponding to the unsubscription amount, consumption amount, or unsubscription handling fee.
     */
    @JsonProperty("preOrderId")
    private String preOrderId;
}
