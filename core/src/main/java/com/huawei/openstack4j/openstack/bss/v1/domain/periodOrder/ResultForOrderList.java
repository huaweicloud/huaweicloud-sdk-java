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
package com.huawei.openstack4j.openstack.bss.v1.domain.periodOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultForOrderList implements ModelEntity
{
    private static final long serialVersionUID = -6881267813327224789L;

    /**
     * Number of records per page.
     */
    @JsonProperty("pageSize")
    private Integer pageSize;

    /**
     * Current page number.
     */
    @JsonProperty("pageIndex")
    private Integer pageIndex;

    /**
     * Number of records that match the query conditions.
     */
    @JsonProperty("totalSize")
    private Integer totalSize;

    /**
     * Order details.
     */
    @JsonProperty("orderInfos")
    private List<CustomerOrderForOrderList> orderInfos;
}
