/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.                                     
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
package com.huawei.openstack4j.api.cdn.v1;

import java.io.IOException;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Log.Logs;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Cdn/Log", enabled = true)
public class LogServiceTest extends AbstractTest
{
	
	@Test
	public void testQueryLogs() throws IOException, InterruptedException
	{
		respondWith("/cdn/query_log_response.json");
		
		Long queryDate = new Date().getTime(); 
		Logs logs = osv3().cdn().logs().queryLogs("www.xxxx.com",queryDate, 10, 1, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/logs?domain_name=www.xxxx.com&page_number=1&query_date="+queryDate+"&page_size=10");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(logs.getLogs().size(), 1);
		Assert.assertEquals(logs.getTotal(), Integer.valueOf(1));
		Assert.assertEquals(logs.getLogs().get(0).getDomainName(), "www.xxxx.com");
		Assert.assertEquals(logs.getLogs().get(0).getSize(), Long.valueOf(4096));
	}

	@Override
	protected Service service()
	{
		return Service.CDN;
	}

}
