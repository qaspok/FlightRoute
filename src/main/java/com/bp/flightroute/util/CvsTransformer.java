package com.bp.flightroute.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.model.Location;

public class CvsTransformer {

	public static Flights process(InputStream cvs) {

		Flights flights = new Flights();
		List<String> rows = getLinesFromInputStream(cvs);
		if (rows != null && rows.size() > 1) {
			for (int r = 1; r < rows.size(); r++) {
				String[] column = rows.get(r).split(",");
				Flight flight = new Flight();
				flight.setStart(new Location(column[0], column[1]));
				flight.setEnd(new Location(column[2], column[3]));
				flight.setCost(Double.parseDouble(column[4]));
				flights.getFlights().add(flight);
			}
		}
		System.out.println(flights);
		return flights;
	}

	private static List<String> getLinesFromInputStream(InputStream is) {

		List<String> rows = new ArrayList<String>();
		String row = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((row = br.readLine()) != null) {
				rows.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
}