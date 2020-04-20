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
package com.huawei.openstack4j.openstack.bssintl.v1.domain.customerManagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerReq implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327220386L;

    /**
     * HUAWEI CLOUD username of the customer.
     */
    @JsonProperty("domainName")
    private String domainName;

    /**
     * Email address.
     */
    @JsonProperty("email")
    private String email;

    /**
     * Verification code.
     */
    @JsonProperty("verificationCode")
    private String verificationCode;

    /**
     * Unique ID of the user on the third-party system, which is assigned by the partner.
     */
    @JsonProperty("xAccountId")
    private String xAccountId;

    /**
     * Platform ID assigned by Huawei to a partner.
     */
    @JsonProperty("xAccountType")
    private String xAccountType;

    /**
     * password
     */
    @JsonProperty("password")
    private String password;

    /**
     * Two-letter ID representing the country/region of the customer.
     */
    @JsonProperty("domainArea")
    private String domainArea;

    /**
     * Indicates whether to disable the marketing message sending function.
     */
    @JsonProperty("isCloseMarketMs")
    private String isCloseMarketMs;

    /**
     * Association type.
     * 1: Referral.
     */
    @JsonProperty("cooperationType")
    private String cooperationType;
}
