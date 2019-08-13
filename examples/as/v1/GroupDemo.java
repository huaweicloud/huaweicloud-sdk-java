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

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;

import jersey.repackaged.com.google.common.collect.Lists;

public class GroupDemo {
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // Create scalingGroup
    public String creatGroup() {
        IdResourceEntity network = new IdResourceEntity();
        network.setId("******");
        
        IdResourceEntity securityGroup = new IdResourceEntity();
        securityGroup.setId("******");
        
        String availabilityZon = "******";
        
        ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder().groupName("test").vpcId("******")
            .networks(Lists.newArrayList(network)).configId("******").securityGroups(Lists.newArrayList(securityGroup))
            .availabilityZones(com.google.common.collect.Lists.newArrayList(availabilityZon)).build();
            
        String groupId = osclient.autoScaling().groups().create(group);
        if (null != groupId) {
            System.out.println("create scalingGroup success, id = " + groupId);
            return groupId;
        } else {
            System.out.println("create scalingGroup failed");
            return null;
        }
        
    }
    
    // Get scalingGroup
    public void getGroup(String groupId) {
        ScalingGroup group = osclient.autoScaling().groups().get(groupId);
        if (null != group) {
            System.out.print("get scalingGroup success, id = " + group.toString());
        } else {
            System.out.println("get scalingGroup failed");
        }
    }
    
    // List all scalingGroups
    public void listGroups() {
        List< ? extends ScalingGroup> grouplist = osclient.autoScaling().groups().list();
        if (null != grouplist) {
            System.out.println("List all scalingGroups success, size is = " + grouplist.size());
        } else {
            System.out.println("get scalingGroups failed");
        }
        
    }
    
    // Update a scalingGroup
    public void updateGroup(String groupId) {
        ASAutoScalingGroupUpdate update =
            ASAutoScalingGroupUpdate.builder().groupName("test1").maxInstanceNumber(10).coolDownTime(800).build();
            
        String group = osclient.autoScaling().groups().update(groupId, update);
        if (null != group) {
            System.out.println("Update a scalingGroup success, id = " + group);
        } else {
            System.out.println("Update a scalingGroup failed");
        }
        
    }
    
    // Resume a scalingGroup
    public void resumeGroup(String groupId) {
        ActionResponse resp = osclient.autoScaling().groups().resume(groupId);
        if (resp.isSuccess()) {
            System.out.println("Resume scalingGroup success");
        } else {
            System.out.println("Resume scalingGroup failed");
        }   
    }
    
    // Pause a scalingGroup
    public void pauseGroup(String groupId) {
        ActionResponse resp = osclient.autoScaling().groups().pause(groupId);
        if (resp.isSuccess()) {
            System.out.println("Pause scalingGroup success");   
        } else {
            System.out.println("Pause scalingGroup failed");   
        }
    }
    
    // Delete a scalingGroup
    public void deleteGroup(String groupId) {
        ActionResponse resp = osclient.autoScaling().groups().delete(groupId);
        if (resp.isSuccess()) {
            System.out.println("Delete scalingGroup success");
        } else {
            System.out.println("Delete scalingGroup failed");
        }
        
    }
    
    public static void main(String[] args) {
        GroupDemo asGroup = new GroupDemo();
        String groupId = asGroup.creatGroup();
        if (null == groupId) {
            return;
        }
        asGroup.getGroup(groupId);
        asGroup.listGroups();
        asGroup.updateGroup(groupId);
        asGroup.resumeGroup(groupId);
        asGroup.pauseGroup(groupId);
        asGroup.deleteGroup(groupId);
        
    }
}
