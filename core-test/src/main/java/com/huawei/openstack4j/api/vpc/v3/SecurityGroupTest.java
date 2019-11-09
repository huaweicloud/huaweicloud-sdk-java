package com.huawei.openstack4j.api.vpc.v3;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.vpc.v3.domain.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(suiteName = "Vpc/SecurityGroup", enabled = true)
public class SecurityGroupTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.VPC3;
    }

    @Test
    public void testCreateSecurityGroup() throws IOException, InterruptedException {
        respondWith("/vpc/v3/create_get_securitygroup_response.json");

        SecurityGroupCreate sg = SecurityGroupCreate.builder()
                .name("qq")
                .description("abc")
                .enterpriseProjectId("99453e9a-33ec-4de4-bc51-2d1e86423506")
                .build();

        SecurityGroupResp securityGroup = osv3().vpcV3().securityGroups().create(sg);
        SecurityGroup sgResp = securityGroup.getSecurityGroup();
        SecurityGroupRule rule = sgResp.getSecurityGroupRules().get(0);

        RecordedRequest request = server.takeRequest();

        Assert.assertEquals(request.getPath(), "/v3/project-id/vpc/security-groups");
        Assert.assertEquals(request.getMethod(), "POST");
        Assert.assertEquals(sgResp.getId(), "16e6c430-3c11-456c-8d61-33b6b6141864");
        Assert.assertEquals(sgResp.getEnterpriseProjectId(), "99453e9a-33ec-4de4-bc51-2d1e86423506");
        Assert.assertEquals(rule.getEtherType(),"IPv4");
        Assert.assertEquals(rule.getAction(),"allow");
    }

    @Test
    public void testGetSecurityGroup() throws IOException, InterruptedException {
        respondWith("/vpc/v3/create_get_securitygroup_response.json");

        SecurityGroupResp securityGroup = osv3().vpcV3().securityGroups().get("16e6c430-3c11-456c-8d61-33b6b6141864");
        SecurityGroup sgResp = securityGroup.getSecurityGroup();
        SecurityGroupRule rule = sgResp.getSecurityGroupRules().get(0);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals(request.getPath(), "/v3/project-id/vpc/security-groups/16e6c430-3c11-456c-8d61-33b6b6141864");
        Assert.assertEquals(request.getMethod(), "GET");
        Assert.assertEquals(sgResp.getId(), "16e6c430-3c11-456c-8d61-33b6b6141864");
        Assert.assertEquals(sgResp.getName(), "qq");
        Assert.assertEquals(rule.getEtherType(),"IPv4");
        Assert.assertEquals(rule.getAction(),"allow");
        Assert.assertEquals(securityGroup.getRequestId(),"bbf6f6e9-604e-462c-9a60-f0f23064478e");
    }

    @Test
    public void testListSecurityGroups() throws IOException, InterruptedException {
        respondWith("/vpc/v3/list_securitygroup_response.json");

        SecurityGroups list = osv3().vpcV3().securityGroups().list();

        SecurityGroup securityGroup = list.getList().get(0);

        SecurityGroupRule rule = securityGroup.getSecurityGroupRules().get(0);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals(request.getPath(), "/v3/project-id/vpc/security-groups");
        Assert.assertEquals(request.getMethod(), "GET");

        Assert.assertNotNull(list);
        Assert.assertEquals(list.getList().size(), 2);
        Assert.assertEquals(securityGroup.getId(), "16e6c430-3c11-456c-8d61-33b6b6141864");
        Assert.assertEquals(rule.getEtherType(),"IPv4");
    }

}
