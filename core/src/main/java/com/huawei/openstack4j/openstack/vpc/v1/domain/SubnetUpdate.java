package com.huawei.openstack4j.openstack.vpc.v1.domain;

import java.util.List;

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
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("subnet")
public class SubnetUpdate implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1608793567329404130L;

	/**
	 * vpc name
	 */
	private String name;
	
	/**
	 * Whether the dhcp function is enabled on the subnet
	 */
	@JsonProperty("dhcp_enable")
	private boolean dhcpEnable;
	
	/**
	 * Subnet dns server address 1
	 */
	@JsonProperty("primary_dns")
	private String primaryDns;
	
	/**
	 * Subnet dns server address 2
	 */
	@JsonProperty("secondary_dns")
	private String secondaryDns;
	
	/**
	 * Subnet dns server address list set 
	 */
	private List<String> dnsList;
	
	
}
