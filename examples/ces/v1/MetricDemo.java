package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.cloudeye.Metric;
import com.huawei.openstack4j.model.cloudeye.OrderType;
import com.huawei.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;

public class MetricDemo {
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

        // step 3: Use client to list all CES metrics
        List<? extends Metric> list = osClient.cloudEye().metrics().getList();
        System.out.println(list);

        // step 4: Use client to list service metrics with url params
        MetricFilterOptions options = MetricFilterOptions.create().limit(50).order(OrderType.DESC).namespace("SYS.REMOTE_CHECK")
                .metricName("round_trip_time").dim(new String[]{"check_id,re15627327639858Z4Dbpnz6", "site,southchina"})
                .start("SYS.REMOTE_CHECK.round_trip_time.check_id:re15627327639858Z4Dbpnz6.site:southchina");
        list = osClient.cloudEye().metrics().getList(options);
        System.out.println(list);
    }
}