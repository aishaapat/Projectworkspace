package org.example.projectworkspace.GUI;

import Database.Privateconnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterButton extends Button {
    private RegisterScreen registerScreen;

    public RegisterButton(RegisterScreen registerScreen) {
        super("Register");
        this.registerScreen = registerScreen;

        // Styling the button
        this.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Setting the event handler for the button click
        this.setOnAction(event -> handleregister());
    }

    private void handleregister() {
        boolean success = performregister();

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("User registered successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Could not register user. Please check your inputs and try again.");
            alert.showAndWait();
        }
    }

    private boolean performregister() {
        // Access the fields from the provided RegisterScreen instance
        String firstName = registerScreen.First.getText();
        String lastName = registerScreen.Last.getText();
        String address = registerScreen.address.getText();
        String zip = registerScreen.zip.getText();
        String state = registerScreen.state.getText();
        String email = registerScreen.email.getText();
        String ssn = registerScreen.ssn.getText();
        String username = registerScreen.username.getText();
        String password = registerScreen.password.getText();
        String question = registerScreen.security.getValue() != null ? registerScreen.security.getValue().toString() : "";
        String answer = registerScreen.answer.getText();
        String type="user";

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || question.isEmpty() || answer.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
            return false;
        }

        Privateconnection dbConnection = new Privateconnection();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO Users (FirstName, LastName, Address, ZipCode, State, Email, SSN, Username, Password, question, answer,type) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)")) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.setString(4, zip);
            stmt.setString(5, state);
            stmt.setString(6, email);
            stmt.setString(7, ssn);
            stmt.setString(8, username);
            stmt.setString(9, password);
            stmt.setString(10, question);
            stmt.setString(11, answer);
            stmt.setString(12,type );

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}