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

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

public class AuthenticationDemo
{
    protected OSClientV3 osclient;
    
    // Init
    public OSClientV3 initailV3Client() {
            
        // setup the authentication credit
        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "https://iam.xxx.yyy.com/v3";
        
        osclient = OSFactory.builderV3()
            .endpoint(authUrl).credentials(user, password, Identifier.byId(userDomainId))
            .scopeToProject(Identifier.byId(projectId)).authenticate();
            
        return osclient;
    }
}
