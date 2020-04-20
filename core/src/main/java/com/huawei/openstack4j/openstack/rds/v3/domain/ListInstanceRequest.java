/*******************************************************************************
 * 	Copyright 2019 ContainX and OpenStack4j
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
package com.huawei.openstack4j.openstack.rds.v3.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListInstanceRequest implements ModelEntity {

    private static final long serialVersionUID = 6584900714496405316L;

    /**
     * Specifies the DB instance ID
     */
    private String id ;

    /**
     * Specifies the DB instance name
     */
    private String name;

    /**
     * Specifies the instance type based query
     */
    private String type;

    /**
     * Specifies the database type
     */
    @JsonProperty("datastore_type")
    private String datastoreType;

    /**
     * Specifies the subnet ID
     */
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * Specifies the index position
     */
    private Integer offset;

    /**
     * Specifies the number of records to be queried
     */
    private Integer limit;
}
