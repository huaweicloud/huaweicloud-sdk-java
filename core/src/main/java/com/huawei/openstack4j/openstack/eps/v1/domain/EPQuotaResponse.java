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
package com.huawei.openstack4j.openstack.eps.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 企业项目配额查询响应
 * @version 版本号, 2019/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EPQuotaResponse implements ModelEntity
{
    private static final long serialVersionUID = 7600354597130321440L;

    /**
     * 配额
     */
    private EPQuotaResponseBase quotas;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EPQuotaResponseBase implements Serializable
    {
        private static final long serialVersionUID = 8428968802591767074L;

        /**
         * 资源配额列表
         */
        @JsonProperty("resources")
        private List<EPQuotaTypeResponseBase> resources = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EPQuotaTypeResponseBase implements Serializable
    {
        private static final long serialVersionUID = 8428968802591767074L;

        /**
         * 资源类型
         */
        @JsonProperty("type")
        private String type;

        /**
         * 已使用配额
         */
        @JsonProperty("used")
        private int used;

        /**
         * 配额
         */
        @JsonProperty("quota")
        private int quota;
    }
}
