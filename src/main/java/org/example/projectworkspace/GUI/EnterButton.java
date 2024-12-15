package org.example.projectworkspace.GUI;

import javafx.scene.control.Button;

public class EnterButton extends Button
{
    public EnterButton() {
        super("Enter");

        // Notes

        // Styling the button
        this.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Setting the event handler for the button click
        this.setOnAction(event -> handleenter());
    }

    // Event handler for login button click
    private void handleenter() {
        boolean enterpressed = enterreg();

        if (enterpressed) {
            System.out.println("Enter button pressed");
        } else {
            System.out.println("Try again");
        }
    }

    // Simulate login logic (replace with actual logic)
    private boolean enterreg() {
        // TODO: Implement real login logic here
        return true; // Simulating a successful login
    }
}
