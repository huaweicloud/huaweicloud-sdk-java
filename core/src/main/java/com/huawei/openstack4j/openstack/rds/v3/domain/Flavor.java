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

import java.util.List;
import java.util.Map;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flavor implements ModelEntity {
    private static final long serialVersionUID = 7313986492815315019L;

    private String vcpus;

    private Integer ram;

    private String id;

    private String spec_code;

    private String instance_mode;

    private List<String> version_name;

    @JsonProperty("az_status")
    private Map<String, String> az_status;

    @JsonProperty("az_desc")
    private Map<String, String> az_desc;

}
