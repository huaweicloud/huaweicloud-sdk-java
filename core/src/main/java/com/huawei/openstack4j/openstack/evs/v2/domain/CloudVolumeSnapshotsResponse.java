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
public class CloudVolumeSnapshotsResponse implements ModelEntity
{
    private static final long serialVersionUID = 4161925387465232007L;

    @JsonProperty("snapshots")
    private List<CloudVolumeSnapshot> snapshotList;

    @JsonProperty ("count")
    private Integer count;

    /**
     * 云硬盘快照列表查询位置标记，与响应body中的snapshots同级，
     * 当查询时指定limit时会返回该字段，返回该字段表示本次查询只查出了部分云硬盘快照信息。
     */
    @JsonProperty("snapshots_links")
    private List<Map<String,String>> snapshotsLinks;
}
