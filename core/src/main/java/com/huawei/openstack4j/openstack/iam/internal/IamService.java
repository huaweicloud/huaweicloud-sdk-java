package com.huawei.openstack4j.openstack.iam.internal;

import com.huawei.openstack4j.api.Apis;

public class IamService extends BaseIamService{

    /**
     * Securitytoken Service API
     *
     * @return the securitytoken service
     */
    public SecuritytokenService securitytokens() {
        return Apis.get(SecuritytokenService.class);
    }

    /**
     *  User Service API
     *
     * @return the user service
     */
    public UserService users() {
        return Apis.get(UserService.class);
    }

    /**
     *  Project Service API
     *
     * @return the user service
     */
    public ProjectService projects() {
        return Apis.get(ProjectService.class);
    }

    /**
     *  Credential Service API
     *
     * @return the credential service
     */
    public CredentialService credentials() {
        return Apis.get(CredentialService.class);
    }

    /**
     *  Role Service API
     *
     * @return the role service
     */
    public CustomRoleService roles() {
        return Apis.get(CustomRoleService.class);
    }

    /**
     *  Agency Service API
     *
     * @return the agency service
     */
    public AgencyService agencies() {
        return Apis.get(AgencyService.class);
    }

}
