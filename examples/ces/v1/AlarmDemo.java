package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;
import com.huawei.openstack4j.model.cloudeye.OrderType;

public class AlarmDemo {
    public static void main(String[] args) {

        // step 1: setup the authentication credit
        String user = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String password = "xxxxxxxxxxxxxxx";
        String projectId = "xxxxxxxxxxxxxxx";
        String userDomainId = "xxxxxxxxxxxxxxxxxx";
        String authUrl = "https://iam.xxx.yyy.com/v3";

        // step 2: initial client
        OSClientV3 osClient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // step 3: Use client to list all alarm rules
        List<? extends Alarm> list = osClient.cloudEye().alarms().list();
        System.out.println(list);

        // step 4: Use client to get alarm rule by alarmID
        String alarmID = list.get(0).getAlarmId();
        Alarm alarm = osClient.cloudEye().alarms().get(alarmID);
        System.out.println(alarm);

        // step 5: Use client to get alarm rules with url params
        AlarmFilterOptions options = AlarmFilterOptions.create();
        options.limit(3).start(alarmID).order(OrderType.DESC);
        list = osClient.cloudEye().alarms().list(options);
        System.out.println(list);

        // step 6: Use client to disable alarm rule by alarmID
        ActionResponse stopResp = osClient.cloudEye().alarms().stopAlarm(alarmID);
        System.out.println(stopResp);

        // step 7: Use client to enable alarm rule by alarmID
        ActionResponse startResp = osClient.cloudEye().alarms().startAlarm(alarmID);
        System.out.println(startResp);

        // step 8: Use client to delete alarm rule by alarmID
        ActionResponse deleteResp = osClient.cloudEye().alarms().deleteAlarm(alarmID);
        System.out.println(deleteResp);
    }
}