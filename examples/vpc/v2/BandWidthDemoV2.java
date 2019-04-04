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
import com.huawei.openstack4j.openstack.vpc.v2.domain.AsyncBandWidthRespEntity;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidthUpdate;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidths;

public class BandWidthDemoV2 {
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Update a BandWidth
        String bandwidthId = "******";
        VirtualBandWidths virtualBandWidths = null;
        VirtualBandWidthUpdate virtualBandWidthUpdate = null;
        virtualBandWidthUpdate.builder().name("updateDemo").size(20).build();
        virtualBandWidths.builder().bandwidth(virtualBandWidthUpdate).build();
        AsyncBandWidthRespEntity updateResp = osclient.vpcV2().bandwidths().update(virtualBandWidths, bandwidthId);
        if (null != updateResp) {
            System.out.println("Update a BandWidth success, orderId = " + updateResp.getOrderId());
        } else {
            System.out.println("Update a BandWidth failed");
        }

    }
}
