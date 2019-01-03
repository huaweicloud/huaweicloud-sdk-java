package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.huawei.openstack4j.model.dns.v2.builder.ZoneBuilder;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateZone;
import com.huawei.openstack.sample.Constant;


public class Zones {

    private static OSClient.OSClientV3 os;

    public static void main(String[] args) {

         os = OSFactory.builderV3()
                .endpoint(Constants.AUTH_URL)
                .credentials(Constants.USERNAME, Constants.PASSWORD, Identifier.byId(Constants.USERDOMAINID))
                .scopeToProject(Identifier.byId(Constants.PROJECT_ID))
                .authenticate();

        //create public zone
        createPublicZone();

        //create zones
        list();

        //create nameservers
        listNameServers();

        //get zone by id
        getZoneByZoneId();

        //delete public zone
        deleteZone();

        //create private zone
        createPrivateZone();

        //assoicate router with private zone
        assoicateRouter();

        //disassoicate router with private zone
        disassoicateRouter();

        //delete private zone
        deleteZone();

    }

    private static void createPublicZone() {
        ZoneBuilder builder = Builders.zone();

        Zone zone = builder.name(Constants.ZONE_NAME)
                .email(Constants.MAIL)
                .description(Constants.ZONE_DESCRIPTION)
                .type(ZoneType.PUBLIC)
                .build();

        DesignateZone designateZone = (DesignateZone) os.dns().zones().create(zone);
        Constants.ZONE_ID = designateZone.getId();
    }

    private static void createPrivateZone() {
        DesignateZone.Router router = new DesignateZone.Router(Constants.CREATE_ROUTER_ID,Constants.REGION,null);
        ZoneBuilder builder = Builders.zone();

        Zone zone = builder.name(Constants.ZONE_NAME)
                .email(Constants.MAIL)
                .description(Constants.ZONE_DESCRIPTION)
                .type(ZoneType.PRIVATE)
                .router(router)
                .build();

        DesignateZone designateZone = (DesignateZone) os.dns().zones().create(zone);
        Constants.ZONE_ID = designateZone.getId();

    }

    private static void list() {
        os.dns().zones().list();
    }

    private static void getZoneByZoneId() {
        os.dns().zones().get(Constants.ZONE_ID);
    }

    private static void listNameServers() {
         os.dns().zones().listNameservers(Constants.ZONE_ID);
    }

    private static void deleteZone(){
        os.dns().zones().delete(Constants.ZONE_ID);
    }

    private static void assoicateRouter(){
        DesignateZone.Router router = new DesignateZone.Router(Constants.ASSOICATE_ROUTER_ID, Constants.REGION,null);
        os.dns().zones().associateRouter(Constants.ZONE_ID,router);
    }

    private static void disassoicateRouter(){
        DesignateZone.Router router = new DesignateZone.Router(Constants.ASSOICATE_ROUTER_ID, Constants.REGION,null);
        os.dns().zones().disassociateRouter(Constants.ZONE_ID,router);
    }
}