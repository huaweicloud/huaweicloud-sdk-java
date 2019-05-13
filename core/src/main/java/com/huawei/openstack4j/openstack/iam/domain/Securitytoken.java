package com.huawei.openstack4j.openstack.iam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;
import java.util.Date;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("credential")
public class Securitytoken implements ModelEntity {

    private static final long serialVersionUID = -5483534917159699316L;

    /**
     * the access of the securitytoken
     */
    private String access;

    /**
     * the secret of the securitytoken
     */
    private String secret;

    /**
     * the expire time of the securitytoken
     */
    @JsonProperty("expires_at")
    private Date expiresAt;

    /**
     * the securitytoken of the securitytoken
     */
    private String securitytoken;

}
