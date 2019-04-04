/*******************************************************************************
 * 	Copyright 2019 HuaWei tld and OTC
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
package com.huawei.openstack4j.openstack.message.queue.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.message.queue.constant.ConsumeStatus;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeConfirmRequest implements ModelEntity {

    private static final long serialVersionUID = 874089548123937094L;

    private String queueId;
    private String consumerGroupId;
    private Map<String, ConsumeStatus> consumeResult;

}
