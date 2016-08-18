package com.bp.flightroute.util;

import java.util.ArrayList;
import java.util.List;

import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.model.Location;
import com.bp.flightroute.model.Route;

public class RouteFinder {

	private List<Flight> flights;
	private static final int MAX_DEPTH = 10;
	private static List<List<Flight>> viableRoutes;
	
	public RouteFinder(Flights flights) {
		
		this.flights = flights.getFlights();
	}

	public Route cheapestRoute(String start, String end) {

		// Find viable routes
		viableRoutes = new ArrayList<List<Flight>>();
		for(Flight flight : flights) {
			if(start.equals(flight.getStart().getName()) || start.equals(flight.getStart().getCode())) {
				ArrayList<Location> visited = new ArrayList<Location>();
				visited.add(flight.getStart());
				findRoutes(end, flight.getStart(), new ArrayList<Flight>(), visited, 0);
				break;
			}
		}
		
		//Find cheapest route
		List<Flight> cheapestRoute = null;
		double cheapestTotal = Double.MAX_VALUE;
		for(List<Flight> route : viableRoutes) {
			double total = 0;
			for(Flight flight: route) {
				total += flight.getCost();
			}
			if(total < cheapestTotal) {
				cheapestRoute = route;
				cheapestTotal = total;
			}
		}
		
		Route route = new Route();
		route.setRoute(cheapestRoute);
		route.setCost(cheapestTotal);
		return route;
	}
	
	private void findRoutes(String end, Location currentLocation, List<Flight> route, List<Location> visited, int depth) {
		
		// Reached maximum inception level
		if(depth == MAX_DEPTH)
			return;
		
		// Find flights that leave from our current location
		for(Flight flight : flights) {
			if(currentLocation.equals(flight.getStart()))  {
				
				// If this flight goes to our destination, add this route to the list of viable routes
				if(end.equals(flight.getEnd().getName()) || end.equals(flight.getEnd().getCode())) {
					ArrayList<Flight> viableRoute = new ArrayList<Flight>(route.size()+1);
					for(Flight f : route) viableRoute.add(f.clone());
					viableRoute.add(flight);
					viableRoutes.add(viableRoute);
				}
				// Otherwise, if we haven't been to the destination yet, search another layer deeper
				else if(!visited.contains(flight.getEnd())) {
					ArrayList<Flight> newRoute = new ArrayList<Flight>(route.size()+1);
					for(Flight f : route) newRoute.add(f.clone());
					newRoute.add(flight);
					ArrayList<Location> newVisited = new ArrayList<Location>(visited.size()+1);
					for(Location l : visited) newVisited.add(l.clone());
					newVisited.add(flight.getEnd());
					findRoutes(end, flight.getEnd(), newRoute, newVisited, ++depth);
				}
			}
		}
	}
}