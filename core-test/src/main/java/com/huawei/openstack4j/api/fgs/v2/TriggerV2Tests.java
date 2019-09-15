package com.huawei.openstack4j.api.fgs.v2;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.FunctionTrigger;
import com.huawei.openstack4j.openstack.fgs.v2.domain.triggersEventDatas.CTSOperations;
import com.huawei.openstack4j.openstack.fgs.v2.domain.triggersEventDatas.EventData;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TriggerV2Tests extends AbstractTest {
    @Override
    protected Service service() {
        return Service.FGS;
    }

    private static final String FGS_LIST_TRIGGERS = "/fgs/v1/functionGraph_listTriggersForFunction.json";
    private static final String FGS_CREATE_TRIGGERS = "/fgs/v1/functionGraph_createTrigger.json";
    private static final String FGS_GET_TRIGGER = "/fgs/v1/functionGraph_getTriggerInfo.json";
    private static final String FUNCTION_URN = "urn:fss:cn-north-7:a1eaedeb26e6497a987ea3386b174ffc:function:default:test001";
    private static final String TYPE_CODE = "CTS";
    private static final String TRIGGER_TYPE_CODE = "CTS";
    private static final String TRIGGER_ID = "xxxxxxx";
    private static final int DELETE_HTTP_CODE = 204;

    private OSClient.OSClientV3 MyOsclient;

    TriggerV2Tests() {
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
    public void createTriggerInstanceTest() throws IOException {
        respondWith(FGS_CREATE_TRIGGERS);
        CTSOperations ctsOperation = CTSOperations.builder()
                .serviceType("Anti-DDos")
                .resourceType("anti-ddos")
                .operationName("openAntiddos")
                .build();
        List<String> opsList = new ArrayList<>();
        opsList.add(ctsOperation.toString());
        EventData cts = EventData.builder()
                .name("TriggerName")
                .operations(opsList)
                .build();
        FunctionTrigger ft = FunctionTrigger.builder()
                .eventData(cts)
                .eventTypeCode("MessageCreated")
                .triggerTypeCode("CTS")
                .triggerStatus("ACTIVE")
                .build();
        FunctionTrigger triggerInstance = this.MyOsclient.functionGraphV2().trigger().createTriggerInstance(FUNCTION_URN, ft);

        assertEquals(triggerInstance.getTriggerTypeCode(), TYPE_CODE);
    }

    @Test
    public void getTriggerInstanceTest() throws IOException {
        respondWith(FGS_GET_TRIGGER);
        FunctionTrigger triggerInstance = this.MyOsclient.functionGraphV2().trigger().getTriggerInstance(FUNCTION_URN, TRIGGER_TYPE_CODE, TRIGGER_ID);

        assertEquals(triggerInstance.getTriggerTypeCode(), TYPE_CODE);
    }

    @Test
    public void listTriggersForFunctionTest() throws IOException {
        respondWith(FGS_LIST_TRIGGERS);
        FunctionTrigger[] functionTriggers = this.MyOsclient.functionGraphV2().trigger().listTriggersForFunction(FUNCTION_URN);

        assertEquals(functionTriggers.length, 1);
    }

    @Test
    public void deleteTriggerTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = this.MyOsclient.functionGraphV2().trigger().deleteTrigger(FUNCTION_URN, TRIGGER_TYPE_CODE, TRIGGER_ID);

        assertTrue(actionResponse.isSuccess());
    }

    @Test
    public void deleteAllTriggersForFunctionTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = this.MyOsclient.functionGraphV2().trigger().deleteAllTriggersForFunction(FUNCTION_URN);

        assertTrue(actionResponse.isSuccess());
    }
}
