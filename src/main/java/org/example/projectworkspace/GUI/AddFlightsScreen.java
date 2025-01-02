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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFlightsScreen extends Application implements EventHandler<ActionEvent>
{
    Stage stage;
    EnterButton enterButton= new EnterButton();
    TextField  fromCityField, toCityField,capacity,flightnumber,takeoff,landing;
    DatePicker flightDate;
    Label label1,label2,label3,label4,label5,label6,label7;



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
        Scene scene = new Scene(root, 500, 450);
        root.add(title, 0, 0);
        root.add(fromCityField, 1, 1);
        root.add(toCityField, 1, 2);
        root.add(flightDate, 1, 3);

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
        // adding takeoff and landing time textinputs
        label6= new Label("Takeoff:");
        takeoff = new TextField();
        root.add(label6,0,7);
        root.add(takeoff,1,7);
        label7= new Label("Landing:");
        landing = new TextField();
        root.add(label7,0,8);
        root.add(landing,1,8);
        //add enter button
        root.add(enterButton,1,9);
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
        int capacityValue= Integer.parseInt(capacity.getText());
        String flightnumberValue=flightnumber.getText();
        String takeoffValue=takeoff.getText();
        String landingValue=landing.getText();

        if(actionEvent.getSource() == enterButton) {
            if (fromCity.isEmpty() | toCity.isEmpty() | flightDateValue.isEmpty()| capacityValue<0 | flightnumberValue.isEmpty() | takeoffValue.isEmpty() | landingValue.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the missing information");
                alert.showAndWait();
            }
            else{

                    String insertQuery = "INSERT INTO Flights (destination,departureLocation,capacity,takeoff,landing,date) VALUES (?, ?, ?, ?, ?, ?)";
                    Privateconnection db= new Privateconnection();

                    try (Connection connection=db.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                        // Set the parameters for the SQL query
                        preparedStatement.setString(1, toCity);
                        preparedStatement.setString(2, fromCity);
                        preparedStatement.setInt(3, capacityValue);
                        preparedStatement.setString(4, takeoffValue);
                        preparedStatement.setString(5, landingValue);
                        preparedStatement.setString(6, flightDateValue);
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Flight added successfully!");
                            alert.showAndWait();
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to add flight. Please try again.");
                            alert.showAndWait();
                        }

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
            }


        }


    }
}
