package com.huawei.openstack.sample.ecs.v1;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.compute.domain.NovaRemoteConsole;
import com.huawei.openstack4j.openstack.compute.domain.NovaRemoteConsoleResponse;

import java.util.HashMap;
import java.util.Map;

public class RemoteConsoleDemo {
    public static void main(String[] args) {
        String authUrl = "https://iam.xxxxxxx.huaweicloud.com/v3"; //endpoint Url
        String username = "xxxxxxx"; //username
        String password = "xxxxxxx"; //password
        String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
        String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

        Config config = Config.newConfig().withSSLVerificationDisabled();
        // create connection
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .withConfig(config)
                .credentials(username, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        String serverId = "xxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String type = "novnc";
        String protocol = "vnc";

        NovaRemoteConsole remoteConsole = NovaRemoteConsole.builder()
                .type(type)
                .protocol(protocol)
                .build();

        // "X-OpenStack-Nova-API-Version","2.6"
        Map<String, String> header = new HashMap<>();
        header.put("X-OpenStack-Nova-API-Version", "xxx");
        NovaRemoteConsoleResponse remoteConsoleResponse = os.compute().servers()
                .getRemoteConsole(serverId, remoteConsole, header);
        System.out.println(remoteConsoleResponse);

    }
}
