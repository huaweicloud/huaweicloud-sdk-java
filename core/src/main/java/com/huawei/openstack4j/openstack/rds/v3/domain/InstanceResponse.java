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
public class InstanceResponse implements ModelEntity {
    private static final long serialVersionUID = -3723436732932000016L;

    /**
     * Indicates the DB instance ID
     */
    private String id;

    /**
     * Indicates the DB instance name
     */
    private String name;

    /**
     * Indicates the DB instance status
     */
    private String status;

    /**
     * Indicates the database information
     */
    private DataStore datastore;

    /**
     * Indicates the HA configuration parameters
     */
    private Ha ha;

    /**
     * Indicates the parameter template ID
     */
    @JsonProperty("configuration_id")
    private String configurationId;

    /**
     * Indicates the database port
     */
    private String port;

    /**
     * Indicates the automated backup policy
     */
    @JsonProperty("backup_strategy")
    private BackupStrategy backupStrategy;

    /**
     * Indicates the project ID
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;

    /**
     * Indicates the key ID for dick encryption
     */
    @JsonProperty("disk_encryption_id")
    private String diskEncryptionId;

    /**
     * Indicates the specification code
     */
    @JsonProperty("flavor_ref")
    private String flavorRef;

    /**
     * Specifies the volume information
     */
    private Volume volume;

    /**
     * Specifies the region ID
     */
    private String region;

    /**
     * Specifies the AZ ID
     */
    @JsonProperty("availability_zone")
    private String availabilityZone;

    /**
     * Indicates the VPC ID
     */
    @JsonProperty("vpc_id")
    private String vpcId;

    /**
     * Indicates the subnet ID
     */
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * Indicates the security group which the RDS DB instance belongs to
     */
    @JsonProperty("security_group_id")
    private String securityGroupId;

    /**
     * Indicates the billing information
     */
    @JsonProperty("charge_info")
    private ChargeInfoResp chargeInfo;

}
