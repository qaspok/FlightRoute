package com.bp.flightroute.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * SearchResults
 * Contains cheapest and shortest routes
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cheapest",
    "shortest"
})
public class SearchResults {

	@JsonProperty("cheapest")
	private Route cheapest;
	@JsonProperty("shortest")
	private Route shortest;
	
	@JsonProperty("cheapest")
	public Route getCheapest() {
		return cheapest;
	}
	@JsonProperty("cheapest")
	public void setCheapest(Route cheapest) {
		this.cheapest = cheapest;
	}
	
	@JsonProperty("shortest")
	public Route getShortest() {
		return shortest;
	}
	@JsonProperty("shortest")
	public void setShortest(Route shortest) {
		this.shortest = shortest;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cheapest: ");
		sb.append(getCheapest());
		sb.append(" Shortest: ");
		sb.append(getShortest());
		return sb.toString();
	}
}