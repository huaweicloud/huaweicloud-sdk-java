package com.huawei.openstack.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.storage.block.Volume;
import com.huawei.openstack4j.model.storage.block.VolumeType;
import com.huawei.openstack4j.model.storage.block.VolumeUploadImage;
import com.huawei.openstack4j.model.storage.block.options.UploadImageData;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeUpdate;

public class VolumeDemo {
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

		//create volume
		Volume volume = Builders.volume().size(10).name("volume").volumeType("SAS").zone("az1.dc1").build();
		Volume newVolume = os.blockStorage().volumes().create(volume);
		if (null != newVolume)
		{
			System.out.println("create volume success, volumeId = " + newVolume.getId());
		}
		else
		{
			System.out.println("create volume failed");
		}
		//wait until volume status available
		int createVolumeCount = 1;
		boolean createVolumeFlag = false;
		while (createVolumeCount < 120) {
			if (os.blockStorage().volumes().get(newVolume.getId()).getStatus().toString().equals("available")) {
				System.out.println("volume created successfully");
				createVolumeFlag = true;
				break;
			}
			Thread.sleep(1000);
			createVolumeCount++;
		}
		if (!createVolumeFlag) {
			System.out.println("volume created failure");
		}

		//create volume with pool
		String dssId = "xxx";
		Map<String, Object> osVendorPools = new HashMap<>();
		osVendorPools.put("dedicated_storage_id", dssId);
		Volume newVolume2 = os.blockStorage().volumes().create(volume, osVendorPools);

		//get single volume
		String volumeId = "xxx";
		Volume singleVolume = os.blockStorage().volumes().get(volumeId);
		if (null != singleVolume)
		{
			System.out.println("get volume success, volumeId = " + singleVolume.getId());
		}
		else
		{
			System.out.println("get volume failed");
		}

		//expand volume
		ActionResponse expandVolumeResponse = os.blockStorage().volumes().extend(volumeId,100);
		//wait until volume status available
		int expandVolumeCount = 1;
		boolean expandVolumeFlag = false;
		while (expandVolumeCount < 120) {
			if (os.blockStorage().volumes().get(volumeId).getStatus().toString().equals("available")) {
				System.out.println("volume expand successfully");
				expandVolumeFlag = true;
				break;
			}
			Thread.sleep(1000);
			expandVolumeCount++;
		}
		if (!expandVolumeFlag) {
			System.out.println("volume created failure");
		}

		//update volume
		String name = "new_name";
		String description = "new_description";
		ActionResponse updateVolumeResponse = os.blockStorage().volumes().update(volumeId, name, description);
		Volume volumeUpdate1 = os.blockStorage().volumes().get(volumeId);
		if (null != volumeUpdate1 && name.equals(volumeUpdate1.getName()) && description.equals(volumeUpdate1.getDescription()))
		{
			System.out.println("update volume success");
		}
		else
		{
			System.out.println("update volume failed");
		}

		//update volume
		CinderVolumeUpdate cinderVolumeUpdate = CinderVolumeUpdate.builder().name(name).description(description).build();
		Volume volumeUpdate2 = os.blockStorage().volumes().update(volumeId,cinderVolumeUpdate);
		if (null != volumeUpdate2 && name.equals(volumeUpdate2.getName()) && description.equals(volumeUpdate2.getDescription()))
		{
			System.out.println("update volume success");
		}
		else
		{
			System.out.println("update volume failed");
		}

		//delete volume
		ActionResponse deleteVolumeResponse = os.blockStorage().volumes().delete(volumeId);
		//wait until volume deleted
		int queryDeletedVolumeCount = 1;
		boolean queryDeletedVolumeFlag = false;
		while (queryDeletedVolumeCount < 120) {
			Volume volumeDeleted = os.blockStorage().volumes().get(volumeId);
			if (null == volumeDeleted) {
				System.out.println("volume deleted successfully");
				queryDeletedVolumeFlag = true;
				break;
			}
			Thread.sleep(1000);
			queryDeletedVolumeCount++;
		}
		if (!queryDeletedVolumeFlag) {
			System.out.println("volume deleted failure");
		}

		//get list of volume
		List<? extends Volume> volumeList = os.blockStorage().volumes().list();
		if (null != volumeList && volumeList.size() >= 0)
		{
			System.out.println("get volumeList success, count = " + volumeList.size());
		}
		else
		{
			System.out.println("get volumeList failed");
		}
		Volume volumeDetail = volumeList.get(0);

		//get list of volume by filter
		Map<String, String> filteringParams2 = new HashMap<>();
		filteringParams2.put("name", "volumeName");
		List<? extends Volume> volumeList2 = os.blockStorage().volumes().list(filteringParams2);
		if (null != volumeList2 && volumeList2.size() >= 0)
		{
			System.out.println("get volumeList success, count = " + volumeList2.size());
		}
		else
		{
			System.out.println("get volumeList failed");
		}
		Volume volumeDetail2 = volumeList2.get(0);

		// get list of volume types
		List<? extends VolumeType> volumeTypes = os.blockStorage().volumes().listVolumeTypes();
		for (VolumeType type: volumeTypes)
		{
			System.out.println(type.getId());
		}

		//update volume to image
		String imageName = "imageName";
		UploadImageData data = UploadImageData.create(imageName);
		VolumeUploadImage volumeUploadImage = os.blockStorage().volumes().uploadToImage(volumeId, data);
		if (null != volumeUploadImage)
		{
			System.out.println("update volume to image success");
		}
		else
		{
			System.out.println("update volume to image failed");
		}

		//readonly mode update
		ActionResponse readOnlyModeUpdateActionResponse = os.blockStorage().volumes().readOnlyModeUpdate(volumeId, true);
		Volume volumeReadOnlyModeUpdated = os.blockStorage().volumes().get(volumeId);
		if (null != volumeReadOnlyModeUpdated && volumeReadOnlyModeUpdated.getMetaData().get("readonly").toString().equals("true"))
		{
			System.out.println("readonly mode update success");
		}
		else
		{
			System.out.println("readonly mode update failed");
		}
	}
}
