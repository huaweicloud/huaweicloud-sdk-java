/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.identity.v3.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import com.huawei.openstack4j.model.identity.v3.Region;
import com.huawei.openstack4j.model.identity.v3.builder.RegionBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Region model class for identity v3
 *
 * @see <a href=
 *      "http://developer.openstack.org/api-ref-identity-v3.html#regions-v3">API
 *      reference</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("region")
public class KeystoneRegion implements Region {

    private static final long serialVersionUID = 1L;
    private String id;
    private String description;
    @JsonProperty("parent_region_id")
    private String parentRegionId;

    private Map<String, String> links;
    private String type;
    private Map<String, String> locales;

    /**
     * @return the region builder
     */
    public static RegionBuilder builder() {
        return new RegionConcreteBuilder();
    }

    @Override
    public RegionBuilder toBuilder() {
        return new RegionConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getParentRegionId() {
        return parentRegionId;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * {@inheritDoc}
     */
    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @Override
    public Map<String, String> getLocales() {
        return locales;
    }

    /**
     * {@inheritDoc}
     */
    public void setLocales(Map<String, String> locales) {
        this.locales = locales;
    }

    @Override
    public String getType() {return type; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("description", description)
                .add("parentRegionId", parentRegionId)
                .add("type", type)
                .add("links", links)
                .add("locales", locales)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeystoneRegion that = KeystoneRegion.class.cast(obj);
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.description, that.description)
                && Objects.equal(this.parentRegionId, that.parentRegionId)
                && Objects.equal(this.links, that.links)
                && Objects.equal(this.type, that.type)
                && Objects.equal(this.locales, that.locales);
    }

    public static class Regions extends ListResult<KeystoneRegion> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("regions")
        private List<KeystoneRegion> list;

        private Map<String, String> links;

        @Override
        public List<KeystoneRegion> value() {
            return list;
        }
    }

    public static class RegionConcreteBuilder implements RegionBuilder {

        KeystoneRegion model;

        RegionConcreteBuilder() {
            this(new KeystoneRegion());
        }

        RegionConcreteBuilder(KeystoneRegion model) {
            this.model = model;
        }

        /**
         * @see KeystoneUser#getId()
         */
        @Override
        public RegionBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public Region build() {
            return model;
        }

        @Override
        public RegionBuilder from(Region in) {
            if (in != null)
                this.model = (KeystoneRegion) in;
            return this;
        }

        @Override
        public RegionBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public RegionBuilder parentRegionId(String parentRegionId) {
            model.parentRegionId = parentRegionId;
            return this;
        }

        @Override
        public RegionBuilder type(String type) {
            model.type = type;
            return this;
        }
        @Override
        public RegionBuilder links(Map<String, String> links){
            model.links = links;
            return this;
        }

        @Override
        public RegionBuilder locales(Map<String, String> locales){
            model.locales = locales;
            return this;
        }
    }
}
