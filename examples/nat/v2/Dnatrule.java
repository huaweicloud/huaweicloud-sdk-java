package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.nat.domain.DnatRuleCreate;



public class Dnatrule {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();

        //config of dnatRule
        String eipIDForDnatRule = "e4c558d4-7905-4429-bf60-0c20bf856b43";
        String portID = "7d8c32c5-8e8c-419c-bfb4-123f467f87e4";
        Integer internalServicePort = 1;
        Integer externalServicePort = 1;
        String protocol = "udp";
        String natGateWayID = "8d45f435-61f7-4a30-b955-ae40f3e3989b";

        //create dnatRule
        DnatRuleCreate dnatRuleCreate = DnatRuleCreate.builder().floatingIpId(eipIDForDnatRule).portId(portID).
                internalServicePort(internalServicePort).externalServicePort(externalServicePort).protocol(protocol).
                natGatewayId(natGateWayID).build();
        osclient.nat().dnatRules().create(dnatRuleCreate);

        //get dnatRuleList
        osclient.nat().dnatRules().list();

        //get dnatRule detail
        String dnatRuleID = "5b95c675-69c2-4656-ba06-58ff72e1d338";
        osclient.nat().dnatRules().get(dnatRuleID);

        //delete dnatRule
        osclient.nat().dnatRules().delete(dnatRuleID);

    }

}