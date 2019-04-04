/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.SecurityGroupRule;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroupRule;

import java.util.List;

public class SecurityGroupRuleDemo {
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a SecurityGroupRule
        String securityGroupId = "******";
        String direction = "direction";
        String protocol = "tcp";
        String remoteIpPrefix = "10.10.0.0/24";
        SecurityGroupRule securityGroupRule = NeutronSecurityGroupRule.builder().id(securityGroupId).direction(direction).remoteIpPrefix(remoteIpPrefix)
                .protocol(protocol).build();
        SecurityGroupRule createResp = osclient.networking().securityrule().create(securityGroupRule);
        if (null != createResp) {
            System.out.println("Create a SecurityGroupRule success, id = " + createResp.getId());
        } else {
            System.out.println("Create a SecurityGroupRule failed");
        }

        //Delete a SecurityGroupRule
        String securityGroupsRulesId = "******";
        osclient.networking().securityrule().delete(securityGroupsRulesId);

        //Get a SecurityGroupRule
        SecurityGroupRule getResp = osclient.networking().securityrule().get(securityGroupsRulesId);
        if (null != getResp) {
            System.out.println("Get a SecurityGroupRule success, id = " + getResp.getId());
        } else {
            System.out.println("Get a SecurityGroupRule failed");
        }

        //List all SecurityGroupRules
        List<? extends SecurityGroupRule> listResp =  osclient.networking().securityrule().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all SecurityGroupRules success, size = " + listResp.size());
        } else {
            System.out.println("List all SecurityGroupRules failed");
        }
    }
}

