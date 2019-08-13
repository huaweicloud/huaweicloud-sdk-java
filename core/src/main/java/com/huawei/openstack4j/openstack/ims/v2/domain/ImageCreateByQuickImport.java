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
package com.huawei.openstack4j.openstack.ims.v2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @date: 2019/7/1 10:50
 * @Description:
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageCreateByQuickImport implements ModelEntity {
    private static final long serialVersionUID = 7020759569500350388L;
    /**
     *镜像名称，必选参数
     */
    @JsonProperty("name")
    private String name;

    /**
     *镜像描述信息
     */
    @JsonProperty("description")
    private String description;

    /**
     *操作系统类型，必选参数
     */
    @JsonProperty("os_type")
    private String osType;

    /**
     *操作系统版本，必选参数
     */
    @JsonProperty("os_version")
    private String osVersion;

    /**
     *OBS桶中外部镜像文件地址，必选参数
     */
    @JsonProperty("image_url")
    private String imageUrl;

    /**
     *最小系统盘大小，必选参数
     */
    @JsonProperty("min_disk")
    private Integer minDisk;

    /**
     *镜像标签列表
     */
    @JsonProperty("tags")
    private List<String> tags;

    /**
     * 新规范的镜像标签列表
     */
    @JsonProperty("image_tags")
    private List<Map> imageTags;

    /**
     * 镜像的类型。
     */
    @JsonProperty("type")
    private String type;

    /**
     * 表示当前镜像所属的企业项目。
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;

}
