package com.bp.flightroute.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Flight
 * Contains flight data
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "start",
    "end",
    "cost"
})
public class Flight {

	@JsonProperty("start")
	Location start;
	@JsonProperty("end")
	Location end;
	@JsonProperty("cost")
	double cost;
	@JsonProperty("distance")
	double distance;
	
	@JsonProperty("start")
	public Location getStart() {
		return start;
	}
	@JsonProperty("start")
	public void setStart(Location start) {
		this.start = start;
	}
	
	@JsonProperty("end")
	public Location getEnd() {
		return end;
	}
	@JsonProperty("end")
	public void setEnd(Location end) {
		this.end = end;
	}
	
	@JsonProperty("cost")
	public double getCost() {
		return cost;
	}
	@JsonProperty("cost")
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@JsonProperty("distance")
	public double getDistance() {
		return distance;
	}
	@JsonProperty("distance")
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return start + " to " + end + ": $" + cost + ", " + distance + "km";
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(!(o instanceof Flight))
			return false;
		
		Flight other = (Flight)o;
		
		// start
		if(this.getStart() == null && other.getStart() != null)
			return false;
		if(this.getStart() != null && other.getStart() == null)
			return false;
		if(this.getStart() != null && other.getStart() != null) {
			if(!this.getStart().equals(other.getStart()))
				return false;
		}
		
		// end
		if(this.getEnd() == null && other.getEnd() != null)
			return false;
		if(this.getEnd() != null && other.getEnd() == null)
			return false;
		if(this.getEnd() != null && other.getEnd() != null) {
			if(!this.getEnd().equals(other.getEnd()))
				return false;
		}
		
		// cost
		if(this.getCost() != other.getCost())
			return false;
		
		// distance
		if(this.getDistance() != other.getDistance())
			return false;
		
		return true;
	}
	
	@Override
	public Flight clone() {
		Flight clone = new Flight();
		clone.setStart(this.start.clone());
		clone.setEnd(this.end.clone());
		clone.setCost(this.cost);
		clone.setDistance(this.distance);
		return clone;
	}
}