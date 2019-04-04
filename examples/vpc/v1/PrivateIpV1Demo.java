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
import com.huawei.openstack4j.openstack.vpc.v1.domain.PrivateIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PrivateIp.PrivateIps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrivateIpDemo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create PrivateIp
        PrivateIp privateIp = PrivateIp.builder().subnetId("531dec0f-3116-411b-a21b-e612e42349fd")
                .build();
        List<PrivateIp> privateIpList = new ArrayList<>();
        privateIpList.add(privateIp);
        PrivateIps privateIps = new PrivateIps();
        privateIps.setPrivateips(privateIpList);

        List<? extends PrivateIp> createResp = osclient.vpc().privateIps().apply(privateIps);
        if (null != createResp) {
            System.out.println("create PrivateIp success, id = " + createResp.get(0).getId());
        } else {
            System.out.println("create PrivateIp failed");
        }

        // Get PrivateIp
        PrivateIp getResp = osclient.vpc().privateIps().get(createResp.get(0).getId());
        if (null != getResp) {
            System.out.println("get PrivateIp success, id = " + getResp.getId());
        } else {
            System.out.println("get PrivateIp failed");
        }

        // List PrivateIps
        List<? extends PrivateIp> listResp = osclient.vpc().privateIps().list("531dec0f-3116-411b-a21b-e612e42349fd");
        if (null != listResp) {
            System.out.println("List PrivateIps success, size is = " + listResp.size());
        } else {
            System.out.println("List PrivateIps failed");
        }

        // List PrivateIps with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends PrivateIp> listWithFilterResp = osclient.vpc().privateIps().list("531dec0f-3116-411b-a21b-e612e42349fd",filteringParams);
        if (null != listWithFilterResp) {
            System.out.println("List PrivateIps success, size is = " + listResp.size());
        } else {
            System.out.println("List PrivateIps failed");
        }

        //Delete a PrivateIp
        ActionResponse resp = osclient.vpc().privateIps().delete(getResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a PrivateIp success");
        } else {
            System.out.println("Delete a PrivateIp failed");
        }

    }
}
