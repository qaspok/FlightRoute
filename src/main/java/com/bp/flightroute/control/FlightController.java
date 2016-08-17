package com.bp.flightroute.control;

import java.io.InputStream;

import com.bp.flightroute.database.FlightDatabase;
import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.transform.CvsTransformer;
import com.bp.flightroute.transform.RouteBuilder;

public class FlightController {

	public static Flights search(String start, String end) {
		
		Flights flights = FlightDatabase.getAllFlights();
		Flights cheapestRoute = RouteBuilder.cheapest(start, end, flights);
		return cheapestRoute;
	}

	public static Flights getAllFlights() {
		
		return FlightDatabase.getAllFlights();
	}

	public static void postFlight(Flight flight) {
		
		FlightDatabase.addFlight(flight);		
	}

	public static void bulkUpload(InputStream cvs) {
		
		Flights flights = CvsTransformer.process(cvs);
		FlightDatabase.addFlights(flights);
	}
}