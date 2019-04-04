/*******************************************************************************
 * 	Copyright 2019 HuaWei tld and OTC
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
public interface AsyncHandler<RESULT> {

	/**
	 * Invoked after an asynchronous request 
	 * @param exception
	 */
	public void onError(Exception exception);

	/**
	 * Invoked after an asynchronous request has completed successfully. Callers
	 * have access to the original request object and the returned response
	 * object.
	 *
	 *            The initial request created by the caller
	 * @param result
	 *            The successful result of the executed operation.
	 */
	public void onSuccess(RESULT result);

}