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
import java.util.Calendar;
import java.util.Date;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingPolicy;
import com.huawei.openstack4j.model.scaling.ScalingPolicyAction;
import com.huawei.openstack4j.model.scaling.ScalingPolicyAction.Operation;
import com.huawei.openstack4j.model.scaling.ScalingPolicyCreateUpdate.ScalingPolicyType;
import com.huawei.openstack4j.model.scaling.ScheduledPolicy;
import com.huawei.openstack4j.model.scaling.ScheduledPolicy.RecurrenceType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate;

public class PolicyDemo {
    
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // Create scalingPolicy
    public String createPolicy(String groupId) {
        ScheduledPolicy schedulePoliy = ScheduledPolicy.builder().launchTime("01:21")
            .recurrenceType(RecurrenceType.DAILY).endTime(getEndTime()).recurrenceValue(null).build();
            
        ASAutoScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder().policyName("sdk")
            .groupId(groupId).policyType(ScalingPolicyType.RECURRENCE).scheduledPolicy(schedulePoliy).coolDownTime(800)
            .scalingPolicyAction(ScalingPolicyAction.builder().operation(Operation.ADD).build()).build();
            
        String policyId = osclient.autoScaling().policies().create(policy);
        if (null != policyId) {
            System.out.println("create scalingPolicy success, id = " + policyId);
            return policyId;
        } else {
            System.out.println("create scalingPolicy failed");
            return null;
        }
        
    }
    
    // List all scalingPolicies
    public void listPolicies(String groupId) {
        List< ? extends ScalingPolicy> policylist = osclient.autoScaling().policies().list(groupId);
        
        if (null != policylist) {
            System.out.println("List all scalingPolicies success, size is = " + policylist.size());
        } else {
            System.out.println("get scalingPolicies failed");
        }
    }
    
    // Get scalingPolicy
    public void getPolicy(String policyId) {
        ScalingPolicy policy = osclient.autoScaling().policies().get(policyId);
        if (null != policy) {
            System.out.print("get scalingPolicy success, id = " + policy.toString());
        } else {
            System.out.println("get scalingPolicy failed");
        }
    }
    
    // Update scalingPolicy
    public void updatePolicy(String policyId) {
        ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
        String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
        ScheduledPolicy scheduledPolicy =
            policy.getScheduledPolicy().toBuilder().launchTime("2017-11-24T01:21Z").build();
            
        osclient.autoScaling().policies()
            .update(ASAutoScalingPolicyCreateUpdate.fromScalingPolicy(policy).toBuilder()
                .scheduledPolicy(scheduledPolicy).policyName(after).policyType(ScalingPolicyType.SCHEDULED)
                .coolDownTime(800)
                .scalingPolicyAction(ScalingPolicyAction.builder().operation(Operation.ADD).instanceNumber(1).build())
                .build());
                
        policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
        if (null != policy) {
            System.out.println("Update a scalingPolicy success, id = " + policy);
        } else {
            System.out.println("Update a scalingPolicy failed");
        }
        
    }
    
    public void updatePolicy1(String policyId) {
        ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
        String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
        ScheduledPolicy scheduledPolicy =
            policy.getScheduledPolicy().toBuilder().launchTime("01:21").recurrenceType(RecurrenceType.MONTHLY)
                .startTime(getStartTime()).endTime(getEndTime()).recurrenceValue("1,2,3,10").build();
                
        osclient.autoScaling().policies()
            .update(ASAutoScalingPolicyCreateUpdate.fromScalingPolicy(policy).toBuilder()
                .scheduledPolicy(scheduledPolicy).policyName(after).policyType(ScalingPolicyType.RECURRENCE)
                .coolDownTime(800)
                .scalingPolicyAction(ScalingPolicyAction.builder().operation(Operation.ADD).instanceNumber(1).build())
                .build());
                
        policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
        if (null != policy) {
            System.out.println("Update a scalingPolicy success, id = " + policy);
        } else {
            System.out.println("Update a scalingPolicy failed");
        }
        
    }
    
    public void updatePolicy2(String policyId) {
        ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
        String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
        ScheduledPolicy scheduledPolicy =
            policy.getScheduledPolicy().toBuilder().launchTime("01:21").recurrenceType(RecurrenceType.MONTHLY)
                .startTime(getStartTime()).endTime(getEndTime()).recurrenceValue("1,2,3,10").build();
                
        osclient.autoScaling().policies()
            .update(ASAutoScalingPolicyCreateUpdate.fromScalingPolicy(policy).toBuilder()
                .scheduledPolicy(scheduledPolicy).policyName(after).policyType(ScalingPolicyType.ALARM)
                .alarmId("******").coolDownTime(800)
                .scalingPolicyAction(ScalingPolicyAction.builder().operation(Operation.ADD).instanceNumber(1).build())
                .build());
                
        policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
        if (null != policy) {
            System.out.println("Update a scalingPolicy success, id = " + policy);
        } else {
            System.out.println("Update a scalingPolicy failed");
        }
        
    }
    
    // Operate scalingPolicy
    public void operatePolicy(String policyId) {
        ActionResponse resp = osclient.autoScaling().policies().pause(policyId);
        if (resp.isSuccess()) {
            System.out.println("Pause scalingPolicy success");
        } else {
            System.out.println("Pause scalingPolicy failed");
        }
        
        resp = osclient.autoScaling().policies().resume(policyId);
        if (resp.isSuccess()) {
            System.out.println("Resume scalingPolicy success");
        } else {
            System.out.println("Resume scalingPolicy failed");
        }
        
        resp = osclient.autoScaling().policies().delete(policyId);
        if (resp.isSuccess()) {
            System.out.println("Delete scalingPolicy success");
        } else {
            System.out.println("Delete scalingPolicy failed");
        }
        
    }
    
    private Date getEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 500);
        return cal.getTime();
        
    }
    
    private Date getStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 0);
        return cal.getTime();
        
    }
    
    public static void main(String[] args) {
        // need an existing scalingGroup 
        String groupId = "******";
        
        PolicyDemo asPolicy = new PolicyDemo();
        String policyId = asPolicy.createPolicy(groupId);
        asPolicy.getPolicy(policyId);
        asPolicy.listPolicies(groupId);
        asPolicy.updatePolicy(policyId);
        asPolicy.operatePolicy(policyId);
        
    }
}
