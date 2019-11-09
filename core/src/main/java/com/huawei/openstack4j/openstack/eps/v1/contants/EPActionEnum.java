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
package com.huawei.openstack4j.openstack.eps.v1.contants;

 import com.google.common.base.Strings;

 public enum EPActionEnum
 {
     /**
      * 操作标识
      */
     enable,
     disable;

     /**
      *
      * 获取操作标识
      * @param     value 操作标识的值
      * @return EPActionEnum
      */
     public static EPActionEnum value(String value)
     {
         if (Strings.isNullOrEmpty(value))
         {
             return null;
         }
         for (EPActionEnum action : EPActionEnum.values())
         {
             if (action.name().equals(value))
             {
                 return action;
             }
         }
         return null;
     }
 }
