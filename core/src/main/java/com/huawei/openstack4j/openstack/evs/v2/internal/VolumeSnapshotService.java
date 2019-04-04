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
package com.huawei.openstack4j.openstack.evs.v2.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumeSnapshotsResponse;
import com.huawei.openstack4j.openstack.evs.v2.domain.Rollback;
import com.huawei.openstack4j.openstack.evs.v2.domain.RollbackResponse;

public class VolumeSnapshotService extends BaseElasticVolumeService{

     /**
      * List Cloud VolumeSnapshot
      * @return CloudVolumeSnapshots
      */
     public CloudVolumeSnapshotsResponse list(){
         return get(CloudVolumeSnapshotsResponse.class, uri("/cloudsnapshots/detail")).execute();
     }

     /**
      * List Cloud VolumeSnapshot By Filter
      * @return CloudVolumeSnapshots
      */
     public CloudVolumeSnapshotsResponse list(Map<String,Object> filteringParams){
         processListParams(filteringParams, "enterprise_project_ids");
         Invocation<CloudVolumeSnapshotsResponse> snapshotInvocation = buildInvocation(filteringParams);
         return snapshotInvocation.execute();
     }

     private Invocation<CloudVolumeSnapshotsResponse> buildInvocation(Map<String, Object> filteringParams) {
         Invocation<CloudVolumeSnapshotsResponse> snapshotInvocation = get(CloudVolumeSnapshotsResponse.class, "/cloudsnapshots/detail");
         if (filteringParams == null) {
             return snapshotInvocation;
         } else {
             for (Map.Entry<String, Object> entry : filteringParams.entrySet()) {
                 snapshotInvocation = snapshotInvocation.param(entry.getKey(), entry.getValue());
             }
         }
         return snapshotInvocation;
     }

     /**
      * Rollback From Snapshot To Volume
      * @return ActionResponse
      */
     public RollbackResponse rollback(String snapshotId) {
         checkArgument(!Strings.isNullOrEmpty(snapshotId), "parameter snapshotId should not be empty");
         Map<String, Object> content = new HashMap<String, Object>();
         content.put("rollback",new HashMap<>());
         return post(RollbackResponse.class, uri("/cloudsnapshots/%s/rollback", snapshotId))
                 .entity(content).execute();
     }

     /**
      * Rollback From Snapshot To Volume
      * @return ActionResponse
      */
     public RollbackResponse rollback(String snapshotId, Rollback rollback) {
         checkArgument(!Strings.isNullOrEmpty(snapshotId), "parameter snapshotId should not be empty");
         Map<String, Object> content = new HashMap<String, Object>();
         content.put("rollback",rollback);
         return post(RollbackResponse.class, uri("/cloudsnapshots/%s/rollback", snapshotId))
                 .entity(content).execute();
     }

//    /**
//     * Rolling Back a Snapshot to an EVS Disk
//     *
//     * @param snapshotId
//     * @param rollback
//     * @return
//     */
//    public SnapshotRollback rollback(String snapshotId, SnapshotRollback rollback) {
//        checkArgument(!Strings.isNullOrEmpty(snapshotId), "`snapshotId` should not be empty");
//        checkNotNull(rollback, "`rollback` is required");
//        return post(SnapshotRollback.class, uri("/os-vendor-snapshots/%s/rollback", snapshotId)).entity(rollback)
//                .execute();
//    }
 }
