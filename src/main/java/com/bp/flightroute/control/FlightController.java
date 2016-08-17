package com.bp.flightroute.control;

import java.io.InputStream;

import com.bp.flightroute.database.FlightDatabase;
import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.transform.CvsTransformer;

public class FlightController {

	public static Flights search(String start, String end) {
		
		return FlightDatabase.search(start, end);
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