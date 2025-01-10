package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import org.example.projectworkspace.Flights.Flight;
import org.example.projectworkspace.UserState.LoggedIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class SearchFlightsScreen extends Application implements EventHandler<ActionEvent>
{
    //adding constructors for menus that need it
   private LoggedIn login;
   SearchFlightsScreen(LoggedIn login){
       this.login = login;
   }

    Label label1, label2, label3;
    TextField fromCityField, toCityField,takeOff;
    DatePicker flightDate;
    Button searchButton, bookButton;
    Stage stage;
    BackButton back = new BackButton();

    //adding a table to display all of the flights
    ObservableList<Flight> currentflights = FXCollections.observableArrayList();

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

        //adding a textfield for searching time
        takeOff = new TextField();
        takeOff.setPromptText("Enter take off time");

        //creating a new tableview to display the flights
        TableView<Flight> tableView = new TableView<>();
        //add methods for searching and adding flights after
        tableView.setItems(currentflights);


        // defining columns
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

        TableColumn<Flight, String> flightstatus = new TableColumn<>("Status");
        flightstatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));


        tableView.getColumns().addAll( fromCityCol, toCityCol,capacityCol, dateCol, timeCol,landingCol,flightstatus);



        // Search button action
        String fromcity=fromCityField.getText();
        String tocity=toCityField.getText();
        String takeoff=takeOff.getText();
        String flightDateValue = flightDate.getValue() != null ? flightDate.getValue().toString() : null;
        searchButton = new Button("Search Flights");
        searchButton.setOnAction(e->searchFlights(tableView,fromcity,tocity,takeoff,flightDateValue));


        // Book button action
        bookButton = new Button("Book Flight");

        bookButton.setOnAction(e -> addFlights(tableView,login.getUserID()));



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
        root.add(takeOff, 1, 4);
        root.add(searchButton, 0, 5, 2, 1);
        root.add(bookButton, 0, 6);
        root.add(tableView, 0, 7);

        root.add(back, 1, 7);

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Post Login - Flight Booking");
        stage.setScene(scene);
        stage.show();
    }






    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==back){
            ManageBookingsScreen manageBookingsScreen = new ManageBookingsScreen(login);
            manageBookingsScreen.start(new Stage());
            stage.close();

        }
    }
    private boolean checkforduplicates(TableView<Flight> tableView, int UserID) {
        Flight selectedFlight = tableView.getSelectionModel().getSelectedItem();
        int flightnum = selectedFlight.getNumber();
        boolean check = false;

        // Assigning the query
        String query = "SELECT uid FROM bookings WHERE uid= " + UserID + " AND fid="+ flightnum;

        // This is to check if the flight hasn't been booked twice
        Privateconnection db = new Privateconnection();
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();  // Corrected to executeQuery() instead of executeUpdate()

            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("You cannot add the same flight twice");
                alert.showAndWait();
            } else {
                check = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    private boolean checkfortime(TableView<Flight> tableView, int UserID) {
        Flight selectedFlight = tableView.getSelectionModel().getSelectedItem();
        int flightnum = selectedFlight.getNumber();
        boolean check = false;
        Date selectedDate = selectedFlight.getDate();

        // Inserting a query to check if there is a date conflict
        String query = "SELECT fid FROM bookings WHERE uid=" + UserID;
        Privateconnection db = new Privateconnection();
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            // Corrected to executeQuery() instead of executeUpdate()
            while (rs.next()) {
                int flightid=rs.getInt("fid");
                String flightdateQuery="SELECT date FROM flights WHERE number =?";
                try(PreparedStatement statement=connection.prepareStatement(flightdateQuery)){
                    statement.setInt(1,flightid);
                    ResultSet rs2 = statement.executeQuery();
                    if(rs2.next()){
                        Date flightDate=rs2.getDate("date");
                        if(flightDate.equals(selectedDate)){
                            check = false;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("You cannot add this flight due to a time issue");
                            alert.showAndWait();
                            break;
                        }
                        else check=true;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;
    }

    private void addFlights(TableView <Flight> tableView,int UserID){
        Flight selectedFlight=tableView.getSelectionModel().getSelectedItem();
        if(selectedFlight==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a flight");
            alert.showAndWait();
        }
        else if(checkforduplicates(tableView,login.getUserID())==true){
//            if(checkfortime(tableView,login.getUserID())==true){

                int flightnum=selectedFlight.getNumber();
                String query= "INSERT INTO bookings (fid,uid) VALUES ("+flightnum+","+UserID+")";
                //now we will add the flights to bookings
                Privateconnection db=new Privateconnection();
                try (Connection connection=db.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(query))
                {
                    int rows=preparedStatement.executeUpdate();
                    if(rows>0){
                        System.out.println("success");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText(null);
                        alert.setContentText("Flight added successfully");
                        alert.showAndWait();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Flight not added");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        //}
        }
    }
    private void searchFlights(TableView<Flight> tableView,String fromcity,  String tocity ,String takeOff,String date)
    {

        String query="";
        Privateconnection db = new Privateconnection();
        if(fromcity.isEmpty() || tocity.isEmpty() || takeOff.isEmpty() || date.isEmpty()) {
            query="SELECT  * FROM flights";
            try (Connection connection = db.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                ResultSet rs = preparedStatement.executeQuery();
                // clearing the table first
                currentflights.clear();
                while (rs.next()) {
                    Flight flight = new Flight(rs.getInt("number"),
                            rs.getString("destination"),
                            rs.getString("departureLocation"),
                            rs.getInt("capacity"),
                            rs.getTimestamp("takeoff"),
                            rs.getTimestamp("landing"),
                            rs.getDate("date"),
                            rs.getString("status"));
                    flight.toString();
                    currentflights.add(flight);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}