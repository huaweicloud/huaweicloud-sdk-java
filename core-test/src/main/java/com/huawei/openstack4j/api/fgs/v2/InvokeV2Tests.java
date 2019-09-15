package com.huawei.openstack4j.api.fgs.v2;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.FuncInvocations;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;

public class InvokeV2Tests extends AbstractTest {

    private static final String FUNCTION_URN = "urn:fss:cn-north-7:a1eaedeb26e6497a987ea3386b174ffc:function:default:test001";
    private static final String FGS_INVOKE = "/fgs/v1/functionGraph_invoke.json";
    private static final int HTTP_CODE = 200;
    @Override
    protected Service service() {
        return Service.FGS;
    }

    private OSClient.OSClientV3 MyOsclient;

    InvokeV2Tests() {
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
    public void invokeFunctionTest() {
        respondWith(HTTP_CODE, FGS_INVOKE);

        FuncInvocations returnMsg = this.MyOsclient.functionGraphV2().function().invokeFunction(FUNCTION_URN, getMap());

        assertNotNull(returnMsg);
    }

    @Test
    public void asyncInvokeFunctionTest() {
        respondWith(HTTP_CODE, FGS_INVOKE);

        FuncInvocations returnMsg = this.MyOsclient.functionGraphV2().function().asyncInvokeFunction(FUNCTION_URN, getMap());

        assertNotNull(returnMsg);
    }

    private Map<?, ?> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        return map;
    }
}
