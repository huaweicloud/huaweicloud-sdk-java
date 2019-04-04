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
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.model.network.NetworkUpdate;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetwork;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetworkUpdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDemo {


    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a Network
        String networkName = "******";
        String tenantId = "******";
        Network network = NeutronNetwork.builder().name(networkName).adminStateUp(true).tenantId(tenantId).isShared(false).build();
        Network createResp = osclient.networking().network().create(network);
        if (null != createResp) {
            System.out.println("Create a Network success, id = " + createResp.getId());
        } else {
            System.out.println("Create a Network failed");
        }

        //Update a Network
        String networkId = "******";
        NetworkUpdate networkUpdate = NeutronNetworkUpdate.builder().name(networkName).shared(false).build();
        Network updateResp = osclient.networking().network().update(networkId, networkUpdate);
        if (null != updateResp) {
            System.out.println("Update a Network success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a Network failed");
        }

        //Get a Network
        Network getResp = osclient.networking().network().get(networkId);
        if (null != getResp) {
            System.out.println("Get a Network success, id = " + getResp.getId());
        } else {
            System.out.println("Get a Network failed");
        }

        //List all networks
        List<? extends Network> listResp = osclient.networking().network().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all Network success, size = " + listResp.size());
        } else {
            System.out.println("List all Network failed");
        }

        //List networks with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("status", "ACTIVE");
        List<? extends Network> listWithFilterResp = osclient.networking().network().list(filteringParams);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List Network with filter success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List Network with filter failed");
        }

        //Delete a network
        ActionResponse rep = osclient.networking().network().delete(networkId);
        if (rep.isSuccess()) {
            System.out.println("Delete a network success");
        } else {
            System.out.println("Delete a network failed");
        }

    }
}