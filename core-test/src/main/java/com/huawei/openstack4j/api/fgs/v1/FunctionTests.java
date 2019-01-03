package com.huawei.openstack4j.api.fgs.v1;


import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FuncCode;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionMetadata;
import com.huawei.openstack4j.openstack.fgs.v1.domain.StrategyConfig;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class FunctionTests extends AbstractTest {
    @Override
    protected Service service() {
        return Service.FGS;
    }

    private static final int MEMORY_SIZE = 128;
    private static final String FUNCTION_URN = "xxx";
    private static final String FGS_LIST_FUNCTION = "/fgs/v1/functionGraph_listFunction.json";
    private static final String FGS_GET_METADATA = "/fgs/v1/functionGraph_getFunctionMetadata.json";
    private static final String FGS_GET_CODE = "/fgs/v1/functionGraph_getFunctionCode.json";
    private static final String FGS_CREATE_FUNCTION = "/fgs/v1/functionGraph_createFunction.json";
    private static final String FGS_UPDATE_CODE = "/fgs/v1/functionGraph_updateFunctionCode.json";
    private static final String FGS_UPDATE_CONFIG = "/fgs/v1/functionGraph_updateFunctionConfig.json";
    private final Base64.Encoder encoder = Base64.getEncoder();
    private static final String CODE_SIZE = "272";
    private static final int TIME_OUT = 3;
    private static final int DELETE_HTTP_CODE = 204;

    @Test
    public void listFunctionTest() throws IOException {
        respondWith(FGS_LIST_FUNCTION);
        int marker = 0;
        int maxItems = 40;
        FunctionMetadata.Functions functions = osv3().functionGraph().function().listFunction(marker, maxItems);
        List<FunctionMetadata> functionList = functions.getList();
        assertEquals(functionList.size(), 1);
        assertEquals(functionList.get(0).getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void getFunctionMetadataTest() throws IOException {
        respondWith(FGS_GET_METADATA);
        FunctionMetadata functionMetadata = osv3().functionGraph().function().getFunctionMetadata(FUNCTION_URN);
        assertEquals(functionMetadata.getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void getFunctionCodeTest() throws IOException {
        respondWith(FGS_GET_CODE);
        FunctionMetadata functionCode = osv3().functionGraph().function().getFunctionCode(FUNCTION_URN);
        assertEquals(functionCode.getCodeSize(), CODE_SIZE);
    }

    @Test
    public void createFunctionTest() throws IOException {
        respondWith(FGS_CREATE_FUNCTION);
        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
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
        FunctionMetadata function = osv3().functionGraph().function().createFunction(functionMetadata);
        assertEquals(function.getMemorySize(), MEMORY_SIZE);
    }

    @Test
    public void deleteFunctionTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = osv3().functionGraph().function().deleteFunction(FUNCTION_URN);
        assertTrue(actionResponse.isSuccess());
    }

    @Test
    public void updateFunctionCodeTest() throws IOException {
        respondWith(FGS_UPDATE_CODE);
        FunctionMetadata updateFunction = FunctionMetadata.builder()
                .codeType("inline")
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .funcCode(FuncCode.builder().file(encoder.encodeToString(getCodeByte())).build()).build();
        FunctionMetadata functionMetadata = osv3().functionGraph().function().updateFunctionCode(FUNCTION_URN, updateFunction);
        assertEquals(functionMetadata.getCodeSize(), CODE_SIZE);
    }

    @Test
    public void updateFunctionConfigTest() throws IOException {
        respondWith(FGS_UPDATE_CONFIG);
        FunctionMetadata updateFunctionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
                .strategyConfig(StrategyConfig.builder().concurrency(-1).build())
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .handler("test.handler")
                .memorySize(MEMORY_SIZE)
                .timeout(TIME_OUT)
                .build();
        FunctionMetadata functionMetadata = osv3().functionGraph().function().updateFunctionConfig(FUNCTION_URN, updateFunctionMetadata);
        assertEquals(functionMetadata.getMemorySize(), MEMORY_SIZE);
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
}
