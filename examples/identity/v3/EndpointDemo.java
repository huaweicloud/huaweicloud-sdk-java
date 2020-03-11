package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Endpoint;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndpointDemo {

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

        //List endpoints with filterparam
        //GET  /v3/endpoints
        Map<String, String> filteringParams01 = new HashMap<>();
        String serviceId01 = "**********";
        filteringParams01.put("interface", "public");
        filteringParams01.put("service_id", serviceId01);
        List<? extends Endpoint> endpointList01 = osclient.identity().serviceEndpoints().listEndpoints(filteringParams01);
        for(Endpoint endpoint01 : endpointList01){
            System.out.println(endpoint01);
        }

        //Querying Endpoint Details
        //GET  /v3/endpoints/{endpoint_id}
        String endpointId02 = "**********";
        Endpoint sampleEndpoint02 = osclient.identity().serviceEndpoints().getEndpoint(endpointId02);
        System.out.println(sampleEndpoint02);
    }
}
