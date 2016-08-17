package com.bp.flightroute.database;

import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;

public class FlightDatabase {

	public static Flights search(String start, String end) {
		
		Flights flights = new Flights();
		Flight flight = new Flight();
		flight.setStartname("London");
		flight.setStartcode("LO");
		flight.setEndname("Toronto");
		flight.setEndcode("TO");
		flight.setCost(123.45);
		flights.getFlights().add(flight);
		return flights;
	}

	public static Flights getAllFlights() {
		
		Flights flights = new Flights();
		Flight flight = new Flight();
		flight.setStartname("London");
		flight.setStartcode("LO");
		flight.setEndname("Toronto");
		flight.setEndcode("TO");
		flight.setCost(123.45);
		flights.getFlights().add(flight);
		flight = new Flight();
		flight.setStartname("Toronto");
		flight.setStartcode("TO");
		flight.setEndname("Montreal");
		flight.setEndcode("MQ");
		flight.setCost(123.45);
		flights.getFlights().add(flight);
		return flights;
	}

	public static void addFlight(Flight flight) {
		
		
	}

	public static void addFlights(Flights flights) {
		
		
	}
}