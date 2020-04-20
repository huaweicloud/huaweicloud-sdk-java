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

import java.util.List;

@Getter
@ToString
public class ListInstanceResp implements ModelEntity {

    private static final long serialVersionUID = 710923768749332275L;
    /**
     * Indicates the DB instance ID
     */
    private String id ;

    /**
     * Indicates the created DB instance name
     */
    private String name;

    /**
     * Indicates the DB instance status
     */
    private String status;

    /**
     * Indicates the private IP address list
     */
    @JsonProperty("private_ips")
    private List<String> privateIps;

    /**
     * Indicates the public IP address list
     */
    @JsonProperty("public_ips")
    private List<String> publicIps;

    /**
     * Indicates the database port number
     */
    private Integer port;

    /**
     * The value is Single, Ha, or Replica,
     * which correspond to single instance,
     * primary/standby instances, and read replica, respectively
     */
    private String type;

    /**
     * Indicates the primary/standby DB instance information
     */
    private Ha ha;

    /**
     * Indicates the region where the DB instance is deployed
     */
    private String region;

    /**
     * Indicates the database information
     */
    private DataStore datastore;

    /**
     * Indicates the creation time in the "yyyy-mm-ddThh:mm:ssZ" format
     */
    private String created;

    /**
     * Indicates the update time
     */
    private String updated;

    /**
     * Indicates the default username
     */
    @JsonProperty("db_user_name")
    private String dbUserName;

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
     * Indicates the security group ID
     */
    @JsonProperty("security_group_id")
    private String securityGroupId;

    /**
     * Indicates the specification code
     */
    @JsonProperty("flavor_ref")
    private String flavorRef;

    /**
     * Indicates the volume information
     */
    private Volume volume;

    /**
     * Indicates the database switchover policy
     */
    @JsonProperty("switch_strategy")
    private String switchStrategy;

    /**
     * Indicates the backup policy
     */
    @JsonProperty("backup_strategy")
    private BackupStrategy backupStrategy;

    /**
     * Indicates the start time of the maintenance time window
     */
    @JsonProperty("maintenance_window")
    private String maintenanceWindow;

    /**
     * Indicates the primary/standby DB instance information
     */
    private List<Nodes> nodes;

    /**
     * Indicates the list of associated DB instances
     */
    @JsonProperty("related_instance")
    private List<RelatedInstance> relatedInstance;

    /**
     * Indicates the disk encryption key ID
     */
    @JsonProperty("disk_encryption_id")
    private String diskEncryptionId;

    /**
     * Indicates the enterprise project ID
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;

    /**
     * Indicates the time zone
     */
    @JsonProperty("time_zone")
    private String timeZone;

    /**
     * Specifies the billing information, which is yearly/monthly or pay-per-use
     */
    @JsonProperty("charge_info")
    private ListChargeInfo chargeInfo;
}
