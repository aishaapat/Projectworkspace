package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFlightsScreen extends Application implements EventHandler<ActionEvent>
{
    Stage stage;
    EnterButton enterButton= new EnterButton();
    TextField  fromCityField, toCityField,capacity,flightnumber;
    DatePicker flightDate;
    Label label1,label2,label3,label4,label5;


    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;

        // Create the layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        root.setHgap(10);
        Text title = new Text(" Add Flights");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        fromCityField = new TextField();
        toCityField = new TextField();
        flightDate = new DatePicker();
        label1 = new Label("From:");
        label2 = new Label("To:");
        label3 = new Label("Date:");
        Scene scene = new Scene(root, 400, 400);
        root.add(title, 0, 0);
        root.add(fromCityField, 1, 1);
        root.add(toCityField, 1, 2);
        root.add(flightDate, 1, 3);
        root.add(enterButton, 1, 7);
        root.add(label1,0,1);
        root.add(label2,0,2);
        root.add(label3,0,3);
        //adding capacity and flight nuber
        label4= new Label("Capacity:");
        capacity = new TextField();
        root.add(label4,0,5);
        root.add(capacity,1,5);
        label5= new Label("Flight number:");
        flightnumber = new TextField();
        root.add(label5,0,6);
        root.add(flightnumber,1,6);
        root.setAlignment(Pos.CENTER);
        stage.setResizable(false);
        stage.setTitle("Admin Only- Add Flights");
        enterButton.setOnAction(this);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        String fromCity = fromCityField.getText();
        String toCity = toCityField.getText();
        String flightDateValue = flightDate.getValue() != null ? flightDate.getValue().toString() : null;
        if(actionEvent.getSource() == enterButton) {
            if (fromCity.isEmpty() | toCity.isEmpty() | flightDateValue.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the missing information");
            }

        }


    }
}
