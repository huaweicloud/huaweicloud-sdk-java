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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * 企业项目管理资源过滤参数
 * @version 版本号, 2019/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EPResourceFilterRequest extends PageQuery implements ModelEntity
{
    private static final long serialVersionUID = -9009426295007281485L;

    /**
     * 项目ID列表
     */
    private List<String> projects;

    /**
     * 资源类型列表
     */
    @JsonProperty("resource_types")
    private List<String> resourceTypes;

    /**
     * limit
     */
    @JsonProperty("limit")
    private String limitStr;

    /**
     * limit
     */
    @JsonProperty("offset")
    private String offsetStr;

    /**
     * 模糊匹配的字段
     */
    private List<Match> matches;

    /**
     * 是否是console
     */
    private Boolean isConsole;
}
