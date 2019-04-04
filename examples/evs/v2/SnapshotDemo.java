package com.huawei.openstack.sample;

import java.util.HashMap;
import java.util.Map;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumeSnapshot;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumeSnapshotsResponse;
import com.huawei.openstack4j.openstack.evs.v2.domain.Rollback;
import com.huawei.openstack4j.openstack.evs.v2.domain.RollbackResponse;

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


		//list snapshot
		CloudVolumeSnapshotsResponse snapshotList = os.evs().snapshots().list();
		if (null != snapshotList && snapshotList.getCount() >= 0)
		{
			System.out.println("get snapshotList success, count = " + snapshotList.getCount());
		}
		else
		{
			System.out.println("get snapshotList failed");
		}
		CloudVolumeSnapshot snapshotDetail = snapshotList.getSnapshotList().get(0);

		//list snapshot by filter
		Map<String, Object> filteringSnapshotParams = new HashMap<String, Object>();
		filteringSnapshotParams.put("id", "xxx");
		CloudVolumeSnapshotsResponse snapshotList2 = os.evs().snapshots().list(filteringSnapshotParams);
		if (null != snapshotList2 && snapshotList2.getCount() >= 0)
		{
			System.out.println("get snapshotList success, count = " + snapshotList2.getCount());
		}
		else
		{
			System.out.println("get snapshotList failed");
		}
		CloudVolumeSnapshot snapshotDetail2 = snapshotList2.getSnapshotList().get(0);

		//rollback from snapshot
		String volumeId = "xxx";
		String snapshotId = "xxx";
		String volumeName = "volumeName";
		Rollback rollback = Rollback.builder().volumeId(volumeId).name(volumeName).build();
		RollbackResponse rollbackResponse = os.evs().snapshots().rollback(snapshotId,rollback);
		if (null != rollbackResponse && volumeId.equals(rollbackResponse.getVolumeId()))
		{
			System.out.println("rollback from snapshot success");
		}
		else
		{
			System.out.println("rollback from snapshot failed");
		}
	}
}
