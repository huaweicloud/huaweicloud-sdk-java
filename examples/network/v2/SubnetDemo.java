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
import com.huawei.openstack4j.model.network.IPVersionType;
import com.huawei.openstack4j.model.network.Subnet;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSubnet;

import java.util.List;

public class SubnetDemo {
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a subnet
        String networdId = "******";
        String tenantId = "******";
        String dns = "8.8.8.8";
        String poolsStart = "10.0.10.2";
        String poolsEnd = "10.0.10.254";
        String gateWay = "10.0.10.1";
        String cidr = "10.0.10.0/24";
        Subnet subnet = NeutronSubnet.builder().name("subnetsdemo").enableDHCP(true).networkId(networdId)
                .tenantId(tenantId).addDNSNameServer(dns).addPool(poolsStart, poolsEnd).ipVersion(IPVersionType.V4)
                .gateway(gateWay).cidr(cidr).build();
        Subnet subnetCreateResp = osclient.networking().subnet().create(subnet);
        if (null != subnetCreateResp) {
            System.out.println("Create a subnet success, id = " + subnetCreateResp.getId());
        } else {
            System.out.println("Create a subnet failed");
        }


        //Get a subnet
        String subnetsId = "******";
        Subnet getResp = osclient.networking().subnet().get(subnetsId);
        if (null != getResp) {
            System.out.println("Get a subnet success, id = " + getResp.getId());
        } else {
            System.out.println("Get a subnet failed");
        }

        //List all subnets
        List<? extends Subnet> listResp = osclient.networking().subnet().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all subnets success, size = " + listResp.size());
        } else {
            System.out.println("List all subnets failed");
        }

        //Update a subnet
        String dnsUpdate = "1.1.1.1";
        Subnet subnetUpdate = NeutronSubnet.builder().name("subnetUpdateDemo").addDNSNameServer(dnsUpdate).enableDHCP(false).build();
        Subnet updateResp = osclient.networking().subnet().update(subnetsId, subnetUpdate);
        if (null != updateResp) {
            System.out.println("Update a subnet success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a subnet failed");
        }

        //Delete a subnet
        ActionResponse resp = osclient.networking().subnet().delete(subnetsId);
        if (resp.isSuccess()) {
            System.out.println("Delete a subnet success");
        } else {
            System.out.println("Delete a subnet failed");
        }
    }
}
