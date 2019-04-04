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
package com.huawei.openstack4j.openstack.vpc.v2.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.huawei.openstack4j.openstack.vpc.v2.domain.AsyncBandWidthRespEntity;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidths;

public class BandWidthService extends BaseVirtualPrivateCloudService {

	/**
	 * Update bandwidth
	 * @param bandWidth
	 * @param bandwidthId
	 * @return
	 */
	public AsyncBandWidthRespEntity update(VirtualBandWidths bandWidth,String bandwidthId){
		checkArgument(!(null == (bandWidth.getBandwidth())), "parameter `bandwidth` should not be empty");
		return put(AsyncBandWidthRespEntity.class, "/bandwidths/"+bandwidthId).entity(bandWidth).execute();
	}

	/**
	 * 创建共享带宽
	 * @param bandwidthCreate
	 * @return
	 */
//	public BandwidthResp create(BandwidthCreate bandwidthCreate){
//		checkArgument(null != bandwidthCreate, "parameter `bandwidthCreate` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(bandwidthCreate.getName()),"parameter `bandwidthCreate.name` should not be empty");
//		checkArgument(null != bandwidthCreate.getSize(),"parameter `bandwidthCreate.size` should not be empty");
//		return post(BandwidthResp.class, uri("/bandwidths")).entity(bandwidthCreate).execute();
//	}

	/**
	 * 批量创建共享带宽
	 * @param bandwidthBatchCreate
	 * @return
	 */
//	public List<BandwidthResp> batchCreate(BandwidthBatchCreate bandwidthBatchCreate){
//		checkArgument(null != bandwidthBatchCreate, "parameter `bandwidthBatchCreate` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(bandwidthBatchCreate.getName()),"parameter `bandwidthBatchCreate.name` should not be empty");
//		checkArgument(null != bandwidthBatchCreate.getSize(),"parameter `bandwidthBatchCreate.size` should not be empty");
//		checkArgument(null != bandwidthBatchCreate.getCount(),"parameter `bandwidthBatchCreate.count` should not be empty");
//		return post(BandwidthResp.BandwidthResps.class, uri("/batch-bandwidths")).entity(bandwidthBatchCreate).execute().getList();
//	}

	/**
	 * 删除共享带宽
	 * @param bandwidthId
	 * @return
	 */
//	public ActionResponse delete(String bandwidthId){
//		checkArgument(!Strings.isNullOrEmpty(bandwidthId),"parameter `bandwidthId` should not be empty");
//		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,
//				uri("/bandwidths/%s",bandwidthId)).executeWithResponse());
//	}

	/**
	 * 共享带宽插入弹性公网IP
	 * @param bandwidthId
	 * @param bandwidthInsert
	 * @return
	 */
//	public BandwidthResp insert(String bandwidthId, BandwidthInsert bandwidthInsert){
//		checkArgument(!Strings.isNullOrEmpty(bandwidthId),"parameter `bandwidthId` should not be empty");
//		checkArgument(null != bandwidthInsert, "parameter `bandwidthBatchCreate` should not be empty");
//		for(PublicIpInfo publicIpInfo : bandwidthInsert.getPublicipInfo()){
//			checkArgument(!Strings.isNullOrEmpty(publicIpInfo.getPublicipId()),"parameter `publicIpInfo.publicipId` should not be empty");
//		}
//		return post(BandwidthResp.class, uri("/bandwidths/%s/insert", bandwidthId)).entity(bandwidthInsert).execute();
//	}

	/**
	 * 共享带宽移除弹性公网IP
	 * @param bandwidthId
	 * @param bandwidthRemove
	 * @return
	 */
//	public ActionResponse remove(String bandwidthId, BandwidthRemove bandwidthRemove){
//		checkArgument(!Strings.isNullOrEmpty(bandwidthId),"parameter `bandwidthId` should not be empty");
//		checkArgument(null != bandwidthRemove, "parameter `bandwidthRemove` should not be empty");
//		checkArgument(null != bandwidthRemove.getSize(), "parameter `bandwidthRemove.size` should not be empty");
//		checkArgument(null != bandwidthRemove.getChargeMode(), "parameter `bandwidthRemove.chargeMode` should not be empty");
//		checkArgument(null != bandwidthRemove.getPublicipInfo(), "parameter `bandwidthRemove.publicipInfo` should not be empty");
//		for(PublicIpInfo publicIpInfo : bandwidthRemove.getPublicipInfo()){
//			checkArgument(!Strings.isNullOrEmpty(publicIpInfo.getPublicipId()),"parameter `publicIpInfo.publicipId` should not be empty");
//		}
//		return ToActionResponseFunction.INSTANCE.apply(post(Void.class, uri("/bandwidths/%s/remove", bandwidthId)).entity(bandwidthRemove).executeWithResponse());
//	}
}
