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
package com.huawei.openstack4j.api.scaling;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform.ASAutoScalingTopics;

public interface AutoScalingInformService extends RestService{


	/**
	 * 给弹性伸缩组配置通知功能。每调用一次该接口，伸缩组即配置一个通知主题及其通知场景，
	 * 每个伸缩组最多可以增加5个主题。通知主题由用户事先在SMN创建，
	 * 当通知主题对应的通知场景出现时，伸缩组会向用户发送通知。
	 * @param groupId
	 * 	      ASAutoScalingInform
	 * @return
	 */
	 ASAutoScalingInform deploy(String groupId , ASAutoScalingInform info);
	
	 /**
	 * 查询弹性伸缩组通知列表。
	 * @param groupId
	 * @return
	 */	 
	 ASAutoScalingTopics list(String groupId);
	 
	 /**
	 * 删除指定的弹性伸缩组中指定的通知。
	 * @param groupId
	 * @return
	 */	
	 ActionResponse  delete(String groupId , String topicUrn);
}
