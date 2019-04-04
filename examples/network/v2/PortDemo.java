/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.Port;
import com.huawei.openstack4j.model.network.options.PortListOptions;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronPort;

import java.util.List;

public class PortDemo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a port
        String ipAddress = "******";
        String subnetId = "******";
        String name = "******";
        String netWorkId = "******";
        String tennatId = "******";
        Port port = NeutronPort.builder().adminState(true).fixedIp(ipAddress, subnetId).name(name).networkId(netWorkId).tenantId(tennatId).build();
        Port createResp = osclient.networking().port().create(port);
        if (null != createResp) {
            System.out.println("Create a port success, id = " + createResp.getId());
        } else {
            System.out.println("Create a port failed");
        }

        //Delete a port
        String portId = "******";
        ActionResponse resp = osclient.networking().port().delete(portId);
        if (resp.isSuccess()) {
            System.out.println("Delete a port success");
        } else {
            System.out.println("Delete a port failed");
        }

        //Get a port
        Port getResp = osclient.networking().port().get(portId);
        if (null != getResp) {
            System.out.println("Get a port success, id = " + getResp.getId());
        } else {
            System.out.println("Get a port failed");
        }

        //List all ports
        List<? extends Port> listResp = osclient.networking().port().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all ports success, size = " + listResp.size());
        } else {
            System.out.println("List all ports failed");
        }

        //List ports with filter
        PortListOptions options = PortListOptions.create();
        options.adminState(false);
        options.deviceId("******");
        List<? extends Port> listWithFilterResp = osclient.networking().port().list(options);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List ports with filter success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List ports with filter failed");
        }

        //Update a port
        Port portUpdate = NeutronPort.builder().name("******").build();
        portUpdate.setId("016ed520-3cfb-4e2c-919d-ce5eb1071aac");
        Port updateResp = osclient.networking().port().update(portUpdate);
        if (null != updateResp) {
            System.out.println("Update a port success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a port failed");
        }

    }
}

