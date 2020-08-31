package com.huawei.openstack.sample.ecs.v1;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.ecs.v1.domain.RemoteConsole;
import com.huawei.openstack4j.openstack.ecs.v1.domain.RemoteConsoleResponse;


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

        RemoteConsole remoteConsole = RemoteConsole.builder()
                .type(type)
                .protocol(protocol)
                .build();

        RemoteConsoleResponse remoteConsoleResponse = os.ecs().servers()
                .remoteConsole(serverId, remoteConsole);
        System.out.println(remoteConsoleResponse);

    }
}
