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
//
//import java.util.List;
//
//import org.testng.annotations.Test;
//
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.model.common.ActionResponse;
//import com.huawei.openstack4j.openstack.vpc.v2.domain.Peering;
//import com.huawei.openstack4j.openstack.vpc.v2.domain.PeeringCreate;
//import com.huawei.openstack4j.openstack.vpc.v2.domain.VpcInfo;
//
//@Test(suiteName = "VPC/Peering")
//public class PeeringTest extends AbstractTest {
//
//    @Override
//    protected Service service() {
//        return Service.VPC2;
//    }
//
//    @Test
//    public void listTest() throws Exception{
//        respondWith("/vpc/v2/peerings.json");
//        List<Peering> peerings = osv3().vpcV2().peerings().list();
//        assertEquals(peerings.size(), 1);
//        assertEquals(peerings.get(0).getId(), "b147a74b-39bb-4c7a-aed5-19cac4c2df13");
//        assertEquals(peerings.get(0).getName(), "test");
//        assertEquals(peerings.get(0).getStatus().toString(), "ACTIVE");
//        assertEquals(peerings.get(0).getId(), "b147a74b-39bb-4c7a-aed5-19cac4c2df13");
//    }
//
//    @Test
//    public void getTest() throws Exception{
//        respondWith("/vpc/v2/peering.json");
//        Peering peering = osv3().vpcV2().peerings().get("peering-id");
//        assertEquals(peering.getId(), "22b76469-08e3-4937-8c1d-7aad34892be1");
//        assertEquals(peering.getName(), "test");
//        assertEquals(peering.getAcceptVpcInfo().getTenantId(), "f65e9ebc-ed5d-418b-a931-9a723718ba4e");
//        assertEquals(peering.getAcceptVpcInfo().getVpcId(), "f583c072-0bb8-4e19-afb2-afb7c1693be5");
//        assertEquals(peering.getRequestVpcInfo().getTenantId(), "f65e9ebc-ed5d-418b-a931-9a723718ba4e");
//        assertEquals(peering.getRequestVpcInfo().getVpcId(), "9daeac7c-a98f-430f-8e38-67f9c044e299");
//    }
//
//    @Test
//    public void createTest() throws Exception{
//        respondWith("/vpc/v2/peering.json");
//        PeeringCreate peeringCreate = PeeringCreate.builder().name("test").acceptVpcInfo(VpcInfo.builder().vpcId("vpc-id").build()).requestVpcInfo(VpcInfo.builder().vpcId("vpc-id").build()).build();
//        Peering peering = osv3().vpcV2().peerings().create(peeringCreate);
//        assertEquals(peering.getId(), "22b76469-08e3-4937-8c1d-7aad34892be1");
//        assertEquals(peering.getName(), "test");
//        assertEquals(peering.getAcceptVpcInfo().getTenantId(), "f65e9ebc-ed5d-418b-a931-9a723718ba4e");
//        assertEquals(peering.getAcceptVpcInfo().getVpcId(), "f583c072-0bb8-4e19-afb2-afb7c1693be5");
//        assertEquals(peering.getRequestVpcInfo().getTenantId(), "f65e9ebc-ed5d-418b-a931-9a723718ba4e");
//        assertEquals(peering.getRequestVpcInfo().getVpcId(), "9daeac7c-a98f-430f-8e38-67f9c044e299");
//    }
//
//    @Test
//    public void deleteTest() throws Exception{
//        respondWith(200);
//        ActionResponse delete = osv3().vpcV2().peerings().delete("peering-id");
//        assertEquals(delete.getCode(), 200);
//    }
//}
