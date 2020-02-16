/*******************************************************************************
 * 	Copyright 2017 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.scaling.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.scaling.ScalingGroupCreate;
import com.huawei.openstack4j.model.scaling.ScalingGroup.HealthPeriodicAuditMethod;
import com.huawei.openstack4j.model.scaling.ScalingGroup.InstanceTerminatePolicy;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;

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
public class ASAutoScalingGroupCreate implements ScalingGroupCreate {

	private static final long serialVersionUID = -8059070824334840043L;

	@JsonProperty("scaling_group_id")
	String groupId;

	@JsonProperty("scaling_group_name")
	String groupName;

	@JsonProperty("scaling_configuration_id")
	String configId;

	@JsonProperty("desire_instance_number")
	Integer desireInstanceNumber;

	@JsonProperty("min_instance_number")
	Integer minInstanceNumber;

	@JsonProperty("max_instance_number")
	Integer maxInstanceNumber;

	@JsonProperty("cool_down_time")
	Integer coolDownTime;

	@JsonProperty("lb_listener_id")
	String lbListenerId;

	@JsonProperty("lbaas_listeners")
	List<LBPool>lbaasListeners;

	@JsonProperty("available_zones")
	List<String> availabilityZones;

	@JsonProperty("networks")
	List<IdResourceEntity> networks;

	@JsonProperty("security_groups")
	List<IdResourceEntity> securityGroups;

	@JsonProperty("vpc_id")
	String vpcId;

	@JsonProperty("health_periodic_audit_method")
	HealthPeriodicAuditMethod healthPeriodicAuditMethod;

	@JsonProperty("health_periodic_audit_time")
	Integer healthPeriodicAuditTime;

	@JsonProperty("health_periodic_audit_grace_period")
	Integer healthPeriodicAuditGracePeriod;

	@JsonProperty("instance_terminate_policy")
	InstanceTerminatePolicy instanceTerminatePolicy;

	@JsonProperty("notifications")
	List<String> notifications;

	@JsonProperty("delete_publicip")
	Boolean deletePublicip;

	@JsonProperty("multi_az_priority_policy")
	MultiAZPriorityPolicy multiAZPriorityPolicy;
}
