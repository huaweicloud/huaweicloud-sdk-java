/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.fgs.v2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FuncVpc implements ModelEntity {
    private static final long serialVersionUID = 8359649576950117556L;

    @JsonProperty("vpc_name")
    private String vpcName;

    @JsonProperty("vpc_id")
    private String vpcId;

    @JsonProperty("subnet_name")
    private String subnetName;

    @JsonProperty("subnet_id")
    private String subnetId;

    @JsonProperty("cidr")
    private String cidr;

    @JsonProperty("gateway")
    private String gateway;
}