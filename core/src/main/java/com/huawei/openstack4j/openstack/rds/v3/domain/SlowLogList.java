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
public class SlowLogList implements ModelEntity {
    private static final long serialVersionUID = 5943569262242460925L;
    /**
     * Indicates the number of executions.
     */
    private String count;
    /**
     * Indicates the average execution duration.
     */
    private String time;
    /**
     * Indicates the average waiting time before locking.
     */
    @JsonProperty("lock_time")
    private String lockTime;
    /**
     * Indicates the average number of rows contained in a result.
     */
    @JsonProperty("rows_sent")
    private String rowsSent;
    /**
     * Indicates the average number of scanned rows.
     */
    @JsonProperty("rows_examined")
    private String rowsExamined;
    /**
     * Indicates the database which the slow log belongs to.
     */
    private String database;
    /**
     *Indicates the account.
     */
    private String users;
    /**
     *Indicates the execution syntax.
     */
    @JsonProperty("query_sample")
    private String querySample;
    /**
     *Indicates the statement type.
     */
    private String type;

}
