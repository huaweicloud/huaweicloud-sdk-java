package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.nat.domain.*;


public class Snatrule {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();

        //config of snatRule
        String eipIDForSnatRule = "91dbd312-732d-4eb0-949e-a7a09803b47b";
        String natGateWayID = "8d45f435-61f7-4a30-b955-ae40f3e3989b";
        String networkID = "247e2ef9-4625-4cfd-870a-f128f6f38acf";

        //create snatRule
        SnatRuleCreate snatRuleCreate = SnatRuleCreate.builder().natGatewayId(natGateWayID).networkId(networkID).
                floatingIpId(eipIDForSnatRule).build();
        osclient.nat().snatRules().create(snatRuleCreate);

        //get snatRuleList
        osclient.nat().snatRules().list();

        //get snatRule detail
        String snatRuleID = "5b95c675-69c2-4656-ba06-58ff72e1d338";
        osclient.nat().snatRules().get(snatRuleID);

        //delete snatRule
        osclient.nat().snatRules().delete(snatRuleID);

    }

}
