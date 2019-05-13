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

}
