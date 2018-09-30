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
package com.huawei.openstack4j.openstack.ecs.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("server")
public class VolumeExtendParam {

	/**
	 * 用来标记当前数据卷是整机备份中所自带的数据卷。
	 * snapshotId的值即为整机备份中所带的原始数据卷所对应的快照id，
	 * 	可以在"云硬盘->快照"页面里根据原始数据卷的磁盘名称找到对应的快照id。
	 * 
	 * 只有在整机备份创建虚拟机的场景下，才需要在请求体的data_volumes字段里的extendparam字段里添加snapshotId字段。
	 * 另外，无需在root_volume字段里的extendparam字段里添加snapshotId字段，非整机镜像场景下无需在请求体里携带该字段。
	 */
	@JsonProperty("snapshotId")
	String snapshotId;

	@java.beans.ConstructorProperties({ "snapshotId" })
	public VolumeExtendParam(String snapshotId) {
		this.snapshotId = snapshotId;
	}

	public VolumeExtendParam() {
	}

	public static VolumeExtendParamBuilder builder() {
		return new VolumeExtendParamBuilder();
	}

	public String getSnapshotId() {
		return this.snapshotId;
	}

	@Override
	public String toString() {
		return "VolumeExtendParam(snapshotId=" + this.getSnapshotId() + ")";
	}

	public VolumeExtendParamBuilder toBuilder() {
		return new VolumeExtendParamBuilder().snapshotId(this.snapshotId);
	}

	public static class VolumeExtendParamBuilder {
		private String snapshotId;

		VolumeExtendParamBuilder() {
		}

		public VolumeExtendParam.VolumeExtendParamBuilder snapshotId(String snapshotId) {
			this.snapshotId = snapshotId;
			return this;
		}

		public VolumeExtendParam build() {
			return new VolumeExtendParam(snapshotId);
		}

		@Override
		public String toString() {
			return "VolumeExtendParam.VolumeExtendParamBuilder(snapshotId=" + this.snapshotId + ")";
		}
	}
}
