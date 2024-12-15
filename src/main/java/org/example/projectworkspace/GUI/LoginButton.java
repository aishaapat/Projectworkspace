package org.example.projectworkspace.GUI;

import javafx.scene.control.Button;

public class LoginButton extends Button {

    public LoginButton() {
        super("Log In");

        // Notes

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
        // TODO: Implement real login logic here
        return true; // Simulating a successful login
    }
}