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
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.vpc.v2.contants.RouteType;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("route")
public class Route implements ModelEntity {

    private static final long serialVersionUID = 6908131351845573231L;

    /**
     * 路由id
     */
    private String id;

    /**
     * 路由目的地址CIDR
     */
    private String destination;

    /**
     * 路由下一跳，如果路由
     * 是“peering”类型，填
     * 写vpc peering id
     */
    private String nexthop;

    /**
     * 路由类型
     */
    private RouteType type;

    /**
     * 请求添加路由的vpc
     */
    @JsonProperty("vpc_id")
    private String vpcId;

    /**
     * 项目ID
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    public static class Routes extends ListResult<Route>{

        private static final long serialVersionUID = -3982425518245263388L;

        @JsonProperty("routes")
        private List<Route> Routes;
        @Override
        protected List<Route> value() {
            return Routes;
        }
    }}
