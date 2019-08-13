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
package com.huawei.openstack4j.openstack.ims.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CopyImageInRegion implements ModelEntity {
    private static final long serialVersionUID = -490230692475582400L;
    /**
     * 镜像名称，必选参数
     */
    @JsonProperty("name")
    private String name;
    /**
     * 镜像描述信息，非必选参数
     */
    @JsonProperty("description")
    private String description;
    /**
     * 加密密钥。默认为空，非必选参数
     */
    @JsonProperty("cmk_id")
    private String cmkId;
    /**
     * 表示当前镜像所属的企业项目，非必选参数
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;
}
