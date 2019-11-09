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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.periodResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResourceInstance implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Internal ID of the resource to be provisioned.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Resource instance ID.
     */
    @JsonProperty("resource_id")
    private String resourceId;

    /**
     * Resource instance name.
     */
    @JsonProperty("resource_name")
    private String resourceName;

    /**
     * Resource pool region ID of cloud services.
     */
    @JsonProperty("region_code")
    private String regionCode;

    /**
     * Cloud service type code. For example, the cloud service type code of ECS is hws.service.type.ec2.
     */
    @JsonProperty("cloud_service_type_code")
    private String cloudServiceTypeCode;

    /**
     * Resource type code. For example, the VM resource type code of ECS is hws.resource.type.vm.
     */
    @JsonProperty("resource_type_code")
    private String resourceTypeCode;

    /**
     * For example, a VM resource specification code is s2.small.1.linux.
     */
    @JsonProperty("resource_spec_code")
    private String resourceSpecCode;

    /**
     * Resource project ID.
     */
    @JsonProperty("project_code")
    private String projectCode;

    /**
     * Product ID.
     */
    @JsonProperty("product_id")
    private String productId;

    /**
     * Primary resource ID.
     */
    @JsonProperty("main_resource_id")
    private String mainResourceId;

    /**
     * Whether a primary resource.
     */
    @JsonProperty("is_main_resource")
    private Integer isMainResource;

    /**
     * Resource status.
     */
    @JsonProperty("status")
    private Integer status;

    /**
     *
     * Effective time of a resource.
     */
    @JsonProperty("valid_time")
    private String validTime;

    /**
     * Expiration time of a resource.
     */
    @JsonProperty("expire_time")
    private String expireTime;

    /**
     * Next billing policy.
     */
    @JsonProperty("next_operation_policy")
    private Integer nextOperationPolicy;
}
