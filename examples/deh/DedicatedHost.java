package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.deh.domain.AutoPlacement;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHost;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHost.DedicatedHosts;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHostCreate;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHostType;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHostUpdate;
import com.huawei.openstack4j.openstack.deh.domain.QuotaSet;


public class DedicateHost {
    public static void main(String[] args) {

        // Using credentials for authentication
        String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
        String user = "xxxxxxxx"; //username
        String password = "xxxxxxxx"; //password
        String projectId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
        String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

        // create connection
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate()

        // create deh
        DedicatedHostCreate create = DedicatedHostCreate.builder()
                .availabilityZone("az1.dc1")
                .name("deh_test")
                .autoPlacement(AutoPlacement.off)
                .hostType("general")
                .quantity(1)
                .build();

        List<String> res = osclient.deh().dedicatedHosts().create(create);
        if (null != res){
            System.out.println("create deh success = " + res);
        }else{
            System.out.println("create deh  failed");
        }

        // get list of deh
        DedicatedHosts list = osclient.deh().dedicatedHosts().list();
        System.out.println("deh list = " + list.getDedicatedHosts() + ", total = " + list.getTotal());

        //get deh detail
        String id = res.get(0);
        DedicatedHost deh = osclient.deh().dedicatedHosts().get(id);
        if (null != deh){
            System.out.println("show deh detail success, deh = " + deh.toString());
        }else{
            System.out.println("show deh detail failed");
        }

        //update deh
        DedicatedHostUpdate hostUpdate = DedicatedHostUpdate.builder().name("test1").build();
        ActionResponse ac = osclient.deh().dedicatedHosts().update(id, hostUpdate);
        if (200 != ac.getCode()){
            System.out.println("update deh failed");
        }else{
            System.out.println("update deh success");
        }

        // get deh servers
        List<? extends Server> serverList = osclient.deh().dedicatedHosts().list(id,"1","");
        if (null != serverList){
            System.out.println("query deh server list success, serverList = " + serverList.toString());
        }else{
            System.out.println("query deh server list failed");
        }

        //delete deh
        ActionResponse ac1 = osclient.deh().dedicatedHosts().delete(id);
        if (200 != ac1.getCode()){
            System.out.println("delete deh failed");
        }else{
            System.out.println("delete deh success");
        }

        //get deh type
        String az = "kvmxen.dc1";
        List<DedicatedHostType> dedicatedHostTypes = osclient.deh().dedicatedHosts().listHostType(az);
        if (null != dedicatedHostTypes){
            System.out.println("query deh host types success, dedicatedHostTypes = " + dedicatedHostTypes.toString());
        }else{
            System.out.println("query deh  host types failed");
        }

        // get quota
        String tenant_id = "128a7bf965154373a7b73c89eb6b65aa";
        QuotaSet quotaSet = osclient.deh().quotaSets().get(tenant_id);
        if (null !=quotaSet){
            System.out.println("query deh quota success, quota = " + quotaSet.toString());
        }else{
            System.out.println("query deh quota failed");
        };

    }
}