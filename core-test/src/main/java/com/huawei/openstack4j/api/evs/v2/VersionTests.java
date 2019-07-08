/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack4j.api.evs.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.storage.block.domain.MediaType;
import com.huawei.openstack4j.openstack.storage.block.domain.Version;
import com.huawei.openstack4j.openstack.storage.block.domain.VersionLink;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName="Version Tests")
public class VersionTests extends AbstractTest{

    @Override
    protected Service service() {
        return Service.EVS;
    }

    @Test
    public void getVersionsTest() throws Exception
    {
        // check get versions
        respondWith("/evs/v2/versions.json");
        List<? extends Version> versions= osv3().evs().versions().list();
        assertEquals(versions.size(), 3);
        Version version = versions.get(0);
        assertEquals(version.getMinVersion(), "");
        // check version media-types
        MediaType mediaType = version.getMediaTypes().get(0);
        assertEquals(mediaType.getType(), "application/vnd.openstack.volume+json;version=1");
        assertEquals(mediaType.getBase(), "application/json");
        // check version links
        VersionLink versionLink = version.getLinks().get(0);
        assertEquals(versionLink.getRel(), "describedby");
        assertEquals(versionLink.getHref(), "http://docs.openstack.org/");
        assertEquals(versionLink.getType(), "text/html");
        assertEquals(version.getId(), "v1.0");
        assertEquals(version.getUpdated(), "2014-06-28T12:20:21Z");
        assertEquals(version.getVersion(), "");
        assertEquals(version.getStatus(), "SUPPORTED");

        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/"));
    }

    @Test
    public void getVersionV2Test() throws Exception
    {
        // check get versions
        respondWith("/evs/v2/versionV2.json");
        List<? extends Version> versions= osv3().evs().versions().get("v2");
        assertEquals(versions.size(), 1);
        Version version = versions.get(0);
        assertEquals(version.getMinVersion(), "");
        // check version media-types
        MediaType mediaType = version.getMediaTypes().get(0);
        assertEquals(mediaType.getType(), "application/vnd.openstack.volume+json;version=1");
        assertEquals(mediaType.getBase(), "application/json");
        // check version links
        VersionLink versionLink = version.getLinks().get(0);
        assertEquals(versionLink.getRel(), "describedby");
        assertEquals(versionLink.getHref(), "http://docs.openstack.org/");
        assertEquals(versionLink.getType(), "text/html");
        assertEquals(version.getId(), "v2.0");
        assertEquals(version.getUpdated(), "2014-06-28T12:20:21Z");
        assertEquals(version.getVersion(), "");
        assertEquals(version.getStatus(), "SUPPORTED");

        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v2"));
    }

}