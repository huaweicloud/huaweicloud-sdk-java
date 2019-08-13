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
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroupInstance;

public class InstanceDemo {
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // List all scalingInstances
    public void listInstances(String groupId) {
        List< ? extends ScalingGroupInstance> listInstances = osclient.autoScaling().groupInstances().list(groupId);
        if (null != listInstances) {
            System.out.println("List all scalingInstances success, size is = " + listInstances.size());
        } else {
            System.out.println("get scalingInstances failed");
        }
        
    }
    
    // Delete a scalingInstance
    public void deleteInstance() {
        String instanceId = "******";
        ActionResponse resp = osclient.autoScaling().groupInstances().delete(instanceId, false);
        if (resp.isSuccess()) {
            System.out.println("Delete scalingInstance success");
        } else {
            System.out.println("Delete scalingInstance failed");
        }
        
    }
    
    // Batch operate scalingInstances
    public void batchOperateInstances(String groupId) throws InterruptedException {
        ArrayList<String> instanceIds = Lists.newArrayList("******");
        ActionResponse resp = osclient.autoScaling().groupInstances().batchRemove(groupId, instanceIds, false);
        if (resp.isSuccess()) {
            System.out.println("batchAdd scalingInstances success");
        } else {
            System.out.println("batchAdd scalingInstances failed");
        }
        
        TimeUnit.MINUTES.sleep(5);
        
        ActionResponse resp1 = osclient.autoScaling().groupInstances().batchAdd(groupId, instanceIds, false);
        if (resp1.isSuccess()) {
            System.out.println("batchRemove scalingInstances success");
        } else {
            System.out.println("batchRemove scalingInstances failed");
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        // need an existing scalingGroup 
        String groupId = "******";
        InstanceDemo asInstance = new InstanceDemo();
        asInstance.listInstances(groupId);
        asInstance.batchOperateInstances(groupId);
        
    }
}
