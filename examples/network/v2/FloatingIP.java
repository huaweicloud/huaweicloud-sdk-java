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
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.NetFloatingIP;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronFloatingIP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloatingIP {


    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a Floatingip
        String floatingNetworkId = "******";
        String portId = "******";
        NetFloatingIP floatingIp = NeutronFloatingIP.builder().floatingNetworkId(floatingNetworkId).portId(portId).build();
        NetFloatingIP createResp = osclient.networking().floatingip().create(floatingIp);

        if (null != createResp) {
            System.out.println("create Floatingip success, id = " + createResp.getId());
        } else {
            System.out.println("create Floatingip failed");
        }

        //Associate Floatingip and port
        String floatingipId = "******";
        NetFloatingIP associateResp = osclient.networking().floatingip().associateToPort(floatingipId, portId);

        if (null != associateResp) {
            System.out.println("Associate Floatingip and port success, id = " + associateResp.getId());
        } else {
            System.out.println("Associate Floatingip and port failed");
        }

        //Disassociate Floatingip and port
        NetFloatingIP disassociateResp = osclient.networking().floatingip().disassociateFromPort(floatingipId);

        if (null != disassociateResp) {
            System.out.println("Disassociate Floatingip and port success, id = " + disassociateResp.getId());
        } else {
            System.out.println("Disassociate Floatingip and port failed");
        }

        //Get Floatingip
        NetFloatingIP getResp = osclient.networking().floatingip().get(floatingipId);
        if (null != getResp) {
            System.out.println("Get Floatingip success, id = " + getResp.getId());
        } else {
            System.out.println("Get Floatingip failed");
        }

        //List all Floatingips
        List<? extends NetFloatingIP> listResp =  osclient.networking().floatingip().list();
        if (listResp != null && listResp.size() > 0) {
            System.out.println("List all Floatingips success, size = " + listResp.size());
        } else {
            System.out.println("List all Floatingips failed");
        }

        //List all Floatingips with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("status", "DOWN");
        List<? extends NetFloatingIP> listWithFilterResp =osclient.networking().floatingip().list(filteringParams);
        if (listWithFilterResp != null && listWithFilterResp.size() > 0) {
            System.out.println("List all Floatingips with filter success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List all Floatingips with filter failed");
        }

        //Delete a Floatingip
        ActionResponse resp = osclient.networking().floatingip().delete(floatingipId);
        if (resp.isSuccess()) {
            System.out.println("Delete a Floatingip success");
        } else {
            System.out.println("Delete a Floatingip failed");
        }


    }


}
