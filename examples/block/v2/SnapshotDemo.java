package com.huawei.openstack.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.storage.block.VolumeSnapshot;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeSnapshotsResponse;
import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotDetail;
import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotMeta;
import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotMetadata;
import com.huawei.openstack4j.openstack.storage.block.domain.SnapshotUpdate;
import com.huawei.openstack4j.openstack.storage.block.options.SnapshotListOptions;

public class SnapshotDemo {
	public static void main(String[] args) throws InterruptedException
	{

		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxx"; //username
		String password = "xxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		//create connection
		OSClientV3 os =
				OSFactory.builderV3().endpoint(authUrl).credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId)).authenticate();

		//create snapshot
		String volumeId = "xxx";
		VolumeSnapshot snapshot = Builders.volumeSnapshot().name("snapshot").volume(volumeId).build();
		VolumeSnapshot newSnapshot = os.blockStorage().snapshots().create(snapshot);
		if (null != newSnapshot)
		{
			System.out.println("create snapshot success, snapshotId = " + newSnapshot.getId());
		}
		else
		{
			System.out.println("create snapshot failed");
		}
		//wait until snapshot status available
		int createSnapshotCount = 1;
		boolean createSnapshotFlag = false;
		while (createSnapshotCount < 120) {
			if (os.blockStorage().snapshots().get(newSnapshot.getId()).getStatus().toString().equals("available")) {
				System.out.println("snapshot created successfully");
				createSnapshotFlag = true;
				break;
			}
			Thread.sleep(1000);
			createSnapshotCount++;
		}
		if (!createSnapshotFlag) {
			System.out.println("snapshot created failure");
		}

		//update snapshot
		String snapshotId = "xxx";
		String name = "new_name";
		String description = "new_desc";
		ActionResponse updateSnapshotResponse = os.blockStorage().snapshots().update(snapshotId, name, description);
		VolumeSnapshot snapshotUpdated = os.blockStorage().snapshots().get(snapshotId);
		if (null != snapshotUpdated && name.equals(snapshotUpdated.getName()) && description.equals(snapshotUpdated.getDescription()))
		{
			System.out.println("update snapshot success");
		}
		else
		{
			System.out.println("update snapshot failed");
		}

		//update snapshot
		String snapshotId2 = "xxx";
		String name2 = "new_name";
		String description2 = "new_desc";
		SnapshotUpdate snapshotUpdate = SnapshotUpdate.builder().name(name2).description(description2).build();
		SnapshotDetail snapshotUpdated2 = os.blockStorage().snapshots().update(snapshotId2, snapshotUpdate);
		if (null != snapshotUpdated2 && name2.equals(snapshotUpdated2.getName()) && description2.equals(snapshotUpdated2.getDescription()))
		{
			System.out.println("update snapshot success");
		}
		else
		{
			System.out.println("update snapshot failed");
		}

		//get snapshot
		VolumeSnapshot singleSnapshotDetail = os.blockStorage().snapshots().get(snapshotId);
		if (null != singleSnapshotDetail)
		{
			System.out.println("get snapshot success, snapshotId = " + singleSnapshotDetail.getId());
		}
		else
		{
			System.out.println("get snapshot failed");
		}

		//delete snapshot
		ActionResponse deleteSnapshotResponse = os.blockStorage().snapshots().delete(snapshotId);
		//wait until snapshot deleted
		int queryDeletedSnapshotCount = 1;
		boolean queryDeletedSnapshotFlag = false;
		while (queryDeletedSnapshotCount < 120) {
			VolumeSnapshot snapshotDeleted = os.blockStorage().snapshots().get(snapshotId);
			if (null == snapshotDeleted) {
				System.out.println("snapshot deleted successfully");
				queryDeletedSnapshotFlag = true;
				break;
			}
			Thread.sleep(1000);
			queryDeletedSnapshotCount++;
		}
		if (!queryDeletedSnapshotFlag) {
			System.out.println("snapshot deleted failure");
		}

		//get snapshots list
		List<? extends VolumeSnapshot> snapshotsList = os.blockStorage().snapshots().list();
		if (null != snapshotsList && snapshotsList.size() >= 0)
		{
			System.out.println("get snapshotsList success, count = " + snapshotsList.size());
		}
		else
		{
			System.out.println("get snapshotsList failed");
		}
		VolumeSnapshot snapshotDetail = snapshotsList.get(0);

		//get snapshots list with filter
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("status","available");
		List<? extends VolumeSnapshot> snapshotsList2 = os.blockStorage().snapshots().list(filteringParams);
		if (null != snapshotsList2 && snapshotsList2.size() >= 0)
		{
			System.out.println("get snapshotsList success, count = " + snapshotsList2.size());
		}
		else
		{
			System.out.println("get snapshotsList failed");
		}
		VolumeSnapshot snapshotDetail2 = snapshotsList2.get(0);

		//get snapshots detail list
		CinderVolumeSnapshotsResponse snapshotsList3 = os.blockStorage().snapshots().listDetail();
		if (null != snapshotsList3 && snapshotsList3.getSnapshotList().size() >= 0)
		{
			System.out.println("get snapshotsList success, count = " + snapshotsList3.getSnapshotList().size());
		}
		else
		{
			System.out.println("get snapshotsList failed");
		}
		SnapshotDetail snapshotDetail3 = snapshotsList3.getSnapshotList().get(0);

		//get snapshots detail list with status
		Map<String,String> filteringParams = new HashMap<String,String>();
        filteringParams.put("status","available");
		CinderVolumeSnapshotsResponse snapshotsList4 = os.blockStorage().snapshots().listDetail(filteringParams);
		if (null != snapshotsList4 && snapshotsList4.getSnapshotList().size() >= 0)
		{
			System.out.println("get snapshotsList success, count = " + snapshotsList4.getSnapshotList().size());

		}
		else
		{
			System.out.println("get snapshotsList failed");
		}
		SnapshotDetail snapshotDetail = snapshotsList4.getSnapshotList().get(0);

		// create snapshot metadata
		Map<String, String> createMetadataMap = Maps.newHashMap();
		createMetadataMap.put("key1", "value1");
		createMetadataMap.put("key2", "value2");
		SnapshotMetadata createMetadataReq = SnapshotMetadata.builder().metadata(createMetadataMap).build();
		SnapshotMetadata createMetadataRep = os.blockStorage().snapshots().createMetadata(snapshotId, createMetadataReq);
		if (null != createMetadataRep)
		{
			System.out.println("create snapshot metadata success");
		}
		else
		{
			System.out.println("create snapshot metadata failed");
		}

		// get snapshot metadata
		SnapshotMetadata getMetadataRep = os.blockStorage().snapshots().getMetadata(snapshotId);
		if (null != getMetadataRep)
		{
			System.out.println("get snapshot metadata success");
		}
		else
		{
			System.out.println("get snapshot metadata failed");
		}

		// update snapshot metadata
		Map<String, String> updateMetadataMap = Maps.newHashMap();
		updateMetadataMap.put("key", "value");
		SnapshotMetadata updateMetadataReq = SnapshotMetadata.builder().metadata(updateMetadataMap).build();
		SnapshotMetadata updateMetadataRep = os.blockStorage().snapshots().updateMetadata(snapshotId, updateMetadataReq);
		if (null != updateMetadataRep)
		{
			System.out.println("update snapshot metadata success");
		}
		else
		{
			System.out.println("update snapshot metadata failed");
		}

		// update snapshot metadata by key
		String updateMetakey = "key";
		Map<String, String> updateMetaMap = Maps.newHashMap();
		updateMetaMap.put(updateMetakey, "value_update");
		updateMetadataMap.put("key1", "value1");
		updateMetadataMap.put("key2", "value2");

		SnapshotMeta updateMetaReq = SnapshotMeta.builder().meta(updateMetaMap).build();
		SnapshotMeta updateMetaRep = os.blockStorage().snapshots().updateMeta(snapshotId, updateMetakey, updateMetaReq);
		if (null != updateMetaRep)
		{
			System.out.println("update snapshot metadata by key success");
		}
		else
		{
			System.out.println("update snapshot metadata by key failed");
		}

		// delete snapshot metadata by key
		String deleteMetatdataKey = "key1";
		ActionResponse deleteMetatdataRep = os.blockStorage().snapshots().deleteMeta(snapshotId, deleteMetatdataKey);
		SnapshotMeta getDeletedMeta = os.blockStorage().snapshots().getMeta(snapshotId, deleteMetatdataKey);
		if (null == getDeletedMeta)
		{
			System.out.println("delete snapshot metadata by key success");
		}
		else
		{
			System.out.println("delete snapshot metadata by key failed");
		}

		// get snapshot metadata by key
		String getMetatdataKey = "key2";
		SnapshotMeta getMetaRep = os.blockStorage().snapshots().getMeta(snapshotId, getMetatdataKey);
		if (null != getMetaRep)
		{
			System.out.println("get snapshot metadata by key success");
		}
		else
		{
			System.out.println("get snapshot metadata by key failed");
		}

	}
}
