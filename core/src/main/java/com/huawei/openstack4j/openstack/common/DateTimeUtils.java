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
package com.huawei.openstack4j.openstack.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm'Z'
	 */
	public static final String FORMAT_YMDTHMZ = "yyyy-MM-dd'T'HH:mm'Z'";
	
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm:ss'Z'
	 */
	public static final String FORMAT_YMDTHMSZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm:ss'Z'
	 */
	public static final String FORMAT_YMDTHMSZONE = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm:ss'Z'
	 * 
	 * example: 2016-12-02T13:00:00.121
	 */
	public static final String FORMAT_YMDTHMS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	
	public static final String FORMAT_YMDTHMS_SSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	/**
	 * DateTime format yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * DateTime format yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_YMDTHMS = "yyyy-MM-dd'T'HH:mm:ss";
	
	/**
	 * DateTime format yyyy-MM-d
	 */
	public static final String FORMAT_YMD = "yyyy-MM-d";
	
	
	public static final String FORMAT_YMD_HM = "yyyy-MM-dd'+'HH:mm";
	
	/**
	 * format date
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
}
