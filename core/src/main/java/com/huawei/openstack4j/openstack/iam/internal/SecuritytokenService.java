package com.huawei.openstack4j.openstack.iam.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.iam.domain.Securitytoken;
import com.huawei.openstack4j.openstack.iam.domain.Auth;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

public class SecuritytokenService extends BaseIamService {

    private static final String PATH_SECURITYTOKENS = "/OS-CREDENTIAL/securitytokens";

    /**
     * Create a new securitytoken by token in body
     *
     * @param auth the auth entity
     * @return the newly created securitytoken
     */
    public Securitytoken create(Auth auth){
        checkArgument(!Strings.isNullOrEmpty(auth.getIdentity().getMethods().get(0)), "parameter `method` should not be empty");
        if(auth.getIdentity().getMethods().get(0).equals("assume_role")){
            checkArgument(!Strings.isNullOrEmpty(auth.getIdentity().getAssumeRole().getAgencyName()), "parameter `agency_name` should not be empty");
            checkArgument(!Strings.isNullOrEmpty(auth.getIdentity().getAssumeRole().getDomainId())||!Strings.isNullOrEmpty(auth.getIdentity().getAssumeRole().getDomainName()), "parameter `domain_id` or `domain_name` should not be empty");
        }
        return post(Securitytoken.class, PATH_SECURITYTOKENS).entity(auth).execute();
    }

}
