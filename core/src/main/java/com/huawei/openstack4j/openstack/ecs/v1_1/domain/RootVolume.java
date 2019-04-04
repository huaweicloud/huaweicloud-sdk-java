/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;

/**
 * Model represent attributes of Server Root Volume
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
public class RootVolume implements ModelEntity {

	private static final long serialVersionUID = 5294355671374520846L;

	/**
	 * 云服务器系统盘对应的磁盘类型，需要与系统所提供的磁盘类型相匹配。

	 SATA：普通IO磁盘类型。
	 SAS：高IO磁盘类型。
	 SSD：超高IO磁盘类型。
	 co-pl：高IO (性能优化Ⅰ型)
	 uh-l1：超高IO (时延优化)
	 */
	@JsonProperty("volumetype")
	VolumeType type;

	/**
	 * 系统盘大小，容量单位为GB， 输入大小范围为[1,1024]。

	 约束：

	 系统盘大小取值应不小于镜像支持的系统盘的最小值(镜像的min_disk属性)。
	 若该参数没有指定或者指定为0，系统盘大小默认取值为镜像中系统盘的最小值(镜像的min_disk属性)。
	 */
	@JsonProperty("size")
	Integer size;

	/**
	 * 磁盘的产品信息。
	 */
	@JsonProperty("extendparam")
	VolumeExtendParam extendParam;

	@java.beans.ConstructorProperties({ "type", "size", "extendParam" })
	public RootVolume(VolumeType type, Integer size, VolumeExtendParam extendParam) {
		this.type = type;
		this.size = size;
		this.extendParam = extendParam;
	}

	public RootVolume() {
	}

	public static RootVolumeBuilder builder() {
		return new RootVolumeBuilder();
	}

	public VolumeType getType() {
		return this.type;
	}

	public Integer getSize() {
		return this.size;
	}

	public VolumeExtendParam getExtendParam() {
		return this.extendParam;
	}

	@Override
	public String toString() {
		return "RootVolume(type=" + this.getType() + ", size=" + this.getSize() + ", extendParam="
				+ this.getExtendParam() + ")";
	}

	public RootVolumeBuilder toBuilder() {
		return new RootVolumeBuilder().type(this.type).size(this.size).extendParam(this.extendParam);
	}

	public static class RootVolumeBuilder {
		private VolumeType type;
		private Integer size;
		private VolumeExtendParam extendParam;

		RootVolumeBuilder() {
		}

		public RootVolumeBuilder type(VolumeType type) {
			this.type = type;
			return this;
		}

		public RootVolumeBuilder size(Integer size) {
			this.size = size;
			return this;
		}

		public RootVolumeBuilder extendParam(VolumeExtendParam extendParam) {
			this.extendParam = extendParam;
			return this;
		}

		public RootVolume build() {
			return new RootVolume(type, size, extendParam);
		}

		@Override
		public String toString() {
			return "RootVolume.RootVolumeBuilder(type=" + this.type + ", size=" + this.size + ", extendParam="
					+ this.extendParam + ")";
		}
	}
}
