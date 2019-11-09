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
package com.huawei.openstack4j.openstack.eps.v1.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业项目对外实体类
 * 2019/7/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EPResponseBase implements ModelEntity
{
    private static final long serialVersionUID = 4302271824759646111L;

    /**
     * 企业项目ID
     */
    private String id;

    /**
     * 企业项目名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private int status;

    /**
     * 创建时间
     */
    @JsonProperty ("created_at")
    private String createdAt;

    /**
     * 更新时间
     */
    @JsonProperty ("updated_at")
    private String updatedAt;
}
