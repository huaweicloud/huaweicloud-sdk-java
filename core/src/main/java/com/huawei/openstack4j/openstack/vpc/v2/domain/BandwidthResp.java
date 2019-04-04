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
import com.huawei.openstack4j.openstack.vpc.v2.contants.ShareType;
import com.huawei.openstack4j.openstack.vpc.v2.contants.VirtualChargingMode;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("bandwidth")
public class BandwidthResp implements ModelEntity {

    private static final long serialVersionUID = -1526896640988814973L;

    /**
     * 带宽名称
     */
    private String name;

    /**
     * 带宽大小
     */
    private Integer size;

    /**
     * 带宽唯一标识
     */
    private String id;

    /**
     * 带宽类型
     */
    @JsonProperty("share_type")
    private ShareType shareType;

    /**
     * 带宽对应的弹性公网IP信息
     */
    @JsonProperty("publicip_info")
    private List<PublicIpInfo> publicipInfo;

    /**
     * 项目ID
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * 带宽类型
     */
    @JsonProperty("bandwidth_type")
    private String bandwidthType;

    /**
     * 按流量计费还是按带宽计费
     */
    @JsonProperty("charge_mode")
    private VirtualChargingMode chargeMode;

    /**
     * 账单信息
     */
    @JsonProperty("billing_info")
    private String billingInfo;

    /**
     * 企业项目ID
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;

    public static class BandwidthResps extends ListResult<BandwidthResp>{

        private static final long serialVersionUID = -8374367023990463178L;
        @JsonProperty("bandwidths")
        private List<BandwidthResp> bandwidthResps;
        @Override
        protected List<BandwidthResp> value() {
            return bandwidthResps;
        }
    }
}
