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
import com.huawei.openstack4j.openstack.vpc.v1.contants.IpType;
import com.huawei.openstack4j.openstack.vpc.v1.contants.ShareType;
import com.huawei.openstack4j.openstack.vpc.v1.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicIpV1Demo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create Publicip
        VirtualPublicIpType virtualPublicIpType = VirtualPublicIpType.builder()
                .type(IpType.BGP)
                .build();

        VirtualPublicIps virtualPublicIVirtualPublicIpsResp = VirtualPublicIps.builder()
                .virtualPublicIp(virtualPublicIpType)
                .virtualBandwidth(VirtualBandWidth.builder().shareType(ShareType.PER).build())
                .build();

        VirtualPublicIpsResp createResp = osclient.vpc().publicips().apply(virtualPublicIVirtualPublicIpsResp);
        if (null != createResp) {
            System.out.println("create Publicip success, id = " + createResp.getId());
        } else {
            System.out.println("create Publicip failed");
        }

        // Get Publicip
        VirtualPublicIp getResp = osclient.vpc().publicips().get(createResp.getId());
        if (null != getResp) {
            System.out.println("get Publicip success, id = " + getResp.getId());
        } else {
            System.out.println("get Publicip failed");
        }

        // List all Publicips
        List<? extends PublicIp> listResp = osclient.vpc().publicips().list();
        if (null != listResp) {
            System.out.println("List all Publicips success, size is = " + listResp.size());
        } else {
            System.out.println("get Publicips failed");
        }

        // List Publicips with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends PublicIp> listWithFilterResp = osclient.vpc().publicips().list(filteringParams);
        if (null != listWithFilterResp) {
            System.out.println("List Publicips success, size is = " + listResp.size());
        } else {
            System.out.println("List Publicips failed");
        }

        //Update a Publicip
        PublicIpUpdate publicIpUpdate = PublicIpUpdate.builder().ipVersion(6).build();
        PublicIp updateResp = osclient.vpc().publicips().update(getResp.getId(),publicIpUpdate);
        if (null != updateResp) {
            System.out.println("Update a Publicip success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a Publicip failed");
        }

        //Delete a Publicip
        ActionResponse resp = osclient.vpc().publicips().delete(getResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a Publicip success");
        } else {
            System.out.println("Delete a Publicip failed");
        }

    }
}
