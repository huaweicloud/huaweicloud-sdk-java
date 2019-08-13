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
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingDefaultResult;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleActionResult;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleHook;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleHookType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingLifecycleInstanceCallback;
import com.huawei.openstack4j.openstack.scaling.domain.AutoScalingInstanceHangupInfo;
import com.huawei.openstack4j.openstack.scaling.options.ScalingInstanceOptions;

public class LifecycleHookDemo {
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // Create lifecycleHook
    public ASAutoScalingLifecycleHook createLifeHook(String groupId, String topicUrn) {
        String lifecycleHookName = "test-hook";
        ASAutoScalingLifecycleHookType lifecycleHookType = ASAutoScalingLifecycleHookType.INSTANCE_TERMINATING;
        ASAutoScalingDefaultResult defaultResult = ASAutoScalingDefaultResult.ABANDON;
        String notificationMetadata = "******";
        
        ASAutoScalingLifecycleHook lifecycleHook = ASAutoScalingLifecycleHook.builder()
            .lifecycleHookName(lifecycleHookName).lifecycleHookType(lifecycleHookType).defaultResult(defaultResult)
            .defaultTimeout("86400").notificationTopicUrn(topicUrn).notificationMetadata(notificationMetadata).build();
        ASAutoScalingLifecycleHook hook = osclient.autoScaling().lifecycleHook().create(lifecycleHook, groupId);
        
        if (null != hook) {
            System.out.print(hook.toString() + "\n");
            return hook;
        } else {
            return null;
        }
        
    }
    
    // List lifecycleHooks
    public List< ? extends ASAutoScalingLifecycleHook> listLifeHooks(String groupId) {
        List< ? extends ASAutoScalingLifecycleHook> result = osclient.autoScaling().lifecycleHook().list(groupId);
        System.out.print("List lifecycleHooks \n");
        System.out.print(result.toString() + "\n");
        return result;
        
    }
    
    // Get lifecycleHook
    public ASAutoScalingLifecycleHook getLifeHook(String groupId, String lifecycleHookName) {
        ASAutoScalingLifecycleHook result = osclient.autoScaling().lifecycleHook().list(groupId, lifecycleHookName);
        if (null != result) {
            System.out.print("get lifecycleHook success, hook = " + result.toString());
        } else {
            System.out.println("get lifecycleHook failed");
        }
        return result;
        
    }
    
    // Update lifecycleHook
    public ASAutoScalingLifecycleHook updateLifeHook(String groupId, String lifecycleHookName) {
        ASAutoScalingLifecycleHookType lifecycleHookType = ASAutoScalingLifecycleHookType.INSTANCE_LAUNCHING;
        ASAutoScalingDefaultResult defaultResult = ASAutoScalingDefaultResult.CONTINUE;
        
        ASAutoScalingLifecycleHook lifecycleHook = ASAutoScalingLifecycleHook.builder()
            .lifecycleHookType(lifecycleHookType).defaultResult(defaultResult).defaultTimeout("300").build();
        ASAutoScalingLifecycleHook result =
            osclient.autoScaling().lifecycleHook().update(groupId, lifecycleHookName, lifecycleHook);
        System.out.print(result.toString() + "\n");
        return result;
        
    }
    
    // Querying Instance Suspension -- without instanceId
    public ASAutoScalingLifecycleHook queryLifeHookInstanceList(String groupId) {
        List< ? extends AutoScalingInstanceHangupInfo> result =
            osclient.autoScaling().lifecycleHook().scalingInstanceHangup(groupId);
        System.out.print(result.toString() + "\n");
        return (ASAutoScalingLifecycleHook) result;
        
    }
    
    // Querying Instance Suspension -- with instanceId
    public ASAutoScalingLifecycleHook queryLifeHookInstance(String groupId, String instanceId) {
        List< ? extends AutoScalingInstanceHangupInfo> result = osclient.autoScaling().lifecycleHook()
            .scalingInstanceHangup(groupId, ScalingInstanceOptions.create().instanceId(instanceId));
        System.out.print(result.toString() + "\n");
        return (ASAutoScalingLifecycleHook) result;
        
    }
    
    // Calling Back an Instance Lifecycle Hook
    public ASAutoScalingLifecycleHook callbackLifeHook(String groupId, String lifecycleHookName, String instanceId) {
        ASAutoScalingLifecycleActionResult lifecycleActionResult = ASAutoScalingLifecycleActionResult.ABANDON;
        ASAutoScalingLifecycleInstanceCallback lifecycleInstanceCallback =
            ASAutoScalingLifecycleInstanceCallback.builder().instanceId(instanceId).lifecycleHookName(lifecycleHookName)
                .lifecycleActionResult(lifecycleActionResult).build();
        osclient.autoScaling().lifecycleHook().scalingInstanceHookCallback(groupId, lifecycleInstanceCallback);
        return null;
        
    }
    
    // Delete a lifecycleHook
    public ASAutoScalingLifecycleHook deleteLifeHook(String groupId, String lifecycleHookName) {
        osclient.autoScaling().lifecycleHook().delete(groupId, lifecycleHookName);
        return null;
        
    }
    
    public static void main(String[] args) {
        // need an existing scalingGroup 
        String groupId = "******";
        // need an existing smn topicUrn 
        String topicUrn = "******";
        String instanceId = "******";
        
        LifecycleHookDemo lifeHook = new LifecycleHookDemo();
        ASAutoScalingLifecycleHook hook = lifeHook.createLifeHook(groupId, topicUrn);
        if (null == hook) {
            System.out.print("Create lifecycleHook failed \n");
            return;
        }
        lifeHook.getLifeHook(groupId, hook.getLifecycleHookName());
        lifeHook.listLifeHooks(groupId);
        lifeHook.updateLifeHook(groupId, hook.getLifecycleHookName());
        lifeHook.deleteLifeHook(groupId, hook.getLifecycleHookName());
        lifeHook.queryLifeHookInstanceList(groupId);
        lifeHook.queryLifeHookInstance(groupId, instanceId);
        lifeHook.callbackLifeHook(groupId, hook.getLifecycleHookName(), instanceId);
        
    }
    
}
