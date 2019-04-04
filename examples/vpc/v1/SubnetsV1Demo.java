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
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.vpc.v1.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubnetsDemo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create subnet
        SubnetCreate subnet = SubnetCreate.builder()
                .name("testSubnet")
                .cidr("192.168.20.0/24")
                .gatewayIp("192.168.20.1")
                .vpcId("3ec3b33f-ac1c-4630-ad1c-7dba1ed79d85")
                .build();

        Subnet createResp = osclient.vpc().subnets().create(subnet);
        if (null != createResp) {
            System.out.println("create subnet success, id = " + createResp.getId());
        } else {
            System.out.println("create subnet failed");
        }

        // Get Subnet
        Subnet getResp = osclient.vpc().subnets().get(createResp.getId());
        if (null != getResp) {
            System.out.println("get Subnet success, id = " + getResp.getId());
        } else {
            System.out.println("get Subnet failed");
        }

        // List all Subnets
        List<? extends Subnet> listResp = osclient.vpc().subnets().list();
        if (null != listResp) {
            System.out.println("List all Subnets success, size is = " + listResp.size());
        } else {
            System.out.println("get Subnets failed");
        }

        // List Subnets with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends Subnet> listWithFilterResp = osclient.vpc().subnets().list(filteringParams);
        if (null != listWithFilterResp) {
            System.out.println("List Subnets success, size is = " + listResp.size());
        } else {
            System.out.println("List Subnets failed");
        }

        //Update a Subnet
        SubnetUpdate subnetUpdate = SubnetUpdate.builder().name("testName").build();
        SubnetUpdateResp updateResp = osclient.vpc().subnets().update(getResp.getVpcId(),getResp.getId(),subnetUpdate);
        if (null != updateResp) {
            System.out.println("Update a Subnet success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a Subnet failed");
        }

        //Delete a Subnet
        ActionResponse resp = osclient.vpc().subnets().delete(getResp.getVpcId(),getResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a Subnet success");
        } else {
            System.out.println("Delete a Subnet failed");
        }

    }
}
