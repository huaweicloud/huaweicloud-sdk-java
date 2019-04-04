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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumeSnapshotsResponse;
import com.huawei.openstack4j.openstack.evs.v2.domain.Rollback;
import com.huawei.openstack4j.openstack.evs.v2.domain.RollbackResponse;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName="Cloud VolumeSnapshot Tests")
public class VolumeSnapshotTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.EVS;
    }
    

    public void listVolumeSnaphotsTest() throws Exception {
        // Check list snapshots
        respondWith("/evs/v2/volumesnapshotslist.json");
        CloudVolumeSnapshotsResponse snapshots = osv3().evs().snapshots().list();
        assertEquals(snapshots.getCount(), new Integer(2));

        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v2/project-id/cloudsnapshots/detail"));

        // Check list snapshot with filters
        respondWith("/evs/v2/volumesnapshotslist_filtered.json");
        final String volumeId = "e51d7a63-c267-4389-a23a-0fc8db41efc3";
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("volume_id", volumeId);
        CloudVolumeSnapshotsResponse filteredSnapshots = osv3().evs().snapshots().list(filters);
        assertEquals(filteredSnapshots.getCount(), new Integer(2));

        // Check that the list request is the one we expect
        RecordedRequest filteredListRequest = server.takeRequest();
        assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
        assertTrue(filteredListRequest.getPath().matches("/v2/project-id/cloudsnapshots/detail\\?volume_id=" + volumeId));
    }

    public void rollbackSnaphotTest() throws Exception {

        final String volumeId = "a1a2c512-680b-43ea-8bb3-9cbf344ee202";
        final String volumeName = "c6bbd380-1d53-11e9-8485-c06a822c1a2d";
        final String snapshotId = "8163f948-05e0-421f-98f5-d64ebfff7a5f";

        // Check snapshot rollback
        respondWith("/evs/v2/volumesnapshotrollback.json");
        RollbackResponse rollbackResponse = osv3().evs().snapshots().rollback(snapshotId);
        assertEquals(rollbackResponse.getVolumeId(), volumeId);

        // Check that the rollback request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v2/project-id/cloudsnapshots/8163f948-05e0-421f-98f5-d64ebfff7a5f/rollback"));

        // Check snapshot rollback with rollback content
        Rollback rollback = Rollback.builder().volumeId(volumeId).name(volumeName).build();
        respondWith("/evs/v2/volumesnapshotrollback.json");
        RollbackResponse rollbackResponse2 = osv3().evs().snapshots().rollback(snapshotId,rollback);
        assertEquals(rollbackResponse2.getVolumeId(), volumeId);

        // Check that the rollback request is the one we expect
        RecordedRequest listRequest2 = server.takeRequest();
        assertNotNull(listRequest2.getHeader("X-Auth-Token"));
        assertTrue(listRequest2.getPath().matches("/v2/project-id/cloudsnapshots/8163f948-05e0-421f-98f5-d64ebfff7a5f/rollback"));
    }

}
