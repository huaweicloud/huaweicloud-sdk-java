package com.huawei.openstack4j.openstack.identity.v3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("config")
public class PasswordConfig implements ModelEntity {

    private static final long serialVersionUID = -4887856515000675547L;

    @JsonProperty("security_compliance")
    private Map<String, String> securityCompliance;

    @JsonProperty("password_regex")
    private String passwordRegex;

    @JsonProperty("password_regex_description")
    private String passwordRegexDescription;

    public String getPasswordRegex() {
        if (passwordRegex == null){
            return securityCompliance.get("password_regex");
        }
        return passwordRegex;
    }

    public String getPasswordRegexDescription() {
        if (passwordRegexDescription == null){
            return securityCompliance.get("password_regex_description");
        }
        return passwordRegexDescription;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("securityCompliance", securityCompliance)
                .add("passwordRegex", passwordRegex)
                .add("passwordRegexDescription", passwordRegexDescription)
                .toString();
    }

}

