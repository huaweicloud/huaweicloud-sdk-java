package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Region;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.List;

public class RegionDemo {

    public static void main(String[] args){

        String user = "**********";
        String password = "**********";
        String userDomainId = "**********";
        String authUrl = "**********";

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        //Query a Region List
        //GET  /v3/regions
        List<? extends Region> regionList = osclient.identity().regions().list();
        for(Region region : regionList){
            System.out.println(region);
        }

        //Query region detail
        //GET  /v3/regions/{region_id}
        String regionId = "**********";
        Region region = osclient.identity().regions().get(regionId);
        System.out.println("Region Id : " + region.getId());

    }
}
