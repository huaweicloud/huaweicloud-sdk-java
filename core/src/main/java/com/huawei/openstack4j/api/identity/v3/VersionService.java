package com.huawei.openstack4j.api.identity.v3;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.openstack.identity.v3.domain.Version;

import java.util.List;


/**
 * identity/v3 Version operations
 *
 */
public interface VersionService extends RestService {

    /**
     * Get Information About Keystone API Version 3.0
     *
     * @return the Version Information
     */
    Version get();

}
