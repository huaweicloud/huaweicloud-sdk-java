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
public class ErrorLogRequest implements ModelEntity {
    private static final long serialVersionUID = -4970678003674173307L;
    /**
     * Specifies the start time in the "yyyy-mm-ddThh:mm:ssZ" format.
     */
    @JsonProperty("start_date")
    private String startDate;
    /**
     * Specifies the end time in the "yyyy-mm-ddThh:mm:ssZ" format.
     */
    @JsonProperty("end_date")
    private String endDate;
    /**
     * Specifies the page offset, such as 1, 2, 3, or 4. The parameter value is 1 by default if it is not specified.
     */
    private Integer offset;
    /**
     * Specifies the number of records on a page. Its value range is from 1 to 100. The parameter value is 10 by default if it is not specified.
     */
    private Integer limit;
    /**
     * Specifies the log level. The default value is ALL. Valid value:ALL/INFO/LOG/WARNING/ERROR/FATAL/PANIC
     */
    private String level;
}
