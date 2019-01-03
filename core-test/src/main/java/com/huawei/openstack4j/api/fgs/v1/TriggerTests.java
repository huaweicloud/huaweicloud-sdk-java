package com.huawei.openstack4j.api.fgs.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionTrigger;
import com.huawei.openstack4j.openstack.fgs.v1.domain.triggersEventDatas.CTSOperations;
import com.huawei.openstack4j.openstack.fgs.v1.domain.triggersEventDatas.EventData;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TriggerTests extends AbstractTest {
    @Override
    protected Service service() {
        return Service.FGS;
    }

    private static final String FGS_LIST_TRIGGERS = "/fgs/v1/functionGraph_listTriggersForFunction.json";
    private static final String FGS_CREATE_TRIGGERS = "/fgs/v1/functionGraph_createTrigger.json";
    private static final String FGS_GET_TRIGGER = "/fgs/v1/functionGraph_getTriggerInfo.json";
    private static final String FUNCTION_URN = "xxx";
    private static final String TYPE_CODE = "OBS";
    private static final String TRIGGER_TYPE_CODE = "xx";
    private static final String TRIGGER_ID = "x";
    private static final int DELETE_HTTP_CODE = 204;


    @Test
    public void listTriggersForFunctionTest() throws IOException {
        respondWith(FGS_LIST_TRIGGERS);
        FunctionTrigger[] functionTriggers = osv3().functionGraph().trigger().listTriggersForFunction(FUNCTION_URN);
        assertEquals(functionTriggers.length, 1);
        assertEquals(functionTriggers[0].getTriggerTypeCode(), TYPE_CODE);
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
        FunctionTrigger triggerInstance = osv3().functionGraph().trigger().createTriggerInstance(FUNCTION_URN, ft);
        assertEquals(triggerInstance.getTriggerTypeCode(), TYPE_CODE);
    }

    @Test
    public void deleteTriggerTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = osv3().functionGraph().trigger().deleteTrigger(FUNCTION_URN, TRIGGER_TYPE_CODE, TRIGGER_ID);
        assertTrue(actionResponse.isSuccess());
    }

    @Test
    public void getTriggerInstanceTest() throws IOException {
        respondWith(FGS_GET_TRIGGER);
        FunctionTrigger triggerInstance = osv3().functionGraph().trigger().getTriggerInstance(FUNCTION_URN, TRIGGER_TYPE_CODE, TRIGGER_ID);
        assertEquals(triggerInstance.getTriggerTypeCode(), "SMN");
    }

    @Test
    public void deleteAllTriggersForFunctionTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = osv3().functionGraph().trigger().deleteAllTriggersForFunction(FUNCTION_URN);
        assertTrue(actionResponse.isSuccess());
    }
}
