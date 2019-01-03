package com.huawei.openstack4j.openstack.vpc.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("subnet")
public class SubnetUpdateResp implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -624052187270905323L;

	private String id;
	
	/**
	 * vpc status
	 */
	private String status;
}
