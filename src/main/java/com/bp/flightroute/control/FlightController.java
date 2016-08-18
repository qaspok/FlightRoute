package com.bp.flightroute.control;

import java.io.InputStream;

import com.bp.flightroute.database.FlightDatabase;
import com.bp.flightroute.model.FlatFlight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.model.Route;
import com.bp.flightroute.util.CvsTransformer;
import com.bp.flightroute.util.RouteFinder;

public class FlightController {

	public static Route search(String start, String end) {
		
		Flights flights = FlightDatabase.getAllFlights();
		RouteFinder routeFinder = new RouteFinder(flights);
		Route cheapestRoute = routeFinder.cheapestRoute(start, end);
		return cheapestRoute;
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