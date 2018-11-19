package com.huawei.openstack4j.openstack.csbs.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8378843143819641161L;
	/**
	 * 待备份对象id
	 */
	private String id;
	/**
	 * 备份对象的实体对象类型
	 */
	private String type;
	/**
	 * 备份对象名称
	 */
	private String name;
	/**
	 * 备份对象的附加信息
	 */
	@JsonProperty("extra_info")
	private ExtraInfo extraInfo;
	
	
}
