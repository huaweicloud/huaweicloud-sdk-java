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
package com.huawei.openstack4j.api.bssintl.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerManagement.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bssintl/v1/CustomerManagementService")
public class CustomerManagementServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSS_INTLV1;
    }

    private static final String customer_user_check= "/bssintl/CustomerManagement/checkUser.json";
    private static final String customer_create_customer= "/bssintl/CustomerManagement/createCustomer.json";
    private static final String customer_mgr_customer= "/bssintl/CustomerManagement/queryCustomerInfoList.json";
    private static final String customer_mgr_frozens= "/bssintl/CustomerManagement/setCustomersFrozen.json";
    private static final String customer_mgr_unfrozens= "/bssintl/CustomerManagement/setCustomersUnfrozen.json";


    @Test
    public void checkUserTest() throws Exception{
        respondWith(customer_user_check);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        CheckUserReq req = CheckUserReq.builder().searchType("email").searchKey("xyz@163.com").build();
        CheckUserRsp rsp = osv3().bssintlV1().customerManagementService().checkUser(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/check-user");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getStatus(),"0");
        assertEquals(rsp.getErrorCode(), "CBC.0000");

        String requestBody = request.getBody().readUtf8();
        String expectBody = this.getResource("/bssintl/CustomerManagement/checkUserReq.json");
        Assert.assertEquals(requestBody, expectBody);
    }

    @Test
    public void createCustomerTest() throws Exception{
        respondWith(customer_create_customer);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        CreateCustomerReq req = CreateCustomerReq.builder().domainArea("MX").xAccountId("111").xAccountType("123132").build();
        CreateCustomerRsp rsp = osv3().bssintlV1().customerManagementService().createCustomer(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/customer");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getDomainId(),"572692056toetge");
        assertEquals(rsp.getDomainName(),"xxxxxxx");
    }

    @Test
    public void queryCustomerInfoListTest() throws Exception{
        respondWith(customer_mgr_customer);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        QueryCustomerInfoListReq req = QueryCustomerInfoListReq.builder()
            .cooperationTimeStart("2019-05-01T00:01:00Z")
            .cooperationTimeEnd("2019-12-01T00:01:00Z")
            .build();
        QueryCustomerInfoListRsp
            rsp = osv3().bssintlV1().customerManagementService().queryCustomerInfoList(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/query");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getCount().toString(),"1");
    }

    @Test
    public void setCustomersFrozenTest() throws Exception{
        respondWith(customer_mgr_frozens);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> customerIds = new ArrayList<>();
        customerIds.add("43570038080259f0fc32032834");
        customerIds.add("214csgyrtwft75683235654udf");
        SetCustomersFrozenReq req = SetCustomersFrozenReq.builder()
            .customerIds(customerIds)
            .reason("testqianfei")
            .build();
        SetCustomersFrozenRsp rsp = osv3().bssintlV1().customerManagementService().setCustomersFrozen(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/frozens");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getSuccessNum().toString(),"2");
    }

    @Test
    public void setCustomersUnfrozenTest() throws Exception{
        respondWith(customer_mgr_unfrozens);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> customerIds = new ArrayList<>();
        customerIds.add("43570038080259f0fc32032834");
        customerIds.add("214csgyrtwft75683235654udf");
        SetCustomersFrozenReq req = SetCustomersFrozenReq.builder()
            .customerIds(customerIds)
            .reason("testqianfei")
            .build();
        SetCustomersFrozenRsp
            rsp = osv3().bssintlV1().customerManagementService().setCustomersUnfrozen(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/unfrozens");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getSuccessNum().toString(),"2");
    }
}
