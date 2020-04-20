/*******************************************************************************
 * 	Copyright 2019 ContainX and OpenStack4j
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
package com.huawei.openstack4j.openstack.rds.v3.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeInfo implements ModelEntity {
    private static final long serialVersionUID = -7136655486814418012L;

    /**
     * Specifies the billing mode
     */
    @JsonProperty("charge_mode")
    public String chargeMode;

    /**
     * Specifies the subscription period
     */
    @JsonProperty("period_type")
    public String periodType;

    /**
     * This parameter is valid and mandatory if charge_mode is set to prePaid
     */
    @JsonProperty("period_num")
    public Integer periodNum;

    /**
     * Specifies whether automatic renewal is enabled for yearly/monthly DB instances
     */
    @JsonProperty("is_auto_renew")
    public String isAutoRenew;

    /**
     * Specifies whether the order will be automatically paid after yearly/monthly DB instances are created
     */
    @JsonProperty("is_auto_pay")
    public String isAutoPay;
}
