package org.example.projectworkspace.GUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
public class RegisterButton extends Button
{
    public RegisterButton() {
        super("Register");

        // Notes

        // Styling the button
        this.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Setting the event handler for the button click
        this.setOnAction(event -> handleregister());
    }

    // Event handler for login button click
    private void handleregister() {
        boolean registerpressed = performregister();

        if (registerpressed) {
            System.out.println("Register button pressed");
        } else {
            System.out.println("Try again");
        }
    }

    // Simulate login logic (replace with actual logic)
    private boolean performregister() {
        // TODO: Implement real login logic here
        return true; // Simulating a successful login
    }

}
