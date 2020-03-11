package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Service;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDemo {
    public static void main(String[] args){

        String user = "**********";
        String password = "**********";
        String userDomainId = "**********";
        String authUrl = "**********";

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        //List services with filterparam
        //GET  /v3/services
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("type", "**********");
        List<? extends Service> serviceList0 = osclient.identity().serviceEndpoints().list(filteringParams);
        for(Service service : serviceList0){
            System.out.println(service);
        }

        //Query service detail
        //GET  /v3/services/{service_id}
        String serviceId = "**********";
        Service sampleService = osclient.identity().serviceEndpoints().get(serviceId);
        System.out.println(sampleService);

        //Query service catalog
        //GET  /v3/auth/catalog
        List<? extends Service> serviceList03 = osclient.identity().tokens().getServiceCatalog();
        for (Service service : serviceList03) {
            System.out.println(service);
        }
    }
}
