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
public class DomainItemDetail implements ModelEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Domain name to query
     */
    @JsonProperty("domain_name")
    private String domainName;

    /**
     * Peak bandwidth of domain name during query time
     */
    private List<Long> bw;

    /**
     * Total traffic of domain name during query time
     */
    private List<Long> flux;

    /**
     * Peak back-to-source bandwidth of the domain name during query time
     */
    @JsonProperty("bs_bw")
    private List<Long> bsBw;

    /**
     * Back-to-origin traffic of domain names within query time
     */
    @JsonProperty("bs_flux")
    private List<Long> bsFlux;

    /**
     * Number of domain name requests during query time
     */
    @JsonProperty("req_num")
    private List<Long> reqNum;

    /**
     * Number of cache hits for domain names during query time
     */
    @JsonProperty("hit_num")
    private List<Long> hitNum;

    /**
     * Number of back-to-origin during query time
     */
    @JsonProperty("bs_num")
    private List<Long> bsNum;

    /**
     * Number of back-to-origin failures during query time
     */
    @JsonProperty("bs_fail_num")
    private List<Long> bsFailNum;

    /**
     * Hit traffic during query time
     */
    @JsonProperty("hit_flux")
    private List<Long> hitFlux;


}