import java.util.HashMap;
import java.util.Map;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.openstack.vpc.v3.domain.*;

public class SecurityGroupV3Demo
{
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a SecurityGroup
        String securityGroupName = "******";
        SecurityGroupCreate securityGroupCreate = SecurityGroupCreate.builder().name(securityGroupName).build();
        SecurityGroupResp createResp = osclient.vpcV3().securityGroups().create(securityGroupCreate);
        if (null != createResp && null != createResp.getSecurityGroup()) {
            System.out.println("Create a SecurityGroup success, id = " + createResp.getSecurityGroup().getId());
        } else {
            System.out.println("Create a SecurityGroup failed");
        }

        //Get a SecurityGroup
        SecurityGroupResp getResp = osclient.vpcV3().securityGroups().get(createResp.getSecurityGroup().getId());
        if (null != getResp && null != getResp.getSecurityGroup()) {
            System.out.println("Get a SecurityGroup success, id = " + getResp.getSecurityGroup().getId());
        } else {
            System.out.println("Get a SecurityGroup failed");
        }

        //List all SecurityGroups
        SecurityGroups listAllResp = osclient.vpcV3().securityGroups().list();
        if (null != listAllResp && null != listAllResp.getList() && listAllResp.getList().size() > 0) {
            System.out.println("List all SecurityGroups success, size = " + listAllResp.getList().size());
        } else {
            System.out.println("List all SecurityGroups failed");
        }

        //List SecurityGroups with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        SecurityGroups listWithFilterResp = osclient.vpcV3().securityGroups().list(filteringParams);
        if (null != listWithFilterResp && null != listWithFilterResp.getList() && listWithFilterResp.getList().size() > 0) {
            System.out.println("List all SecurityGroups success, size = " + listWithFilterResp.getList().size());
        } else {
            System.out.println("List all SecurityGroups failed");
        }

        //Delete a SecurityGroup
        ActionResponse resp = osclient.vpcV3().securityGroups().delete(createResp.getSecurityGroup().getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a SecurityGroup success");
        } else {
            System.out.println("Delete a SecurityGroup failed");
        }

        // Create a SecurityGroupRule
        String securityGroupId = createResp.getSecurityGroup().getId();
        SecurityGroupRuleCreate securityGroupRuleCreate = SecurityGroupRuleCreate.builder()
                .securityGroupId(securityGroupId)
                .direction("ingress")
                .protocol("tcp")
                .portRangeMax(80)
                .portRangeMin(80)
                .action("allow")
                .priority(5)
                .build();
        SecurityGroupRuleResp createRuleResp = osclient.vpcV3().securityGroups().createSecurityGroupRule(securityGroupRuleCreate);
        if (null != createRuleResp && null != createRuleResp.getSecurityGroupRule()) {
            System.out.println("Create a SecurityGroupRule success, id = " + createRuleResp.getSecurityGroupRule().getId());
        } else {
            System.out.println("Create a SecurityGroupRule failed");
        }

        // Get a SecurityGroupRule
        SecurityGroupRuleResp getRuleResp = osclient.vpcV3().securityGroups().getSecurityGroupRule(createRuleResp.getSecurityGroupRule().getId());
        if (null != getRuleResp && null != getRuleResp.getSecurityGroupRule()) {
            System.out.println("Get a SecurityGroupRule success, id = " + getRuleResp.getSecurityGroupRule().getId());
        } else {
            System.out.println("Get a SecurityGroupRule failed");
        }

        //List all SecurityGroupRule
        SecurityGroupRules listRuleResp = osclient.vpcV3().securityGroups().listSecurityGroupRules();
        if (null != listRuleResp && null != listRuleResp.getList() && listRuleResp.getList().size() > 0) {
            System.out.println("List all SecurityGroupRule success, size = " + listRuleResp.getList().size());
        } else {
            System.out.println("List all SecurityGroupRule failed");
        }

        //List SecurityGroupRule with filter
        SecurityGroupRules listRuleWithFilterResp = osclient.vpcV3().securityGroups().listSecurityGroupRules(filteringParams);
        if (null != listRuleWithFilterResp && null != listRuleWithFilterResp.getList() && listRuleWithFilterResp.getList().size() > 0) {
            System.out.println("List listRuleWithFilterResp success, size = " + listRuleWithFilterResp.getList().size());
        } else {
            System.out.println("List listRuleWithFilterResp failed");
        }

        //Delete a SecurityGroupRule
        ActionResponse DelRuleResp = osclient.vpcV3().securityGroups().deleteSecurityGroupRule(getRuleResp.getSecurityGroupRule().getId());
        if (DelRuleResp.isSuccess()) {
            System.out.println("Delete a SecurityGroupRule success");
        } else {
            System.out.println("Delete a SecurityGroupRule failed");
        }
    }
}