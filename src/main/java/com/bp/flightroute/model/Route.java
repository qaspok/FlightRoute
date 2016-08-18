package com.bp.flightroute.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Flight
 * Contains flight route
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "route",
    "cost"
})
public class Route {

	@JsonProperty("route")
	private List<Flight> route;
	
	@JsonProperty("cost")
	private double cost;

	@JsonProperty("route")
	public List<Flight> getRoute() {
		if(route == null)
			route = new ArrayList<Flight>();
		return route;
	}
	@JsonProperty("route")
	public void setRoute(List<Flight> route) {
		this.route = route;
	}
	
	@JsonProperty("cost")
	public double getCost() {
		return cost;
	}
	@JsonProperty("cost")
	public void setCost(double cost) {
		this.cost = cost;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Flight flight : route) {
			sb.append(flight + "\n");
		}
		sb.append("Cost: " + cost);
		return sb.toString();
	}
}