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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoScalingPolicy implements ModelEntity{
	private static final long serialVersionUID = 3523520023683359968L;

	@JsonProperty("auto_scaling_enable")
    private boolean autoScalingEnable;

    @JsonProperty("min_capacity")
    private int minCapacity;

    @JsonProperty("max_capacity")
    private int maxCapacity;

    @JsonProperty("rules")
    private List<Rule> rules;

    public boolean getAutoScalingEnable() {
        return autoScalingEnable;
    }

    public void setAutoScalingEnable(boolean autoScalingEnable) {
        this.autoScalingEnable = autoScalingEnable;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public static class Rule {

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("adjustment_type")
        private String adjustmentType;

        @JsonProperty("cool_down_minutes")
        private int coolDownMinutes;

        @JsonProperty("scaling_adjustment")
        private int scalingAdjustment;

        @JsonProperty("trigger")
        private Trigger trigger;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAdjustmentType() {
            return adjustmentType;
        }

        public void setAdjustmentType(String adjustmentType) {
            this.adjustmentType = adjustmentType;
        }

        public int getCoolDownMinutes() {
            return coolDownMinutes;
        }

        public void setCoolDownMinutes(int coolDownMinutes) {
            this.coolDownMinutes = coolDownMinutes;
        }

        public int getScalingAdjustment() {
            return scalingAdjustment;
        }

        public void setScalingAdjustment(int scalingAdjustment) {
            this.scalingAdjustment = scalingAdjustment;
        }

        public Trigger getTrigger() {
            return trigger;
        }

        public void setTrigger(Trigger trigger) {
            this.trigger = trigger;
        }
    }

    public static class Trigger {
        @JsonProperty("metric_name")
        private String metricName;

        @JsonProperty("metric_value")
        private String metricValue;

        @JsonProperty("comparison_operator")
        private String comparisonOperator;

        @JsonProperty("evaluation_periods")
        private int evaluationPeriods;

        public String getMetricName() {
            return metricName;
        }

        public void setMetricName(String metricName) {
            this.metricName = metricName;
        }

        public String getMetricValue() {
            return metricValue;
        }

        public void setMetricValue(String metricValue) {
            this.metricValue = metricValue;
        }

        public String getComparisonOperator() {
            return comparisonOperator;
        }

        public void setComparisonOperator(String comparisonOperator) {
            this.comparisonOperator = comparisonOperator;
        }

        public int getEvaluationPeriods() {
            return evaluationPeriods;
        }

        public void setEvaluationPeriods(int evaluationPeriods) {
            this.evaluationPeriods = evaluationPeriods;
        }
    }

     
}
