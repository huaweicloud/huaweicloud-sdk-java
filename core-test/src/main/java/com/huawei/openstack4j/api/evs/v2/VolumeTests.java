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

import okhttp3.mockwebserver.RecordedRequest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumesResponse;

@Test(suiteName="Cloud Volume Tests")
public class VolumeTests extends AbstractTest{

     @Override
     protected AbstractTest.Service service() {
         return Service.EVS;
     }

     @Test
     public void listVolumesTest() throws Exception {
         // Check list volumes
         respondWith("/evs/v2/volumeslist.json");
         CloudVolumesResponse volumes = osv3().evs().volumes().list();
         assertEquals(volumes.getCount(),new Integer(3));
         System.out.println(volumes.getCount());
         System.out.println((volumes.getVolumeList().get(0).getTenantId()));
         assertEquals(volumes.getVolumeList().get(0).getTenantId(), "000efdc5f9064584b718b181df137bd7");
         assertEquals(volumes.getVolumeList().get(0).getServiceType(), "DSS");
         assertEquals(volumes.getVolumeList().get(0).getDedicatedStorageName(), "dss_yq");
         assertEquals(volumes.getVolumeList().get(0).getDedicatedStorageId(), "517eb026-2cdc-45da-9ebc-0c6ececb1236");

         // Check that the list request is the one we expect
         RecordedRequest listRequest = server.takeRequest();
         assertNotNull(listRequest.getHeader("X-Auth-Token"));
         assertTrue(listRequest.getPath().matches("/v2/project-id/cloudvolumes/detail"));

         // Check list volumes with filters
         respondWith("/evs/v2/volumeslist_filtered.json");
         final String volName = "7-000";
         Map<String, Object> filters = new HashMap<String, Object>();
         filters.put("name", volName);
         CloudVolumesResponse filteredVolumes = osv3().evs().volumes().list(filters);
         assertEquals(filteredVolumes.getCount(), new Integer(3));

         // Check that the list request is the one we expect
         RecordedRequest filteredListRequest = server.takeRequest();
         assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
         assertTrue(filteredListRequest.getPath().matches("/v2/project-id/cloudvolumes/detail\\?name=" + volName));
         // scsi
         assertEquals(filteredVolumes.getVolumeList().get(0).getWwn(), "6888603000106569fa166ab071602132");
         assertEquals(filteredVolumes.getVolumeList().get(0).getServiceType(), "DSS");
         assertEquals(filteredVolumes.getVolumeList().get(0).getDedicatedStorageName(), "dss_yq");
         assertEquals(filteredVolumes.getVolumeList().get(0).getDedicatedStorageId(), "517eb026-2cdc-45da-9ebc-0c6ececb1236");
     }

//     @Test
//     public void rollbackTest() throws IOException {
//         respondWith("/evs/v2/snapshot_rollback.json");
//         String snapshotId = "snapshotId";
//         String volumeId = "5aa119a8-d25b-45a7-8d1b-88e127885635";
//         SnapshotRollback rollback = SnapshotRollback.builder().volumeId(volumeId).build();
//         SnapshotRollback resp = osv3().evs().snapshots().rollback(snapshotId, rollback);
//
//         assertTrue(resp != null);
//         assertTrue(volumeId.equals(resp.getVolumeId()));
//     }
}