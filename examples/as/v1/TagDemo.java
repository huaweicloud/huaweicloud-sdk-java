/*******************************************************************************
 *  Copyright 2019 Huawei Technologies Co., Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 *******************************************************************************/
package com.huawei.openstack.sample;

import java.util.ArrayList;
import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceTag;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceTag.ASAutoScalingResourceTags;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingResourceType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingTag.ASAutoScalingTags;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingTagActionType;

public class TagDemo {
    AuthenticationDemo as_auth = new AuthenticationDemo();
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // Query Tags
    public void queryTags(String groupId) {
        // query tags by projectId
        ASAutoScalingResourceType arg0 = ASAutoScalingResourceType.scaling_group_tag;
        ASAutoScalingTags result = osclient.autoScaling().tags().get(arg0);	
        
        System.out.print("query tags by projectId\n");
        System.out.print(result.getList().toString() + "\n");
        
        // query tags by projectId and resourceId(groupId)
        ASAutoScalingResourceTags result1 = osclient.autoScaling().tags().get(arg0, groupId);
        
        System.out.print("query tags by projectId and resourceId\n");
        System.out.print(result1.getList().toString() + "\n");			
    }
    
    // Create Tags
    public void createTags(String groupId) {
        ASAutoScalingResourceType arg0 = ASAutoScalingResourceType.scaling_group_tag;
        List<ASAutoScalingResourceTag> tags = new ArrayList<ASAutoScalingResourceTag>();
        
        String key = "testKey";
        String value = "testValue";
        
        String key1 = "ENV151";
        String value1 = "ENV151";
        
        String key2 = "ENV15";
        String value2 = "ENV15";
        
        tags.add(ASAutoScalingResourceTag.builder().key(key).value(value).build());
        tags.add(ASAutoScalingResourceTag.builder().key(key1).value(value1).build());
        tags.add(ASAutoScalingResourceTag.builder().key(key2).value(value2).build());
        
        // query tags by projectId and resourceId before create
        ASAutoScalingResourceTags result0 = osclient.autoScaling().tags().get(arg0, groupId);
        
        System.out.print("query tags before create\n");
        System.out.print(result0.getList().toString() + "\n");	
        
        ASAutoScalingTagActionType action = ASAutoScalingTagActionType.create;
        ASAutoScalingResourceTags arg1 = ASAutoScalingResourceTags.builder().tags(tags).action(action).build();
        osclient.autoScaling().tags().updateOrDelete(arg0, groupId, arg1);
        
        // query tags by projectId and resourceId after create
        ASAutoScalingResourceTags result1 = osclient.autoScaling().tags().get(arg0, groupId);
        
        System.out.print("query tags after create\n");
        System.out.print(result1.getList().toString() + "\n");
        
    }
    
    // Delete Tags
    public void deleteTags(String groupId) {
        ASAutoScalingResourceType arg0 = ASAutoScalingResourceType.scaling_group_tag;
        List<ASAutoScalingResourceTag> tags = new ArrayList<ASAutoScalingResourceTag>();
        
        String key = "testKey";
        String value = "testValue";

        tags.add(ASAutoScalingResourceTag.builder().key(key).value(value).build());
        
        ASAutoScalingTagActionType action = ASAutoScalingTagActionType.delete;
        ASAutoScalingResourceTags arg1 = ASAutoScalingResourceTags.builder().tags(tags).action(action).build();
        osclient.autoScaling().tags().updateOrDelete(arg0, groupId, arg1);        
    }
    
    public static void main(String[] args) {
        // need an existing scalingGroup 
        String groupId = "******";
        TagDemo asTag= new TagDemo();
        asTag.createTags(groupId);
        asTag.queryTags(groupId);
        asTag.deleteTags(groupId);
        asTag.queryTags(groupId);

    }

}
