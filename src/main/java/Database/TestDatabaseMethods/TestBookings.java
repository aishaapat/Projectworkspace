package Database.TestDatabaseMethods;

import org.example.projectworkspace.Reservations.Booking;
import org.example.projectworkspace.Reservations.BookingDAO;
import Database.DatabaseManager;

import java.sql.Connection;

// All statements work

public class TestBookings {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.connect();

        BookingDAO bookingData = new BookingDAO(connection);

        System.out.println(bookingData.getBookingById(1));

            // bookingData.updateBooking(1, 13, 8);

        Booking testBooking = new Booking(2, 13, 15);

        boolean isBookingCreated = bookingData.createBooking(testBooking);

        // bookingData.deleteBooking(2);


        /*

        Booking newBooking = new Booking(1, 13, 1);

        boolean isBookingCreated = bookingData.createBooking(newBooking);

        if (isBookingCreated) {
            System.out.println("Booking created successfully!");
        } else {
            System.out.println("Failed to create booking.");
        }

        */

    }
}