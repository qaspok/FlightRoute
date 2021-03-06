package com.bp.flightroute.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bp.flightroute.model.FlatFlight;
import com.bp.flightroute.model.Flight;
import com.bp.flightroute.model.Flights;
import com.bp.flightroute.model.Location;

public class FlightDatabase {
	
	private static String URL = "jdbc:derby:directory:flights;create=true";
	private static String CREATE = "CREATE TABLE Flights(FlightId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), StartName VARCHAR(50) NOT NULL, StartCode VARCHAR(3) NOT NULL, EndName VARCHAR(24) NOT NULL, EndCode VARCHAR(3) NOT NULL, Cost NUMERIC(8,2) NOT NULL, Distance NUMERIC(5,1) NOT NULL)";
	private static String INSERT = "INSERT INTO Flights(StartName, StartCode, EndName, EndCode, Cost, Distance) VALUES(?, ?, ?, ?, ?, ?)";
	private static String GET_ALL = "SELECT * FROM Flights";
	
	public static void init() {

		try (Connection connection = DriverManager.getConnection(URL)) {
			try(PreparedStatement statement = connection.prepareStatement(CREATE)) {
				statement.execute();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Flights getAllFlights() {
		
		Flights flights = new Flights();
		try (Connection connection = DriverManager.getConnection(URL)) {
			try(PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				while(resultSet.next()) {
					Flight flight = new Flight();
					flight.setStart(new Location(resultSet.getString("StartName"), resultSet.getString("StartCode")));
					flight.setEnd(new Location(resultSet.getString("EndName"), resultSet.getString("EndCode")));
					flight.setCost(resultSet.getDouble("Cost"));
					flight.setDistance(resultSet.getDouble("Distance"));
					flights.getFlights().add(flight);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

	public static void addFlight(FlatFlight flight) {
		
		try (Connection connection = DriverManager.getConnection(URL)) {
			try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
				statement.setString(1, flight.getStartname());
				statement.setString(2, flight.getStartcode());
				statement.setString(3, flight.getEndname());
				statement.setString(4, flight.getEndcode());
				statement.setBigDecimal(5, BigDecimal.valueOf(flight.getCost()));
				statement.setBigDecimal(6, BigDecimal.valueOf(flight.getDistance()));
				statement.execute();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addFlights(Flights flights) {
		
		try (Connection connection = DriverManager.getConnection(URL)) {
			try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
				for(Object o : flights.getFlights()) {
					Flight flight = (Flight) o;
					statement.setString(1, flight.getStart().getName());
					statement.setString(2, flight.getStart().getCode());
					statement.setString(3, flight.getEnd().getName());
					statement.setString(4, flight.getEnd().getCode());
					statement.setBigDecimal(5, BigDecimal.valueOf(flight.getCost()));
					statement.setBigDecimal(6, BigDecimal.valueOf(flight.getDistance()));
					statement.addBatch();
				}
				statement.executeBatch();				
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}