package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionVersionAlias;
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

        //Release function version
        String version = "replace-you-version";
        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
                .strategyConfig(StrategyConfig.builder().concurrency(-1).build())
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .handler("test.handler")
                .memorySize(128)
                .timeout(30)
                .build();
        FunctionMetadata build = functionMetadata.builder().version(version).build();
        osclient.functionGraph().versions().publishVersion(functionUrn, build);

        //Get the list of versions of the specified function
        int marker = 0;
        int maxItems = 40;
        osclient.functionGraph().versions().listFunctionVersions(functionUrn, marker, maxItems);

        //Create a function version alias
        FunctionVersionAlias fva = FunctionVersionAlias.builder()
                .name("replace-you-name")
                .version("replace-you-Version")
                .build();

        osclient.functionGraph().versions().createVersionAlias(functionUrn, fva);

        //Modify function version alias information
        FunctionVersionAlias updateAlias = FunctionVersionAlias.builder()
                .version("replace-you-Version")
                .name("replace-you-old-function_Version_Alias")
                .description("Alias description")
                .build();

        osclient.functionGraph().versions().updateVersionAlias(functionUrn, updateAlias);

        // Get the version alias information specified by the function
        osclient.functionGraph().versions().getVersionAlias(functionUrn, alias_name);

        //Get the list of function version aliases
        osclient.functionGraph().versions().listVersionAlias(functionUrn);

        //Delete function version alias
        String alias_name = "replace-you-aliasName";
        osclient.functionGraph().versions().deleteVersionAlias(functionUrn, alias_name);

    }
}
