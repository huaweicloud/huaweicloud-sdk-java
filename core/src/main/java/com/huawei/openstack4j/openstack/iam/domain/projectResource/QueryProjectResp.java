/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.iam.domain.projectResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("project")
public class QueryProjectResp implements ModelEntity {

    private static final long serialVersionUID = -1876643900253395436L;

    /**
     * the id of the project
     */
    private String id;

    /**
     * the name of the project
     */
    private String name;

    /**
     * the description of the project
     */
    private String description;

    /**
     * the domain_id of the project
     */
    @JsonProperty("domain_id")
    private String domainId;

    /**
     * if the project is enabled
     */
    private Boolean enabled;

    /**
     * if the project is a domain
     */
    @JsonProperty("is_domain")
    private Boolean isDomain;

    /**
     * parent id of the project
     */
    @JsonProperty("parent_id")
    private String parentId;

    /**
     * the status of the project
     */
    private String status;
}
