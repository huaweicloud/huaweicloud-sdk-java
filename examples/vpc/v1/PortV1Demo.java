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
import com.huawei.openstack4j.openstack.vpc.v1.domain.Port;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PortCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PortUpdate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortV1Demo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create a port
        String name = "******";
        String netWorkId = "******";
        PortCreate port = PortCreate.builder()
                .networkId(netWorkId)
                .name(name)
                .build();
        Port createResp = osclient.vpc().ports().create(port);
        if (null != createResp) {
            System.out.println("Create a port success, id = " + createResp.getId());
        } else {
            System.out.println("Create a port failed");
        }


        // Get a port
        Port getResp = osclient.vpc().ports().get(createResp.getId());
        if (null != getResp) {
            System.out.println("Get a port success, id = " + getResp.getId());
        } else {
            System.out.println("Get a port failed");
        }

        // List all ports
        List<? extends Port> listResp = osclient.vpc().ports().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all ports success, size = " + listResp.size());
        } else {
            System.out.println("List all ports failed");
        }

        // List ports with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends Port> listWithFilterResp = osclient.vpc().ports().list(filteringParams);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List ports with filter success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List ports with filter failed");
        }

        // Update a port
        PortUpdate portUpdate = PortUpdate.builder()
                .name("testupdate")
                .build();
        Port updateResp = osclient.vpc().ports().update(getResp.getId(),portUpdate);
        if (null != updateResp) {
            System.out.println("Update a port success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a port failed");
        }

        // Delete a port
        ActionResponse resp = osclient.vpc().ports().delete(updateResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a port success");
        } else {
            System.out.println("Delete a port failed");
        }


    }
}

