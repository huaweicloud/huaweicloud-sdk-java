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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Model represent attributes of Server Tags
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerTags implements ModelEntity {

    private static final long serialVersionUID = 5294355111374520846L;

    /**
     * 云服务器标签的键。
     * 约束：
     * 最大长度36个unicode字符。key不能为空。
     * 不能包含非打印字符ASCII(0-31)，"=", "*",“<”,“>”,“\”,“,”,“|”,“/”。
     * 同一资源的key值不能重复。
     */
    @JsonProperty("key")
    private String key;

    /**
     * 云服务器标签的值。
     约束：
     每个值最大长度43个unicode字符，可以为空字符串。
     不能包含非打印字符ASCII(0-31)，“=”,“*”,“<”,“>”,“\”,“,”,“|”,“/”。
     */
    @JsonProperty("value")
    private String value;

}
