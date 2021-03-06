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
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Nodes implements ModelEntity {
    private static final long serialVersionUID = 4778168617112401595L;

    /**
     * Indicates the node ID
     */
    private String id;

    /**
     * Indicates the node name
     */
    private String name;

    /**
     * Indicates the node type
     */
    private String role;

    /**
     * Indicates the node status
     */
    private String status;

    /**
     * Indicates the AZ
     */
    @JsonProperty("availability_zone")
    private String availabilityZone;

}
