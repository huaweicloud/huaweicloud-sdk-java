package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.FunctionVersionAlias;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

public class Versions {

    public static void main(String[] args) {
        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        String functionUrn = "replace-you-functionUrn";

        //Create a function version alias
        FunctionVersionAlias fva = FunctionVersionAlias.builder()
                .name("replace-you-name")
                .version("replace-you-Version")
                .build();

        FunctionVersionAlias versionAlias = osclient.functionGraphV2().versions().createVersionAlias(functionUrn, fva);
        if (null != versionAlias) {
            System.out.println("Create function alias with function version success!");
        }else {
            System.out.println("Create function alias with function version failed!");
        }

        //Modify function version alias information
        FunctionVersionAlias updateAlias = FunctionVersionAlias.builder()
                .version("replace-you-Version")
                .name("replace-you-old-function_Version_Alias")
                .description("Alias description")
                .build();

        FunctionVersionAlias functionVersionAlias = osclient.functionGraphV2().versions().updateVersionAlias(functionUrn, updateAlias);
        if (null != versionAlias) {
            System.out.println("Update function alias with function version success!");
        }else {
            System.out.println("Update function alias with function version failed!");
        }

        // Get the version alias information specified by the function
        FunctionVersionAlias versionAlias = osclient.functionGraphV2().versions().getVersionAlias(functionUrn, alias_name);
        if (null != versionAlias) {
            System.out.println("Get function alias success!");
        }else {
            System.out.println("Get function alias failed!");
        }

        //Get the list of function version aliases
        FunctionVersionAlias[] functionVersionAliases = osclient.functionGraphV2().versions().listVersionAlias(functionUrn);
        if (0 != functionVersionAliases.size()) {
            System.out.println("Get function alias list success!");
        }else {
            System.out.println("Get function alias list failed!");
        }

        //Delete function version alias
        String alias_name = "replace-you-aliasName";
        ActionResponse actionResponse = osclient.functionGraphV2().versions().deleteVersionAlias(functionUrn, alias_name);
        if (actionResponse.isSuccess()) {
            System.out.println("Delete function alias success!");
        }else {
            System.out.println("Delete function alias failed!");
        }
    }
}
