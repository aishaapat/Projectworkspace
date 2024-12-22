package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableRow;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageBookingsScreen extends Application implements EventHandler<ActionEvent> {

    Stage stage;
    String loggedInUsername = "username"; // Replace with actual username from login
    // had to move this button again for event handler
    Button searchButton = new Button("Search Flights");


    // Sample data for booked flights (you can replace this with real database queries)
    ObservableList<Flight> bookedFlights = FXCollections.observableArrayList(
            new Flight("101", "New York", "London", "2024-12-25", "18:00"),
            new Flight("102", "Los Angeles", "Tokyo", "2024-12-26", "09:00"),
            new Flight("103", "Chicago", "Paris", "2024-12-27", "14:00")
    );

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Create the layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        Label title = new Label("Your Booked Flights");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Table to display booked flights
        TableView<Flight> tableView = new TableView<>();
        tableView.setItems(bookedFlights);

        // Define columns for flight details
        TableColumn<Flight, String> flightIdCol = new TableColumn<>("Flight ID");
        flightIdCol.setCellValueFactory(new PropertyValueFactory<>("flightId"));

        TableColumn<Flight, String> fromCityCol = new TableColumn<>("From City");
        fromCityCol.setCellValueFactory(new PropertyValueFactory<>("fromCity"));

        TableColumn<Flight, String> toCityCol = new TableColumn<>("To City");
        toCityCol.setCellValueFactory(new PropertyValueFactory<>("toCity"));

        TableColumn<Flight, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Flight, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        tableView.getColumns().addAll(flightIdCol, fromCityCol, toCityCol, dateCol, timeCol);

        // Delete button action
        Button deleteButton = new Button("Delete Flight");
        deleteButton.setOnAction(e -> handleDeleteFlight(tableView));

        searchButton.setOnAction(this);

        // Back to Main Menu Button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> {
            // Navigate to Main Menu screen (you can replace this with actual navigation logic)
            mainMenu mainMenuScreen = new mainMenu();
            mainMenuScreen.start(new Stage());
            stage.close(); // Close the current screen (ManageBookingsScreen)
        });

        // Add components to layout
        root.add(title, 0, 0, 2, 1);
        root.add(tableView, 0, 1, 2, 1);
        root.add(deleteButton, 0, 2);
        root.add(searchButton, 1, 2);
        root.add(backButton, 0, 3, 2, 1);  // Position the back button

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Manage Your Bookings");
        stage.setScene(scene);
        stage.show();
    }

    // Handle deleting a flight from the table (user's booking)
    private void handleDeleteFlight(TableView<Flight> tableView) {
        Flight selectedFlight = tableView.getSelectionModel().getSelectedItem();

        if (selectedFlight == null) {
            // Show an error alert if no flight is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Flight Selected");
            alert.setContentText("Please select a flight to delete.");
            alert.showAndWait();
        } else {
            // Here, you would remove the flight from the database and the list
            bookedFlights.remove(selectedFlight);
            // Show a confirmation alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Flight Deleted");
            alert.setHeaderText("Your flight has been deleted.");
            alert.setContentText("You have successfully deleted the flight.");
            alert.showAndWait();
        }
    }

    // Handle navigating to the search flights screen
    private void handleSearchFlights(ActionEvent event) {
        // You can navigate to the search screen (use a new stage or change scene)
        System.out.println("Navigating to Search Flights screen...");
        // Here you would switch to your search screen
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        // adding search flights pane
        if(actionEvent.getSource()==searchButton){
            SearchFlightsScreen searchFlightsScreen = new SearchFlightsScreen();
            searchFlightsScreen.start(new Stage());
            stage.close();
        }

    }
}

class Flight {
    private String flightId;
    private String fromCity;
    private String toCity;
    private String date;
    private String time;

    public Flight(String flightId, String fromCity, String toCity, String date, String time) {
        this.flightId = flightId;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
        this.time = time;
    }

    // Getters and Setters
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}