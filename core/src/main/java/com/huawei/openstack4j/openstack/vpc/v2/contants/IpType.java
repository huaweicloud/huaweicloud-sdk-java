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
package com.huawei.openstack4j.openstack.vpc.v2.contants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 	
	1、取值范围：5_telcom，5_union，5_bgp，5_sbgp
	东北：5_telcom、5_union
	华南：5_sbgp
	华东：5_sbgp
	2、功能说明：弹性公网IP的类型
	3、约束：必须是系统具体支持的类型
 * @author 
 *
 */
public enum IpType {
	
	BGP("5_bgp"),			// 全动态BGP
	SBGP("5_sbgp"),			// 静态BGP
	Telcom("5_telcom"),		// 中国电信
	Union("5_union"),		// 中国联通
	Mobile("5_chinamobile");// 中国移动
	
	String value;

	IpType(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static IpType forValue(String value) {
		if (value != null) {
			for (IpType state : IpType.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}