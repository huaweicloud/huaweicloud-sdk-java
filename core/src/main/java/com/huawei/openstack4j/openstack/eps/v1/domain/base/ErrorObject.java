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
 *
 * 错误
 * @version 版本号, 2019/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject implements ModelEntity
{
    private static final long serialVersionUID = 1787821030469482864L;

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
     * 错误码
     */
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * 错误消息
     */
    @JsonProperty("error_msg")
    private String errorMsg;

    public boolean isMatch(String resourceType, String projectId)
    {
        if (resourceType.equals(this.resourceType) && projectId.equals(this.projectId))
        {
            return true;
        }
        return false;
    }
}
