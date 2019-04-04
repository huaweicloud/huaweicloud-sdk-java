package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumes;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.evs.v2.contants.ChargingType;
import com.huawei.openstack4j.openstack.evs.v2.domain.Metadata;
import com.huawei.openstack4j.openstack.evs.v2_1.contants.PeriodType;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.BssParam;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.Extend;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.OSExtend;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.Volumes;

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
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.EVS2_1, "https://evs.xxx.yyy.com/v2.1/%(project_id)s");
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver);
		OSClient.OSClientV3 os = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToDomain(Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();

		// create volume
		Metadata buildMetadata = Metadata.builder().systemEncrypted("0").hwPassthrough(true).build();
		CloudVolumes vo = CloudVolumes.builder().name("namexxx").volumeType(VolumeType.SAS).metadata(buildMetadata)
				.size(120).availabilityZone("kvmxen.dc1").multiattach(true).count(1).build();
		BssParam bssParam = BssParam.builder().chargingMode(ChargingType.prePaid).periodType(PeriodType.year).periodNum(1).isAutoPay(true).isAutoRenew(true).build();
		Volumes volumes = Volumes.builder().cloudVolumes(vo).bssParam(bssParam).build();
		AsyncRespEntity createVolumeResponse =os.evsV2_1().volumes().create(volumes);
		String createVolumeOrderId = createVolumeResponse.getOrderId();

		// extend volume
		Extend extend = Extend.builder().osExtend(OSExtend.builder().newSize(210).build()).build();
		AsyncRespEntity extendVolumeResponse = os.evsV2_1().volumes().extend(extend,"volumeId");
		String extendVolumeOrderId = extendVolumeResponse.getOrderId();
	}
}
