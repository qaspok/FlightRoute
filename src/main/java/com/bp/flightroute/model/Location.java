package com.bp.flightroute.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Flight
 * Contains location data
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "code"
})
public class Location {

	@JsonProperty("name")
	String name;
	@JsonProperty("code")
	String code;
	
	public Location(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("code")
	public String getCode() {
		return code;
	}
	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(!(o instanceof Location))
			return false;
		
		Location other = (Location)o;
		
		// name
		if(this.getName() == null && other.getName() != null)
			return false;
		if(this.getName() != null && other.getName() == null)
			return false;
		if(this.getName() != null && other.getName() != null) {
			if(!this.getName().equals(other.getName()))
				return false;
		}
		
		// code
		if(this.getCode() == null && other.getCode() != null)
			return false;
		if(this.getCode() != null && other.getCode() == null)
			return false;
		if(this.getCode() != null && other.getCode() != null) {
			if(!this.getCode().equals(other.getCode()))
				return false;
		}
		
		return true;
	}
	
	@Override
	public Location clone() {
		return new Location(new String(this.getName()), new String(this.getCode()));
	}
}