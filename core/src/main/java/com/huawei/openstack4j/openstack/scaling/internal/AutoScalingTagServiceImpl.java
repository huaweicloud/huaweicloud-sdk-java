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
package com.huawei.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.scaling.AutoScalingTagService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceTag.ASAutoScalingResourceTags;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingTag.ASAutoScalingTags;

public class AutoScalingTagServiceImpl extends BaseAutoScalingServices implements AutoScalingTagService {	
	
	@Override
	public ASAutoScalingTags get(ASAutoScalingResourceType resource_type) {
		checkArgument(null !=resource_type,"parameter `resource_type` should not be empty");	
		return get(ASAutoScalingTags.class, uri("/%s/tags",resource_type)).execute();
	}

	@Override
	public ASAutoScalingResourceTags get(ASAutoScalingResourceType resource_type,String resource_id) {
		checkArgument(null !=resource_type,"parameter `resource_type` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(resource_id),"parameter `resource_id` should not be empty");	
		return get(ASAutoScalingResourceTags.class, uri("/%s/tags",resource_type+"/"+resource_id)).execute();
}

	@Override
	public ActionResponse updateOrDelete(ASAutoScalingResourceType resource_type,
			String resource_id , ASAutoScalingResourceTags tags) {
		checkArgument(null !=resource_type,"parameter `resource_type` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(resource_id),"parameter `resource_id` should not be empty");
		checkArgument(!(null == tags.getAction()),"parameter `action` should not be empty");																
		return ToActionResponseFunction.INSTANCE
				.apply(post(Void.class, uri("/%s/tags/action", resource_type+"/"+resource_id)).entity(tags).executeWithResponse());				
						
	}
}
