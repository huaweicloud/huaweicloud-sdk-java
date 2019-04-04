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
package com.huawei.openstack4j.openstack.evs.v2_1.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.openstack.evs.v2_1.contants.ChargingMode;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("bssParam")
public class BssParamExtend {

	
	/**
	 * 功能说明：是否立即支付。chargingMode为PrePaid时该参数会生效。默认值为false。
		取值范围
		false：不立即支付，创建订单暂不支付
		true：立即支付，从帐户余额中自动扣费
	 */
	private Boolean isAutoPay;

	/**
	 * 功能说明：计费模式。默认值为postPaid。
	 * 取值范围
	 * −	prePaid：包年/包月
	 * −	postPaid：按需计费
	 * 若是扩容包周期云硬盘，必须填prePaid，而扩容按需云硬盘，必须填postPaid或使用默认值。
	 */
	private ChargingMode chargingMode;
	
}
