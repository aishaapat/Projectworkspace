package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class mainMenu extends Application {

    Stage stage;
    String loggedInUsername = "username"; // Replace with actual username from login

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Create the layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        root.setHgap(10);

        // Title
        Text title = new Text("Main Menu");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Buttons for Manage Bookings and Logout
        Button manageBookingsButton = new Button("Manage Bookings");
        Button logoutButton = new Button("Logout");

        // Action for Manage Bookings button
        manageBookingsButton.setOnAction(this::handleManageBookings);

        // Action for Logout button
        logoutButton.setOnAction(this::handleLogout);

        // Add components to layout
        root.add(title, 0, 0, 2, 1);  // Spans 2 columns
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER); // Center the title horizontally

        root.add(manageBookingsButton, 0, 1);
        root.add(logoutButton, 1, 1);

        // Set up the scene and stage
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    // Handle Manage Bookings button click
    private void handleManageBookings(ActionEvent event) {
        // Open the Manage Bookings screen
        ManageBookingsScreen manageBookingsScreen = new ManageBookingsScreen();
        manageBookingsScreen.start(new Stage()); // Open Manage Bookings in a new stage
        stage.close(); // Close the current Main Menu screen
    }

    // Handle Logout button click
    private void handleLogout(ActionEvent event) {
        // Close the current screen and go back to the login screen
        System.out.println("Logging out...");
        LoginScreen loginScreen = new LoginScreen(); // Replace with your Login screen class
        loginScreen.start(new Stage()); // Open the Login screen
        stage.close(); // Close the current Main Menu screen
    }

    public static void main(String[] args) {
        launch(args);
    }
}