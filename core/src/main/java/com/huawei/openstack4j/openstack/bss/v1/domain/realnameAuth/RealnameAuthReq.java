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
package com.huawei.openstack4j.openstack.bss.v1.domain.realnameAuth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RealnameAuthReq implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Customer ID.
     */
    @JsonProperty("customerId")
    private String customerId;

    /**
     * Authentication method.
     */
    @JsonProperty("identifyType")
    private Integer identifyType;

    /**
     * Certificate type.
     */
    @JsonProperty("verifiedType")
    private Integer verifiedType;

    /**
     * Attachment URL for individual certificate authentication. The URL must be entered in sequence.
     */
    @JsonProperty("verifiedFileURL")
    private List<String> verifiedFileURL;

    /**
     * Name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Certificate number.
     */
    @JsonProperty("verifiedNumber")
    private String verifiedNumber;

    /**
     * Change type.
     */
    @JsonProperty("changeType")
    private Integer changeType;

    /**
     * Platform ID assigned by Huawei to a partner.
     */
    @JsonProperty("xaccountType")
    private String xaccountType;

    /**
     * Bank card information.
     */
    @JsonProperty("bankCardInfo")
    private BankCardInfo bankCardInfo;
}
