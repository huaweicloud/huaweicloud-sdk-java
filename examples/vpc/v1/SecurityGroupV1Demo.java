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
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.vpc.v1.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityGroupV1Demo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create a SecurityGroup
        String securityGroupName = "******";
        SecurityGroupCreate securityGroup = SecurityGroupCreate.builder().name(securityGroupName).build();
        SecurityGroup createResp = osclient.vpc().securityGroups().create(securityGroup);
        if (null != createResp) {
            System.out.println("Create a SecurityGroup success, id = " + createResp.getId());
        } else {
            System.out.println("Create a SecurityGroup failed");
        }

        // Get a SecurityGroup
        SecurityGroup getResp = osclient.vpc().securityGroups().get(createResp.getId());
        if (null != getResp) {
            System.out.println("Get a SecurityGroup success, id = " + getResp.getId());
        } else {
            System.out.println("Get a SecurityGroup failed");
        }

        // List all SecurityGroups
        List<? extends SecurityGroup> listResp = osclient.vpc().securityGroups().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all SecurityGroups success, size = " + listResp.size());
        } else {
            System.out.println("List all SecurityGroups failed");
        }

        // List SecurityGroups with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends SecurityGroup> listWithFilterResp = osclient.vpc().securityGroups().list(filteringParams);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List all SecurityGroups success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List all SecurityGroups failed");
        }

        // Delete a SecurityGroup
        ActionResponse resp = osclient.vpc().securityGroups().delete(getResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a SecurityGroup success");
        } else {
            System.out.println("Delete a SecurityGroup failed");
        }

        // Create  SecurityGroupRule
        SecurityGroupRule securityGroupRule = SecurityGroupRule.builder()
                .securityGroupId("a7734e61-b545-452d-a3cd-0189cbd9747a")
                .direction("egress")
                .portRangeMin(80)
                .portRangeMax(80)
                .protocol("tcp")
                .build();
        SecurityGroupRule createRuleResp = osclient.vpc().securityGroups().createSecurityGroupRule(securityGroupRule);
        if (null != createRuleResp) {
            System.out.println("Create a SecurityGroupRule success, id = " + createRuleResp.getId());
        } else {
            System.out.println("Create a SecurityGroupRule failed");
        }

        // Get a SecurityGroupRule
        SecurityGroupRule getRuleResp = osclient.vpc().securityGroups().getSecurityGroupRule(createRuleResp.getId());
        if (null != getRuleResp) {
            System.out.println("Get a SecurityGroupRule success, id = " + getRuleResp.getId());
        } else {
            System.out.println("Get a SecurityGroupRule failed");
        }

        // List all SecurityGroupRule
        List<? extends SecurityGroupRule> listRuleResp = osclient.vpc().securityGroups().listSecurityGroupRules();
        if (null != listRuleResp && listRuleResp.size() > 0) {
            System.out.println("List all SecurityGroupRule success, size = " + listRuleResp.size());
        } else {
            System.out.println("List all SecurityGroupRule failed");
        }

        // List SecurityGroupRule with filter

        List<? extends SecurityGroupRule> listRuleWithFilterResp = osclient.vpc().securityGroups().listSecurityGroupRules(filteringParams);
        if (null != listRuleWithFilterResp && listRuleWithFilterResp.size() > 0) {
            System.out.println("List listRuleWithFilterResp success, size = " + listRuleWithFilterResp.size());
        } else {
            System.out.println("List listRuleWithFilterResp failed");
        }

        // Delete a SecurityGroupRule
        ActionResponse DelRuleResp = osclient.vpc().securityGroups().deleteSecurityGroupRule(getRuleResp.getId());
        if (DelRuleResp.isSuccess()) {
            System.out.println("Delete a SecurityGroupRule success");
        } else {
            System.out.println("Delete a SecurityGroupRule failed");
        }



    }
}
