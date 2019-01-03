package com.huawei.openstack4j.openstack.csbs.v1.domain;

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
@JsonRootName("protect_config")
public class ProtectConfig implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1862842863592363531L;
	/**
	 *配置信息id 
	 */
	private String id;
	/**
	 *项目id 
	 */
	@JsonProperty("project_id")
	private String projectId;
	/**
	 *项目是否开通专属备份 
	 */
	@JsonProperty("dec_enabled")
	private Boolean decEnabled;
	/**	
	 *项目专属备份容量，以GB为单位 
	 */
	@JsonProperty("dec_capacity")
	private Integer decCapacity;
	/**
	 *项目已使用专属备份容量，以GB为单位 
	 */
	@JsonProperty("dec_used_capacity")
	private Integer decUsedCapacity;
}
