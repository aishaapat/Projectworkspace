package org.example.projectworkspace.Flights;

import java.sql.Timestamp;
import java.util.Date;

public class Flight {

    private int fid; // Flight ID
    private String destination; // Destination city
    private String departureLocation; // Departure city or airport
    private int capacity; // Total seating capacity
    private int currentCapacity; // Seats left on the flight
    private Date takeoff; // Takeoff time
    private Date landing; // Landing time
    private Date date; // Date of the flight
    private String status; // Flight status (e.g., "On time", "Delayed", "Cancelled")

    // Constructor to initialize all fields
    public Flight(int fid, String destination, String departureLocation, int capacity, int currentCapacity,
                  Date takeoff, Date landing, Date date, String status) {
        this.fid = fid;
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

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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
        return (Timestamp) takeoff;
    }

    public void setTakeoff(Date takeoff) {
        this.takeoff = takeoff;
    }

    public Timestamp getLanding() {
        return (Timestamp) landing;
    }

    public void setLanding(Date landing) {
        this.landing = landing;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
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
        return "Flight [fid=" + fid + ", destination=" + destination + ", departureLocation=" + departureLocation +
                ", capacity=" + capacity + ", currentCapacity=" + currentCapacity + ", takeoff=" + takeoff +
                ", landing=" + landing + ", date=" + date + ", status=" + status + "]";
    }
}