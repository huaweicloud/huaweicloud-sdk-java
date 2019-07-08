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
import com.huawei.openstack4j.openstack.vpc.v2.contants.VirtualChargingMode;
import com.huawei.openstack4j.openstack.vpc.v2.domain.*;

import java.util.ArrayList;
import java.util.List;

public class BandWidthV2Demo {
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

        // Create a BandWidth
        BandwidthCreate bandwidthCreate = BandwidthCreate.builder()
                .size(10)
                .name("SDK_demo")
                .build();
        BandwidthResp createResp = osclient.vpcV2().bandwidths().create(bandwidthCreate);

        if (null != createResp) {
            System.out.println("Create a BandWidth success, id = " + createResp.getId());
        } else {
            System.out.println("Create a BandWidth failed");
        }

        // Batch Create BandWidth
        BandwidthBatchCreate bandwidthBatchCreate = BandwidthBatchCreate.builder()
                .count(2)
                .size(10)
                .name("batch_create_demo")
                .build();
        List<BandwidthResp> batchCreateResp = osclient.vpcV2().bandwidths().batchCreate(bandwidthBatchCreate);

        if (null != batchCreateResp) {
            for (BandwidthResp resp : batchCreateResp) {
                System.out.println("Batch Create BandWidth success, id = " + resp.getId());
            }
        } else {
            System.out.println("Batch Create BandWidth failed");
        }


        // Update a BandWidth
        VirtualBandWidthUpdate virtualBandWidthUpdate = VirtualBandWidthUpdate.builder()
                .name("updateDemo")
                .size(20)
                .build();
        VirtualBandWidths virtualBandWidths = VirtualBandWidths.builder()
                .bandwidth(virtualBandWidthUpdate)
                .build();
        AsyncBandWidthRespEntity updateResp = osclient.vpcV2().bandwidths().update(virtualBandWidths, createResp.getId());
        if (null != updateResp) {
            System.out.println("Update a BandWidth success, id = " + updateResp.getVirtualBandWidthResp().getId());
        } else {
            System.out.println("Update a BandWidth failed");
        }

        // Insert ip into BandWidth
        List<PublicIpInfo> insertPublicIpInfoList = new ArrayList<>();
        PublicIpInfo insertPublicIpInfo = PublicIpInfo.builder()
                .publicipId("******")
                .build();
        insertPublicIpInfoList.add(insertPublicIpInfo);
        BandwidthInsert bandwidthInsert  = BandwidthInsert.builder()
                .publicipInfo(insertPublicIpInfoList)
                .build();

        BandwidthResp insertResp = osclient.vpcV2().bandwidths().insert(createResp.getId(),bandwidthInsert);

        if (null != insertResp) {
            System.out.println("Insert IP into BandWidth success, id = " + createResp.getId());
        } else {
            System.out.println("Insert IP into BandWidth failed");
        }



        // Remove ip into BandWidth
        List<PublicIpInfo> removePublicIpInfoList = new ArrayList<>();
        PublicIpInfo removePublicIpInfo = PublicIpInfo.builder()
                .publicipId("******")
                .build();
        removePublicIpInfoList.add(removePublicIpInfo);
        BandwidthRemove bandwidthRemove  = BandwidthRemove.builder()
                .publicipInfo(removePublicIpInfoList)
                .size(5)
                .chargeMode(VirtualChargingMode.BANDWIDTH)
                .build();

        ActionResponse removeResp = osclient.vpcV2().bandwidths().remove(createResp.getId(),bandwidthRemove);

        if (removeResp.isSuccess()) {
            System.out.println("Remove ip into BandWidth success");
        } else {
            System.out.println("Remove ip into BandWidth failed");
        }

        // Delete a BandWidth
        ActionResponse resp = osclient.vpcV2().bandwidths().delete(createResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a BandWidth success");
        } else {
            System.out.println("Delete a BandWidth failed");
        }



    }
}
