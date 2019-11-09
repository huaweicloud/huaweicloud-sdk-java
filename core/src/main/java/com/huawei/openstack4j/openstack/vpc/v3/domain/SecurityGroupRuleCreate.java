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

package com.huawei.openstack4j.openstack.vpc.v3.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder (toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName ("security_group_rule")
public class SecurityGroupRuleCreate implements ModelEntity
{
    private static final long serialVersionUID = -3088073859829389896L;

    @JsonProperty ("project_id")
    private String projectId;

    @JsonProperty ("security_group_id")
    private String securityGroupId;

    @JsonProperty ("remote_group_id")
    private String remoteGroupId;

    @JsonProperty ("remote_ip_prefix")
    private String remoteIpPrefix;

    private String direction;

    private String protocol;

    @JsonProperty ("port_range_min")
    private Integer portRangeMin;

    @JsonProperty ("port_range_max")
    private Integer portRangeMax;

    @JsonProperty ("remote_address_group_id")
    private String remoteAddressGroupId;

    private String description;

    @JsonProperty ("ethertype")
    private String etherType;

    private String action;

    private Integer priority;
}
