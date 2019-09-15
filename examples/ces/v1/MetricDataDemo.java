package sample;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.cloudeye.*;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.cloudeye.OrderType;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetric;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricData;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricDemension;

public class MetricDataDemo {
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

        // step 3: Build post metric data
        Date now = new Date();
        Date fiveMinAgo = new Date(now.getTime() - 1000 * 60 * 5);
        Date sixMinAgo = new Date(now.getTime() - 1000 * 60 * 5 - 1000);

        CloudEyeMetricDemension dimension = CloudEyeMetricDemension.builder().name("instance_id")
                .value("33328f02-3814-422e-b688-bfdba93d4050").build();
        List<CloudEyeMetricDemension> dimensions = Lists.newArrayList(dimension);

        CloudEyeMetric metric = CloudEyeMetric.builder().namespace("Demo.Test").metricName("cpu_util")
                .dimensions(dimensions).build();

        CloudEyeMetricData data1 = CloudEyeMetricData.builder().metric(metric).ttl(604800).collectTime(fiveMinAgo)
                .value(60).unit("%").type(MetricData.ValueType.Integer).build();

        CloudEyeMetricData data2 = CloudEyeMetricData.builder().metric(metric).ttl(604800).collectTime(now).value(60)
                .unit("%").type(MetricData.ValueType.Integer).build();

        // step 4: Use client to add metric data
        ActionResponse resp = osClient.cloudEye().metricsDatas().add(Lists.newArrayList(data1, data2));
        System.out.println(resp);

        // step 5: Use client to get metric data with url params
        MetricAggregation aggregations = osClient.cloudEye().metricsDatas().get("Demo.Test", "cpu_util", sixMinAgo,
                now, Period.REAL_TIME, Filter.AVERAGE,
                new String[] { "instance_id,33328f02-3814-422e-b688-bfdba93d4050" });
        List<? extends DataPoint> dataPoints = aggregations.getDatapoints();
        System.out.println(dataPoints);
    }
}