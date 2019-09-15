/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import com.huawei.openstack4j.openstack.common.ListResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("cdn_ip")
public class CdnIP implements ModelEntity {

    private static final long serialVersionUID = 1L;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("region")
    private String region;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("isp")
    private String isp;

    @JsonProperty("belongs")
    private String belongs;

    @ToString
    public static class CdnIPs extends ListResult<CdnIP> {

        /**
         *
         */
        private static final long serialVersionUID = 5867110128256719487L;


        @JsonProperty("cdn_ips")
        List<CdnIP> CdnIPs;


        @Override
        protected List<CdnIP> value() {
            return CdnIPs;
        }

    }

}
