///*******************************************************************************
// * 	Copyright 2019 Huawei Technologies Co., Ltd.
// *
// * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * 	use this file except in compliance with the License. You may obtain a copy of
// * 	the License at
// *
// * 	    http://www.apache.org/licenses/LICENSE-2.0
// *
// * 	Unless required by applicable law or agreed to in writing, software
// * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * 	License for the specific language governing permissions and limitations under
// * 	the License.
// *******************************************************************************/
//package com.huawei.openstack4j.api.storage;
//
//import static org.testng.Assert.assertTrue;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.annotations.Test;
//
//import com.google.common.collect.Maps;
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.model.common.ActionResponse;
//import com.huawei.openstack4j.model.storage.block.Volume;
//import com.huawei.openstack4j.model.storage.block.VolumeType;
//import com.huawei.openstack4j.openstack.storage.block.domain.Extension;
//import com.huawei.openstack4j.openstack.storage.block.domain.VolumeMeta;
//import com.huawei.openstack4j.openstack.storage.block.domain.VolumeMetadata;
//
//@Test(suiteName = "EVS/Volumes", enabled = true)
//public class VolumeV2Tests extends AbstractTest {
//    private static final Logger LOGGER = LoggerFactory.getLogger(VolumeV2Tests.class);
//
//    private static final String JSON_VOLUMES_1 = "/storage/v2/volumes1.json";
//
//    private static final String JSON_VOLUME_METADATA_UPDATE = "/storage/v2/volume_metadata_update.json";
//
//    private static final String JSON_VOLUME_METADATA = "/storage/v2/volume_metadata.json";
//
//    private static final String JSON_VOLUME_METADATA_CREATE = "/storage/v2/volume_metadata_create.json";
//
//    private static final String JSON_VOLUME_METADATAS = "/storage/v2/volume_metadatas.json";
//
//    private static final String JSON_VOLUME_METADATA_UPDATE_WITH_KEY = "/storage/v2/volume_metadata_update_with_key.json";
//
//    private static final String JSON_EXTENSIONS = "/storage/v2/extensions.json";
//
//    private static final String JSON_TYPE = "/storage/v2/type.json";
//
//    @Test
//    public void listVolumesTest() throws IOException {
//        respondWith(JSON_VOLUMES_1);
//        List<? extends Volume> volumes = osv3().blockStorage().volumes().listVolumes();
//        LOGGER.info("{}", volumes);
//        assertTrue(volumes != null);
//    }
//
//    @Test
//    public void listVolumesWithOptionsTest() throws IOException {
//        respondWith(JSON_VOLUMES_1);
//        Map<String, String> query = new HashMap<String, String>();
//        query.put("name", "volume-for-snapshot");
//        List<? extends Volume> volumes = osv3().blockStorage().volumes().listVolumes(query);
//        LOGGER.info("{}", volumes);
//        assertTrue(volumes != null);
//    }
//
//    @Test
//    public void updateMetadataTest() throws IOException {
//        respondWith(JSON_VOLUME_METADATA_UPDATE);
//
//        String volumeId = "volumeId";
//        Map<String, String> map = Maps.newHashMap();
//        map.put("key", "value");
//        VolumeMetadata metadata = VolumeMetadata.builder().metadata(map).build();
//        VolumeMetadata updateMetadata = osv3().blockStorage().volumes().updateMetadata(volumeId, metadata);
//
//        assertTrue(updateMetadata != null);
//        assertTrue("value1".equals(updateMetadata.getMetadata().get("key1")));
//    }
//
//    @Test
//    public void deleteMetadataTest() {
//        respondWith(200);
//
//        String key = "key";
//        String volumeId = "volumeId";
//        ActionResponse resp = osv3().blockStorage().volumes().deleteMetadata(volumeId, key);
//
//        assertTrue(resp.isSuccess());
//    }
//
//    @Test
//    public void getMetaTest() throws IOException {
//        respondWith(JSON_VOLUME_METADATA);
//
//        String key = "key";
//        String volumeId = "volumeId";
//        VolumeMeta metadata = osv3().blockStorage().volumes().getMeta(volumeId, key);
//
//        assertTrue(metadata != null);
//        assertTrue("value1".equals(metadata.getMeta().get("key1")));
//    }
//
//    @Test
//    public void createMetadataTest() throws IOException {
//        respondWith(JSON_VOLUME_METADATA_CREATE);
//
//        String volumeId = "volumeId";
//        Map<String, String> map = Maps.newHashMap();
//        map.put("key1", "value1");
//        map.put("key2", "value2");
//        VolumeMetadata metadata = VolumeMetadata.builder().metadata(map).build();
//        VolumeMetadata createMetadata = osv3().blockStorage().volumes().createMetadata(volumeId, metadata);
//
//        assertTrue(createMetadata != null);
//        assertTrue("value1".equals(createMetadata.getMetadata().get("key1")));
//        assertTrue("value2".equals(createMetadata.getMetadata().get("key2")));
//    }
//
//    @Test
//    public void getMetadataTest() throws IOException {
//        respondWith(JSON_VOLUME_METADATAS);
//
//        String volumeId = "volumeId";
//        VolumeMetadata metadata = osv3().blockStorage().volumes().getMetadata(volumeId);
//
//        assertTrue(metadata != null);
//        assertTrue("value1".equals(metadata.getMetadata().get("key1")));
//        assertTrue("value2".equals(metadata.getMetadata().get("key2")));
//    }
//
//    @Test
//    public void updateMetaTest() throws IOException {
//        respondWith(JSON_VOLUME_METADATA_UPDATE_WITH_KEY);
//
//        String key = "key";
//        String volumeId = "volumeId";
//        Map<String, String> map = Maps.newHashMap();
//        map.put("key1", "value1");
//        VolumeMeta metadata = VolumeMeta.builder().meta(map).build();
//        VolumeMeta updateMetadata = osv3().blockStorage().volumes().updateMeta(volumeId, key, metadata);
//
//        assertTrue(updateMetadata != null);
//        assertTrue("value1".equals(updateMetadata.getMeta().get("key1")));
//    }
//
//    @Test
//    public void listExtensionsTest() throws IOException {
//        respondWith(JSON_EXTENSIONS);
//
//        List<? extends Extension> extensions = osv3().blockStorage().volumes().listExtensions();
//
//        assertTrue(extensions.size() > 0);
//        assertTrue("SchedulerHints".equals(extensions.get(0).getName()));
//    }
//
//    @Test
//    public void getVolumeTypeTest() throws IOException {
//        respondWith(JSON_TYPE);
//
//        String typeId = "ea6e3c13-aac5-46e0-b280-745ed272e662";
//        VolumeType type = osv3().blockStorage().volumes().getVolumeType(typeId);
//
//        assertTrue(type != null);
//        assertTrue(typeId.equals(type.getId()));
//    }
//
//    @Override
//    protected Service service() {
//        return Service.BLOCK_STORAGE;
//    }
//}
