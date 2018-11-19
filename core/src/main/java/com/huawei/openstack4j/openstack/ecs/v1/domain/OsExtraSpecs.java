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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("os_extra_specs")
public class OsExtraSpecs implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -323763716774569002L;
	/**
	 * 云服务器规格的分类：
	 *	
	 *	normal：通用型
	 *	cpuv1：计算I型
	 *	cpuv2：计算II型
	 *	highmem：内存优化型
	 *	gpu：GPU加速型
	 */
	@JsonProperty("ecs:performancetype")
	private String ecsPerformancetype;
	/**
	 * 资源类型。
	 */
	@JsonProperty("resource_type")
	private String resourceType;
	/**
	 * 	弹性云服务器类型的代数。
	 *
	 *	s1：通用型I代
	 *	s2：通用型II代
	 *	m1：内存优化型I代
	 *	m2：内存优化型II代
	 *	h1：高计算型I代
	 *	h2：高计算型II代
	 *	d1：密集存储型I代
	 *	d2：密集存储型II代
	 *	g1：GPU加速型I代
	 *	g2：GPU加速型II代
	 */
	@JsonProperty("ecs:generation")
	private String ecsGeneration;
	/**
	 * 虚拟化类型。
	 *
	 *	如果值为“FusionCompute”，表示弹性云服务器使用基于XEN的虚拟化技术。
	 *	如果值为“CloudCompute”，表示弹性云服务器使用基于KVM的虚拟化技术。
	 */
	@JsonProperty("ecs:virtualization_env_types")
	private String ecsVirtualizationEnvTypes;
	/**
	 * 显卡是否直通。值为“true”，表示GPU直通。
	 */
	@JsonProperty("pci_passthrough:enable_gpu")
	private Boolean pciPassthroughEnableGpu;
	/**
	 * G1型和G2型云服务器应用的技术，包括GPU虚拟化和GPU直通。
	 * 如果该规格的云服务器使用GPU虚拟化技术，且GPU卡的型号为M60-1Q，参数值可设置为“m60_1q:virt:1”
	 * 如果该规格的云服务器使用GPU直通技术，且GPU卡的型号为M60，参数值可设置为“m60:direct_graphics:1”
	 */
	@JsonProperty("pci_passthrough:gpu_specs")
	private String 	pciPassthroughGpuSpecs;
	
	/**
	 * P1型云服务器本地直通GPU的型号和数量，参数值可设置为“nvidia-p100:1”，
	 * 表示使用该规格创建的弹性云服务器将占用1张NVIDIA P100显卡。
	 */
	@JsonProperty("pci_passthrough:alias")
	private String  pciPassthroughAlias;
}
