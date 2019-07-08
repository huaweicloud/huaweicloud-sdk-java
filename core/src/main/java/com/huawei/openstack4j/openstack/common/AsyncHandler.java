/*
 * Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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