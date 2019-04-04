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
package com.huawei.openstack4j.openstack.vpc.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;


@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("network_ip_availability")
public class NetworkIpAvailability implements ModelEntity {

    private static final long serialVersionUID = 2365991224868591006L;

    /**
     * 网络ID
     */
    @JsonProperty("network_id")
    private String networkId;

    /**
     * 网络名称
     */
    @JsonProperty("network_name")
    private String networkName;

    /**
     * 项目ID
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * 网络中IP总数
     */
    @JsonProperty("total_ips")
    private Integer totalIps;

    /**
     * 网络中已经使用的IP数目
     */
    @JsonProperty("used_ips")
    private Integer usedIps;

    /**
     * 子网IP使用情况的对象
     */
    @JsonProperty("subnet_ip_availability")
    private List<SubnetIpAvailability> subnetIpAvailability;

}
