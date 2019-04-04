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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeeringRequest implements ModelEntity {

    private static final long serialVersionUID = -1652775597460522L;
    /**
     * 对等连接id
     */
    private String id;

    /**
     * 对等连接的名称
     */
    private String name;

    /**
     * 状态位
     */
    @JsonProperty("status")
    private String status;

    /**
     *对等连接发起端vpc信息
     */
    @JsonProperty("request_vpc_info")
    private VpcInfo requestVpcInfo;

    /**
     *对等连接接受端vpc信息
     */
    @JsonProperty("accept_vpc_info")
    private VpcInfo acceptVpcInfo;

}
