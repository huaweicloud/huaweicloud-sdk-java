package com.huawei.openstack.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.storage.block.VolumeSnapshot;
import com.huawei.openstack4j.openstack.OSFactory;

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
		Map<String, String> filteringParams = new HashMap<>();
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
	}
}
