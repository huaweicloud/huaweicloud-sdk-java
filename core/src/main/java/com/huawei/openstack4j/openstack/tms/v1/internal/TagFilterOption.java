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
package com.huawei.openstack4j.openstack.tms.v1.internal;

import java.util.Map;

import com.google.common.collect.Maps;
import com.huawei.openstack4j.openstack.tms.v1.contants.OrderField;
import com.huawei.openstack4j.openstack.tms.v1.contants.OrderMethod;

public class TagFilterOption {
	private Map<String, Object> queryParams = Maps.newHashMap();
	
	private TagFilterOption(){
	}
	
	public static TagFilterOption create(){
		return new TagFilterOption();
	}
	/**
	 * 键，支持模糊查询，不区分大小写，如果包含“non-URL-safe”的字符，需要进行“urlencoded”。
	 * @param key
	 * @return
	 */
	public TagFilterOption key(String key){
		return add("key", key);
	}
	/**
	 * 值，支持模糊查询，不区分大小写，如果包含“non-URL-safe”的字符，需要进行“urlencoded”。
	 * @param value
	 * @return
	 */
	public TagFilterOption value(String value){
		return add("value", value);
	}
	/**
	 * 	查询记录数。最小为1，最大为1000，未输入时默认为10，为0时不限制查询数据条数。
	 * @param limit
	 * @return
	 */
	public TagFilterOption limit(Integer limit){
		return add("limit", limit);
	}
	/**
	 * 分页位置标识（索引）。从marker指定索引的下一条数据开始查询。说明：
	 * 查询第一页数据时，不需要传入此参数，查询后续页码数据时，将查询前一页数据时响应体中的值配入此参数。
	 * @param marker
	 * @return
	 */
	public TagFilterOption marker(String marker){
		return add("marker", marker);
	}
	/**
	 * 	排序字段：
	 *	可输入的值包含（区分大小写）：update_time（更新时间）、key（键）、value（值）。
	 *	只能选择以上排序字段中的一个，并按照排序方法字段order_method进行排序，如果不传则默认值为：update_time。
	 *	如以下：
	 *	若该字段为update_time，则剩余两个默认字段排序为key升序，value升序。
	 *	若该字段如为key，则剩余两个默认字段排序为update_time降序，value升序。
	 *	若该字段如为value，则剩余两个默认字段排序为update_time降序，key升序。
	 *	若该字段不传，默认字段为update_time，则剩余两个默认字段排序为key升序，value升序。
	 * @param orderField
	 * @return
	 */
	public TagFilterOption orderField(OrderField orderField){
		return add("order_field", orderField);
	}
	/**
	 * 	
	 *	排序方法：
	 *	可输入的值包含（区分大小写）：asc（升序）、desc（降序）。
	 *	只能选择以上值的其中之一，是order_field字段的排序方法。不传则默认值为：desc。
	 * @param orderMethod
	 * @return
	 */
	public TagFilterOption orderMethod(OrderMethod orderMethod){
		return add("order_method", orderMethod);
	}
	
	
	
	private TagFilterOption add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}
	
	 public Map<String, Object> getOptions() {
	    return queryParams;
	 }
}
