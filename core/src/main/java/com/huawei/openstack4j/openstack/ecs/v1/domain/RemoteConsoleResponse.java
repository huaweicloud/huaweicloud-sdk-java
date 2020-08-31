package com.huawei.openstack4j.openstack.ecs.v1.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("remote_console")
public class RemoteConsoleResponse implements ModelEntity {

    /**
     * 远程登录的类型
     */
    private String type;

    /**
     * 远程登录协议
     */
    private String protocol;
    /**
     * 远程登录的url
     */
    private String url;
}
