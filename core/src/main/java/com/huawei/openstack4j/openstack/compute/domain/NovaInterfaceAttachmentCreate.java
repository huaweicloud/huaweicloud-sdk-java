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
package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("interfaceAttachment")
public class NovaInterfaceAttachmentCreate implements ModelEntity {

    /**
     * 私有IP。
     * 有port_id时，该参数不起作用，并且有该参数时，必须有net_id。
     * 只有列表中第一个元素有效。传入两个及以上元素会报错。
     */
    @JsonProperty("fixed_ips")
    private List<FixedIp> fixedIps;

    /**
     * Network ID。
     * 有port_id时，该参数不起作用。
     */
    @JsonProperty("net_id")
    private String netId;

    /**
     * Port ID。
     * port_id和net_id不能同时传入。
     */
    @JsonProperty("port_id")
    private String portId;
}
