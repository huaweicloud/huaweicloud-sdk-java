package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.User;
import com.huawei.openstack4j.openstack.OSFactory;

public class TokenDemo {

    public static void main(String[] args) {

        // setup the authentication credit
        String username = "replace-with-your-username";
        String password = "replace-with-your-password";
        String userDomainId = "replace-with-your-user-domain-id";
        String authUrl = "https://iam.example.com/v3";

        OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(username, password, Identifier.byId(userDomainId)).scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        // use client to visit IAM list user API
        List<? extends User> list = osclient.identity().users().list();
        if(list.size() > 0)
        {
            System.out.println("get userList success, size = " + list.size());
            for (User user : list) {
                System.out.println(user);
            }
        }
        else {
            System.out.println("no user exists.");
        }
    }

}