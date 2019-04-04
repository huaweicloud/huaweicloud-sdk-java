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
package com.huawei.openstack4j.openstack.evs.v2.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

 @Getter
 @ToString
 @NoArgsConstructor
 @AllArgsConstructor
 public class CloudVolumeResponse implements ModelEntity
 {
     private static final long serialVersionUID = 2025864449639011577L;
     /**
      * {
      *             "attachments": [
      *                 {
      *                     "attachment_id": "20b73502-0098-4351-a953-348f9328ca61",
      *                     "volume_id": "de3108dd-860c-43bc-a572-42a47f561582",
      *                     "device": "/dev/vdb",
      *                     "server_id": "43608a25-7e0a-40be-a86b-13166e24c161",
      *                     "host_name": null,
      *                     "attached_at": "2019-01-10T02:07:25.343363",
      *                     "id": "de3108dd-860c-43bc-a572-42a47f561582"
      *                 }
      *              ],
      *             "availability_zone": "xxx",
      *             "bootable": "false",
      *             "consistencygroup_id": null,
      *             "created_at": "2016-05-25T02:42:10.856332",
      *             "description": null,
      *             "encrypted": false,
      *             "id": "b104b8db-170d-441b-897a-3c8ba9c5a214",
      *             "links": [
      *                 {
      *                     "href": "https://volume.localdomain.com:8776/v2/dd14c6ac581f40059e27f5320b60bf2f/volumes/b104b8db-170d-441b-897a-3c8ba9c5a214",
      *                     "rel": "self"
      *                 },
      *                 {
      *                     "href": "https://volume.localdomain.com:8776/dd14c6ac581f40059e27f5320b60bf2f/volumes/b104b8db-170d-441b-897a-3c8ba9c5a214",
      *                     "rel": "bookmark"
      *                 }
      *             ],
      *             "metadata": {
      *                 "__openstack_region_name": "pod01.xxx",
      *                 "a": "b",
      *                 "quantityGB": "1",
      *                 "volInfoUrl": "fusionstorage://172.30.64.10/0/FEFEEB07D3924CDEA93C612D4E16882D"
      *             },
      *             "enterprise_project_id": "5aa119a8-d25b-45a7-8d1b-88e127885635",
      *             "name": "zjb_u25_test",
      *             "os-vol-host-attr:host": "pod01.xxx#SATA",
      *             "volume_image_metadata": {
      *                 "__support_live_resize": "true",
      *                 "__is_support_kvm": "true",
      *                 "min_disk": "40",
      *                 "__image_source_type": "uds",
      *                 "container_format": "bare",
      *                 "image_name": "HWS Public testv6 CentOS 7.3 64bit",
      *                 "checksum": "d41d8cd98f00b204e9800998ecf8427e",
      *                 "__isregistered": "true",
      *                 "min_ram": "0",
      *                 "__lazyloading": "true",
      *                 "__os_type": "Linux",
      *                 "hw_vif_multiqueue_enabled": "true",
      *                 "__imagetype": "gold",
      *                 "__image_location": "10.175.38.120:443:pcsimssouthchina:CentOS-7.3-64bit",
      *                 "virtual_env_type": "FusionCompute",
      *                 "__support_xen": "true",
      *                 "__support_kvm": "true",
      *                 "__account_code": "linux",
      *                 "__support_highperformance": "true",
      *                 "__platform": "CentOS",
      *                 "size": "0",
      *                 "__os_bit": "64",
      *                 "__os_version": "CentOS 7.3 64bit",
      *                 "disk_format": "zvhd2",
      *                 "image_id": "918e1362-af25-469e-a295-f333b0736fcf"
      *             },
      *             "os-vol-mig-status-attr:migstat": null,
      *             "os-vol-mig-status-attr:name_id": null,
      *             "os-vol-tenant-attr:tenant_id": "dd14c6ac581f40059e27f5320b60bf2f",
      *             "os-volume-replication:extended_status": null,
      *             "replication_status": "disabled",
      *             "multiattach": false,
      *             "size": 1,
      *             "snapshot_id": null,
      *             "source_volid": null,
      *             "status": "available",
      *             "updated_at": "2016-05-25T02:42:22.341984",
      *             "user_id": "b0524e8342084ef5b74f158f78fc3049",
      *             "volume_type": "SATA",
      *             "service_type": "EVS",
      *             "dedicated_storage_id": null,
      *             "dedicated_storage_name": null,
      *             "wwn": "688860300000d136fa16f48f05992360",
      *             "backup_id": "null"
      *         }
      */

     /**
      * 云硬盘ID。
      */
     @JsonProperty("id")
     private String id;

     /**
      * 云硬盘URI自描述信息。
      */
     @JsonProperty("links")
     private List<Map<String,String>> links;

     /**
      * 云硬盘名称。
      */
     @JsonProperty("name")
     private String name;

     /**
      * 云硬盘状态。
      */
     @JsonProperty("status")
     private String status;

     /**
      * 挂载信息。
      */
     @JsonProperty("attachments")
     private List<Map<String,String>> attachments;

     /**
      * 云硬盘所属的AZ信息。
      */
     @JsonProperty("availability_zone")
     private String availabilityZone;

     /**
      * 云硬盘所在的主机。
      */
     @JsonProperty("os-vol-host-attr:host")
     private String host;

     /**
      * 源云硬盘ID，如果是从源云硬盘创建，则有值。
      */
     @JsonProperty("source_volid")
     private String sourceVolid;

     /**
      * 快照ID，如果是从快照创建，则有值。
      */
     @JsonProperty("snapshot_id")
     private String snapshotId;

     /**
      * 描述。
      */
     @JsonProperty("description")
     private String description;

     /**
      * 云硬盘创建时间。
      */
     @JsonProperty("created_at")
     private String createdAt;

     /**
      * 云硬盘类型。
      */
     @JsonProperty("volume_type")
     private String volumeType;

     /**
      * 云硬盘所属的租户ID。
      */
     @JsonProperty("os-vol-tenant-attr:tenant_id")
     private String tenantId;

     /**
      * 云硬盘大小。
      */
     @JsonProperty("size")
     private Integer size;

     /**
      * 云硬盘的元数据。
      * 如果元数据中不包含hw:passthrough字段，云硬盘默认为VBD类型。
      * 如果元数据中不包含__system__encrypted字段，云硬盘默认为不加密。
      */
     @JsonProperty("metadata")
     private Map<String, String> metadata;

     /**
      * 云硬盘迁移状态。
      */
     @JsonProperty("os-vol-mig-status-attr:migstat")
     private String migrateStatus;

     /**
      * 云硬盘迁移名称ID。
      */
     @JsonProperty("os-vol-mig-status-attr:name_id")
     private String migrateNameId;

     /**
      * 云硬盘复制扩展状态。
      */
     @JsonProperty("os-volume-replication:extended_status")
     private String replicationExtendedStatus;

     /**
      * 是否为加密云硬盘。
      */
     @JsonProperty("encrypted")
     private Boolean encrypted;

     /**
      * 云硬盘迁移状态。
      */
     @JsonProperty("replication_status")
     private String replicationStatus;

     /**
      * 使用云硬盘的用户ID。（预留属性）
      */
     @JsonProperty("user_id")
     private String userId;

     /**
      * 云硬盘所属一致性组ID。
      */
     @JsonProperty("consistencygroup_id")
     private String consistencygroupId;

     /**
      * 是否为可启动云硬盘。
      */
     @JsonProperty("bootable")
     private String bootable;

     /**
      * 云硬盘更新时间。
      */
     @JsonProperty("updated_at")
     private String updatedAt;

     /**
      * 是否为可共享云硬盘。
      * 说明：该字段已经废弃，请使用multiattach。
      */
     @JsonProperty("shareable")
     private Boolean shareable;

     /**
      * 是否为可共享云硬盘。
      */
     @JsonProperty("multiattach")
     private Boolean multiattach;

     /**
      * 如果云硬盘是从镜像创建的则会有该字段，否则该字段为空。
      */
     @JsonProperty("volume_image_metadata")
     private Map<String,String> volumeImageMetadata;

     /**
      * 服务类型，结果为EVS、DSS、DESS。
      */
     @JsonProperty("service_type")
     private String serviceType;

     /**
      * 云硬盘所属的专属存储池ID。
      */
     @JsonProperty("dedicated_storage_id")
     private String dedicatedStorageId;

     /**
      * 云硬盘所属的专属存储池的名称。
      */
     @JsonProperty("dedicated_storage_name")
     private String dedicatedStorageName;

     /**
      * 云硬盘的标签。
      * 如果云硬盘有标签，则会有改字段，否则该字段为空。
      */
     @JsonProperty("tags")
     private Map<String,String> tags;

     /**
      * SCSI云硬盘挂载时的唯一标识。
      */
     @JsonProperty("wwn")
     private String wwn;

     /**
      * 云硬盘上绑定的企业项目ID。
      */
     @JsonProperty("enterprise_project_id")
     private String enterpriseProjectId;

     /**
      * 备份ID，如果是从备份创建云硬盘，则有值。
      */
     @JsonProperty("backup_id")
     private String backupId;

     /**
      * 系统标签列表。只有通过op_service权限查询时才会返回该字段。
      */
     @JsonProperty("sys_tags")
     private Map<String,String> sysTags;

     /**
      * 云硬盘的加锁类型。
      */
     @JsonProperty("os-vendor-extended:lock_scene")
     private String lockScene;

     /**
      * 云硬盘的锁归属的服务类型。
      */
     @JsonProperty("os-vendor-extended:lock_source_service")
     private String lockSourceService;

     /**
      * 云硬盘的锁归属的资源ID。
      */
     @JsonProperty("os-vendor-extended:lock_source_id")
     private String lockSourceId;

     /**
      * 检查锁是否有效的回调链接。
      */
     @JsonProperty("os-vendor-extended:lock_check_endpoint")
     private String lockCheckEndpoint;

     /**
      * 镜像ID，如果是从镜像创建云硬盘，则有值。
      */
     private String imageId;
     public String getImageId()
     {
         if (null != this.volumeImageMetadata)
         {
             return this.volumeImageMetadata.get("image_id");
         }
         return null;
     }
 }
