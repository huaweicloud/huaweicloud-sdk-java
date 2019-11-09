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
package com.huawei.openstack4j.openstack.bss.v1.domain.resource;

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
public class QueryCustomerResourceReq implements ModelEntity
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
     * Queries resource IDs in batches.
     */
    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    /**
     * Resource instance name. Fuzzy search is not supported.
     */
    @JsonProperty("resourceName")
    private String resourceName;

    /**
     * Start time of the validity period.
     */
    @JsonProperty("startTimeBegin")
    private String startTimeBegin;

    /**
     * End time of the validity period.
     */
    @JsonProperty("startTimeEnd")
    private String startTimeEnd;

    /**
     * Current page.
     */
    @JsonProperty("pageNo")
    private Integer pageNo;

    /**
     * Number of records displayed on each page.
     */
    @JsonProperty("pageSize")
    private Integer pageSize;
}
