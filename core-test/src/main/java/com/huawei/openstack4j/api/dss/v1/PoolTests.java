 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.api.dss.v1;

 import static org.testng.Assert.assertEquals;
 import static org.testng.Assert.assertTrue;

 import org.testng.annotations.Test;

 import com.huawei.openstack4j.api.AbstractTest;
 import com.huawei.openstack4j.openstack.dss.v1.domain.Pool;

 import okhttp3.mockwebserver.RecordedRequest;

 @Test(suiteName = "DSS/POOL")
 public class PoolTests extends AbstractTest{

     @Override
     protected Service service() {
         return Service.DSS;
     }

     @Test
     public void getPoolTest() throws Exception {
         respondWith("/dss/v1/pool.json");
         final String dssId= "517eb026-2cdc-45da-9ebc-0c6ececb1236";
         // Check get pool
         Pool pool = osv3().dss().pools().get(dssId);
         RecordedRequest getRequest = server.takeRequest();
         assertEquals(getRequest.getPath(), "/v1/project-id/pools/517eb026-2cdc-45da-9ebc-0c6ececb1236?usage=false");

         assertEquals(pool.getId(), dssId);
         assertEquals(pool.getAvailabilityZone(), "kvmxen.dc1");
         assertEquals(pool.getCapacity(), new Integer(32768));
         assertEquals(pool.getCreatedAt(), "2019-01-09T06:14:07.105684");
         assertEquals(pool.getName(), "dss_yq");
         assertEquals(pool.getStatus(), "available");
         assertEquals(pool.getProjectId(), "000efdc5f9064584b718b181df137bd7");
         assertEquals(pool.getType(), "SAS");

         // Check get pool with usage
         respondWith("/dss/v1/pool.json");
         Pool pool2 = osv3().dss().pools().get(dssId,true);
         RecordedRequest getRequest2 = server.takeRequest();
         assertEquals(getRequest2.getPath(), "/v1/project-id/pools/517eb026-2cdc-45da-9ebc-0c6ececb1236?usage=true");


     }

 }
