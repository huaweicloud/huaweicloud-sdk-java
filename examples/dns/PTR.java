package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack.sample.Constant;

import java.util.HashMap;
import java.util.Map;

public class PTR {
    private static OSClient.OSClientV3 os;
    public static void main(String[] args) {
         os = OSFactory.builderV3()
                .endpoint(Constants.AUTH_URL)
                .credentials(Constants.USERNAME, Constants.PASSWORD, Identifier.byId(Constants.USERDOMAINID))
                .scopeToProject(Identifier.byId(Constants.PROJECT_ID))
                .authenticate();
         //create ptr record
        setup();

        //list ptr record
        list();

        //list ptr record by filiter
        listByFliter();

        //list ptr record by filiter
        get();

        //restore ptr record
        restore();

    }

    private static void setup(){
        PtrBuilder builder = Builders.ptr();
        DesignatePTR ptr = (DesignatePTR) builder.ptrdname(Constants.PTRNAME).floatingIpId(Constants.FLOATINGIPID).description(Constants.PTR_DESCRIPTION).ttl(Constants.TTL).region(Constants.REGION).build();
        DesignatePTR result = os.dns().ptrs().setup(ptr);
    }
    private static void restore(){
        os.dns().ptrs().restore(Constants.REGION,Constants.FLOATINGIPID);
    }

    private static void get() {
        os.dns().ptrs().get(Constants.REGION, Constants.FLOATINGIPID);
    }

    private static void listByFliter() {
        Map<String,Object> fliters = new HashMap<>();
        fliters.put(Constants.LIMIT, Constants.PTR_LIMIT_VALUE);
        os.dns().ptrs().list(fliters);
    }

    private static void list() {
        os.dns().ptrs().list();
    }
}
