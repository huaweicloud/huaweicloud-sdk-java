/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
 * *******************************************************************************/
package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName ("volume")
public class CinderVolumeUpdate implements ModelEntity{

	private static final long serialVersionUID = -7217567826625502919L;

	/**
	 * 云硬盘名称。最大支持255个字节。
	 */
	private String name;

	/**
	 * 云硬盘描述。最大支持255个字节。
	 */
	private String description;

	/**
	 * 云硬盘的元数据。
	 *
	 * 元数据中的key和value长度不大于255字节。
	 */
	private Map<String,String> metadata;

	/**
	 * 同name，name和display_name任意指定一个即可（若两个都指定，则以name为主）。最大支持255个字节。
	 */
	@JsonProperty ("display_name")
	private String displayName;

	/**
	 * 同description，description和display_description任意指定一个即可（若两个都指定，则以description为主）。最大支持255个字节。
	 */
	@JsonProperty("display_description")
	private String displayDescription;

}
