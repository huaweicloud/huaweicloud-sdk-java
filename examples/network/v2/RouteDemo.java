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
import com.huawei.openstack4j.model.network.AttachInterfaceType;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.model.network.RouterInterface;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronRouter;

import java.util.List;

public class RouteDemo {
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();


        //Add router interface
        String routerId = "******";
        String subnetsId = "******";
        RouterInterface interfaceResp = osclient.networking().router().attachInterface(routerId, AttachInterfaceType.SUBNET, subnetsId);
        if (null != interfaceResp) {
            System.out.println("Add router interface success, id = " + interfaceResp.getId());
        } else {
            System.out.println("Add router interface failed");
        }

        //Create a router
        String routerName = "******";
        String networkId = "******";

        Router router = NeutronRouter.builder().name(routerName).adminStateUp(true).externalGateway(networkId).build();
        Router createResp = osclient.networking().router().create(router);
        if (null != createResp) {
            System.out.println("Create a router success, id = " + createResp.getId());
        } else {
            System.out.println("Create a router failed");
        }

        //Create a basic router with minimal params
        Router miniCreateResp = osclient.networking().router().create(routerName, true);
        if (null != miniCreateResp) {
            System.out.println("Create a router success, id = " + miniCreateResp.getId());
        } else {
            System.out.println("Create a router failed");
        }

        //Delete a router
        ActionResponse resp = osclient.networking().router().delete(miniCreateResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a router success");
        } else {
            System.out.println("Delete a router failed");
        }

        //Delete a router interface
        RouterInterface routerInterfaceDeleteResp = osclient.networking().router().detachInterface(routerId, subnetsId, null);
        if (null != routerInterfaceDeleteResp) {
            System.out.println("Delete a router interface success, id = " + routerInterfaceDeleteResp.getId());
        } else {
            System.out.println("Delete a router interface failed");
        }

        //Get a router
        Router routerGetResp = osclient.networking().router().get(routerId);
        if (null != routerGetResp) {
            System.out.println("Get a router success, id = " + routerGetResp.getId());
        } else {
            System.out.println("Get a router failed");
        }

        //List all routers
        List<? extends Router> routerListResp = osclient.networking().router().list();
        if (null != routerListResp && routerListResp.size() > 0) {
            System.out.println("List all routers success, size = " + routerListResp.size());
        } else {
            System.out.println("List all routers failed");
        }

        //Toggle AdminStateUp
        Router routerToggleResp = osclient.networking().router().toggleAdminStateUp(routerId, true);
        if (null != routerToggleResp) {
            System.out.println("Toggle AdminStateUp success, id = " + routerToggleResp.getId());
        } else {
            System.out.println("Toggle AdminStateUp failed");
        }

        //Update a router
        Router routerUpdate = NeutronRouter.builder().name(routerName).adminStateUp(true).externalGateway(networkId).id(routerId).build();
        Router routerUpdateResp = osclient.networking().router().update(routerUpdate);
        if (null != routerUpdateResp) {
            System.out.println("Update a router success, id = " + routerUpdateResp.getId());
        } else {
            System.out.println("Update a router failed");
        }
    }
}
