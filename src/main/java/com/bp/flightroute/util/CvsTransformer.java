package com.bp.flightroute.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.model.Location;

public class CvsTransformer {
	
	private static final Pattern PATTERN = Pattern.compile("(?<=base64,)(.*)(?=------)");

	public static Flights process(InputStream is) {

		Flights flights = new Flights();
		
		String stream = getStreamAsString(is);
		String payload = getBase64Payload(stream);
		String[] rows = getCvsData(payload);
		
		if (rows != null && rows.length > 1) {
			for (int r = 1; r < rows.length; r++) {
				String[] column = rows[r].split(",");
				Flight flight = new Flight();
				flight.setStart(new Location(column[0], column[1]));
				flight.setEnd(new Location(column[2], column[3]));
				flight.setCost(Double.parseDouble(column[4]));
				flight.setDistance(Double.parseDouble(column[5]));
				flights.getFlights().add(flight);
			}
		}
		System.out.println(flights);
		return flights;
	}
	
	private static String getStreamAsString(InputStream is) {

		String row = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((row = br.readLine()) != null) {
				sb.append(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private static String getBase64Payload(String stream) {

		Matcher matcher = PATTERN.matcher(stream);
		matcher.find();
		return matcher.group();
	}
	
	private static String[] getCvsData(String stream) {
		
		byte[] decoded = Base64.getDecoder().decode(stream);
		String data = new String(decoded);
		return data.split("\n");
	}
}