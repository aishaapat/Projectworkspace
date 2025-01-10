package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import org.example.projectworkspace.Flights.Flight;
import org.example.projectworkspace.UserState.LoggedIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageBookingsScreen extends Application implements EventHandler<ActionEvent> {
    private LoggedIn login;
    ManageBookingsScreen(LoggedIn login) {
        this.login = login;
    }


    Stage stage;
  // going to replace with a loggedin class instead
    Button searchButton = new Button("Search Flights");


    // Sample data for booked flights (you can replace this with real database queries)
    ObservableList<Flight> bookedFlights = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Create the layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        Label title = new Label("Your Booked Flights "+login.getFirstName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Table to display booked flights
        TableView<Flight> tableView = new TableView<>();
        userBookings(login.getUserID());
        tableView.setItems(bookedFlights);

        // Define columns for flight details


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



        // Delete button action
        Button deleteButton = new Button("Delete Flight");
        deleteButton.setOnAction(e -> handleDeleteFlight(tableView));

        searchButton.setOnAction(this);

        // Back to Main Menu Button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e ->
        {
            if("user".equals(login.getType()) || login.getType() == null) {
                mainMenu main = new mainMenu(login);
                main.start(new Stage());

                stage.close();
            }
            else if("admin".equals(login.getType())){

                AdminMainMenu adminMainMenu = new AdminMainMenu(login);
                adminMainMenu.start(new Stage());
                stage.close();
            }
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

        //assign the query here
        String query="DELETE FROM bookings WHERE fid=? and uid=?";

        if (selectedFlight == null) {
            // Show an error alert if no flight is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Flight Selected");
            alert.setContentText("Please select a flight to delete.");
            alert.showAndWait();
        } else {
            //get login id for the delete query
            int UserID = login.getUserID();
            //get flight number
            int flightID= selectedFlight.getNumber();



            //I am adding the database stuff  now
            Privateconnection db=new Privateconnection();
            try(Connection connection =db.getConnection();
            PreparedStatement stmt=connection.prepareStatement(query)){
                stmt.setInt(1, flightID);
                stmt.setInt(2, UserID);

                int rows= stmt.executeUpdate();
                int getcapacity=selectedFlight.getCurrentCapacity();

                if(rows>0){
                    selectedFlight.setCurrentCapacity(getcapacity+1);

                    // Show a confirmation alert
                    bookedFlights.remove(selectedFlight);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Flight Deleted");
                    alert.setHeaderText("Your flight has been deleted.");
                    alert.setContentText("You have successfully deleted the flight.");
                    alert.showAndWait();
                }
                else{

                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Flight Deletion Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Something went wrong.");
                    alert.showAndWait();
                }


            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }

        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        // adding search flights pane
        if(actionEvent.getSource()==searchButton){
            SearchFlightsScreen searchFlightsScreen = new SearchFlightsScreen(login);
            searchFlightsScreen.start(new Stage());
            stage.close();
        }

    }
    private void userBookings(int userID) {

        String query="SELECT f.number ,f.destination,f.departureLocation,f.capacity,f.currentCapacity,f.takeoff,f.landing,f.date,f.status FROM bookings b " +
                "INNER JOIN flights f on b.fid=f.number WHERE b.uid=?";
        Privateconnection db=new Privateconnection();
        try (Connection connection=db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);)
        {
            preparedStatement.setInt(1,userID);
            ResultSet rs=preparedStatement.executeQuery();
            // clearing the table first
            bookedFlights.clear();

            while(rs.next()){
                Flight flight = new Flight(rs.getInt("number"),
                        rs.getString("destination"),
                        rs.getString("departureLocation"),
                        rs.getInt("capacity"),
                        rs.getInt("currentCapacity"),
                        rs.getTimestamp("takeoff"),
                        rs.getTimestamp("landing"),
                        rs.getDate("date"),
                        rs.getString("status"));
                flight.toString();
                bookedFlights.add(flight);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}








