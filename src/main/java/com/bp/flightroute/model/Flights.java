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
    "flights"
})
public class Flights {

	@JsonProperty("flights")
	private List<Flight> flights;

	@JsonProperty("flights")
	public List<Flight> getFlights() {
		if(flights == null)
			flights = new ArrayList<Flight>();
		return flights;
	}
	@JsonProperty("flights")
	public void setFlights(List<Flight> route) {
		this.flights = route;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Flight flight : flights) {
			sb.append(flight + "\n");
		}
		return sb.toString();
	}
}