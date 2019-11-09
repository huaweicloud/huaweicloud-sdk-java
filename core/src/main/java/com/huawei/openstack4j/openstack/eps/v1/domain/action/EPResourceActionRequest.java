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
package com.huawei.openstack4j.openstack.eps.v1.domain.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EPResourceActionRequest implements ModelEntity
{

    /**
     *
     */
    private static final long serialVersionUID = 1169444698047565837L;

    /**
     * 操作标识
     */
    private String action;

    /**
     * 项目ID
     */
    @JsonProperty("project_id")
    private String projectId;

    /**
     * 资源类型
     */
    @JsonProperty("resource_type")
    private String resourceType;

    /**
     *  主资源ID
     */
    @JsonProperty("resource_id")
    private String resourceId;

    /**
     * 关联迁移
     */
    private Boolean associated;

    /**
     * region信息
     */
    @JsonProperty("region_id")
    private String regionId;

    /**
     * 扩展参数
     */
    @JsonProperty("extend_param")
    private MigrateExtendParam extendParam;


}
