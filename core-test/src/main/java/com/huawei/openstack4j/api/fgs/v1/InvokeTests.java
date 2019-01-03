package com.huawei.openstack4j.api.fgs.v1;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;

public class InvokeTests extends AbstractTest {

    private static final String FUNCTION_URN = "xxx";
    private static final String FGS_INVOKE = "/fgs/v1/functionGraph_invoke.json";
    private static final int HTTP_CODE = 200;
    @Override
    protected Service service() {
        return Service.FGS;
    }

    @Test
    public void invokeFunctionTest() {
        respondWith(HTTP_CODE, "\"Hello {\\r\\n  \\\"Message\\\" : \\\"Hello world\\\"\\r\\n}.\"");
        String returnMsg = osv3().functionGraph().function().invokeFunction(FUNCTION_URN, getMap());
        assertNotNull(returnMsg);
    }

    @Test
    public void asyncInvokeFunctionTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("Message", "Hello world");
        respondWith(FGS_INVOKE);
        String returnMsg = osv3().functionGraph().function().asyncInvokeFunction(FUNCTION_URN, map);
        assertNotNull(returnMsg);
    }

    private Map<?, ?> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Message", "Hello world");
        return map;
    }
}
