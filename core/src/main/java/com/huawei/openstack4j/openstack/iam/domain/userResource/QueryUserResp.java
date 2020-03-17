/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.iam.domain.userResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;


/**
 * User model class for iam/v3
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("user")
public class QueryUserResp implements ModelEntity {
    private static final long serialVersionUID = 1L;

    /**
     * User name.
     */
    @JsonProperty
    private String name;

    /**
     * Domain ID.
     */
    @JsonProperty("domain_id")
    private String domainId;

    /**
     * User email.
     */
    private String email;

    /**
     * The flag to check if the user is enabled.
     */
    private Boolean enabled;

    /**
     * User ID.
     */
    private String id;


    /**
     * The phone number of user.
     */
    private String phone;

    /**
     * The flag to check wheather the user need to change the password.
     */
    @JsonProperty("pwd_status")
    private Boolean pwdStatus;

    /**
     * The areacode for phone number.
     */
    private String areacode;

    /**
     * The Xuser ID.
     */
    @JsonProperty("xuser_id")
    private String xuserId;

    /**
     * The Xuser type.
     */
    @JsonProperty("xuser_type")
    private String xuserType;

    /**
     * The time when user is updated.
     */
    @JsonProperty("update_time")
    private String updateTime;

    /**
     * The links.
     */
    private Map<String, String> links;
}
