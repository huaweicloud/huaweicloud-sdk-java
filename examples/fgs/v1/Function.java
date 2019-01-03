package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FuncCode;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionMetadata;
import com.huawei.openstack4j.openstack.fgs.v1.domain.StrategyConfig;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Function {
    public static void main(String[] args) {
        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Get function list
        int marker = 0;
        int maxItems = 40;
        osclient.functionGraph().function().listFunction(marker, maxItems);

        //Get the metadata of the function
        String functionUrn = "replace-you-functionUrn";
        osclient.functionGraph().function().getFunctionMetadata(functionUrn);

        //Get the code of the specified function
        osclient.functionGraph().function().getFunctionCode(functionUrn);

        //Create function
        final Base64.Encoder encoder = Base64.getEncoder();
        StringBuilder code = new StringBuilder("");
        code.append("import json \n")
                .append("def handler (event, context): \n")
                .append(" output = 'Hello message: ' + json.dumps(event) \n")
                .append(" return output");
        byte[] codeByte = null;
        try {
            codeByte = code.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }

        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
                .packageName("default")
                .codeType("inline")
                .handler("test.handler")
                .memorySize(128)
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .timeout(3)
                .funcCode(FuncCode.builder()
                        .file(encoder.encodeToString(codeByte))
                        .build())
                .build();

        osclient.functionGraph().function().createFunction(functionMetadata);

        //Modify function code
        FunctionMetadata updateFunction = FunctionMetadata.builder()
                .codeType("inline")
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .funcCode(FuncCode.builder().file(encoder.encodeToString(codeByte)).build()).build();
        osclient.functionGraph().function().updateFunctionCode(functionUrn, updateFunction);

        //Modify the function metadata information
        FunctionMetadata updateFunctionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
                .strategyConfig(StrategyConfig.builder().concurrency(-1).build())
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .handler("test.handler")
                .memorySize(128)
                .timeout(30)
                .build();
        osclient.functionGraph().function().updateFunctionConfig(functionUrn, updateFunctionMetadata);

        //Delete funtion
        osclient.functionGraphService().function().deleteFunction(functionUrn);
    }
}
