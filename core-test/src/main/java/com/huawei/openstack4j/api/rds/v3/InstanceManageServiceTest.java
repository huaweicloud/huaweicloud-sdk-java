/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack4j.api.rds.v3;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
@org.testng.annotations.Test(suiteName = "RDS/RDSV3", enabled = true)
public class InstanceManageServiceTest extends AbstractTest {

    @Test
    public void createInstanceTest() throws IOException, InterruptedException {
        respondWith("/rds/v3/create_instance_response.json");

        //Openstack4jSample.osclient = osclient;
        DataStore datastore = DataStore.builder().type("MySQL")
                .version("5.6").build();
        Ha createha = Ha.builder().mode("Ha")
                .replicationMode("semisync").build();
        Volume createVolume = Volume.builder().type("ULTRAHIGH")
                .size(100).build();
        BackupStrategy createBackupStrategy = BackupStrategy.builder().keepDays(7)
                .startTime("06:15-07:15").build();
        CreateInstanceRequest	CreateReq = CreateInstanceRequest.builder()
                .name("JAVA100_2_S-2")
                .volume(createVolume)
                .ha(createha)
                .datastore(datastore)
                .backupStrategy(createBackupStrategy)
                .flavorRef("rds.mysql.s1.medium.ha")
                .availabilityZone("*******")
                .vpcId("3138ce3d-8837-49a6-b68a-4cdbc5b30a45")
                .subnetId("0f48e1d1-c244-422a-baa0-acfb1133c148")
                .securityGroupId("702e9e18-34a2-4eda-a847-59546c3f5fa5")
                .password("*******")
                .port("*******")
                .region("*******").build();
        CreateInstanceResponse response = osv3().rds().instanceManage().create(CreateReq);
        RecordedRequest request = server.takeRequest();
        Assert.assertEquals(request.getPath(), "/v3/project-id/instances");
        Assert.assertEquals(request.getMethod(), "POST");
        Assert.assertEquals(response.getJobId(), "dff1d289-4d03-4942-8b9f-463ea07c000d");
        Assert.assertEquals(response.getInstance().getId(), "dsfae23fsfdsae3435in01");

    }

    @Override
    protected Service service() {
        return Service.RDSV3;
    }
}
