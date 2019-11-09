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
package com.huawei.openstack4j.openstack.eps.v1.domain.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 企业项目管理资源过滤响应
 * @version 版本号, 2019/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EPResourceFilterResponseBase implements ModelEntity
{
    private static final long serialVersionUID = 2569430339586933948L;

    /**
     * 项目ID
     */
    @JsonProperty("project_id")
    private String projectId;

    /**
     * 项目名
     */
    @JsonProperty("project_name")
    private String projectName;

    /**
     * 资源类型
     */
    @JsonProperty("resource_type")
    private String resourceType;

    /**
     * 资源ID
     */
    @JsonProperty("resource_id")
    private String resourceId;

    /**
     * 资源名称
     */
    @JsonProperty("resource_name")
    private String resourceName;

    /**
     * 资源详情
     */
    @JsonProperty("resource_detail")
    private Object resourceDetail;

    /**
     * 企业项目ID
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;
}
