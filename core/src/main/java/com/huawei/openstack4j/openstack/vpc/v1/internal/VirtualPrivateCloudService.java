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
package com.huawei.openstack4j.openstack.vpc.v1.internal;

import com.huawei.openstack4j.api.Apis;

/**
 * Relation Vpc Operations API implementation
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class VirtualPrivateCloudService extends BaseVirtualPrivateCloudService {
	
	/**
	 * Service implementation which provides methods for manipulation of vpcs
	 *
	 * @return {@link VpcService} instance
	 */
	public VpcService vpcs(){
		return Apis.get(VpcService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of subnets
	 *
	 * @return {@link SubnetService} instance
	 */
	public SubnetService subnets(){
		return Apis.get(SubnetService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of bandwidths
	 *
	 * @return {@link BandWidthService} instance
	 */
	public BandWidthService bandwidths(){
		return Apis.get(BandWidthService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of quotas
	 *
	 * @return {@link QuotaService} instance
	 */
	public QuotaService quotas(){
		return Apis.get(QuotaService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of publicips
	 *
	 * @return {@link PublicIpService} instance
	 */
	public PublicIpService publicips(){
		return Apis.get(PublicIpService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of privateips
	 *
	 * @return {@link PrivateIpService} instance
	 */
	public PrivateIpService privateIps(){
		return Apis.get(PrivateIpService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of SecurityGroups
	 *
	 * @return {@link SecurityGroupService} instance
	 */
	public SecurityGroupService securityGroups(){
		return Apis.get(SecurityGroupService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of ports
	 *
	 * @return {@link PortService} instance
	 */
	public PortService ports(){
		return Apis.get(PortService.class);
	}
}
