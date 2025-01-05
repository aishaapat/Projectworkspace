package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterAdmin extends Application implements EventHandler<ActionEvent>
{
    Label title;
    TextField First, Last, address, zip, state, username, password, email, ssn, answer;
    ComboBox<String> security;
    RegisterButton register;
    Stage stage;
    //need to add a submit button
    Button submit = new Button("Submit");
//I am going to mimic the register screen for users

    @Override
    public void start(Stage stage) throws Exception
    {
        GridPane root = new GridPane();
        this.stage = stage;

        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        // Header Label (Title)
        title = new Label("Register an Admin!");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");
        root.add(title, 0, 0, 2, 1);
        //setting style for button
        submit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Initialize fields
        First = new TextField();
        Last = new TextField();
        address = new TextField();
        zip = new TextField();
        state = new TextField();
        ssn = new TextField();
        email = new TextField();
        username = new TextField();
        password = new TextField();
        answer = new TextField();
        security = new ComboBox<>();
        security.getItems().addAll(
                "What is your favorite color?",
                "What is your birthday?",
                "What is the name of the city you were born?"
        );
        // once again going to mimic the register screen so that we can make sure all users still have the same data

        // Add input fields and labels
        root.add(new Label("Enter First Name"), 0, 1);
        root.add(First, 1, 1);
        root.add(new Label("Enter Last Name"), 0, 2);
        root.add(Last, 1, 2);
        root.add(new Label("Enter Address"), 0, 3);
        root.add(address, 1, 3);
        root.add(new Label("Enter Zip Code"), 0, 4);
        root.add(zip, 1, 4);
        root.add(new Label("Enter State"), 0, 5);
        root.add(state, 1, 5);
        root.add(new Label("Enter SSN"), 0, 6);
        root.add(ssn, 1, 6);
        root.add(new Label("Enter Email"), 0, 7);
        root.add(email, 1, 7);
        root.add(new Label("Enter Username"), 0, 8);
        root.add(username, 1, 8);
        root.add(new Label("Enter Password"), 0, 9);
        root.add(password, 1, 9);
        root.add(new Label("Pick your Security Question"), 0, 10);
        root.add(security, 1, 10);
        root.add(new Label("Enter Answer"), 0, 11);
        root.add(answer, 1, 11);
        root.add(submit,1,12);

        //setting submit button action now
        submit.setOnAction(this);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Add Admins!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == submit){
            //parse all the textfields
            String firstName = First.getText();
            String lastName = Last.getText();
            String getaddress = address.getText();
            String getzip = zip.getText();
            String getstate = state.getText();
            String getemail = email.getText();
            String getssn = ssn.getText();
            String getusername = username.getText();
            String getpassword = password.getText();
            String getquestion = security.getValue() != null ? security.getValue().toString() : "";
            String getanswer = answer.getText();
            String type="admin";
            // checking if everything is filled in
            if (firstName.isEmpty() || lastName.isEmpty() || getusername.isEmpty() || getpassword.isEmpty() || getquestion.isEmpty() || getanswer.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all required fields.");
                alert.showAndWait();
            }
            String query= "INSERT INTO Users (FirstName, LastName, Address, ZipCode, State, Email, SSN, Username, Password, question, answer,type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            // now we will attempt to register admin with connection
            Privateconnection database=new Privateconnection();
            try (Connection connection = DriverManager.getConnection(database.getURL(), database.getUsername(), database.getPassword());
                 PreparedStatement stmt = connection.prepareStatement(query))
            {

                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, getaddress);
                stmt.setString(4, getzip);
                stmt.setString(5, getstate);
                stmt.setString(6, getemail);
                stmt.setString(7, getssn);
                stmt.setString(8, getusername);
                stmt.setString(9, getpassword);
                stmt.setString(10, getquestion);
                stmt.setString(11, getanswer);
                stmt.setString(12,type );
                // execute statement
                stmt.executeUpdate();

                int rows=stmt.getUpdateCount();
                if(rows>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Registered!");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Could not register check connection or inputs");
                    alert.showAndWait();
                }


            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
