package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.iam.domain.userResource.CreateUserReq;
import com.huawei.openstack4j.openstack.iam.domain.userResource.CreateUserResp;
import com.huawei.openstack4j.openstack.iam.domain.userResource.QueryUserResp;
import com.huawei.openstack4j.openstack.iam.domain.userResource.UpdateUserByAdminReq;
import com.huawei.openstack4j.openstack.iam.domain.userResource.UpdateUserReq;
import com.huawei.openstack4j.openstack.iam.domain.userResource.UpdateUserByAdminResp;

public class UserDemo {

    public static void main(String[] args) {

        String user = "**********";
        String password = "**********";
        String userDomainId = "**********";
        String authUrl = "**********";

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        //Create a user
        //POST  /v3.0/OS-USER/users
        //sample 01 with required parameters
        CreateUserReq createUserReq01 = CreateUserReq.builder().name("**********").domainId("**********").build();
        CreateUserResp createUserResp01 = osclient.iam().users().create(createUserReq01);
        System.out.println(createUserResp01);

        //Create a user with email
        //POST  /v3.0/OS-USER/users
        //sample 02 with optional parameters
        CreateUserReq createUserReq02 = CreateUserReq.builder().name("**********").domainId("**********").email("**********").build();
        CreateUserResp createUserResp02 = osclient.iam().users().create(createUserReq02);
        System.out.println(createUserResp02);

        //Create a user with mobile
        //POST  /v3.0/OS-USER/users
        //sample 03 with optional parameter
        CreateUserReq createUserReq03 = CreateUserReq.builder().name("**********").domainId("**********").phone("**********").areacode("**********").build();
        CreateUserResp createUserResp03 = osclient.iam().users().create(createUserReq03);
        System.out.println(createUserResp03);

        //Create a user with mobile and email
        //POST  /v3.0/OS-USER/users
        //sample 04 with optional parameters
        CreateUserReq createUserReq04 = CreateUserReq.builder().name("**********").domainId("**********").phone("**********").areacode("**********").email("**********").build();
        CreateUserResp createUserResp04 = osclient.iam().users().create(createUserReq04);
        System.out.println(createUserResp04);

        //Create a user with mobile and email
        //POST  /v3.0/OS-USER/users
        //sample 05 with complete parameters
        CreateUserReq createUserReq05 = CreateUserReq.builder().name("**********").domainId("**********").phone("**********").areacode("**********").email("**********")
                .defaultProjectId("**********").description("**********").pwdStatus(false).xuserId("**********").enabled(true).password("**********")
                .build();
        CreateUserResp createUserResp05 = osclient.iam().users().create(createUserReq05);
        System.out.println(createUserResp05);

        //Query user detail with mobile and email
        //GET  /v3.0/OS-USER/users/{user_id}
        String queryUserId01 = "**********";
        QueryUserResp queryUser01 = osclient.iam().users().query(queryUserId01);
        System.out.println(queryUser01);

        //Update user information
        //PUT  /v3.0/OS-USER/users/{user_id}/info
        //sample 01 with optionial parameters
        String updateUserId01 = "**********";
        UpdateUserReq updateUserReq01 = UpdateUserReq.builder()
                .areacode("**********")
                .build();
        ActionResponse updateResponse01 = osclient.iam().users().update(updateUserId01, updateUserReq01);
        if (updateResponse01.isSuccess()) {
            System.out.println("Update user information successfully");
        } else {
            System.out.println("Update user information failed : " + updateResponse01.getFault());
        }

        //Update user information
        //PUT  /v3.0/OS-USER/users/{user_id}/info
        //sample 02 with complete parameters
        String updateUserId02 = "**********";
        UpdateUserReq updateUserReq02 = UpdateUserReq.builder()
                .areacode("**********")
                .email("**********")
                .phone("**********")
                .build();
        ActionResponse updateResponse02 = osclient.iam().users().update(updateUserId02, updateUserReq02);
        if (updateResponse02.isSuccess()) {
            System.out.println("Update user information successfully");
        } else {
            System.out.println("Update user information failed : " + updateResponse02.getFault());
        }

        //Update user information with Security Administrator Authority
        //PUT  /v3.0/OS-USER/users/{user_id}
        //sample 01 with optionial parameters
        String updateUserId03 = "**********";
        UpdateUserByAdminReq updateUserByAdminReq03 = UpdateUserByAdminReq.builder()
                .description("**********")
                .build();
        UpdateUserByAdminResp updateUserByAdminResp03  = osclient.iam().users().updateByAdmin(updateUserId03, updateUserByAdminReq03);
        System.out.println(updateUserByAdminResp03);

        //Update user information with Security Administrator Authority
        //PUT  /v3.0/OS-USER/users/{user_id}
        //sample 02 with complete parameters
        String updateUserId04 = "**********";
        UpdateUserByAdminReq updateUserByAdminReq04 = UpdateUserByAdminReq.builder()
                .areacode("**********")
                .description("**********")
                .email("**********")
                .enabled(true)
                .name("**********")
                .password("**********")
                .phone("**********")
                .pwdStatus(false)
                .xuserId("**********")
                .xuserType("**********")
                .build();
        UpdateUserByAdminResp updateUserByAdminResp04 = osclient.iam().users().updateByAdmin(updateUserId04, updateUserByAdminReq04);
        System.out.println(updateUserByAdminResp04);
    }
}
