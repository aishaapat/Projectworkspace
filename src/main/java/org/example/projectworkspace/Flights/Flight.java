package org.example.projectworkspace.Flights;

import Database.Privateconnection;

import java.sql.*;
import java.util.Date;

public class Flight {

    final private int  number;
    private String destination; // Destination city
    private String departureLocation; // Departure city or airport
    private int capacity; // Total seating capacity
    private int currentCapacity; // Seats left on the flight
    private Timestamp takeoff; // Takeoff time
    private static Timestamp landing; // Landing time
    private static Date date; // Date of the flight
    private static String status; // Flight status (e.g., "On time", "Delayed", "Cancelled")

    // Constructor to initialize all fields
    public Flight(int number, String destination, String departureLocation, int capacity, int currentCapacity, Timestamp takeoff, Timestamp landing, Date date, String status) {
        this.number = number;
        this.destination = destination;
        this.departureLocation = departureLocation;
        this.capacity = capacity;
        this.currentCapacity = currentCapacity;
        this.takeoff = takeoff;
        this.landing = landing;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters for all fields
    public int getNumber() {
        return number;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Timestamp getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(Timestamp takeoff) {
        this.takeoff = takeoff;
    }

    public static Timestamp getLanding() {
        return landing;
    }

    public void setLanding(Timestamp landing) {
        this.landing = landing;
    }

    public static Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method to print the flight details
    @Override
    public String toString() {
        return "Flight [number=" + number + ", destination=" + destination + ", departureLocation=" + departureLocation +
                ", capacity=" + capacity + ", currentCapacity=" + currentCapacity + ", takeoff=" + takeoff +
                ", landing=" + landing + ", date=" + date + ", status=" + status + "]";
    }
    public void setCurrentCapacity(Flight flight, int num, int flightnumber) {
        String query = "UPDATE flights SET currentCapacity = ? WHERE number = ?";

        try (Connection connection = new Privateconnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Calculate the new capacity
            int setCapacity = flight.getCurrentCapacity() + num;

            preparedStatement.setInt(1, setCapacity); // New capacity
            preparedStatement.setInt(2, flightnumber);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Flight capacity updated successfully.");
            } else {
                System.out.println("Flight was not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the flight capacity.", e);
        }
    }


}
