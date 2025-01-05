package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.projectworkspace.UserState.LoggedIn;

public class AdminManageFlightsScreen extends Application implements EventHandler<ActionEvent>
{
    LoggedIn login;
    AdminManageFlightsScreen(LoggedIn login){
        this.login = login;
    }
    Stage stage;
    TableView<Flight> tableView = new TableView<>();
    //adding all the buttons for this pane
    BackButton backbutton=new BackButton();
    Button AddButton= new Button("Add Flight");
    DeleteButton DeleteButton = new DeleteButton();



    // Sample data for booked flights (you can replace this with real database queries)
    ObservableList<Flight> bookedFlights = FXCollections.observableArrayList(
            new Flight("101", "New York", "London", "2024-12-25", "18:00"),
            new Flight("102", "Los Angeles", "Tokyo", "2024-12-26", "09:00"),
            new Flight("103", "Chicago", "Paris", "2024-12-27", "14:00")

    );
    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        Label title = new Label("Admin Manage Flights");

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
        Scene scene=new Scene(root,800,600);
        stage.setScene(scene);
        root.add(title, 0, 0, 2, 1);
        //adding aesthetics
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");
        root.add(tableView, 0, 1, 2, 1);
        root.add(backbutton, 5,0);
        AddButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        root.add(AddButton, 1, 2, 2, 1);
        root.add(DeleteButton, 2, 2, 2, 1);
        backbutton.setOnAction(this);
        AddButton.setOnAction(this);
        DeleteButton.setOnAction(this);



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
            AddFlightsScreen addflights=new AddFlightsScreen();
            try {
                addflights.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(actionEvent.getSource()==DeleteButton){
            //add logic that allows the user to delete selected flight
        }

    }
}
