package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.util.HashMap;
import java.util.Map;

public class Invoke {
    public static void main(String[] args) {
        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        String functionUrn = "replace-you-functionUrn";
        Map<String, String> map = new HashMap<>();

        map.put("Message", "Hello world");

        //Synchronous execution function
        osclient.functionGraph().function().invokeFunction(functionUrn, map);

        //Asynchronous call function
        osclient.functionGraph().function().asyncInvokeFunction(functionUrn, map);
    }
}
