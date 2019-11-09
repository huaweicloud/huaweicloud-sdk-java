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
package com.huawei.openstack4j.openstack.bss.v1.domain.customerManagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckUserRsp implements ModelEntity
{
    private static final long serialVersionUID = -6881267823327224583L;

    /**
     * Error description.
     */
    @JsonProperty("error_msg")
    private String errorMsg;

    /**
     * Error code.
     */
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * 0: The account name, mobile number, or email address does not exist.
     * 1: The account name, mobile number, or email address already exists.
     */
    @JsonProperty("status")
    private String status;

    /**
     * Whether the number of verification code sending times reaches the upper limit (15 times per hour, 60 times per day)
     */
    @JsonProperty("uplimit")
    private String uplimit;
}
