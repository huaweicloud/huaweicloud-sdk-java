package com.huawei.openstack4j.openstack.csbs.v1.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResp implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5298869633038625759L;
	/**
	 * 备份策略名称id
	 */
	private String id;
	/**
	 * 备份策略名称
	 */
	private String name;
	/**
	 * 备份对象列表
	 */
	private List<Resource> resources;
	
	
}
