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
package com.huawei.openstack4j.model.common.serializer;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.Lists;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:41:42
 */
public class BooleanDeserializer extends JsonDeserializer<Boolean> {

	ArrayList<String> trueValues = Lists.newArrayList("Y", "1", "YES");
	ArrayList<String> falseValues = Lists.newArrayList("N", "0", "NO");

	@Override
	public Boolean deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String value = parser.getText();
		
		if (value != null) {
			if (trueValues.contains(value.toUpperCase())) {
				return true;
			}
			
			if (falseValues.contains(value.toUpperCase())) {
				return false;
			}
		}

		return null;
	}
}
