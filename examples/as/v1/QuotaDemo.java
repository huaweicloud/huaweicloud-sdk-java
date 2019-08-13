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
import com.huawei.openstack4j.openstack.common.Quota;

public class QuotaDemo {
    
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    public void queryQuota(String groupId) {
        // query the total quotas and used quotas of AS groups, configurations, policies, and instances for a specified tenant.
        List< ? extends Quota> list0 = osclient.autoScaling().quotas().list();
        if (null != list0) {
            System.out.println("get quotas success:" + list0.toString());
        } else {
            System.out.println("get quotas failed");
        }
        
        // query the total quotas and used quotas of AS policies and instances of a specified AS group by groupId.
        List< ? extends Quota> list = osclient.autoScaling().quotas().list(groupId);
        if (null != list) {
            System.out.println("get quotas success:" + list.toString());
        } else {
            System.out.println("get quotas failed");
        }
        
    }
    
    public static void main(String[] args) {
        // need an existing scalingGroup 
        String groupId = "******";
        QuotaDemo asQuota = new QuotaDemo();
        asQuota.queryQuota(groupId);
        
    }
}
