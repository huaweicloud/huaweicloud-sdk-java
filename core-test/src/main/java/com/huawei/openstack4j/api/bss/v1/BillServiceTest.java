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
import com.huawei.openstack4j.openstack.bss.v1.domain.bill.QueryMonthlySumRsp;
import com.huawei.openstack4j.openstack.bss.v1.domain.bill.QueryResFeeRecordRsp;
import com.huawei.openstack4j.openstack.bss.v1.domain.bill.QueryResRecordRsp;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bss/v1/BillService")
public class BillServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSSV1;
    }

    private static final String account_mgr_bill_monthly_sum= "/bss/bill/queryMonthlySum.json";
    private static final String account_mgr_bill_res_records= "/bss/bill/queryResRecord.json";
    private static final String account_mgr_bill_res_fee_records= "/bss/bill/queryResFeeRecord.json";


    @Test
    public void queryMonthlySumTest() throws Exception{
        respondWith(account_mgr_bill_monthly_sum);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("cycle", "2019-09");
        QueryMonthlySumRsp rsp = osv3().bssV1().billService().queryMonthlySum(userDomainId, filteringParams);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/account-mgr/bill/monthly-sum?cycle=2019-09");
        assertEquals(request.getMethod(),"GET");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void queryResRecordTest() throws Exception{
        respondWith(account_mgr_bill_res_records);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("cycle", "2019-03");
        filteringParams.put("payMethod", "0");
        filteringParams.put("offset", "1");
        filteringParams.put("limit", "10");

        QueryResRecordRsp rsp = osv3().bssV1() .billService()
            .queryResRecord(userDomainId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/account-mgr/bill/res-records?offset=1&payMethod=0&limit=10&cycle=2019-03");
        assertEquals(request.getMethod(),"GET");
    }

    @Test
    public void queryResFeeRecordTest() throws Exception{
        respondWith(account_mgr_bill_res_fee_records);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("startTime", "2019-08-01");
        filteringParams.put("endTime", "2019-08-31");
        filteringParams.put("payMethod", "0");

        QueryResFeeRecordRsp rsp = osv3().bssV1().billService().queryResFeeRecord(userDomainId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/account-mgr/bill/res-fee-records?payMethod=0&startTime=2019-08-01&endTime=2019-08-31");
        assertEquals(request.getMethod(),"GET");
    }
}
