/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co.,Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack.sample;


import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.ecs.v1.domain.FixedIp;
import com.huawei.openstack4j.openstack.ecs.v1.domain.InterfaceAttachment;
import com.huawei.openstack4j.openstack.ecs.v1.domain.InterfaceAttachment.InterfaceAttachments;

public class CloudServerInterfaceService {
    public static void main(String[] args) {

        // Using credentials for authentication
        String authUrl = "https://iam.XXX.YYY.com/v3"; //endpoint Url
        String user = "xxxxxxxx"; //username
        String password = "xxxxxxxx"; //password
        String projectId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
        String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

        //create connection
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        String serverId = "xxxxxx";
        InterfaceAttachments interfaceAttachments = os.ecs().interfaces().list(serverId);
        if (interfaceAttachments.getInterfaces() != null && interfaceAttachments.getInterfaces().size() > 0) {

            System.out.println("interfaceAttachment size:" + interfaceAttachments.getInterfaces().size());
            System.out.println("interfaceAttachment:" + interfaceAttachments.getInterfaces().toString());

            InterfaceAttachment interfaceAttachment = interfaceAttachments.getInterfaces().get(0);
            System.out.println("port_state:" + interfaceAttachment.getPortState());
            System.out.println("net_id:" + interfaceAttachment.getNetworkId());
            System.out.println("port_id:" + interfaceAttachment.getPortId());
            System.out.println("mac_addr:" + interfaceAttachment.getMacAddr());

            if (interfaceAttachment.getFixedIps().size() > 0) {
                FixedIp fixedIp = interfaceAttachment.getFixedIps().get(0);
                System.out.println("subnet_id:" + fixedIp.getSubnetId());
                System.out.println("ip_address:" + fixedIp.getIpAddress());
            }

        }
    }
}
