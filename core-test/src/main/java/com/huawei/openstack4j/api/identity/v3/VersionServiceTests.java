package com.huawei.openstack4j.api.identity.v3;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.identity.v3.domain.Version;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class VersionServiceTests extends AbstractTest {

    private static final String VERSION_GET = "/identity/v3/authv3_version.json";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    @Test
    public void testGetVersion() throws Exception {
        respondWith(VERSION_GET);
        Version version = osv3().identity().versions().get();
        assertEquals(version.getStatus(), "stable");
        assertEquals(version.getId(), "v3.6");
        assertEquals(version.getMediaTypes().get(0).get("base"), "application/json");
        assertEquals(version.getMediaTypes().get(0).get("type"), "application/vnd.openstack.identity-v3+json");
    }

}
