package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.*;
import com.huawei.openstack4j.openstack.fgs.v2.domain.mountConfigDatas.FuncMount;
import com.huawei.openstack4j.openstack.fgs.v2.domain.mountConfigDatas.MountUser;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
        FunctionMetadata.Functions functions = osclient.functionGraphV2().function().listFunction(marker, maxItems);
        List<FunctionMetadata> functionList = functions.getList();
        if (0 != functionList.size()) {
            System.out.println("Get functionlist success!");
        }else {
            System.out.println("Get functionlist failed!");
        }

        FunctionMetadata.Functions functions = osclient.functionGraphV2().function().listFunction();
        functionList = functions.getList();
        if (0 != functionList.size()) {
            System.out.println("Get functionlist success!");
        }else {
            System.out.println("Get functionlist failed!");
        }

        //Get the metadata of the function
        String functionUrn = "replace-you-functionUrn";
        FunctionMetadata functionMetadata = osclient.functionGraphV2().function().getFunctionMetadata(functionUrn);
        if (null != functionMetadata) {
            System.out.println("Get function config success!");
        }else {
            System.out.println("Get function config failed!");
        }

        //Get the code of the specified function
        FunctionMetadata functionCode = osclient.functionGraphV2().function().getFunctionCode(functionUrn);
        if (null != functionCode) {
            System.out.println("Get function code success!");
        }else {
            System.out.println("Get function code failed!");
        }

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

        FunctionMetadata function = osclient.functionGraphV2().function().createFunction(functionMetadata);
        if (null != function) {
            System.out.println("Create function success!");
        }else {
            System.out.println("Create function failed!");
        }

        //Modify function code
        //Modify function depends
        List<String> dependList = new  ArrayList<>();
        dependList.add("xxxxxxxId");
        dependList.add("xxxxxxxId");

        FunctionMetadata updateFunction = FunctionMetadata.builder()
                .codeType("inline")
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .funcCode(FuncCode.builder().file(encoder.encodeToString(codeByte)).build()).build();
                .dependList(dependList)
        FunctionMetadata functionMetadata = osclient.functionGraphV2().function().updateFunctionCode(functionUrn, updateFunction);
        if (null != functionMetadata) {
            System.out.println("Update function code success!");
        }else {
            System.out.println("Update function code failed!");
        }

        //Modify the function metadata information
        //mount sfs/ecs/sfs-trubo disk to function
        List<FuncMount> fm_list = new ArrayList<>();
        fm_list.add(FuncMount.builder()
                .mountType("sfs")
                .mountResource("xxxxxxx")
                .localMountPath("/test")
                .build());

        FunctionMetadata updateFunctionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
                .strategyConfig(StrategyConfig.builder().concurrency(-1).build())
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .handler("test.handler")
                .memorySize(256)
                .timeout(30)
                .funcVpc(FuncVpc.builder()
                        .vpcId(VPC_ID)
                        .subnetId(SUBNET_ID)
                        .build()
                )//Modify Vpc
                .mountConfig(MountConfig.builder()
                        .mountUser(MountUser.builder().userId(1001).userGroupId(1000).build())
                        .funcMounts(fm_list)
                        .build()
                )
                .initializerHandler("test.initializer")
                .initializerTimeout(10)
                .build();
        FunctionMetadata functionMetadata = osclient.functionGraphV2().function().updateFunctionConfig(functionUrn, updateFunctionMetadata);
        if (null != functionMetadata) {
            System.out.println("Update function config success!");
        }else {
            System.out.println("Update function config failed!");
        }

        //Release function version
        String version = "replace-you-version";
        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .version("1.0.1")
                .description("version 1.0.1")
                .build();
        FunctionMetadata function = osclient.functionGraphV2().function().createFunctionVersion(functionUrn, functionMetadata);
        if (null != functionMetadata) {
            System.out.println("Release function version success!");
        }else {
            System.out.println("Release function version failed!");
        }

        //Get the list of versions of the specified function
        Map<String, String> param = new HashMap<>();
        param.put("marker", "0");
        param.put("maxItems", "1");
        FunctionMetadata.FunctionVersions functionVersions = osclient.functionGraphV2().function().listFunctionVersion(functionUrn);
        if (0 != functionVersions.size()) {
            System.out.println("Get function version list success!");
        }else {
            System.out.println("Get function version list failed!");
        }

        FunctionMetadata.FunctionVersions functionVersions = FunctionMetadata.FunctionVersions functions = osclient.functionGraphV2().function().listFunctionVersion(functionUrn, param);
        if (0 != functionVersions.size()) {
            System.out.println("Get function version list success!");
        }else {
            System.out.println("Get function version list failed!");
        }

        //Delete funtion
        ActionResponse actionResponse = osclient.functionGraphV2().function().deleteFunction(functionUrn);
        if (actionResponse.isSuccess()) {
            System.out.println("Delete function success!");
        }else {
            System.out.println("Delete function failed!");
        }

        Map<String, String> map = new HashMap<>();
        map.put("Message", "Hello world");

        //Synchronous execution function
        FuncInvocations returnMsg = osclient.functionGraphV2().function().invokeFunction(functionUrn, map);
        if (null != returnMsg) {
            System.out.println("Invoke function success!");
        }else {
            System.out.println("Invoke function failed!");
        }

        //Asynchronous call function
        FuncInvocations returnMsg = osclient.functionGraphV2().function().asyncInvokeFunction(functionUrn, map);
        if (null != returnMsg) {
            System.out.println("Invoke function success!");
        }else {
            System.out.println("Invoke function failed!");
        }
    }
}
