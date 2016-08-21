
package com.bp.flightroute.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "startname",
    "startcode",
    "endname",
    "endcode",
    "cost"
})
public class FlatFlight {

    @JsonProperty("startname")
    private String startname;
    @JsonProperty("startcode")
    private String startcode;
    @JsonProperty("endname")
    private String endname;
    @JsonProperty("endcode")
    private String endcode;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("distance")
    private Double distance;

    @JsonProperty("startname")
    public String getStartname() {
        return startname;
    }
    @JsonProperty("startname")
    public void setStartname(String startname) {
        this.startname = startname;
    }

    @JsonProperty("startcode")
    public String getStartcode() {
        return startcode;
    }
    @JsonProperty("startcode")
    public void setStartcode(String startcode) {
        this.startcode = startcode;
    }

    @JsonProperty("endname")
    public String getEndname() {
        return endname;
    }
    @JsonProperty("endname")
    public void setEndname(String endname) {
        this.endname = endname;
    }

    @JsonProperty("endcode")
    public String getEndcode() {
        return endcode;
    }
    @JsonProperty("endcode")
    public void setEndcode(String endcode) {
        this.endcode = endcode;
    }

    @JsonProperty("cost")
    public Double getCost() {
        return cost;
    }
    @JsonProperty("cost")
    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    @JsonProperty("distance")
    public Double getDistance() {
        return distance;
    }
    @JsonProperty("distance")
    public void setDistance(Double distance) {
        this.distance = distance;
    }
}