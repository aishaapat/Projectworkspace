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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.projectworkspace.Flights.Flight;
import org.example.projectworkspace.UserState.LoggedIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManageFlightsScreen extends Application implements EventHandler<ActionEvent>
{
    private LoggedIn login;

    //creating a constructor to pass over the login
    AdminManageFlightsScreen(LoggedIn login){
        this.login = login;
    }

    Stage stage;
    BackButton backbutton=new BackButton();
    Button AddButton= new Button("Add Flight");
    DeleteButton DeleteButton = new DeleteButton();

    //adding a observable list for the flights creating it public so that Add flights can acess it
    public ObservableList<Flight> allFlights = FXCollections.observableArrayList();
    TableView<Flight> tableView = new TableView<>();


    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        Label title = new Label("Admin Manage Flights");

        //adding table componenets

        //add method here for parsing flights into table from query
        displayFlights();
        tableView.setItems(allFlights);
        // using same cell value factors as managebookings screen
        TableColumn<Flight, String> fromCityCol = new TableColumn<>("From City");
        fromCityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartureLocation()));

        TableColumn<Flight, String> toCityCol = new TableColumn<>("To City");
        toCityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

        TableColumn<Flight, String> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacity())));

        TableColumn<Flight, String> currentcapacityCol = new TableColumn<>("Current Capacity");
        currentcapacityCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCurrentCapacity())));

        TableColumn<Flight, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));

        TableColumn<Flight, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTakeoff().toString()));

        TableColumn<Flight, String> landingCol = new TableColumn<>("Landing Time");
        landingCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLanding().toString()));

        TableColumn<Flight, String> flightstatus = new TableColumn<>("Status");
        flightstatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        tableView.getColumns().addAll( fromCityCol, toCityCol,capacityCol,currentcapacityCol ,dateCol, timeCol,landingCol,flightstatus);

        root.add(backbutton, 5,0);
        AddButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        root.add(AddButton, 1, 2, 2, 1);
        root.add(DeleteButton, 2, 2, 2, 1);
        root.add(title, 0, 0, 2, 1);
        root.add(tableView, 0, 1, 2, 1);
        backbutton.setOnAction(this);
        AddButton.setOnAction(this);
        DeleteButton.setOnAction(this);
        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Admin manage flights");
        stage.setScene(scene);



        stage.show();


    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        if(actionEvent.getSource()==backbutton){
            AdminEditScreen adminedit=new AdminEditScreen(login);
            try {
                adminedit.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();

        }
        else if(actionEvent.getSource()==AddButton){
            //I will create a subpane thay wil allow the user to add flights, I will not close this current stgae so it will be more of a
            // pop up window
            AddFlightsScreen addflights=new AddFlightsScreen(login);
            try {
                addflights.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(actionEvent.getSource()==DeleteButton){
            // once again mimicking the same logic from manage bookings
            Flight selectedFlight = tableView.getSelectionModel().getSelectedItem();


            if(selectedFlight==null){
                //show error
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No Flight Selected");
                alert.setContentText("Please select a flight to delete.");
                alert.showAndWait();
            }
            else{
                //I realized I need to delete it from bookings first
                String bookingquery="DELETE FROM bookings WHERE fid=?";
                int flightnum=selectedFlight.getNumber();
                Privateconnection db=new Privateconnection();
                try(Connection connection =db.getConnection();
                    PreparedStatement stmt=connection.prepareStatement(bookingquery)){
                    stmt.setInt(1, flightnum);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                //set logic and query for deleting the flight

                //assign the query here
                String query="DELETE FROM Flights WHERE number=?";
                //set connection

                try(Connection connection =db.getConnection();
                    PreparedStatement stmt=connection.prepareStatement(query)){
                    stmt.setInt(1, flightnum);

                    int rows=stmt.executeUpdate();

                    if(rows>0){
                        // Show a confirmation alert
                        allFlights.remove(selectedFlight);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Flight Deleted");
                        alert.setHeaderText("Your flight has been deleted.");
                        alert.setContentText("You have successfully deleted the flight.");
                        alert.showAndWait();
                    }
                    else{

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Flight Deletion Failed");
                        alert.setHeaderText(null);
                        alert.setContentText("Something went wrong.");
                        alert.showAndWait();
                    }
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
    //create method to show all the flights
    public void displayFlights(){
        String query="SELECT * FROM flights";
        Privateconnection db=new Privateconnection();
        try(Connection connection=db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);)
        {
            ResultSet rs=preparedStatement.executeQuery();
            // mimicking the same logic from managebookingscreen by creating new flight object and then adding it
            while(rs.next()){
                Flight flight=new Flight(rs.getInt("number"),
                        rs.getString("destination"),
                        rs.getString("departureLocation"),
                        rs.getInt("capacity"),
                        rs.getInt("currentCapacity"),
                        rs.getTimestamp("takeoff"),
                        rs.getTimestamp("landing"),
                        rs.getDate("date"),
                        rs.getString("status"));
                flight.toString();
                allFlights.add(flight);


        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
