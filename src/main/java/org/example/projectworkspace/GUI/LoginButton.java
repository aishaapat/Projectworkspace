package org.example.projectworkspace.GUI;

import Database.DatabaseManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
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

        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.connect();

        if (connection == null) {
            System.out.println("Connection failed");
            return false;
        }

        try {
            String sql = "SELECT password, type FROM users WHERE username = ?";

            // var recognizes the prepared statement as an object of the prepared statement class

            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredUsername);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Finds the password from the database
                String storedPassword = resultSet.getString("password");
                // Check if the entered password matches the one stored
                if (enteredPassword.equals(storedPassword)) {
                    String type = resultSet.getString("type");
                    System.out.println("User type: " + type);
                    return true; // Login successful
                } else {
                    System.out.println("Incorrect password.");
                    return false; // Login failed due to incorrect password
                }
            } else {
                System.out.println("Username not found");
                return false; // Username not found
            }
        }

        catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}