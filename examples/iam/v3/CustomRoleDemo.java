package com.huawei.openstack.sample.iam.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.CreateRoleReq;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.CreateRoleResp;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.ListRoleResp;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.Policy;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.QueryRoleResp;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.Resource;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.Statement;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.UpdateRoleReq;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.UpdateRoleResp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomRoleDemo {

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

        //Create agency custome role
        //POST  /v3.0/OS-ROLE/roles
        //sample 01
        String description01 = "**********";
        String descriptionCn01 = "**********";
        String displayName01 = "**********";
        String[] actions01 = {"**********"};
        String effecct01 = "**********";
        String[] uris01 = {"**********"};
        String version01 = "**********";
        String type01 = "**********";
        List<Statement> statementList = new ArrayList<>();
        statementList.add(Statement.builder().action(Arrays.asList(actions01)).effect(effecct01).resource(Resource.builder().uri(Arrays.asList(uris01)).build()).build());
        CreateRoleReq createRoleReq = CreateRoleReq.builder().description(description01).descriptionCn(descriptionCn01).displayName(displayName01)
                .policy(Policy.builder().statements(statementList).version(version01).build()).type(type01).build();
        CreateRoleResp createRoleResp = osclient.iam().roles().create(createRoleReq);
        System.out.println(createRoleResp);

        //Create cloud service custome role
        //POST  /v3.0/OS-ROLE/roles
        //sample 02
        String description02 = "**********";
        String descriptionCn02 = "**********";
        String displayName02 = "**********";
        String[] actions02 = {"**********"};
        String effecct02 = "**********";
        String[] resource02 = {"**********"};
        String version02 = "**********";
        String type02 = "**********";
        Map<String, Map<String, List<String>>> conditon02 = new HashMap<>();
        Map<String, List<String>> conditonMap02 = new HashMap<>();
        List<String> conditionList02 = new ArrayList<>();
        conditionList02.add("**********");
        conditonMap02.put("**********", conditionList02);
        conditon02.put("**********", conditonMap02);
        List<Statement> statementList02 = new ArrayList<>();
        statementList02.add(Statement.builder().condition(conditon02).action(Arrays.asList(actions02)).effect(effecct02).resource(Arrays.asList(resource02)).build());
        CreateRoleReq createRoleReq02 = CreateRoleReq.builder().description(description02).descriptionCn(descriptionCn02).displayName(displayName02)
                .type(type02).policy(Policy.builder().version(version02).statements(statementList02).build()).build();
        CreateRoleResp createRoleResp02 = osclient.iam().roles().create(createRoleReq02);
        System.out.println(createRoleResp02);

        //Delete custome role
        //DELETE  /v3.0/OS-ROLE/roles/{role_id}
        String roleId = "**********";
        ActionResponse actionResponse = osclient.iam().roles().delete(roleId);
        if (actionResponse.isSuccess()) {
            System.out.println("Delete role successfully");
        } else {
            System.out.println("Delete role failed : " + actionResponse.getFault());
        }

        //List custom roles
        //GET  /v3.0/OS-ROLE/roles
        List<CreateRoleResp> listRoleResp = osclient.iam().roles().list().getRoles();
        for (CreateRoleResp listRoleResp1 : listRoleResp) {
            System.out.println(listRoleResp1);
        }

        //Query custom role detail
        //GET  /v3.0/OS-ROLE/roles/{role_id}
        String queryRoleId = "**********";
        QueryRoleResp queryRoleResp = osclient.iam().roles().get(queryRoleId);
        System.out.println(queryRoleResp);

        //Update agency custome role
        //PATCH  /v3.0/OS-ROLE/roles/{role_id}
        //sample 01
        String description03 = "**********";
        String descriptionCn03 = "**********";
        String displayName03 = "**********";
        String[] actions03 = {"**********"};
        String effecct03 = "**********";
        String[] uris03 = {"**********"};
        String version03 = "**********";
        String type03 = "**********";
        String roleId03 = "**********";
        List<Statement> statementList03 = new ArrayList<>();
        statementList.add(Statement.builder().action(Arrays.asList(actions03)).effect(effecct03).resource(Resource.builder().uri(Arrays.asList(uris03)).build()).build());
        UpdateRoleReq updateRoleReq = UpdateRoleReq.builder().description(description03).descriptionCn(descriptionCn03).displayName(displayName03)
                .policy(Policy.builder().statements(statementList03).version(version03).build()).type(type03).build();
        UpdateRoleResp updateRoleResp = osclient.iam().roles().update(roleId03, updateRoleReq);
        System.out.println(updateRoleResp);

        //Update cloud service custome role
        //PATCH  /v3.0/OS-ROLE/roles/{role_id}
        //sample 02
        String description04 = "**********";
        String descriptionCn04 = "**********";
        String displayName04 = "**********";
        String[] actions04 = {"**********"};
        String effecct04 = "**********";
        String[] resource04 = {"**********"};
        String version04 = "**********";
        String type04 = "**********";
        Map<String, Map<String, List<String>>> conditon04 = new HashMap<>();
        Map<String, List<String>> conditonMap04 = new HashMap<>();
        List<String> conditionList04 = new ArrayList<>();
        conditionList04.add("**********");
        conditonMap04.put("**********", conditionList04);
        conditon04.put("**********", conditonMap04);
        List<Statement> statementList04 = new ArrayList<>();
        String roleId04 = "**********";
        statementList04.add(Statement.builder().condition(conditon04).action(Arrays.asList(actions04)).effect(effecct04).resource(Arrays.asList(resource04)).build());
        UpdateRoleReq updateRoleReq04 = UpdateRoleReq.builder().description(description04).descriptionCn(descriptionCn04).displayName(displayName04)
                .type(type04).policy(Policy.builder().version(version04).statements(statementList04).build()).build();
        UpdateRoleResp updateRoleResp04 = osclient.iam().roles().update(roleId04, updateRoleReq04);
        System.out.println(updateRoleResp04);
    }
}
