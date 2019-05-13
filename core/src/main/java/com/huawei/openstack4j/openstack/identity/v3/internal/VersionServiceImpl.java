package com.huawei.openstack4j.openstack.identity.v3.internal;

import com.huawei.openstack4j.api.identity.v3.VersionService;
import com.huawei.openstack4j.openstack.identity.v3.domain.Version;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class VersionServiceImpl extends BaseOpenStackService implements VersionService {

    @Override
    public Version get(){
        return get(Version.class, uri("")).execute();
    }

}
