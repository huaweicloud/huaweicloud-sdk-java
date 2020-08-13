package com.huawei.openstack4j.model.network.ext;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Operating status of a load balancer v2 pool member
 */
public enum MemberOperatingStatus {
    ONLINE, OFFLINE, NO_MONITOR;

    @JsonCreator
    public static MemberOperatingStatus forValue(String value) {
        if (value != null) {
            for (MemberOperatingStatus s : MemberOperatingStatus.values()) {
                if (s.name().equalsIgnoreCase(value)) {
                    return s;
                }
            }
        }
        return MemberOperatingStatus.OFFLINE;
    }
}
