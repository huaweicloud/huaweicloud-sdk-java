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
public class Resources implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Resource instance ID.
     */
    @JsonProperty("resourceId")
    private String resourceId;

    /**
     * Cloud service type code.
     */
    @JsonProperty("cloudServiceType")
    private String cloudServiceType;

    /**
     * Cloud service region code, for example, cn-north-1.
     */
    @JsonProperty("regionCode")
    private String regionCode;

    /**
     * Resource type code.
     */
    @JsonProperty("resourceType")
    private String resourceType;

    /**
     * For example, a VM resource specification code is s2.small.1.linux.
     */
    @JsonProperty("resourceSpecCode")
    private String resourceSpecCode;

    /**
     * Resource capacity.
     */
    @JsonProperty("resourceSize")
    private Double resourceSize;

    /**
     * Resource capacity measurement ID.
     */
    @JsonProperty("resouceSizeMeasureId")
    private Integer resouceSizeMeasureId;

    /**
     * Resource provisioning status.
     */
    @JsonProperty("status")
    private Integer status;
}
