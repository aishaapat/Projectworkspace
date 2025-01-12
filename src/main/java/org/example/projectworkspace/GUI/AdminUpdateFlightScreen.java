package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.projectworkspace.Flights.Flight;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class AdminUpdateFlightScreen extends Application {

    private Flight flight; // passes selected flight details

    // Passes the selected flight object to the screen
    public AdminUpdateFlightScreen(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void start(Stage stage) {
        // Layout of the screen
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        // Title
        Label title = new Label("Update Flight");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        root.add(title, 0, 0, 2, 1);

        // Input fields based on which flight is being passed
        TextField fromCityField = new TextField(flight.getDepartureLocation());
        TextField toCityField = new TextField(flight.getDestination());
        TextField capacityField = new TextField(String.valueOf(flight.getCapacity()));

// Convert flight date to LocalDate for DatePicker
        Date sqlDate = (Date) flight.getDate();  // Assuming flight.getDate() returns a java.sql.Date
        LocalDate localDate = sqlDate.toLocalDate();  // Convert to LocalDate directly

// DatePicker now accepts LocalDate
        DatePicker dateField = new DatePicker(localDate);


        TextField timeField = new TextField(flight.getTakeoff().toString());
        TextField landingField = new TextField(flight.getLanding().toString());
        TextField statusField = new TextField(flight.getStatus());

        // Adding input fields and label to the grid
        root.add(new Label("From City:"), 0, 1);
        root.add(fromCityField, 1, 1);
        root.add(new Label("To City:"), 0, 2);
        root.add(toCityField, 1, 2);
        root.add(new Label("Capacity:"), 0, 3);
        root.add(capacityField, 1, 3);
        root.add(new Label("Date:"), 0, 4);
        root.add(dateField, 1, 4);
        root.add(new Label("Time:"), 0, 5);
        root.add(timeField, 1, 5);
        root.add(new Label("Landing Time"), 0, 6);
        root.add(landingField, 1, 6);
        root.add(new Label("Status"), 0, 7);
        root.add(statusField, 1, 7);

        // Adding update button to confirm updated fields
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px");
        root.add(updateButton, 1, 8);

        // Logic for updateButton event
        updateButton.setOnAction(e -> {
            // SQL query to update flight details
            String stmt = "UPDATE flights SET departureLocation = ?, destination = ?, capacity = ?, " +
                    "date = ?, takeoff = ?, landing = ?, status = ? WHERE number = ?";

            try (Connection connection = new Privateconnection().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(stmt)) {

                // Parameters for SQL query
                preparedStatement.setString(1, fromCityField.getText());
                preparedStatement.setString(2, toCityField.getText());
                preparedStatement.setInt(3, Integer.parseInt(capacityField.getText()));
                preparedStatement.setDate(4, Date.valueOf(dateField.getValue()));  // Convert LocalDate to java.sql.Date

                // Handle timeField and landingField for Timestamp conversion
                preparedStatement.setTimestamp(5, Timestamp.valueOf(timeField.getText())); // Ensure the correct format (yyyy-MM-dd HH:mm:ss)
                preparedStatement.setTimestamp(6, Timestamp.valueOf(landingField.getText())); // Ensure the correct format

                preparedStatement.setString(7, statusField.getText());
                preparedStatement.setInt(8, flight.getNumber());

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight updated successfully");
                } else {
                    System.out.println("Flight was not updated");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                System.out.println("Error in timestamp format: " + ex.getMessage());
            }
        });

        // Set the scene to show stage
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Admin - Update Flight");
        stage.show();
    }

    // Main method to launch app
    public static void main(String[] args) {
        launch(args);
    }
}
