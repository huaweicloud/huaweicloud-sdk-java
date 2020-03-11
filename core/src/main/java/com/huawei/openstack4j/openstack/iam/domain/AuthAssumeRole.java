package com.huawei.openstack4j.openstack.iam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.Map;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("assume_role")
public class AuthAssumeRole implements ModelEntity {

    private static final long serialVersionUID = -5146208600719001922L;

    /**
     * the name of the agency
     */
    @JsonProperty("agency_name")
    private String agencyName;

    /**
     * the id of the domain creating the agency
     */
    @JsonProperty("domain_id")
    private String domainId;

    /**
     * the name of the domain creating the agency
     */
    @JsonProperty("domain_name")
    private String domainName;

    /**
     * the duration time of the securitytoken
     */
    @JsonProperty("duration-seconds")
    private String durationSeconds;

    /**
     * the session user information
     */
    @JsonProperty("session_user")
    private AuthSessionUser sessionUser;
}
