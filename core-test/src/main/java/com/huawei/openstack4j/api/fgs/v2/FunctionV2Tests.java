package com.huawei.openstack4j.api.fgs.v2;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.*;
import com.huawei.openstack4j.openstack.fgs.v2.domain.mountConfigDatas.FuncMount;
import com.huawei.openstack4j.openstack.fgs.v2.domain.mountConfigDatas.MountUser;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static org.testng.Assert.*;

@Test(suiteName = "FGS2.0/Servers")
public class FunctionV2Tests extends AbstractTest {
    @Override
    protected Service service() {
        return Service.FGS;
    }

    private static final int MEMORY_SIZE = 128;
    private static final int MEMORY_UPDATE_SIZE = 256;
    private static final String FUNCTION_URN = "urn:fss:cn-north-7:xxxxxxx:function:default:test001";
    private static final String FGS_LIST_FUNCTION = "/fgs/v1/functionGraph_listFunction.json";
    private static final String FGS_GET_METADATA = "/fgs/v1/functionGraph_getFunctionMetadata.json";
    private static final String FGS_GET_CODE = "/fgs/v1/functionGraph_getFunctionCode.json";
    private static final String FGS_CREATE_FUNCTION = "/fgs/v1/functionGraph_createFunction.json";
    private static final String FGS_UPDATE_CODE = "/fgs/v1/functionGraph_updateFunctionCode.json";
    private static final String FGS_UPDATE_CONFIG = "/fgs/v1/functionGraph_updateFunctionConfig.json";
    private static final String XROLE = "test";
    private static final String VPC_ID = "xxxxxxx";
    private static final String SUBNET_ID = "xxxxxxx";
    private final Base64.Encoder encoder = Base64.getEncoder();
    private static final int CODE_SIZE = 107;
    private static final int CODE_UPDATE_SIZE = 110;
    private static final int TIME_OUT = 3;
    private static final int DELETE_HTTP_CODE = 204;

    private OSClientV3 MyOsclient;
    //private OSClient.OSClientAKSK MyOsclient;

    FunctionV2Tests() {
        String authUrl = "https://127.0.0.1/v3";//endpointUrl
        String user = "xxxxxxx";//用户名
        String password = "xxxxxxx";//用户密码
        String projectId = "xxxxxxx";//项目ID
        String userDomainId = "xxxxxxx";//账号ID

        OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
        endpointResolver.addOverrideEndpoint(ServiceType.FGS,"https://127.0.0.1:443");
        endpointResolver.addOverrideEndpoint(ServiceType.IAM,"https://127.0.0.1:31943");

        OSFactory.enableHttpLoggingFilter(true);
        Config config = Config.newConfig().withEndpointURLResolver(endpointResolver).withLanguage("zh-cn")
                .withSSLVerificationDisabled();
        this.MyOsclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();
    }

    @Test
    public void createFunctionTest() throws IOException {
        respondWith(FGS_CREATE_FUNCTION);
        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .funcName("test001")
                .packageName("default")
                .codeType("inline")
                .handler("test.handler")
                .memorySize(MEMORY_SIZE)
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .timeout(TIME_OUT)
                .funcCode(FuncCode.builder()
                        .file(encoder.encodeToString(getCodeByte()))
                        .build())
                .build();

        FunctionMetadata function = this.MyOsclient.functionGraphV2().function().createFunction(functionMetadata);

        assertEquals(function.getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void getFunctionMetadataTest() throws IOException {
        respondWith(FGS_GET_METADATA);
        FunctionMetadata functionMetadata = this.MyOsclient.functionGraphV2().function().getFunctionMetadata(FUNCTION_URN);

        assertEquals(functionMetadata.getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void getFunctionCodeTest() throws IOException {
        respondWith(FGS_GET_CODE);
        FunctionMetadata functionCode = this.MyOsclient.functionGraphV2().function().getFunctionCode(FUNCTION_URN);

        assertEquals(functionCode.getCodeSize(), CODE_SIZE);
    }

    @Test
    public void listFunctionTest() throws IOException {
        respondWith(FGS_LIST_FUNCTION);

        FunctionMetadata.Functions functions = this.MyOsclient.functionGraphV2().function().listFunction();
        List<FunctionMetadata> functionList = functions.getList();

        assertEquals(functionList.size(), 1);
        assertEquals(functionList.get(0).getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void listFunctionWithMarkerTest() throws IOException {
        respondWith(FGS_LIST_FUNCTION);

        Map<String, String> param = new HashMap<>();
        param.put("marker", "0");
        param.put("maxItems", "40");

        FunctionMetadata.Functions functions = this.MyOsclient.functionGraphV2().function().listFunction(param);

        List<FunctionMetadata> functionList = functions.getList();

        assertEquals(functionList.size(), 1);
        assertEquals(functionList.get(0).getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void updateFunctionCodeTest() throws IOException {
        respondWith(FGS_UPDATE_CODE);
        List<String> dependList = new  ArrayList<>();
        dependList.add("xxxxxxx");
        dependList.add("xxxxxxx");

        FunctionMetadata updateFunction = FunctionMetadata.builder()
                .codeType("inline")
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .dependList(dependList)
                .funcCode(FuncCode.builder()
                        .file(encoder.encodeToString(getUpdateCodeByte())).build())
                .build();
        FunctionMetadata functionMetadata = this.MyOsclient.functionGraphV2().function().updateFunctionCode(FUNCTION_URN, updateFunction);

        assertEquals(functionMetadata.getCodeSize(), CODE_UPDATE_SIZE);
    }

    @Test
    public void updateFunctionConfigTest() throws IOException {
        respondWith(FGS_UPDATE_CONFIG);

        List<FuncMount> fm_list = new ArrayList<>();
        fm_list.add(FuncMount.builder()
                .mountType("sfs")
                .mountResource("xxxxxxx")
                .localMountPath("/test")
                .build());

        FunctionMetadata updateFunctionMetadata = FunctionMetadata.builder()
                .strategyConfig(StrategyConfig.builder().concurrency(-1).build())
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .handler("test.handler")
                .appXrole(XROLE)
                .xrole(XROLE)
                .funcVpc(FuncVpc.builder()
                        .vpcId(VPC_ID)
                        .subnetId(SUBNET_ID)
                        .build()
                )
                .mountConfig(MountConfig.builder()
                        .mountUser(MountUser.builder().userId(1001).userGroupId(1000).build())
                        .funcMounts(fm_list)
                        .build()
                )
                .initializerHandler("test.initializer")
                .initializerTimeout(50)
                .memorySize(MEMORY_UPDATE_SIZE)
                .timeout(TIME_OUT)
                .build();
        FunctionMetadata functionMetadata = this.MyOsclient.functionGraphV2().function().updateFunctionConfig(FUNCTION_URN, updateFunctionMetadata);

        assertEquals(functionMetadata.getMemorySize(), MEMORY_UPDATE_SIZE);
    }

    @Test
    public void createFunctionVersionTest() throws IOException {
        respondWith(FGS_CREATE_FUNCTION);
        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .version("1.0.1")
                .description("version 1.0.1")
                .build();

        FunctionMetadata function = this.MyOsclient.functionGraphV2().function().createFunctionVersion(FUNCTION_URN, functionMetadata);

        assertEquals(function.getMemorySize(), MEMORY_UPDATE_SIZE);
    }

    @Test
    public void listFunctionVersionTest() throws IOException {
        respondWith(FGS_LIST_FUNCTION);

        FunctionMetadata.FunctionVersions functions = this.MyOsclient.functionGraphV2().function().listFunctionVersion(FUNCTION_URN);
        List<FunctionMetadata> functionList = functions.getList();

        assertEquals(functionList.size(), 2);
        assertEquals(functionList.get(0).getMemorySize(), MEMORY_UPDATE_SIZE);
    }


    @Test
    public void listFunctionVersionWithMarkerTest() throws IOException {
        respondWith(FGS_LIST_FUNCTION);

        Map<String, String> param = new HashMap<>();
        param.put("marker", "0");
        param.put("maxItems", "1");

        FunctionMetadata.FunctionVersions functions = this.MyOsclient.functionGraphV2().function().listFunctionVersion(FUNCTION_URN, param);

        List<FunctionMetadata> functionList = functions.getList();

        assertEquals(functionList.size(), 1);
        assertEquals(functionList.get(0).getMemorySize(), MEMORY_UPDATE_SIZE);
    }

    @Test
    public void deleteFunctionTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = this.MyOsclient.functionGraphV2().function().deleteFunction(FUNCTION_URN);

        assertTrue(actionResponse.isSuccess());
    }

    private byte[] getCodeByte() {
        StringBuilder code = new StringBuilder();
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
        return codeByte;
    }

    private byte[] getUpdateCodeByte() {
        StringBuilder code = new StringBuilder();
        code.append("import json \n")
                .append("def handler (event, context): \n")
                .append(" output = 'Hello message: 003' + json.dumps(event) \n")
                .append(" return output");
        byte[] codeByte = null;
        try {
            codeByte = code.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
        return codeByte;
    }
}
