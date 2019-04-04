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
//import java.util.List;
//import java.util.Map;
//
//import org.testng.annotations.Test;
//
//import com.google.common.collect.Maps;
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.model.common.ActionResponse;
//import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotDetail;
//import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotMeta;
//import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotMetadata;
//import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotUpdate;
//import com.huawei.openstack4j.openstack.storage.block.options.SnapshotListOptions;
//
//@Test(suiteName = "EVS/Snapshots", enabled = true)
//public class VolumeSnapshotV2Tests extends AbstractTest {
//
//	private static final String JSON_SNAPSHOTS = "/storage/v2/snapshots.json";
//	private static final String JSON_SNAPSHOT_UPDATE = "/storage/v2/snapshot_update.json";
//	private static final String JSON_SNAPSHOT_METADATA_CREATE = "/storage/v2/snapshot_metadata_create.json";
//	private static final String JSON_SNAPSHOT_METADATAS = "/storage/v2/snapshot_metadatas.json";
//	private static final String JSON_SNAPSHOT_METADATA_UPDATE = "/storage/v2/snapshot_metadata_update.json";
//	private static final String JSON_SNAPSHOT_METADATA = "/storage/v2/snapshot_metadata.json";
//
//	@Test
//	public void listDetailTest() throws IOException {
//		respondWith(JSON_SNAPSHOTS);
//
//		List<? extends SnapshotDetail> snapshots = osv3().blockStorage().snapshots().listDetail();
//
//		assertTrue(snapshots.size() > 0);
//		assertTrue("b836dc3d-4e10-4ea4-a34c-8f6b0460a583".equals(snapshots.get(0).getId()));
//	}
//
//	@Test
//	public void listDetailWithOptionsTest() throws IOException {
//		respondWith(JSON_SNAPSHOTS);
//
//		SnapshotListOptions options = SnapshotListOptions.create();
//		List<? extends SnapshotDetail> snapshots = osv3().blockStorage().snapshots().listDetail(options);
//
//		assertTrue(snapshots.size() > 0);
//		assertTrue("b836dc3d-4e10-4ea4-a34c-8f6b0460a583".equals(snapshots.get(0).getId()));
//	}
//
//	@Test
//	public void updateTest() throws IOException {
//		respondWith(JSON_SNAPSHOT_UPDATE);
//
//		String snapshotId = "f9faf7df-fdc1-4093-9ef3-5cba06eef995";
//		String name = "snap-001";
//		SnapshotUpdate snapshot = SnapshotUpdate.builder().name(name).build();
//		SnapshotDetail update = osv3().blockStorage().snapshots().update(snapshotId, snapshot);
//
//		assertTrue(update != null);
//		assertTrue(snapshotId.equals(update.getId()));
//	}
//
//	@Test
//	public void createMetadataTest() throws IOException {
//		respondWith(JSON_SNAPSHOT_METADATA_CREATE);
//
//		String snapshotId = "snapshotId";
//		Map<String, String> map = Maps.newHashMap();
//		map.put("key1", "value1");
//		map.put("key2", "value2");
//		SnapshotMetadata metadata = SnapshotMetadata.builder().metadata(map).build();
//		SnapshotMetadata createMetadata = osv3().blockStorage().snapshots().createMetadata(snapshotId, metadata);
//
//		assertTrue(createMetadata != null);
//		assertTrue("value1".equals(createMetadata.getMetadata().get("key1")));
//	}
//
//	@Test
//	public void getMetadataTest() throws IOException {
//		respondWith(JSON_SNAPSHOT_METADATAS);
//
//		String snapshotId = "snapshotId";
//		SnapshotMetadata metadata = osv3().blockStorage().snapshots().getMetadata(snapshotId);
//
//		assertTrue(metadata != null);
//		assertTrue("value1".equals(metadata.getMetadata().get("key1")));
//	}
//
//	@Test
//	public void updateMetadataTest() throws IOException {
//		respondWith(JSON_SNAPSHOT_METADATA_UPDATE);
//
//		String snapshotId = "snapshotId";
//		Map<String, String> map = Maps.newHashMap();
//		map.put("key1", "value1");
//		map.put("key2", "value2");
//		SnapshotMetadata metadata = SnapshotMetadata.builder().metadata(map).build();
//		SnapshotMetadata updateMetadata = osv3().blockStorage().snapshots().updateMetadata(snapshotId, metadata);
//
//		assertTrue(updateMetadata != null);
//		assertTrue("value1".equals(updateMetadata.getMetadata().get("key1")));
//	}
//
//	@Test
//	public void deleteMetaTest() {
//		respondWith(200);
//
//		String key = "key";
//		String snapshotId = "snapshotId";
//		ActionResponse resp = osv3().blockStorage().snapshots().deleteMeta(snapshotId, key);
//
//		assertTrue(resp.isSuccess());
//	}
//
//	@Test
//	public void getMetaTest() throws IOException {
//		respondWith(JSON_SNAPSHOT_METADATA);
//
//		String key = "key1";
//		String snapshotId = "snapshotId";
//		SnapshotMeta metadata = osv3().blockStorage().snapshots().getMeta(snapshotId, key);
//
//		assertTrue(metadata != null);
//		assertTrue("value1".equals(metadata.getMeta().get(key)));
//	}
//
//	@Test
//	public void updateMetaTest() throws IOException {
//		respondWith(JSON_SNAPSHOT_METADATA);
//
//		String key = "key1";
//		String value = "value1";
//		String snapshotId = "snapshotId";
//		Map<String, String> map = Maps.newHashMap();
//		map.put(key, value);
//		SnapshotMeta metadata = SnapshotMeta.builder().meta(map).build();
//		SnapshotMeta updateMetadata = osv3().blockStorage().snapshots().updateMeta(snapshotId, key, metadata);
//
//		assertTrue(updateMetadata != null);
//		assertTrue(value.equals(updateMetadata.getMeta().get(key)));
//	}
//
//	@Override
//	protected Service service() {
//		return Service.BLOCK_STORAGE;
//	}
//
//}
