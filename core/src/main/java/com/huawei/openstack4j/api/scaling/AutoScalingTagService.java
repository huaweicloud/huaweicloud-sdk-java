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
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceTag.ASAutoScalingResourceTags;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingTag.ASAutoScalingTags;

public interface AutoScalingTagService extends RestService{

	
	/**
	 * 根据租户id查询指定资源类型的标签列表
	 * @param resource_type
	 * @return
	 */
	ASAutoScalingTags get(ASAutoScalingResourceType resource_type);
	
	/**
	 * 根据租户id和资源id查询指定资源类型的资源标签列表
	 * @param resource_type
	 * 		  resource_id	
	 * @return
	 */
	ASAutoScalingResourceTags get(ASAutoScalingResourceType resource_type , String resource_id);

	/**
	 * 更新或删除指定资源的标签。	
	 *	每个伸缩组最多添加10个标签。	
	 * @param resource_type
	 * 		  resource_id	
	 * @return
	 */
	
	ActionResponse updateOrDelete(ASAutoScalingResourceType resource_type , String resource_id , ASAutoScalingResourceTags tags );

}
