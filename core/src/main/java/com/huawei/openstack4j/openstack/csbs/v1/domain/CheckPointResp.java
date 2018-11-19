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
public class CheckPointResp implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6971159142542305581L;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 创建时间
	 */
	@JsonProperty("created_at")
	private String createdAt;
	/**
	 * 备份记录id
	 */
	private String id;
	/**
	 * 资源图
	 */
	@JsonProperty("resource_graph")
	private String resourceGraph;
	/**
	 *项目id
	 */
	@JsonProperty("project_id")
	private String projectId;
	/**
	 * 备份策略信息
	 */
	@JsonProperty("protection_plan")
	private PlanResp protectionPlan;
	

	
	
}
