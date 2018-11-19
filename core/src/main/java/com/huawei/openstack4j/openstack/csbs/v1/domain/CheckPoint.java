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
@JsonRootName("checkpoint")
public class CheckPoint implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 597960740058490343L;
	/**
	 *备份策略id
	 */
	@JsonProperty("plan_id")
	private String planId;
	/**
	 * 备份参数
	 */
	private CheckPointParam parameters;
}
