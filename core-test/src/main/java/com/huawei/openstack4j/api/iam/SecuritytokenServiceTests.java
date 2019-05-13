package com.huawei.openstack4j.api.iam;

import java.io.IOException;
import java.util.Arrays;

import com.huawei.openstack4j.openstack.iam.domain.*;
import com.huawei.openstack4j.api.AbstractTest;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class SecuritytokenServiceTests extends AbstractTest{

    @Override
    protected Service service() {
        return Service.IAM;
    }

    private static final String SECURITYTOKEN_CREATE = "/iam/securitytoken.json";
    private static final String DURATION_SECONDS = "12000";
    private static final String AGENCY_NAME = "testagency";
    private static final String DOMAIN_NAME = "default";
    private static final String PROJECT_ID = "******";

    @Test
    public void testCreateByToken() throws IOException{
        respondWith(SECURITYTOKEN_CREATE);
        Auth auth = Auth.builder().identity(AuthIdentity.builder().methods(Arrays.asList("token")).token(AuthToken.builder().durationSeconds(DURATION_SECONDS).build()).build()).build();
        Securitytoken securitytoken = osv3().iam().securitytokens().create(auth);
        assertEquals(securitytoken.getAccess(), "******");
        assertEquals(securitytoken.getSecret(), "******");
        assertEquals(securitytoken.getSecuritytoken(), "******");
    }

    @Test
    public void testCreateByAssume() throws IOException{
        respondWith(SECURITYTOKEN_CREATE);
        Auth auth = Auth.builder().identity(AuthIdentity.builder().methods(Arrays.asList("assume_role")).assumeRole(AuthAssumeRole.builder().agencyName(AGENCY_NAME).domainName(DOMAIN_NAME).durationSeconds(DURATION_SECONDS).build()).build()).scope(AuthScope.builder().project(AuthProject.builder().id(PROJECT_ID).build()).build()).build();
        Securitytoken securitytoken = osv3().iam().securitytokens().create(auth);
        assertEquals(securitytoken.getAccess(), "******");
        assertEquals(securitytoken.getSecret(), "******");
        assertEquals(securitytoken.getSecuritytoken(), "******");
    }

}
