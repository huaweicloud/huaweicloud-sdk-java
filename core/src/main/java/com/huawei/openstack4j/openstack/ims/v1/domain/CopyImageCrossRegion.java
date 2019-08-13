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
public class CopyImageCrossRegion implements ModelEntity {
    private static final long serialVersionUID = 7825255493415049134L;
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
     * 目的区域的Region ID,必选参数
     */
    @JsonProperty("region")
    private String region;
    /**
     * 目的区域的项目名称，必选参数
     */
    @JsonProperty("project_name")
    private String projectName;

    /**
     * IMS服务委托名称，必选参数
     */
    @JsonProperty("agency_name")
    private String agencyName;
}
