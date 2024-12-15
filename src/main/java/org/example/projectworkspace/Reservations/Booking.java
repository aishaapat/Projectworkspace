package org.example.projectworkspace.Reservations;

public class Booking {
    private int id; // The booking ID (primary key)
    private int uid; // The user ID (foreign key referencing the User table)
    private int fid; // The flight ID (foreign key referencing the Flights table)

    // Constructor that initializes the fields

    public Booking(int id, int uid, int fid) {
        this.id = id;
        this.uid = uid;
        this.fid = fid;
    }

    // Getters and setters for each field


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    // Method to print the details of the booking
    @Override
    public String toString() {
        return "Booking [id=" + id + ", userId=" + uid + ", flightId=" + fid + "]";
    }
}



