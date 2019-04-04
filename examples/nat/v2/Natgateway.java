package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWayCreate;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWayUpdate;




public class Natgateway {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();

        //config of natGateWay create
        String natGateWayName = "nat_gateway_create_test";
        String routerID = "22be6617-e727-436c-a905-d050ea4aead8";
        String networkID = "96afbb64-94a8-4b76-83bb-11c6edb02424";
        String natGateWayDescription = "nat_gateway_create_description";
        String spec = "3";

        //create natGateWay
        NatGateWayCreate natGateWayCreate = NatGateWayCreate.builder().name(natGateWayName).
                description(natGateWayDescription).routerId(routerID).internalNetworkId(networkID).spec(spec).build();
        osclient.nat().natGateWays().create(natGateWayCreate);

        //get NatGateWayList
        osclient.nat().natGateWays().list();

        //get NatGateWay detail
        String natGateWayID = "8d45f435-61f7-4a30-b955-ae40f3e3989b";
        osclient.nat().natGateWays().get(natGateWayID);

        //config of natGateWay update
        String natGateWayUpdateName = "nat_gateway_update_test";
        String natGateWayUpdateDescription = "nat_gateway_update_description";
        String updateSpec = "1";

        //update natGateWay
        NatGateWayUpdate natGateWayUpdate = NatGateWayUpdate.builder().name(natGateWayUpdateName).
                description(natGateWayUpdateDescription).spec(updateSpec).build();
        osclient.nat().natGateWays().update(natGateWayUpdate, natGateWayID);

        //delete NatGateWay
        osclient.nat().natGateWays().delete(natGateWayID);


    }


}