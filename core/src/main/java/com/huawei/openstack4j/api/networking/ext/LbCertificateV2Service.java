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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate.NeutronCertificates;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificateUpdate;

public interface LbCertificateV2Service extends RestService {

	/**
	 * 查询指定SSL证书的详情信息
	 * @param Id
	 * @return
	 */
	NeutronCertificate  get(String id);
	
	/**
	 * 查询SSL证书。
	 * @return
	 */
	NeutronCertificates	list();

	/**
	 * 查询SSL证书。
	 * @return
	 */
	List<? extends NeutronCertificate> list(Map<String, String> filteringParams);
	
	/**
	 * 创建SSL证书。
	 * @param model
	 * @return
	 */
	NeutronCertificate		create(NeutronCertificate model);
	
	/**
	 * 更新SSL证书。
	 * @param model
	 * @param Id
	 * @return
	 */
	NeutronCertificate		update(NeutronCertificateUpdate model , String id);
	
	/**
	 * 删除SSL证书。
	 * @param id
	 * @return
	 */
	ActionResponse  delete(String id);
}
