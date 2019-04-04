/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.api.types;

public enum ServiceType {

	//@off
	IDENTITY("keystone", "identity"),
	APP_CATALOG("murano", "application-catalog"),
	COMPUTE("nova", "compute"),
	IMAGE("glance", "image"),
	BLOCK_STORAGE("cinder", "volume"),
	OBJECT_STORAGE("object-store", "object-store"),
	NETWORK("neutron", "network"),
	EC2("ec2", "ec2"),
	TELEMETRY("ceilometer", "metering"),
	TELEMETRY_AODH("aodh", "alarming"),
	ORCHESTRATION("heat", "orchestration"),
	CLUSTERING("senlin", "clustering"),
	MAP_REDUCE("MRS", "data_processing"),
	SHARE("manila", "share"),
	DATABASE("trove","database"),
	BARBICAN("barbican", "key-manager"),
	TACKER("tacker", "nfv-orchestration"),
	ARTIFACT("glare", "artifact"),
  	MAGNUM("magnum", "container"),
	DNS("designate", "dns"),
	WORKFLOW("mistral", "workflow"),
	
	// qianbiao.ng - new add services
	VOLUME_BACKUP("VBS", "volume-backup"),
	AUTO_SCALING("AS", "auto-scaling"),
	LOAD_BALANCER("ELB", "elbv1"),
	CLOUD_EYE("CES", "cloud-eye"),
	TAG_MANAGEMENT("TMS", "tag-management"),
	VPC("VPC","vpc"),
	VPC2("VPC","vpcv2.0"),
	EVS("EVS","evs"),
	EVS2_1("EVS","evsv2.1"),
	DSS("DSS","dss"),
	ECS("ECS","ecs"),
	ECS1_1("ECS","ecsv1.1"),
	KEY_MANAGEMENT("KMS", "key-management"),
	CLOUD_TRACE("CTS", "cloud-trace"),
	ANTI_DDOS("antiddos", "anti-ddos"),
	Notification("SMN", "notification-message"),
	MessageQueue("DMS", "distributed-message"),
	MAAS("MaaS", "MaaS"),
	NAT("NAT", "nat"),
	BMS("BMS", "bms"),
	DEH("DEH","deh"),
	CSBS("csbs","data-protect"),
	CDN("cdn","cdn"),
	FGS("fgs","fgs"),
	UNKNOWN("NA", "NA")
	;
	//@on

	private final String serviceName;
	private final String type;

	ServiceType(String serviceName, String type) {
		this.serviceName = serviceName;
		this.type = type;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public String getType() {
		return this.type;
	}

	public static ServiceType forName(String name) {
		for (ServiceType s : ServiceType.values())
		{
			if (s.getServiceName().equalsIgnoreCase(name))
			    return s;
			if (s.name().equalsIgnoreCase(name))
				return s;
			if (s.type.equalsIgnoreCase(name))
				return s;
		}
		return ServiceType.UNKNOWN;
	}
}
