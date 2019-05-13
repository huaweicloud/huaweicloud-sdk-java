package sample;

import java.util.List;

import com.google.common.collect.Lists;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.model.network.Port;
import com.huawei.openstack4j.model.network.Subnet;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.compute.domain.FixedIp;

import com.huawei.openstack4j.model.compute.InterfaceAttachment;
import com.huawei.openstack4j.openstack.compute.domain.NovaInterfaceAttachmentCreate;

public class InterfaceDemo {
	public static void main(String[] args) {

		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxx"; //username
		String password = "xxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		// create connection
		OSClientV3 os = OSFactory.builderV3()
			.endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		String serId = "2dda0766-6a71-4c1d-b1e8-9a0387bf8cd8";
		String portId = null;
		String attachmentId = null;
		
		//create network 
		Network network = os.networking().network().create(Builders 
					.network() 
					.name("network-name") 
					.adminStateUp(true) 
					.build()); 
		//create subnet 
		Subnet subnet = os.networking().subnet().create(Builders.subnet() 
					.networkId(network.getId()) 
					.name("subnet-name") 
					.enableDHCP(true) 
					.cidr("192.168.0.0/24") 
					.addDNSNameServer("8.8.8.8") 
					.gateway("192.168.0.1") 
					.build()); 
		
		//create port
		Port port = os.networking().port().create(Builders.port()
				.name("port-name")
				.networkId(network.getId())
				.fixedIp("192.168.0.101", subnet.getId())
				.build());
		portId = port.getId();
		
		//create interface with port_id
		InterfaceAttachment newAttach = os.compute().servers().interfaces().create(serId, portId);
		if (null != newAttach) {
			attachmentId = newAttach.getPortId();
			System.out.println("create InterfaceAttachment success, portStatus = " + newAttach.getPortState());
		} else {
			System.out.println("create InterfaceAttachment failed");
		}

		//create interface with net_id and fixed_ips
		String ipAddress = "192.168.1.100";
		FixedIp fixedIp = FixedIp.builder().ipAddress(ipAddress).build();
		List<FixedIp> fixedIps = Lists.newArrayList();
		fixedIps.add(fixedIp);
		NovaInterfaceAttachmentCreate build = NovaInterfaceAttachmentCreate.builder()
				.netId(network.getId())
				.fixedIps(fixedIps)
				.build();
		InterfaceAttachment created = os.compute().servers().interfaces().create(serId, build);
		System.out.println("created interfaceAttachment is : " + created);
		
		//get list of interface
		List<? extends InterfaceAttachment> list = os.compute().servers().interfaces().list(serId);
		if (list.size() > 0) {
			System.out.println("get InterfaceAttachmentList success, size = " + list.size());
		} else {
			System.out.println("get InterfaceAttachmentList failed");
		}
		
		//get interface
		InterfaceAttachment gotAttach = os.compute().servers().interfaces().get(serId, attachmentId);
		if (null != gotAttach) {
			System.out.println("get InterfaceAttachment success, AttachmentId = " + gotAttach.getPortId());
		} else {
			System.out.println("get InterfaceAttachment failed");
		}
		
		//delete interface
		ActionResponse rep = os.compute().servers().interfaces().detach(serId, attachmentId);
		if (rep.isSuccess()) {
			InterfaceAttachment delAttach = os.compute().servers().interfaces().get(serId, attachmentId);
			System.out.println("detach success, deletedAttachment = " + delAttach);
		} else {
			System.out.println("detach failed");
		}
	}
}
