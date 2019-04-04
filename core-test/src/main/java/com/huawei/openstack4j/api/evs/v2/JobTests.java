/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.api.evs.v2;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.evs.v2.domain.Job;
import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName="Job Tests")
public class JobTests extends AbstractTest{

    @Override
    protected AbstractTest.Service service() {
        return Service.EVS;
    }

    @Test
    public void getJobTest() throws Exception
    {
        // check create single volume job
        respondWith("/evs/v2/createsinglevolumejob.json");
        String createSingleVolumeJobId = "ff808081690a667101692e1724c4166c";
        Job createSingleVolumeJobDetail = osv3().evs().jobs().get(createSingleVolumeJobId);
        assertEquals(createSingleVolumeJobDetail.getStatus(), "SUCCESS");
        assertEquals(createSingleVolumeJobDetail.getJobType(), "createVolume");
        assertEquals(createSingleVolumeJobDetail.getBeginTime(), "2019-02-27T08:34:22.531Z");
        assertEquals(createSingleVolumeJobDetail.getEndTime(), "2019-02-27T08:34:30.581Z");
        assertEquals(createSingleVolumeJobDetail.getErrorCode(), null);
        assertEquals(createSingleVolumeJobDetail.getFailReason(), null);
        assertEquals(createSingleVolumeJobDetail.getJobId(), createSingleVolumeJobId);
        assertEquals(createSingleVolumeJobDetail.getEntities().getVolumeId(), "00392cdc-9755-4329-ae71-d8d93ce5ee67");

        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v1/project-id/jobs/" + createSingleVolumeJobId));

        // check create batch volume job
        respondWith("/evs/v2/createbatchvolumejob.json");
        String createBatchVolumeJobId = "ff808081690a667101692e1854b81685";
        Job createBatchVolumeJobDetail = osv3().evs().jobs().get(createBatchVolumeJobId);
        assertEquals(createBatchVolumeJobDetail.getStatus(), "RUNNING");
        assertEquals(createBatchVolumeJobDetail.getJobType(), "batchCreateVolume");
        assertEquals(createBatchVolumeJobDetail.getBeginTime(), "2019-02-27T08:35:40.343Z");
        assertEquals(createBatchVolumeJobDetail.getEndTime(), "");
        assertEquals(createBatchVolumeJobDetail.getErrorCode(), null);
        assertEquals(createBatchVolumeJobDetail.getFailReason(), null);
        assertEquals(createBatchVolumeJobDetail.getJobId(), createBatchVolumeJobId);
        List<Job> subJobs = createBatchVolumeJobDetail.getEntities().getSubJobs();
        assertEquals(subJobs.get(0).getEntities().getVolumeId(), "6f111eb6-3b1e-40ef-8594-a937a859e338");
        assertEquals(subJobs.get(0).getBeginTime(), "2019-02-27T08:35:40.440Z");
        assertEquals(subJobs.get(0).getEndTime(), "");
        assertEquals(subJobs.get(0).getStatus(), "RUNNING");
        assertEquals(subJobs.get(0).getErrorCode(), null);
        assertEquals(subJobs.get(0).getFailReason(), null);
        assertEquals(subJobs.get(0).getJobId(), "ff808081690a667101692e1855181688");
        assertEquals(subJobs.get(0).getJobType(), "createVolume");

        // Check that the list request is the one we expect
        RecordedRequest listRequest2 = server.takeRequest();
        assertNotNull(listRequest2.getHeader("X-Auth-Token"));
        assertTrue(listRequest2.getPath().matches("/v1/project-id/jobs/" + createBatchVolumeJobId));

    }

}