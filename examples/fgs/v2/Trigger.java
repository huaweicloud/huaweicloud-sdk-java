package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.FunctionTrigger;
import com.huawei.openstack4j.openstack.fgs.v2.domain.triggersEventDatas.CTSOperations;
import com.huawei.openstack4j.openstack.fgs.v2.domain.triggersEventDatas.EventDataCTS;
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
        FunctionTrigger[] functionTriggers = osclient.functionGraphV2().trigger().listTriggersForFunction(functionUrn);
        if (0 != functionTriggers.size()) {
            System.out.println("Get function trigger list success!");
        }else {
            System.out.println("Get function trigger list failed!");
        }

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
        FunctionTrigger triggerInstance = osclient.functionGraphV2().trigger().createTriggerInstance(functionUrn, ft);
        if (null != triggerInstance) {
            System.out.println("Create function trigger success!");
        }else {
            System.out.println("Create function trigger failed!");
        }

        //Get information about the specified trigger
        FunctionTrigger triggerInstance = osclient.functionGraphV2().trigger().getTriggerInstance(functionUrn, trigger_type_code, trigger_id);
        if (null != triggerInstance) {
            System.out.println("Get function trigger success!");
        }else {
            System.out.println("Get function trigger failed!");
        }

        //Delete all triggers for the specified function
        ActionResponse actionResponse = osclient.functionGraphV2().trigger().deleteAllTriggersForFunction(functionUrn);
        if (actionResponse.isSuccess()) {
            System.out.println("Delete all function trigger success!");
        }else {
            System.out.println("Delete all function trigger failed!");
        }

        // Delete trigger
        String trigger_type_code = "replace-you-trigger_typeCode";
        String trigger_id = "replace-you-triggerId";
        ActionResponse actionResponse = osclient.functionGraphV2().trigger().deleteTrigger(functionUrn, trigger_type_code, trigger_id);
        if (actionResponse.isSuccess()) {
            System.out.println("Delete function trigger success!");
        }else {
            System.out.println("Delete function trigger failed!");
        }
    }
}
