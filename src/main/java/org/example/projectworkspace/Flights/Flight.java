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
    private Timestamp landing; // Landing time
    private Date date; // Date of the flight
    private String status; // Flight status (e.g., "On time", "Delayed", "Cancelled")

    // Constructor to initialize all fields
    public Flight(int number, String destination, String departureLocation, int capacity,
                  Timestamp takeoff, Timestamp landing, Date date, String status) {
        this.number = number;
        this.destination = destination;
        this.departureLocation = departureLocation;
        this.capacity = capacity;
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

    public Timestamp getLanding() {
        return landing;
    }

    public void setLanding(Timestamp landing) {
        this.landing = landing;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
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

}
