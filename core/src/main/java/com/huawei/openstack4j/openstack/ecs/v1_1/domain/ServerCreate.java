 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.ecs.v1_1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Network;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.PublicIP;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.RootVolume;

/**
 * A model represent Compute V1 Server creation
 */
@JsonRootName("server")
public class ServerCreate implements ModelEntity {

	private static final long serialVersionUID = 190714568493709721L;

	/**
	 * 云服务器名称,
	 * 取值范围： 只能由中文字符、英文字母（a~zA~Z）、数字（0~9）、下划线（_）、中划线（-）组成，且长度为[1-64]个字符。
	 * 创建的云服务器数量（count字段对应的值）大于1时，为区分不同云服务器，创建过程中系统会自动在名称后加“-0000”的类似标记。故此时名称的长度为[1-59]个字符。
	 */
	private String name;

	/**
	 * 待创建云服务器的系统镜像，需要指定已创建镜像的ID，ID格式为通用唯一识别码（Universally Unique Identifier，简称UUID）。
	 */
	private String imageRef;

	/**
	 * 待创建云服务器的系统规格的ID
	 */
	private String flavorRef;

	/**
	 * 创建云服务器过程中注入文件信息。最大支持注入5个文件，每个文件最大1KB。
	 */
	private List<Personality> personality;

	/**
	 * 创建云服务器过程中注入用户数据。
	 */
	@JsonProperty("user_data")
	private String userData;

	/**
	 * 创建云服务器过程中注入文件信息。
	 * 当前使用cloud-init的方式实现密码注入，该参数无效。
	 */
	private String adminPass;

	/**
	 * 如果需要使用SSH秘钥方式登录云服务器，请指定已创建秘钥的名称。
	 * 约束：当key_name与user_data同时指定时，user_data只做用户数据注入。
	 */
	@JsonProperty("key_name")
	private String keyName;

	/**
	 * 	待创建云服务器所属虚拟私有云（简称VPC），需要指定已创建VPC的ID，UUID格式。
	 */
	@JsonProperty("vpcid")
	private String vpcId;

	/**
	 * 	待创建云服务器的网卡信息。
	 * 	约束： 网卡对应的网络（network）必须属于vpcid对应的VPC。 当前单个云服务器支持最多挂载12张网卡。
	 */
	@JsonProperty("nics")
	private List<Network> networks;

	/**
	 * 配置云服务器的弹性IP信息，弹性IP有三种配置方式。
	
		不使用（无该字段）
		自动分配，需要指定新创建弹性IP的信息
		使用已有，需要指定已创建弹性IP的信息
	 */
	@JsonProperty("publicip")
	private PublicIP publicIP;

	/**
	 * 创建云服务器数量。
	 * 约束： 不传该字段时默认取值为1。 租户的配额足够时，最大值为500。
	 */
	@JsonProperty("count")
	private Integer count;

	/**
	 * 云服务器对应系统盘相关配置。
	 */
	@JsonProperty("root_volume")
	private RootVolume rootVolume;

	/**
	 * 云服务器对应数据盘相关配置。每一个数据结构代表一块待创建的数据盘。
		约束：目前新创建的弹性云服务器最多可挂载23块数据盘。
	 */
	@JsonProperty("data_volumes")
	private List<DataVolume> dataVolumes;

	/**
	 * 云服务器对应安全组信息。
	 * 约束：当该值指定为空时，默认给云服务器绑定default安全组。
	 */
	@JsonProperty("security_groups")
	private List<IdResourceEntity> securityGroups;

	/**
	 * 待创建云服务器所在的可用分区，需要指定可用分区（AZ）的名称。
	 */
	@JsonProperty("availability_zone")
	private String availabilityZone;

	/**
	 * 创建云服务器附加信息。
	 */
	@JsonProperty("extendparam")
	private ServerExtendParam extendParam;

	/**
	 * 创建云服务器元数据。
		创建密码方式鉴权的Windows弹性云服务器时，为必填字段。
	 */
	@JsonProperty("metadata")
	private Map<String, Object> metadata;

	/**
	 * 弹性云服务器的标签。
		标签的格式为“key.value”。其中，key的长度不超过36个字符，value的长度不超过43个字符。
		标签命名时，需满足如下要求：
		标签的key值只能包含大写字母（A~Z）、小写字母（a~z）、数字（0-9）、下划线（_）、中划线（-）以及中文字符。
		标签的value值只能包含大写字母（A~Z）、小写字母（a~z）、数字（0-9）、下划线（_）、中划线（-）、小数点（.）以及中文字符。
	 */
	@JsonProperty("tags")
	private List<String> tags;
	
	/**
	 * 云服务器名称是否允许重名。
	 */
	@JsonProperty("isAutoRename")
	private Boolean isAutoRename;
	
	/**
	 * 云服务器调度信息。
	 */
	@JsonProperty("os:scheduler_hints")
	private SchedulerHints schedulerHints;

	@java.beans.ConstructorProperties({ "name", "imageRef", "flavorRef", "personality", "userData", "adminPass",
			"keyName", "vpcId", "networks", "publicIP", "count", "rootVolume", "dataVolumes", "securityGroups",
			"availabilityZone", "extendParam", "metadata", "tags" ,"isAutoRename","schedulerHints" })
	public ServerCreate(String name, String imageRef, String flavorRef, List<Personality> personality, String userData,
			String adminPass, String keyName, String vpcId, List<Network> networks, PublicIP publicIP, Integer count,
			RootVolume rootVolume, List<DataVolume> dataVolumes, List<IdResourceEntity> securityGroups,
			String availabilityZone, ServerExtendParam extendParam, Map<String, Object> metadata, List<String> tags,
			SchedulerHints schedulerHints,Boolean isAutoRename) {
		this.name = name;
		this.imageRef = imageRef;
		this.flavorRef = flavorRef;
		this.personality = personality;
		this.userData = userData;
		this.adminPass = adminPass;
		this.keyName = keyName;
		this.vpcId = vpcId;
		this.networks = networks;
		this.publicIP = publicIP;
		this.count = count;
		this.rootVolume = rootVolume;
		this.dataVolumes = dataVolumes;
		this.securityGroups = securityGroups;
		this.availabilityZone = availabilityZone;
		this.extendParam = extendParam;
		this.metadata = metadata;
		this.tags = tags;
		this.schedulerHints =schedulerHints;
		this.isAutoRename =isAutoRename;
	}

	public ServerCreate() {
	}

	public static ServerCreateBuilder builder() {
		return new ServerCreateBuilder();
	}

	public ServerCreateBuilder toBuilder() {
		return new ServerCreateBuilder().name(this.name).imageRef(this.imageRef).flavorRef(this.flavorRef)
				.personality(this.personality).userData(this.userData).adminPass(this.adminPass).keyName(this.keyName)
				.vpcId(this.vpcId).networks(this.networks).publicIP(this.publicIP).count(this.count)
				.rootVolume(this.rootVolume).dataVolumes(this.dataVolumes).securityGroups(this.securityGroups)
				.availabilityZone(this.availabilityZone).extendParam(this.extendParam).metadata(this.metadata)
				.tags(this.tags);
	}

	public String getName() {
		return this.name;
	}

	public String getImageRef() {
		return this.imageRef;
	}

	public String getFlavorRef() {
		return this.flavorRef;
	}

	public List<Personality> getPersonality() {
		return this.personality;
	}

	public String getUserData() {
		return this.userData;
	}

	public String getAdminPass() {
		return this.adminPass;
	}

	public String getKeyName() {
		return this.keyName;
	}

	public String getVpcId() {
		return this.vpcId;
	}

	public List<Network> getNetworks() {
		return this.networks;
	}

	public PublicIP getPublicIP() {
		return this.publicIP;
	}

	public Integer getCount() {
		return this.count;
	}

	public RootVolume getRootVolume() {
		return this.rootVolume;
	}

	public List<DataVolume> getDataVolumes() {
		return this.dataVolumes;
	}

	public List<IdResourceEntity> getSecurityGroups() {
		return this.securityGroups;
	}

	public String getAvailabilityZone() {
		return this.availabilityZone;
	}

	public ServerExtendParam getExtendParam() {
		return this.extendParam;
	}

	public Map<String, Object> getMetadata() {
		return this.metadata;
	}

	public List<String> getTags() {
		return this.tags;
	}
	
	public Boolean getIsAutoRename(){
		return this.isAutoRename;
	}

	public SchedulerHints getSchedulerHints(){
		return this.schedulerHints;
	}
	
	
	@Override
	public String toString() {
		return "ServerCreate(name=" + this.getName() + ", imageRef=" + this.getImageRef() + ", flavorRef="
				+ this.getFlavorRef() + ", personality=" + this.getPersonality() + ", userData=" + this.getUserData()
				+ ", adminPass=" + this.getAdminPass() + ", keyName=" + this.getKeyName() + ", vpcId=" + this.getVpcId()
				+ ", networks=" + this.getNetworks() + ", publicIP=" + this.getPublicIP() + ", count=" + this.getCount()
				+ ", rootVolume=" + this.getRootVolume() + ", dataVolumes=" + this.getDataVolumes()
				+ ", securityGroups=" + this.getSecurityGroups() + ", availabilityZone=" + this.getAvailabilityZone()
				+ ", extendParam=" + this.getExtendParam() + ", metadata=" + this.getMetadata() + ", tags="
				+ this.getTags() +this.getIsAutoRename() +this.getSchedulerHints() + ")";
	}
	public static class ServerCreates extends ListResult<ServerCreate> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2458222536946947327L;
		/**
		 * 
		 */
		
		@JsonProperty("servers")
		private List<ServerCreate> ServerCreates;

		@Override
		protected List<ServerCreate> value() {
			return ServerCreates;
		}
	}
	public static class ServerCreateBuilder {
		private String name;
		private String imageRef;
		private String flavorRef;
		private List<Personality> personality;
		private String userData;
		private String adminPass;
		private String keyName;
		private String vpcId;
		private List<Network> networks;
		private PublicIP publicIP;
		private Integer count;
		private RootVolume rootVolume;
		private List<DataVolume> dataVolumes;
		private List<IdResourceEntity> securityGroups;
		private String availabilityZone;
		private ServerExtendParam extendParam;
		private Map<String, Object> metadata;
		private List<String> tags;
		private Boolean isAutoRename;
		private SchedulerHints schedulerHints;
		
		ServerCreateBuilder() {
		}

		public ServerCreateBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ServerCreateBuilder imageRef(String imageRef) {
			this.imageRef = imageRef;
			return this;
		}

		public ServerCreateBuilder flavorRef(String flavorRef) {
			this.flavorRef = flavorRef;
			return this;
		}

		public ServerCreateBuilder personality(List<Personality> personality) {
			this.personality = personality;
			return this;
		}

		public ServerCreateBuilder addPersonality(Personality personality) {
			if (this.personality == null) {
				this.personality = new ArrayList<>();
			}
			this.personality.add(personality);
			return this;
		}

		public ServerCreateBuilder userData(String userData) {
			this.userData = userData;
			return this;
		}

		public ServerCreateBuilder adminPass(String adminPass) {
			this.adminPass = adminPass;
			return this;
		}

		public ServerCreateBuilder keyName(String keyName) {
			this.keyName = keyName;
			return this;
		}

		public ServerCreateBuilder vpcId(String vpcId) {
			this.vpcId = vpcId;
			return this;
		}

		public ServerCreateBuilder networks(List<Network> networks) {
			this.networks = networks;
			return this;
		}

		public ServerCreateBuilder addNetwork(Network network) {
			if (this.networks == null) {
				this.networks = new ArrayList<>();
			}
			this.networks.add(network);
			return this;
		}

		public ServerCreateBuilder addNetwork(String subnetId) {
			this.addNetwork(Network.builder().subnetId(subnetId).build());
			return this;
		}

		public ServerCreateBuilder addNetwork(String subnetId, String ipAddress) {
			Network network = Network.builder().subnetId(subnetId).ipAddress(ipAddress).build();
			this.addNetwork(network);
			return this;
		}

		public ServerCreateBuilder publicIP(PublicIP publicIP) {
			this.publicIP = publicIP;
			return this;
		}

		public ServerCreateBuilder publicIP(String floatingIP) {
			this.publicIP = PublicIP.builder().floatingIP(floatingIP).build();
			return this;
		}

		public ServerCreateBuilder publicIP(FloatingIPCreate eip) {
			this.publicIP = PublicIP.builder().eip(eip).build();
			return this;
		}

		public ServerCreateBuilder count(Integer count) {
			this.count = count;
			return this;
		}

		public ServerCreateBuilder rootVolume(RootVolume rootVolume) {
			this.rootVolume = rootVolume;
			return this;
		}

		public ServerCreateBuilder dataVolumes(List<DataVolume> dataVolumes) {
			this.dataVolumes = dataVolumes;
			return this;
		}

		public ServerCreateBuilder addDataVolume(DataVolume volume) {
			if (this.dataVolumes == null) {
				this.dataVolumes = new ArrayList<>();
			}
			this.dataVolumes.add(volume);
			return this;
		}

		public ServerCreateBuilder securityGroups(List<IdResourceEntity> securityGroups) {
			this.securityGroups = securityGroups;
			return this;
		}

		public ServerCreateBuilder addSecurityGroup(String securityGroupId) {
			if (this.securityGroups == null) {
				this.securityGroups = new ArrayList<>();
			}
			this.securityGroups.add(new IdResourceEntity(securityGroupId));
			return this;
		}

		public ServerCreateBuilder availabilityZone(String availabilityZone) {
			this.availabilityZone = availabilityZone;
			return this;
		}

		public ServerCreateBuilder extendParam(ServerExtendParam extendParam) {
			this.extendParam = extendParam;
			return this;
		}

		public ServerCreateBuilder metadata(Map<String, Object> metadata) {
			this.metadata = metadata;
			return this;
		}
		
		public ServerCreateBuilder addMetadata(String key, Object value) {
			if (this.metadata == null) {
				this.metadata = new HashMap<String , Object>();
			}
			this.metadata.put(key, value);
			return this;
		}

		public ServerCreateBuilder tags(List<String> tags) {
			this.tags = tags;
			return this;
		}

		public ServerCreateBuilder addTag(String tag) {
			if (this.tags == null) {
				this.tags = new ArrayList<>();
			}
			this.tags.add(tag);
			return this;
		}

		public ServerCreateBuilder addTag(String key, String value) {
			if (this.tags == null) {
				this.tags = new ArrayList<>();
			}
			this.tags.add(key + "." + value);
			return this;
		}

		public ServerCreateBuilder isAutoRename(Boolean isAutoRename){
			this.isAutoRename = isAutoRename;
			return this;
		}
		
		public ServerCreateBuilder schedulerHints(SchedulerHints schedulerHints){
			this.schedulerHints = schedulerHints;
			return this;
		}
		public ServerCreate build() {
			return new ServerCreate(name, imageRef, flavorRef, personality, userData, adminPass, keyName, vpcId,
					networks, publicIP, count, rootVolume, dataVolumes, securityGroups, availabilityZone, extendParam,
					metadata, tags,schedulerHints,isAutoRename);
		}

		@Override
		public String toString() {
			return "ServerCreate.ServerCreateBuilder(name=" + this.name + ", imageRef=" + this.imageRef + ", flavorRef="
					+ this.flavorRef + ", personality=" + this.personality + ", userData=" + this.userData
					+ ", adminPass=" + this.adminPass + ", keyName=" + this.keyName + ", vpcId=" + this.vpcId
					+ ", networks=" + this.networks + ", publicIP=" + this.publicIP + ", count=" + this.count
					+ ", rootVolume=" + this.rootVolume + ", dataVolumes=" + this.dataVolumes + ", securityGroups="
					+ this.securityGroups + ", availabilityZone=" + this.availabilityZone + ", extendParam="
					+ this.extendParam + ", metadata=" + this.metadata + ", tags=" + this.tags + ")";
		}
	}
}
