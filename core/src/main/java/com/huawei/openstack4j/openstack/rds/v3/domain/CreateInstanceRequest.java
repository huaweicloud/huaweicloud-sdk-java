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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonRootName("")
//@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateInstanceRequest implements ModelEntity {

   private static final long serialVersionUID = 662960464009027393L;

   /**
    * Specifies the DB instance name
    */
   private String name ;

   /**
    * Specifies the database information
    */
   private DataStore datastore;

   /**
    * Specifies the HA configuration parameters
    */
   private Ha ha;

   /**
    * Specifies the parameter template ID
    */
   @JsonProperty("configuration_id")
   private String configurationId;

   /**
    * Specifies the database port
    */
   private String port;

   /**
    * Specifies the database password
    */
   private String password;

   /**
    * Specifies the advanced backup policy
    */
   @JsonProperty("backup_strategy")
   private BackupStrategy backupStrategy;

   /**
    * Specifies the enterprise project ID
    */
   @JsonProperty("enterprise_project_id")
   private String enterpriseProjectId;

   /**
    * Specifies the key ID for disk encryption
    */
   @JsonProperty("disk_encryption_id")
   private String diskEncryptionId;

   /**
    * Specifies the specification code
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
    * Specifies the VPC ID
    */
   @JsonProperty("vpc_id")
   private String vpcId;

   /**
    * Specifies the subnet ID
    */
   @JsonProperty("subnet_id")
   private String subnetId;

   /**
    * Specifies the security group which the RDS DB instance belongs to
    */
   @JsonProperty("security_group_id")
   private String securityGroupId;

   /**
    * Specifies the billing information, which is yearly/monthly or pay-per-use
    */
   @JsonProperty("charge_info")
   private ChargeInfo chargeInfo;

   /**
    * Specifies the UTC time zone
    */
   @JsonProperty("time_zone")
   private String timeZone;

   /**
    * Specifies the restoration information
    */
   @JsonProperty("restore_point")
   private RestorePoint restorePoint;
}
