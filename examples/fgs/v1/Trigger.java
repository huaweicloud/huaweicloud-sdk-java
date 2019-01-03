package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionTrigger;
import com.huawei.openstack4j.openstack.fgs.v1.domain.triggersEventDatas.CTSOperations;
import com.huawei.openstack4j.openstack.fgs.v1.domain.triggersEventDatas.EventDataCTS;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.util.ArrayList;
import java.util.List;

public class Trigger {

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
        //Get all the trigger settings for the specified function
        osclient.functionGraph().trigger().listTriggersForFunction(functionUrn);

        //Create trigger
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
        osclient.functionGraph().trigger().createTriggerInstance(functionUrn, ft);

        //Get information about the specified trigger
        osclient.functionGraph().trigger().getTriggerInstance(functionUrn, trigger_type_code, trigger_id);

        //Delete all triggers for the specified function
        osclient.functionGraph().trigger().deleteAllTriggersForFunction(functionUrn);

        // Delete trigger
        String trigger_type_code = "replace-you-trigger_typeCode";
        String trigger_id = "replace-you-triggerId";
        osclient.functionGraph().trigger().deleteTrigger(functionUrn, trigger_type_code, trigger_id);
    }
}
