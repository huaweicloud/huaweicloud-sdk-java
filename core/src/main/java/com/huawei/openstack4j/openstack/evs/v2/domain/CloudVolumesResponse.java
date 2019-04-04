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
package com.huawei.openstack4j.openstack.evs.v2.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CloudVolumesResponse implements ModelEntity
{
    private static final long serialVersionUID = -6628416400615199903L;

    @JsonProperty("volumes")
    private List<CloudVolumeResponse> volumeList;

    @JsonProperty ("count")
    private Integer count;

    /**
     * 云硬盘列表查询位置标记，与响应body中的volumes同级。如果本次查询只返回部分列表信息时，
     * 会返回查询到的当前磁盘mark标记的url，可以继续使用这个url查询剩余列表信息。
     */
    @JsonProperty("volumes_links")
    private List<Map<String,String>> volumesLinks;

}
