
package io.coupon.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "day",
    "min",
    "max",
    "night",
    "eve",
    "morn"
})
public class Temp {

    @JsonProperty("day")
    private Double day;
    @JsonProperty("min")
    private Double min;
    @JsonProperty("max")
    private Double max;
    @JsonProperty("night")
    private Double night;
    @JsonProperty("eve")
    private Double eve;
    @JsonProperty("morn")
    private Double morn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("day")
    public Double getDay() {
        return day;
    }

    @JsonProperty("day")
    public void setDay(Double day) {
        this.day = day;
    }

    @JsonProperty("min")
    public Double getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(Double min) {
        this.min = min;
    }

    @JsonProperty("max")
    public Double getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(Double max) {
        this.max = max;
    }

    @JsonProperty("night")
    public Double getNight() {
        return night;
    }

    @JsonProperty("night")
    public void setNight(Double night) {
        this.night = night;
    }

    @JsonProperty("eve")
    public Double getEve() {
        return eve;
    }

    @JsonProperty("eve")
    public void setEve(Double eve) {
        this.eve = eve;
    }

    @JsonProperty("morn")
    public Double getMorn() {
        return morn;
    }

    @JsonProperty("morn")
    public void setMorn(Double morn) {
        this.morn = morn;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "Temp [day=" + day + ", min=" + min + ", max=" + max + ", night=" + night + ", eve=" + eve + ", morn="
				+ morn + ", additionalProperties=" + additionalProperties + "]";
	}

}
