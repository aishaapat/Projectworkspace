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
    private boolean performLogin() {
        String enteredUsername = text1.getText(); // Get username from text1
        String enteredPassword = text2.getText(); // Get password from text2

        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = dbManager.connect();

        if (connection == null) {
            System.out.println("Connection failed");
            return false;
        }

        try
        {
            String sql = "SELECT password FROM users WHERE username = ?";

            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredUsername);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                String storedUsername = resultSet.getString("username");
                String storedPassword = resultSet.getString("password");

                    if (enteredPassword.equals(storedPassword)&& enteredUsername.equals(storedUsername))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }

            }
            else return false;

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