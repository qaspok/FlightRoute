package com.bp.flightroute.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GreetingsDatabase {

	private static String URL = "jdbc:derby:memory:greeting;create=true";
	private static String CREATE = "CREATE TABLE Greetings(GreetingId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Text VARCHAR(24) NOT NULL)";
	private static String INSERT = "INSERT INTO Greetings(Text) VALUES(?)";
	private static String GET = "SELECT Text FROM Greetings ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY";
	private static String[] GREETINGS = new String[] {"Hello", "Hey", "Hi", "Aloha", "Shalom", "Namaste", "Hola", "Bonjour"};

	public static void init() {

		try (Connection connection = DriverManager.getConnection(URL)) {
			try(PreparedStatement statement = connection.prepareStatement(CREATE)) {
				statement.execute();				
			}
			for(String greeting : GREETINGS) {
				try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
					statement.setString(1, greeting);
					statement.execute();				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static String getGreeting() {

		try (Connection connection = DriverManager.getConnection(URL)) {
			try(PreparedStatement statement = connection.prepareStatement(GET)) {
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				resultSet.next();
				return resultSet.getString("Text");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}