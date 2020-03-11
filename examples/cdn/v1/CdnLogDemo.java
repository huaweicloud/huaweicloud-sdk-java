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

import java.util.TimeZone;

public class CdnLogDemo {

    public static void main(String[] args) {
        // step 1: add cloud service override endpoint
        OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
        endpointResolver.addOverrideEndpoint(ServiceType.CDN, "xxx");//example:"https://cdn.myhuaweicloud.com/v1.0"

        // step 2: initial OpenStack4j Client
        OSFactory.enableHttpLoggingFilter(true);

        // step 3: config of the client
        Config config = Config.newConfig()
                .withEndpointURLResolver(endpointResolver);

        // step4：AKSK authorization：：setup the authentication credit
        String ak = "xxxx";
        String sk = "xxxx";
        String projectId = "xxxx";// the project ID of cn-north-1
        String region = "xxxx"; //example: region = "cn-north-1"
        String cloud = "xxxx"; //example: cloud = "myhuaweicloud.com"

        OSClient.OSClientAKSK osclient = OSFactory.builderAKSK().withConfig(config).credentials(ak, sk, region, projectId, cloud).authenticate();

        /*
        // step 4: token authorization：setup the authentication credit
        String user = "username"; // IAM User Name
        String password = "password"; // IAM User Password
        String projectId = "projectId"; // the project ID of cn-north-1
        String userDomainId = "userDomainId"; // Account ID
        String authUrl = "xxxxxxx"; // example: authUrl = "https://iam.myhuaweicloud.com/v3/"

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .withConfig(config)
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();
        */

        // set domain name
        String domainName = "example.domain.xxx";
        // set date
        Long queryDate = System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        // set page size & page number
        int pageSize = 10;
        int pageNumber = 1;
        //set enterprise_project_id or set is null or set is all
        String enterpriseProjectId = null;
        // queryLogs
        Log.Logs logs = osclient.cdn().logs().queryLogs(domainName, queryDate, pageSize, pageNumber, enterpriseProjectId);
        //print
        System.out.println(logs);
    }
}
