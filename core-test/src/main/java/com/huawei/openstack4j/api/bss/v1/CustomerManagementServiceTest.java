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
package com.huawei.openstack4j.api.bss.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bss.v1.domain.customerManagement.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bss/v1/CustomerManagementService")
public class CustomerManagementServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSSV1;
    }

    private static final String customer_user_check= "/bss/customerManagement/checkUser.json";
    private static final String customer_create_customer= "/bss/customerManagement/createCustomer.json";
    private static final String customer_mgr_customer= "/bss/customerManagement/queryCustomerInfoList.json";


    @Test
    public void checkUserTest() throws Exception{
        respondWith(customer_user_check);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        CheckUserReq req = CheckUserReq.builder().searchType("mobile").searchKey("0086-13488712889").build();
        CheckUserRsp rsp = osv3().bssV1().customerManagementService().checkUser(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/check-user");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getStatus(),"0");
        assertEquals(rsp.getErrorCode(), "CBC.0000");

        String requestBody = request.getBody().readUtf8();
        String expectBody = this.getResource("/bss/customerManagement/checkUserReq.json");
        Assert.assertEquals(requestBody, expectBody);
    }

    @Test
    public void createCustomerTest() throws Exception{
        respondWith(customer_create_customer);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        CreateCustomerReq req = CreateCustomerReq.builder().xAccountId("111").xAccountType("123132").build();
        CreateCustomerRsp rsp = osv3().bssV1().customerManagementService().createCustomer(userDomainId, req);
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
            rsp = osv3().bssV1().customerManagementService().queryCustomerInfoList(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/query");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getCount().toString(),"1");
    }
}
