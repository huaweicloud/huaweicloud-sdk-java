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
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualBandWidths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BandWidthDemo {
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Get a bandWidths
        String bandWidthId = "******";
        VirtualBandWidths getResp = osclient.vpc().bandwidths().get(bandWidthId);
        if (null != getResp) {
            System.out.println("Get a subnet success, id = " + getResp.getId());
        } else {
            System.out.println("Get a subnet failed");
        }

        //List all bandWidths
        List<VirtualBandWidths> listResp = osclient.vpc().bandwidths().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all bandWidths success, size = " + listResp.size());
        } else {
            System.out.println("List all bandWidths failed");
        }

        //List bandWidths with filteringParams
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("share_type", "PER");
        List<VirtualBandWidths> listWithFilterResp = osclient.vpc().bandwidths().list(filteringParams);
        if (null != listWithFilterResp && listWithFilterResp.size() > 0) {
            System.out.println("List bandWidths with filteringParams success, size = " + listWithFilterResp.size());
        } else {
            System.out.println("List bandWidths with filteringParams failed");
        }
    }
}
