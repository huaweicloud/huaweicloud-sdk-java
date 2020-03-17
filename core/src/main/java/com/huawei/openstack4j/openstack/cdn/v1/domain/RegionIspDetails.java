/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.cdn.v1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegionIspDetails implements ModelEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Query region information
     */
    private String region;

    /**
     * Query ISP information
     */
    private String isp;

    /**
     * Traffic information
     */
    private List<Long> flux;

    /**
     * Peak bandwidth information
     */
    private List<Long> bw;

    /**
     * Request Number
     */
    @JsonProperty("req_num")
    private List<Long> reqNum;


}