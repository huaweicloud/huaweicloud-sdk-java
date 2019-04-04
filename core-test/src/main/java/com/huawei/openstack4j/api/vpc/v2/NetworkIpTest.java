///*******************************************************************************
// * 	Copyright 2019 Huawei Technologies Co.,Ltd.
// *
// * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * 	use this file except in compliance with the License. You may obtain a copy of
// * 	the License at
// *
// * 	    http://www.apache.org/licenses/LICENSE-2.0
// *
// * 	Unless required by applicable law or agreed to in writing, software
// * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * 	License for the specific language governing permissions and limitations under
// * 	the License.
// *******************************************************************************/
//package com.huawei.openstack4j.api.vpc.v2;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertNotNull;
//
//import org.testng.annotations.Test;
//
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.openstack.vpc.v2.domain.NetworkIpAvailability;
//import com.huawei.openstack4j.openstack.vpc.v2.domain.SubnetIpAvailability;
//
//@Test(suiteName = "VPC/NetworkIp")
//public class NetworkIpTest extends AbstractTest {
//
//    @Override
//    protected Service service() {
//        return Service.VPC2;
//    }
//
//    @Test
//    public void getTest() throws Exception{
//        respondWith("/vpc/v2/network_ip.json");
//        NetworkIpAvailability networkIpAvailability = osv3().vpcV2().networkips().get("network-ip");
//        assertEquals(networkIpAvailability.getNetworkId(), "6b50d967-779c-40c9-a157-de1df3c17043");
//        assertEquals(networkIpAvailability.getNetworkName(), "pch_test_003");
//        assertEquals(networkIpAvailability.getTenantId(), "7c4b23cb125d481c95cbe4f91b2c11cd");
//        assertEquals(networkIpAvailability.getTotalIps().toString(), "300");
//        assertEquals(networkIpAvailability.getUsedIps().toString(), "4");
//        for(SubnetIpAvailability subnetIpAvailabilityInfo : networkIpAvailability.getSubnetIpAvailability()){
//            assertNotNull(subnetIpAvailabilityInfo.getCidr());
//            assertNotNull(subnetIpAvailabilityInfo.getIpVersion());
//            assertNotNull(subnetIpAvailabilityInfo.getTotalIps());
//            assertNotNull(subnetIpAvailabilityInfo.getUsedIps());
//        }
//
//
//
//    }
//}
