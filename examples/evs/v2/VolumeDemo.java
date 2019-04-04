package com.huawei.openstack.sample;

import java.util.HashMap;
import java.util.Map;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.evs.v2.domain.Job;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumeResponse;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumes;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumesResponse;
import com.huawei.openstack4j.openstack.evs.v2.domain.Extend;
import com.huawei.openstack4j.openstack.evs.v2.domain.OSExtend;


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
		CloudVolumes volume = CloudVolumes.builder().size(10).name("volume").volumeType(VolumeType.SAS).availabilityZone("kvmxen.dc1").build();
		String createVolumeJobId = os.evs().volumes().create(volume);
		if (null != createVolumeJobId)
		{
			System.out.println("submit createJobId success, jobId = " + createVolumeJobId);
		}
		else
		{
			System.out.println("submit createJobId failed");
		}

		// get job
		Job job = os.evs().jobs().get(createVolumeJobId);
		if (null != job)
		{
			System.out.println("get jobId success!" + job);
			System.out.println("volumeId is " + job.getEntities().getVolumeId());
		}
		else
		{
			System.out.println("get jobId failed!");
		}

		//extend volume
		String volumeId = "xxx";
		Extend extend = Extend.builder().osExtend(OSExtend.builder().newSize(100).build()).build();
		String extendJobId = os.evs().volumes().extend(extend, volumeId);
		if (null != extendJobId)
		{
			System.out.println("submit extendJobId success, jobId = " + extendJobId);
		}
		else
		{
			System.out.println("submit extendJobId failed");
		}

		//get list of volume
		CloudVolumesResponse volumes = os.evs().volumes().list();
		if (null != volumes && volumes.getCount() >= 0)
		{
			System.out.println("get volumeList success, count = " + volumes.getCount());
		}
		else
		{
			System.out.println("get volumeList failed");
		}
		CloudVolumeResponse volumeDetail = volumes.getVolumeList().get(0);

		//get list of volume by filter
		Map<String, Object> filteringParams = new HashMap<>();
		filteringParams.put("name", "volumeName");
		CloudVolumesResponse volumes2 = os.evs().volumes().list(filteringParams);
		if (null != volumes2 && volumes2.getCount() >= 0)
		{
			System.out.println("get volumeList success, count = " + volumes2.getCount());
		}
		else
		{
			System.out.println("get volumeList failed");
		}
		CloudVolumeResponse volumeDetail2 = volumes2.getVolumeList().get(0);
	}
}
