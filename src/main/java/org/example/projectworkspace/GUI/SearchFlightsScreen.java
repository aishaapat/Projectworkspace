package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import org.example.projectworkspace.Flights.Flight;
import org.example.projectworkspace.UserState.LoggedIn;

import java.sql.*;
import java.util.Date;

// Note application is the main class used for javaFX applications so the UI can work

public class SearchFlightsScreen extends Application {

    private LoggedIn login; // holds user login information

    // Tells the app whose login information to use for the screen
    public SearchFlightsScreen(LoggedIn login) {
        this.login = login;
    }

    // Declaring the ui components
    private Label label1, label2, label3;
    private TextField fromCityField, toCityField, takeOff;
    private DatePicker flightDate;
    private Button searchButton, bookButton;
    private TableView<Flight> tableView;
    private ObservableList<Flight> currentFlights = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) {
        // Creates the root layout with a grid pane
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        // Label and text fields for the search
        label1 = new Label("Search Flights");
        label1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        label2 = new Label("From City:");
        label3 = new Label("To City:");

        fromCityField = new TextField();
        fromCityField.setPromptText("Enter departure city");

        toCityField = new TextField();
        toCityField.setPromptText("Enter destination city");

        flightDate = new DatePicker();
        flightDate.setPromptText("Select flight date");

        takeOff = new TextField();
        takeOff.setPromptText("Enter take-off time");

        tableView = createTableView(); // Creates a table to display flight information

        // Creating buttons for searching and booking flights as well as one to go back to the previous screen
        searchButton = new Button("Search Flights");
        searchButton.setOnAction(e -> searchFlights());

        bookButton = new Button("Book Flight");
        bookButton.setOnAction(e -> addFlights());

        // Add back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> goToManageBookingsScreen(stage));

        // Adds the components created to the UI
        root.add(label1, 0, 0, 2, 1);
        root.add(label2, 0, 1);
        root.add(fromCityField, 1, 1);
        root.add(label3, 0, 2);
        root.add(toCityField, 1, 2);
        root.add(new Label("Flight Date:"), 0, 3);
        root.add(flightDate, 1, 3);
        root.add(new Label("Flight Time:"), 0, 4);
        root.add(takeOff, 1, 4);
        root.add(searchButton, 0, 5);
        root.add(bookButton, 1, 5);
        root.add(backButton, 0, 6); // Add back button
        root.add(tableView, 0, 7, 2, 1);

        // Creates the scene and shows the stage
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Flight Booking System");
        stage.setScene(scene);
        stage.show();
    }

    // Method to assign to the back button event handler that goes back to the previous screen
    private void goToManageBookingsScreen(Stage stage) {
        ManageBookingsScreen manageBookingsScreen = new ManageBookingsScreen(login);
        try {
            manageBookingsScreen.start(stage); // Navigate to the ManageBookingsScreen
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to navigate to Manage Bookings Screen.");
        }
    }


    private TableView<Flight> createTableView() {
        TableView<Flight> tableView = new TableView<>();

        TableColumn<Flight, String> fromCityCol = new TableColumn<>("From City");
        fromCityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartureLocation()));

        TableColumn<Flight, String> toCityCol = new TableColumn<>("To City");
        toCityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

        TableColumn<Flight, String> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacity())));

        TableColumn<Flight, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));

        TableColumn<Flight, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTakeoff().toString()));

        TableColumn<Flight, String> landingCol = new TableColumn<>("Landing Time");
        landingCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLanding().toString()));

        TableColumn<Flight, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        tableView.getColumns().addAll(fromCityCol, toCityCol, capacityCol, dateCol, timeCol, landingCol, statusCol);
        tableView.setItems(currentFlights);

        return tableView;
    }

    private void searchFlights() {
        currentFlights.clear();

        String fromCity = fromCityField.getText();
        String toCity = toCityField.getText();
        String takeOffTime = takeOff.getText();
        String flightDateValue = (flightDate.getValue() != null) ? flightDate.getValue().toString() : null;

        // Build the base query string
        StringBuilder query = new StringBuilder("SELECT * FROM flights WHERE 1=1");

        // Append conditions for each search criteria
        if (fromCity != null && !fromCity.isEmpty()) {
            query.append(" AND departureLocation LIKE ?");
        }
        if (toCity != null && !toCity.isEmpty()) {
            query.append(" AND destination LIKE ?");
        }
        if (flightDateValue != null && !flightDateValue.isEmpty()) {
            query.append(" AND DATE(takeoff) = ?");
        }
        if (takeOffTime != null && !takeOffTime.isEmpty()) {
            query.append(" AND takeoff LIKE ?");
        }

        try (Connection connection = new Privateconnection().getConnection();
             PreparedStatement preparedStatement = buildSearchQuery(query, connection, fromCity, toCity, takeOffTime, flightDateValue)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Create the flight object with 8 required arguments
                Flight flight = new Flight(
                        rs.getInt("number"),
                        rs.getString("destination"),
                        rs.getString("departureLocation"),
                        rs.getInt("capacity"),
                        rs.getInt("currentCapacity"),
                        rs.getTimestamp("takeoff"),
                        rs.getTimestamp("landing"),
                        rs.getDate("date"),
                        rs.getString("status")
                );
                currentFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch flights.");
        }
    }

    private PreparedStatement buildSearchQuery(StringBuilder query, Connection connection, String fromCity, String toCity, String takeOffTime, String flightDateValue) throws SQLException {
        System.out.println("Generated Query: " + query.toString()); // Debug print to check the query structure

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
        int index = 1;

        if (fromCity != null && !fromCity.isEmpty()) {
            preparedStatement.setString(index++, "%" + fromCity + "%");
        }
        if (toCity != null && !toCity.isEmpty()) {
            preparedStatement.setString(index++, "%" + toCity + "%");
        }
        if (takeOffTime != null && !takeOffTime.isEmpty()) {
            System.out.println("Setting takeOffTime: " + takeOffTime); // Debug print to check takeOffTime
            preparedStatement.setString(index++, "%" + takeOffTime + "%");
        }
        if (flightDateValue != null && !flightDateValue.isEmpty()) {
            System.out.println("Setting flightDateValue: " + flightDateValue); // Debug print to check flightDateValue
            preparedStatement.setString(index, flightDateValue);  // Assuming flightDateValue is formatted correctly
        }

        return preparedStatement;
    }



    private boolean checkForDuplicateBookings(Flight selectedFlight) {
        String query = "SELECT COUNT(*) FROM bookings WHERE fid = ? AND uid = ?";
        try (Connection connection = new Privateconnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, selectedFlight.getNumber());
            preparedStatement.setInt(2, login.getUserID());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                showAlert(Alert.AlertType.WARNING, "Duplicate Booking", "You have already booked this flight.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to check for duplicate bookings.");
        }
        return true;
    }

    private boolean checkForTimeConflicts(Flight selectedFlight) {
        String query = "SELECT COUNT(*) FROM bookings b " +
                "JOIN flights f ON b.fid = f.number " +
                "WHERE b.uid = ? AND (f.takeoff = ? OR f.landing = ?)";
        try (Connection connection = new Privateconnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, login.getUserID());

            // Use java.sql.Timestamp for takeoff and landing
            preparedStatement.setTimestamp(2, new Timestamp(selectedFlight.getTakeoff().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(selectedFlight.getLanding().getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                showAlert(Alert.AlertType.WARNING, "Time Conflict", "You already have a booking during this time.");
                return false; // There is a conflict
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to check for time conflicts.");
        }
        return true; // No conflict found
    }



    private void addFlights() {
        Flight selectedFlight = tableView.getSelectionModel().getSelectedItem();
        if (selectedFlight == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a flight to book.");
            return;
        }

        // Check for time conflicts first
        if (!checkForTimeConflicts(selectedFlight)) {
            return; // If there's a conflict, stop the booking process
        }

        String query = "INSERT INTO bookings (fid, uid) VALUES (?, ?)";

        try (Connection connection = new Privateconnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, selectedFlight.getNumber());
            preparedStatement.setInt(2, login.getUserID());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Flight booked successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to book the flight.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while booking the flight.");
        }
    }



    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}