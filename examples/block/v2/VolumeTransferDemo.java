package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.storage.block.VolumeTransfer;
import com.huawei.openstack4j.openstack.OSFactory;

public class VolumeTransferDemo
{
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

		//create transfer
		String volumeId = "xxx";
		String name = null;
		VolumeTransfer volumeTransfer =  os.blockStorage().volumes().transfer().create(volumeId, name);
		if (null != volumeTransfer)
		{
			System.out.println("create transfer success, transferId = " + volumeTransfer.getId());
		}
		else
		{
			System.out.println("create transfer failed");
		}
		String transferId = volumeTransfer.getId();
		String authKey = volumeTransfer.getAuthKey();

		//get transfer
		VolumeTransfer volumeTransferDetail = os.blockStorage().volumes().transfer().get(transferId);
		if (null != volumeTransferDetail)
		{
			System.out.println("get transfer success, transferId = " + volumeTransferDetail.getId());
		}
		else
		{
			System.out.println("get transfer failed");
		}

		//accept transfer
		VolumeTransfer acceptVolumeTransfer = os.blockStorage().volumes().transfer().accept(transferId, authKey);
		if (null != acceptVolumeTransfer)
		{
			System.out.println("accept transfer success, transferId = " + acceptVolumeTransfer.getId());
		}
		else
		{
			System.out.println("accept transfer failed");
		}

		//delete transfer
		ActionResponse deleteVolumeTransferResponse =os.blockStorage().volumes().transfer().delete(transferId);
		VolumeTransfer volumeTransferDeleted = os.blockStorage().volumes().transfer().get(transferId);
		if (null == volumeTransferDeleted)
		{
			System.out.println("delete transfer success");
		}
		else
		{
			System.out.println("delete transfer failed");
		}

		//list transfer
		List<? extends VolumeTransfer> volumeTransferList = os.blockStorage().volumes().transfer().list(false);
		if (null != volumeTransferList && volumeTransferList.size() >= 0)
		{
			System.out.println("get volumeTransferList success, count = " + volumeTransferList.size());
		}
		else
		{
			System.out.println("get volumeList failed");
		}
		VolumeTransfer volumeTransferDetail1 = volumeTransferList.get(0);

		//list transfer detail
		List<? extends VolumeTransfer> volumeTransferDetailList = os.blockStorage().volumes().transfer().list();
		if (null != volumeTransferDetailList && volumeTransferDetailList.size() >= 0)
		{
			System.out.println("get volumeTransferList success, count = " + volumeTransferDetailList.size());
		}
		else
		{
			System.out.println("get volumeList failed");
		}
		VolumeTransfer volumeTransferDetail2 = volumeTransferList.get(0);
	}
}
