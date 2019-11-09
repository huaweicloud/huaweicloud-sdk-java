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
package com.huawei.openstack.sample.bssintl.v1;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.exceptions.ClientResponseException;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.realnameAuth.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealnameAuth
{
    private static OSClient.OSClientV3 getOsClientV3(String username, String password, String userDomainId) {
        String authUrl = "https://iam.xxx.com/v3";
        OSFactory.enableHttpLoggingFilter(true);
        // create connection
        return OSFactory.builderV3()
            .endpoint(authUrl)
            .credentials(username, password, Identifier.byId(userDomainId))
            .scopeToDomain(Identifier.byId(userDomainId))
            .authenticate();
    }

    private static RealnameAuthRsp individualRealnameAuth(OSClient.OSClientV3 client, String domainID,  RealnameAuthReq req)
    {
        try {
            RealnameAuthRsp rsp = client.bssintlV1().realnameAuthService().individualRealnameAuth(domainID, req);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    private static RealnameAuthRsp enterpriseRealnameAuth(OSClient.OSClientV3 client, String domainID,  EnterpriseRealnameAuthReq req)
    {
        try {
            RealnameAuthRsp rsp = client.bssintlV1().realnameAuthService().enterpriseRealnameAuth(domainID, req);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    private static RealnameAuthRsp enterpriseRealnameAuthChange(OSClient.OSClientV3 client, String domainID,  EnterpriseRealnameAuthChangeReq req)
    {
        try {
            RealnameAuthRsp rsp = client.bssintlV1().realnameAuthService().enterpriseRealnameAuthChange(domainID, req);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    private static QueryRealnameAuthReviewResultRsp queryRealnameAuthReviewResult(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryRealnameAuthReviewResultRsp rsp = client.bssintlV1().realnameAuthService().queryRealnameAuthReviewResult(domainID, filteringParams);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }


    public static void main(String[] args)
    {
//create connection
        String domainId = "xxx";
        OSClient.OSClientV3 osclient = getOsClientV3("xxx","xxxxx", domainId);

//Submitting an Individual Real-Name Authentication Application
        List<String> verifiedFileURLIndividual = new ArrayList<>();
        verifiedFileURLIndividual.add("zhengmian.jpg");
        verifiedFileURLIndividual.add("fanmian.jpg");
        verifiedFileURLIndividual.add("chizheng2.jpg");
        RealnameAuthReq realnameAuthReq = RealnameAuthReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxx")
            .identifyType(0)
            .verifiedType(0)
            .verifiedFileURL(verifiedFileURLIndividual)
            .name("xxx")
            .verifiedNumber("xxxxxxxxxxxxxxxxxx")
            .changeType(-1)
            .xaccountType("xxxxxxxxx_IDP")
            .build();
        RealnameAuthRsp individualRealnameAuthRsp = individualRealnameAuth(osclient, domainId, realnameAuthReq);

//Submitting an Enterprise Real-Name Authentication Application
        List<String> verifiedFileURLEnterprise = new ArrayList<>();
        verifiedFileURLEnterprise.add("xxxx.jpg");
        verifiedFileURLEnterprise.add("xxxxxx.jpg");
        verifiedFileURLEnterprise.add("xxxxxxx.jpg");
        EnterprisePerson enterprisePerson = new EnterprisePerson();
        enterprisePerson.setLegelName("xxxxxxx");
        enterprisePerson.setLegelIdNumber("xxxxxxxxxxxxxxxxxx");
        enterprisePerson.setCertifierRole("legalPerson");
        EnterpriseRealnameAuthReq enterpriseRealnameAuthReq = EnterpriseRealnameAuthReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxxx")
            .identifyType(1)
            .certificateType(0)
            .verifiedFileURL(verifiedFileURLEnterprise)
            .corpName("xxxxxxx")
            .verifiedNumber("xxxxxx")
            .regCountry("CN")
            .regAddress("nanjing")
            .xaccountType("xxxxxxxxxx_IDP")
            .enterprisePerson(enterprisePerson)
            .build();
        RealnameAuthRsp enterpriseRealnameAuthRsp = enterpriseRealnameAuth(osclient, domainId, enterpriseRealnameAuthReq);

//Submitting a Real-Name Authentication Change Application
        List<String> verifiedFileURLChange = new ArrayList<>();
        verifiedFileURLChange.add("zhengmian.jpg");
        verifiedFileURLChange.add("fanmian.jpg");
        verifiedFileURLChange.add("chizheng2.jpg");
        EnterprisePerson enterprisePersonChange = new EnterprisePerson();
        enterprisePerson.setLegelName("xxxxx");
        enterprisePerson.setLegelIdNumber("xxxxxxxxxxxxxxxxxx");
        enterprisePerson.setCertifierRole("legalPerson");
        EnterpriseRealnameAuthChangeReq enterpriseRealnameAuthChangeReq = EnterpriseRealnameAuthChangeReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxxx")
            .identifyType(1)
            .certificateType(0)
            .verifiedFileURL(verifiedFileURLChange)
            .corpName("xxxxxxxxxxxx")
            .verifiedNumber("xxxxxx")
            .regCountry("CN")
            .regAddress("nanjing")
            .changeType(1)
            .xaccountType("xxxxxxxxxxxx_IDP")
            .enterprisePerson(enterprisePerson)
            .build();
        RealnameAuthRsp enterpriseRealnameAuthChangeRsp = enterpriseRealnameAuthChange(osclient, domainId, enterpriseRealnameAuthChangeReq);

//Querying the Real-Name Authentication Review Result
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("customerId", "xxxxxxxxxxxxxxxxxxxxx");
        QueryRealnameAuthReviewResultRsp queryRealnameAuthReviewResultRsp = queryRealnameAuthReviewResult(osclient, domainId, filteringParams);
    }
}
