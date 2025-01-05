package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import org.example.projectworkspace.UserState.LoggedIn;

public class SearchFlightsScreen extends Application implements EventHandler<ActionEvent>
{
    //adding constructors for menus that need it
    LoggedIn login;

    Label label1, label2, label3;
    TextField fromCityField, toCityField;
    DatePicker flightDate;
    ComboBox<String> flightTimes;
    Button searchButton, bookButton, deleteButton;
    Stage stage;
    String loggedInUsername = "username"; // Replace with actual username from login
    // had to move some buttons above the start method so that the event handler can get it
    BackButton back = new BackButton();

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Set up the layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        label1 = new Label("Search Flights");
        label1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        label2 = new Label("From City:");
        label3 = new Label("To City:");

        // Input fields for search criteria
        fromCityField = new TextField();
        fromCityField.setPromptText("Enter departure city");

        toCityField = new TextField();
        toCityField.setPromptText("Enter destination city");

        flightDate = new DatePicker();
        flightDate.setPromptText("Select flight date");

        flightTimes = new ComboBox<>();
        flightTimes.getItems().addAll("Morning", "Afternoon", "Evening");

        // Search button action
        searchButton = new Button("Search Flights");
        searchButton.setOnAction(this::handleSearchFlights);

        // Book button action
        bookButton = new Button("Book Flight");
        bookButton.setOnAction(this::handleBookFlight);

        // Delete button action
        deleteButton = new Button("Delete Flight");
        deleteButton.setOnAction(this::handleDeleteFlight);


        back.setOnAction(this);


        // Add components to grid
        root.add(label1, 0, 0, 2, 1);
        root.add(label2, 0, 1);
        root.add(fromCityField, 1, 1);
        root.add(label3, 0, 2);
        root.add(toCityField, 1, 2);
        root.add(new Label("Flight Date:"), 0, 3);
        root.add(flightDate, 1, 3);
        root.add(new Label("Flight Time:"), 0, 4);
        root.add(flightTimes, 1, 4);
        root.add(searchButton, 0, 5, 2, 1);
        root.add(bookButton, 0, 6);
        root.add(deleteButton, 1, 6);
        root.add(back, 1, 7);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Post Login - Flight Booking");
        stage.setScene(scene);
        stage.show();
    }

    // Handle flight search
    private void handleSearchFlights(ActionEvent event) {
        // Get the user input
        String fromCity = fromCityField.getText();
        String toCity = toCityField.getText();
        String date = flightDate.getValue().toString();
        String time = flightTimes.getValue();

        // Perform search based on the criteria
        // Query the database here to get matching flights
        System.out.println("Searching flights from " + fromCity + " to " + toCity + " on " + date + " in the " + time);

        // Implement the database logic for searching flights (SQL queries)
    }

    // Handle flight booking
    private void handleBookFlight(ActionEvent event) {
        // Ensure user is not booking the same flight again
        // Check if flight is full, check for conflicting times with current bookings

        System.out.println("Booking flight...");
        // Perform booking logic, ensure no conflicts and the flight is not already booked
    }

    // Handle flight deletion
    private void handleDeleteFlight(ActionEvent event) {
        // Delete the flight from user's account
        System.out.println("Deleting flight...");
        // Implement logic for deleting a flight from the user's account
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==back){
            ManageBookingsScreen manageBookingsScreen = new ManageBookingsScreen();
            manageBookingsScreen.start(new Stage());
            stage.close();

        }
    }
}