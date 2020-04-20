package com.huawei.openstack4j.openstack.rds.v3.internal;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseRdsServices extends BaseOpenStackService {
    protected BaseRdsServices() {
        super(ServiceType.RDSV3);
    }

    //需要特殊处理的时候需要
    /*
    public BaseRdsServices(ServiceType serviceType, Function<String, String> endpointFunc) {
        super(serviceType, endpointFunc);
    }
    */

    /**
     * HuaWei Relation Database Service validate the content-type in every request
     */
    protected <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
        // add common base path for database service
        // path = "/rds/v3/%(project_id)s" + path;

        // setup common headers for database service
        Invocation<R> invocation = super.builder(returnType, path, method);
        Config config = invocation.getRequest().getConfig();
        return invocation.header("Content-Type", CONTENT_JSON).header("X-Language", config.getLanguage());
    }
}
