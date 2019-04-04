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
package com.huawei.openstack4j.openstack.evs.v2.internal;

import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.common.functions.ReplaceVersionOfURL;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseElasticVolumeService extends BaseOpenStackService{
	
	 protected BaseElasticVolumeService() {
	    	super(ServiceType.EVS);
	    }
	    
		public BaseElasticVolumeService(ServiceType serviceType, Function<String, String> endpointFunc) {
			super(serviceType, endpointFunc);
		}

	/**
	 * check and transform type to string if filterValue is list
	 * @param filteringParams
	 * @param filterKey
	 */
	protected void processListParams(Map<String,Object> filteringParams, String filterKey){
		if (filteringParams.containsKey(filterKey)){
			if (filteringParams.get(filterKey) instanceof List && !((List)(filteringParams.get(filterKey))).isEmpty()){
				filteringParams.put(filterKey,parseListToString((List)filteringParams.get(filterKey)));
			} else {
				throw new IllegalArgumentException(String.format("parameter %s is invalid",filterKey));
			}
		}
	}

	/**
	 * transform list to string(without space in string)
	 * @param list
	 * @return string
	 */
	private static String parseListToString(List<String> list){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[");
		for (String index : list)
		{
			stringBuffer.append("\"").append(index).append("\",");
		}
		return stringBuffer.substring(0, stringBuffer.length()-1) + "]";
	}
}
