package org.example.projectworkspace.Flights;

import Database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class flightDAO {

    // Connect to the database
    private final Connection connection;

    public flightDAO() {
        this.connection = new DatabaseManager().connect();
    }

    // Create a new flight in the database
    public boolean createFlight(Flight flight) {
        String sql = "INSERT INTO flights (number, destination, depatureLocation, capacity, currentCapacity, takeoff, landing, date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, flight.getFid());
            stmt.setString(2, flight.getDestination());
            stmt.setString(3, flight.getDepartureLocation());
            stmt.setInt(4, flight.getCapacity());
            stmt.setInt(5, flight.getCurrentCapacity());
            stmt.setTimestamp(6, flight.getTakeoff());
            stmt.setTimestamp(7, flight.getLanding());
            stmt.setDate(8, flight.getDate());
            stmt.setString(9, flight.getStatus());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a flight by its ID
    public Flight getFlightById(int id) {
        String sql = "SELECT * FROM flights WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Set the flight ID
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Flight(
                        rs.getInt("number"), // Flight number
                        rs.getString("destination"), // Destination
                        rs.getString("departureLocation"), // Departure location
                        rs.getInt("capacity"), // Capacity
                        rs.getInt("currentCapacity"), // Current capacity
                        rs.getTimestamp("takeoff"), // Takeoff time
                        rs.getTimestamp("landing"), // Landing time
                        rs.getDate("date"), // Date
                        rs.getString("status") // Status
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no flight is found
    }

    // Retrieve all flights from the database
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                flights.add(new Flight(
                        rs.getInt("number"), // Flight number
                        rs.getString("destination"), // Destination
                        rs.getString("departureLocation"), // Departure location
                        rs.getInt("capacity"), // Capacity
                        rs.getInt("currentCapacity"), // Current capacity
                        rs.getTimestamp("takeoff"), // Takeoff time
                        rs.getTimestamp("landing"), // Landing time
                        rs.getDate("date"), // Date
                        rs.getString("status") // Status
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights; // Return the list of flights
    }

    // Update an existing flight
    public boolean updateFlight(Flight flight) {
        String sql = "UPDATE flights SET destination = ?, depatureLocation = ?, capacity = ?, currentCapacity = ?, " +
                "takeoff = ?, landing = ?, date = ?, status = ? WHERE number = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, flight.getDestination());
            stmt.setString(2, flight.getDepartureLocation());
            stmt.setInt(3, flight.getCapacity());
            stmt.setInt(4, flight.getCurrentCapacity());
            stmt.setTimestamp(5, flight.getTakeoff());
            stmt.setTimestamp(6, flight.getLanding());
            stmt.setDate(7, flight.getDate());
            stmt.setString(8, flight.getStatus());
            stmt.setInt(9, flight.getFid());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a flight by its ID
    public boolean deleteFlight(int id) {
        String sql = "DELETE FROM flights WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Set the flight ID

            int result = stmt.executeUpdate(); // Execute the delete statement
            return result > 0; // Return true if the delete was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }
}