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
import com.huawei.openstack4j.openstack.vpc.v2.contants.RouteType;
import com.huawei.openstack4j.openstack.vpc.v2.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteV2Demo {

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


        // Create a Route
        Route routeCreate = Route.builder()
                .vpcId("******")
                .destination("******")
                .nexthop("******")
                .type(RouteType.peering)
                .build();

        Route createResp = null;
        try {
            createResp = osclient.vpcV2().routes().create(routeCreate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != createResp) {
            System.out.println("Create a Route success, id = " + createResp.getId());
        } else {
            System.out.println("Create a Route failed");
        }

        // Get a Route
        Route getResp = osclient.vpcV2().routes().get(createResp.getId());
        if (null != getResp) {
            System.out.println("Get a Route success, id = " + getResp.getId());
        } else {
            System.out.println("Get a Route failed");
        }

        // List all Route
        List<? extends Route> listResp = osclient.vpcV2().routes().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all Route success, size = " + listResp.size());
        } else {
            System.out.println("List all Route failed");
        }

        // List Route with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "2");
        List<? extends Route> listWithFilterResp = osclient.vpcV2().routes().list(filteringParams);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List Route with filter success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List Route with filter failed");
        }

        // Delete a Route
        ActionResponse resp = osclient.vpcV2().routes().delete(createResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a Route success");
        } else {
            System.out.println("Delete a Route failed");
        }


    }
}

