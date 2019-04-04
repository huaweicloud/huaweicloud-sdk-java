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
import com.huawei.openstack4j.openstack.vpc.v2.contants.IpType;
import com.huawei.openstack4j.openstack.vpc.v2.contants.ShareType;
import com.huawei.openstack4j.openstack.vpc.v2.domain.AsyncPublicipRespEntity;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidth;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualPublicIpType;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualPublicIps;


public class PublicIpDemoV2 {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a publicIp
        VirtualPublicIps virtualPublicIps = null;
        VirtualPublicIpType virtualPublicIpType = null;
        VirtualBandWidth virtualBandWidth = null;

        virtualBandWidth.builder().name("bandwidthDemo").size(10).shareType(ShareType.PER).build();
        virtualPublicIpType.builder().type(IpType.BGP);
        virtualPublicIps.builder().virtualPublicIp(virtualPublicIpType).virtualBandwidth(virtualBandWidth).build();
        AsyncPublicipRespEntity createResp = osclient.vpcV2().publicips().apply(virtualPublicIps);
        if (null != createResp) {
            System.out.println("Create a publicIp success, id = " + createResp.getOrderId());
        } else {
            System.out.println("Create a publicIp failed");
        }

    }
}
