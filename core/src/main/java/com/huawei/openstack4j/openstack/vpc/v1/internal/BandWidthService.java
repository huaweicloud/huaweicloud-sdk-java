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

 import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualBandWidthUpdate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualBandWidths;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualBandWidths.VirtualBandWidthResps;


public class BandWidthService extends BaseVirtualPrivateCloudService {

	/**
	 * 查询带宽列表
	 * @param filteringParams
	 * @return
	 */
	public List<VirtualBandWidths> list(Map<String, String> filteringParams){
		Invocation<VirtualBandWidthResps> serverInvocation = get(VirtualBandWidthResps.class,"/bandwidths");
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}
	
	/**
	 * 查询带宽列表
	 * @return
	 */
	public List<VirtualBandWidths> list(){
		return get(VirtualBandWidthResps.class,"/bandwidths").execute().getList();
	}
	
	/**
	 * 查询带宽
	 * @param bandwidthId
	 * @return
	 */
	public VirtualBandWidths get(String bandwidthId){
		checkArgument(!Strings.isNullOrEmpty(bandwidthId), "parameter `bandwidthId` should not be empty");
		return get(VirtualBandWidths.class,"/bandwidths/"+bandwidthId).execute();
	}

	/**
	 * Update bandwidth
	 * @param bandWidtUpdate
	 * @param bandwidthId
	 * @return
	 */
	public VirtualBandWidths update(VirtualBandWidthUpdate bandWidtUpdate, String bandwidthId){
		checkArgument(!(null == bandWidtUpdate), "parameter `bandWidtUpdate` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(bandwidthId), "parameter `bandwidthId` should not be empty");
		return put(VirtualBandWidths.class, "/bandwidths/"+bandwidthId).entity(bandWidtUpdate).execute();
	}
}