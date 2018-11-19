package com.huawei.openstack4j.openstack.csbs.v1.domain;

import java.util.List;

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
public class ExtraInfo implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2011559142785366770L;
	/**
	 * 磁盘的id列表
	 */
	@JsonProperty("exclude_volumes")
	private List<String> excludeVolumes;
}	
