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
public class CheckPointParam implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9077067778840813521L;
	/**
	 * 是否自动触发
	 */
	@JsonProperty("auto_trigger")
	private Boolean autoTrigger;
	/**
	 * 待备份的资源id列表
	 */
	private List<String> resources;
}
