/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.storage.block.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.Link;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Volume extension model
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Extension implements ModelEntity {

    private static final long serialVersionUID = 100771135891961931L;

    /**
     * The last update time
     */
    private String updated;

    /**
     * The description
     */
    private String descrption;

    /**
     * The link for the disk transfer.
     */
    private List<Link> links;

    /**
     * The name-space of the extension
     */
    private String namespace;

    /**
     * The alias of the extension.
     */
    private String alias;

    /**
     * The name of the disk transfer.
     */
    private String name;

    /**
     * List of extension
     * @author bill
     *
     */
    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Extensions extends ListResult<Extension> {

        private static final long serialVersionUID = 5455563527196302280L;

        private List<Extension> extensions;

        @Override
        protected List<Extension> value() {
            return extensions;
        }
    }
}
