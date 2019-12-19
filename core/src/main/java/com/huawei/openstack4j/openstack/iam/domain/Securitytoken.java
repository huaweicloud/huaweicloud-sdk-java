package com.huawei.openstack4j.openstack.iam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.internal.Parser;
import lombok.*;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    private String expiresAt;

    /**
     * the securitytoken of the securitytoken
     */
    private String securitytoken;

    public Date getExpiresAt() {
        String dateSource = this.expiresAt;
        SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        formator.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = formator.parse(dateSource.substring(0, dateSource.length() - 4));
        } catch (ParseException e) {
            LoggerFactory.getLogger(Parser.class).error(e.getMessage(), e);
        }
        return date;
    }

}
