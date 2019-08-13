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
import com.huawei.openstack4j.model.scaling.ScalingActivityLog;

public class ActivityLogDemo {
    
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // List group activityLogs
    public void listActivityLogs(String groupId) {
        List< ? extends ScalingActivityLog> log = osclient.autoScaling().activityLogs().list(groupId);
        System.out.print(log.toString() + "\n");
        
    }
    
    public static void main(String[] args) {
        // need an existing scalingGroup 
        String groupId = "******";
        ActivityLogDemo activityLog = new ActivityLogDemo();
        activityLog.listActivityLogs(groupId);
        
    }
}
