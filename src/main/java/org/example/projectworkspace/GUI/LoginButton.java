package org.example.projectworkspace.GUI;

import Database.DatabaseManager;
import Database.Privateconnection;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginButton extends Button {

    private TextField text1, text2;

    public LoginButton(TextField usernameField, TextField passwordField) {
        super("Log In");

        // Store references to the TextFields
        this.text1 = usernameField;
        this.text2 = passwordField;

        // Styling the button
        this.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Setting the event handler for the button click
        this.setOnAction(event -> handleLogin());
    }

    // Event handler for login button click
    private void handleLogin() {
        boolean loginSuccessful = performLogin();

        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }
    }

    // Simulate login logic (replace with actual logic)
    public boolean performLogin() {
        String enteredUsername = text1.getText(); // Get username from text1
        String enteredPassword = text2.getText(); // Get password from text2
        Boolean logincomplete=false;

        String query = "SELECT username, password FROM users WHERE username = ? AND password = ?";

        Privateconnection connect = new Privateconnection();
        try(Connection dbconnect=connect.getConnection();
            PreparedStatement preparedStatement = dbconnect.prepareStatement(query))
        {
            preparedStatement.setString(1, enteredUsername);
            preparedStatement.setString(2, enteredPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                if(resultSet.getString("username").equals(enteredUsername) && resultSet.getString("password").equals(enteredPassword)) logincomplete=true;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return logincomplete;
    }
}