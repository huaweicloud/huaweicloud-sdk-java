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
package com.huawei.sdk;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.vpc.v2.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeeringV2Demo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        // Initial client
        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create a peering
        VpcInfo requestVpc = VpcInfo.builder()
                .vpcId("******")
                .tenantId("******")
                .build();
        VpcInfo acceptVpc = VpcInfo.builder()
                .vpcId("******")
                .tenantId("******")
                .build();

        PeeringCreate peeringCreate = PeeringCreate.builder()
                .name("testjavasdk")
                .requestVpcInfo(requestVpc)
                .acceptVpcInfo(acceptVpc)
                .build();

        Peering createResp = osclient.vpcV2().peerings().create(peeringCreate);

        if (null != createResp) {
            System.out.println("Create a peering success, id = " + createResp.getId());
        } else {
            System.out.println("Create a peering failed");
        }

        // Get a peering
        Peering getResp = osclient.vpcV2().peerings().get(createResp.getId());
        if (null != getResp) {
            System.out.println("Get a peering success, id = " + getResp.getId());
        } else {
            System.out.println("Get a peering failed");
        }

        // List all peerings
        List<? extends Peering> listResp = osclient.vpcV2().peerings().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all peerings success, size = " + listResp.size());
        } else {
            System.out.println("List all peerings failed");
        }

        // List peerings with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends Peering> listWithFilterResp = osclient.vpcV2().peerings().list(filteringParams);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List peerings with filter success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List peerings with filter failed");
        }

        // Update a peering
        PeeringUpdate peeringUpdate = PeeringUpdate
                .builder()
                .name("peering-5409")
                .build();
        Peering updateResp = osclient.vpcV2().peerings().update(createResp.getId(), peeringUpdate);
        if (null != updateResp) {
            System.out.println("Update a peering success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a peering failed");
        }

        // Delete a peering
        ActionResponse resp = osclient.vpcV2().peerings().delete(createResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a port success");
        } else {
            System.out.println("Delete a port failed");
        }

        // Accept peering request
        PeeringRequest acceptResp = osclient.vpcV2().peerings().accept("******");
        if (null != acceptResp) {
            System.out.println("Accept request success, id = " + acceptResp.getId());
        } else {
            System.out.println("Accept request failed");
        }

        // Reject peering request
        PeeringRequest rejectResp = osclient.vpcV2().peerings().reject("******");
        if (null != rejectResp) {
            System.out.println("Reject request success, id = " + rejectResp.getId());
        } else {
            System.out.println("Reject request failed");
        }
    }
}

