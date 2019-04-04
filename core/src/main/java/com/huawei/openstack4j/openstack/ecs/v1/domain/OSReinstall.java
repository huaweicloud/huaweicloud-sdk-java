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
package com.huawei.openstack4j.openstack.ecs.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("os-reinstall")
public class OSReinstall implements ModelEntity {

    private static final long serialVersionUID = 4515781290460219633L;

    /**
     * 云服务器管理员帐户的初始登录密码
     */
    @JsonProperty("adminpass")
    private String adminPass;

    /**
     *密钥名称
     */
    @JsonProperty("keyname")
    private String keyName;

    /**
     *用户ID
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 重装云服务器的元数据
     */
    private OSReinstallMetadata metadata;

}
