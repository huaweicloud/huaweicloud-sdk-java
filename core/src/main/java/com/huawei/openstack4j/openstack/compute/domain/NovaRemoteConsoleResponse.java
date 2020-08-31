package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("remote_console")
public class NovaRemoteConsoleResponse implements ModelEntity {

    /**
     * 远程登录的类型
     */
    private String type;

    /**
     * 远程登录协议
     */
    private String protocol;

    /**
     * 远程登录的url。
     *
     * 该url有效时间10min，超过10min请重新获取。
     */
    private String url;
}
