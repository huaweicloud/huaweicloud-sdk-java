/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Log;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.util.Date;

public class CdnLogDemo
{

    public static void main(String[] args)
    {
        // step 1: add cloud service override endpoint
        OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
        endpointResolver.addOverrideEndpoint(ServiceType.CDN, "https://cdn.example.com/v1.0");
        // step 2: setup the authentication credit
        String user = "username";
        String password = "password";
        String projectId = "projectId";
        String userDomainId = "userDomainId";
        String authUrl = "xxxxxxx";

        // step 3: initial OpenStack4j Client
        OSFactory.enableHttpLoggingFilter(true);
        // config of the client
        Config config = Config.newConfig()
                .withEndpointURLResolver(endpointResolver);

        // initial client
        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .withConfig(config)
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();

        Long queryDate = new Date().getTime();
        //set enterprise_project_id or set is null or set is all
        String enterpriseProjectId = "xxxxxxxx";
        // queryLogs
        Log.Logs logs = osclient.cdn().logs().queryLogs("xxxxxxxx",queryDate, 10,
                1, enterpriseProjectId);

        //print
        System.out.println(logs);
    }
}
