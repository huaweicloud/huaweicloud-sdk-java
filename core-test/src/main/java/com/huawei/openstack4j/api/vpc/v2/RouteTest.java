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
//import com.huawei.openstack4j.openstack.vpc.v2.contants.RouteType;
//import com.huawei.openstack4j.openstack.vpc.v2.domain.Route;
//
//@Test(suiteName = "VPC/Route")
//public class RouteTest extends AbstractTest {
//    @Override
//    protected Service service() {
//        return Service.VPC2;
//    }
//
//    @Test
//    public void listTest() throws Exception{
//        respondWith("/vpc/v2/routes.json");
//        List<Route> routes = osv3().vpcV2().routes().list();
//        assertEquals(routes.size(), 1);
//        assertEquals(routes.get(0).getId(), "3d42a0d4-a980-4613-ae76-a2cddecff054");
//        assertEquals(routes.get(0).getDestination(), "192.168.200.0/24");
//        assertEquals(routes.get(0).getVpcId(), "ab78be2d-782f-42a5-aa72-35879f6890ff");
//        assertEquals(routes.get(0).getTenantId(), "6fbe9263116a4b68818cf1edce16bc4f");
//        assertEquals(routes.get(0).getNexthop(), "60c809cb-6731-45d0-ace8-3bf5626421a9");
//        assertEquals(routes.get(0).getType().toString(), "peering");
//    }
//
//    @Test
//    public void getTest() throws Exception{
//        respondWith("/vpc/v2/route.json");
//        Route route = osv3().vpcV2().routes().get("route-id");
//        assertEquals(route.getId(), "3d42a0d4-a980-4613-ae76-a2cddecff054");
//        assertEquals(route.getDestination(), "192.168.200.0/24");
//        assertEquals(route.getVpcId(), "ab78be2d-782f-42a5-aa72-35879f6890ff");
//        assertEquals(route.getTenantId(), "6fbe9263116a4b68818cf1edce16bc4f");
//        assertEquals(route.getNexthop(), "60c809cb-6731-45d0-ace8-3bf5626421a9");
//        assertEquals(route.getType().toString(), "peering");
//    }
//
//    @Test
//    public void createTest() throws Exception{
//        respondWith("/vpc/v2/route.json");
//        Route routeCreate = Route.builder().type(RouteType.peering).nexthop("nexthop")
//                .vpcId("vpc-id").destination("192.168.200.0/24").build();
//        Route route = osv3().vpcV2().routes().create(routeCreate);
//        assertEquals(route.getId(), "3d42a0d4-a980-4613-ae76-a2cddecff054");
//        assertEquals(route.getDestination(), "192.168.200.0/24");
//        assertEquals(route.getVpcId(), "ab78be2d-782f-42a5-aa72-35879f6890ff");
//        assertEquals(route.getTenantId(), "6fbe9263116a4b68818cf1edce16bc4f");
//        assertEquals(route.getNexthop(), "60c809cb-6731-45d0-ace8-3bf5626421a9");
//        assertEquals(route.getType().toString(), "peering");
//    }
//
//    @Test
//    public void deleteTest() throws Exception{
//        respondWith(200);
//        ActionResponse delete = osv3().vpcV2().routes().delete("route-id");
//        assertEquals(delete.getCode(), 200);
//    }}
