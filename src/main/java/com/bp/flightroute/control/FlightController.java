package com.bp.flightroute.control;

import java.io.InputStream;

import com.bp.flightroute.database.FlightDatabase;
import com.bp.flightroute.model.FlatFlight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.model.SearchResults;
import com.bp.flightroute.util.CvsTransformer;
import com.bp.flightroute.util.RouteSearch;

public class FlightController {

	public static SearchResults search(String start, String end) {
		
		Flights flights = FlightDatabase.getAllFlights();
		RouteSearch routeFinder = new RouteSearch(flights);
		SearchResults results = routeFinder.search(start, end);
		return results;
	}

	public static Flights getAllFlights() {
		
		return FlightDatabase.getAllFlights();
	}

	public static void postFlight(FlatFlight flight) {
		
		FlightDatabase.addFlight(flight);		
	}

	public static void bulkUpload(InputStream cvs) {
		
		Flights flights = CvsTransformer.process(cvs);
		FlightDatabase.addFlights(flights);
	}
}