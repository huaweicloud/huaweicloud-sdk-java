package com.huawei.openstack4j.openstack.csbs.v1.domain;

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
public class BackupCount implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7999227430134598571L;
	/**
	 * 备份数量
	 */
	private Integer count;
}
