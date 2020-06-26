
package io.weather.models;

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
    "night",
    "eve",
    "morn"
})
public class FeelsLike {

    @JsonProperty("day")
    private Double day;
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
		return "FeelsLike [day=" + day + ", night=" + night + ", eve=" + eve + ", morn=" + morn
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}
