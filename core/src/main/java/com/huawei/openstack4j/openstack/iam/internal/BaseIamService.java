package com.huawei.openstack4j.openstack.iam.internal;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.common.functions.ReplaceVersionOfURL;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseIamService extends BaseOpenStackService {

    protected BaseIamService() {
        super(ServiceType.IAM);
    }

}
