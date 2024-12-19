package org.example.projectworkspace.Reservations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private final Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new booking
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (id, uid, fid) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, booking.getId());
            stmt.setInt(2, booking.getUid());
            stmt.setInt(3, booking.getFid());

            int result = stmt.executeUpdate(); // Executes the query and returns the number of affected rows
            return result > 0;      // If rows were affected, return true, otherwise false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;   // In case of an error, return false
        }
    }

    // Get a booking by its ID
    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create and return a new Booking object
                return new Booking(
                        rs.getInt("id"),
                        rs.getInt("uid"),
                        rs.getInt("fid")
                );
            } else {
                // Return null if no booking is found
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // Return null in case of an error
        }
    }

    // Get all bookings from the database
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create and add a new Booking object for each row in the result set
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("uid"),
                        rs.getInt("fid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean updateBooking(int id, int uid, int fid) {
        // Update the booking for the given id, but don't change the id field
        String sql = "UPDATE bookings SET uid = ?, fid = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the values for the booking update
            stmt.setInt(1, uid);
            stmt.setInt(2, fid);
            stmt.setInt(3, id);  // Specify which booking to update based on the id

            // Execute the update and return whether any rows were affected
            int result = stmt.executeUpdate();
            return result > 0;  // Returns true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }

    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}