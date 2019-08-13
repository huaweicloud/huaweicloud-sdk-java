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
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInforType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingInform.ASAutoScalingTopics;

public class NotificationDemo {
    
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // List all notifications
    public void listNotifications(String groupId) {
        ASAutoScalingTopics result = osclient.autoScaling().inform().list(groupId);
        System.out.println("List all scalingGroups success, size is = " + result.getList().size());
        
    }
    
    // Create notification
    public ASAutoScalingTopics createNotification(String topicUrn, String groupId) {
        List<ASAutoScalingInforType> topicScene = new ArrayList<ASAutoScalingInforType>();
        topicScene.add(ASAutoScalingInforType.SCALING_UP);
        topicScene.add(ASAutoScalingInforType.SCALING_DOWN_FAIL);
        topicScene.add(ASAutoScalingInforType.SCALING_GROUP_ABNORMAL);
        
        ASAutoScalingInform arg1 = ASAutoScalingInform.builder().topic_scene(topicScene).topic_urn(topicUrn).build();
        osclient.autoScaling().inform().deploy(groupId, arg1);
        
        ASAutoScalingTopics result = osclient.autoScaling().inform().list(groupId);
        System.out.print(result.getList().toString() + "\n");
        return result;
        
    }
    
    public ASAutoScalingTopics deleteNotication(String topicUrn, String groupId) {
        osclient.autoScaling().inform().delete(groupId, topicUrn);
        ASAutoScalingTopics result = osclient.autoScaling().inform().list(groupId);
        
        System.out.print(result.getList().toString() + "\n");
        return result;
        
    }
    
    public static void main(String[] args) {
        // need an existing scalingGroup 
        String groupId = "******";
        // need an existing smn topicUrn 
        String topicUrn = "******";
        
        NotificationDemo Informlist = new NotificationDemo();
        Informlist.createNotification(topicUrn, groupId);
        Informlist.deleteNotication(topicUrn, groupId);
        
    }
}
