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
import org.example.projectworkspace.UserState.LoggedIn;

public class mainMenu extends Application {

    Stage stage;

    LoggedIn loggedInUser;


    mainMenu(LoggedIn loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

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
        //adding log in to check if the user state stays logged in
        // I wanted to double check what their name will be etc



        Text welcometitle1 = new Text("Welcome "+loggedInUser.getFirstName());
        welcometitle1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Buttons for Manage Bookings and Logout
        Button manageBookingsButton = new Button("Manage Bookings");
        Button logoutButton = new Button("Logout");

        // Action for Manage Bookings button
        manageBookingsButton.setOnAction(this::handleManageBookings);

        // Action for Logout button
        logoutButton.setOnAction(this::handleLogout);

        // Add components to layout
        root.add(title, 0, 0, 2, 1);  // Spans 2 columns
        root.add(welcometitle1, 0, 1, 3, 1);
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
        //adding logic where loggedin state will be closed
        loggedInUser.setLoggedIn(false);
        loggedInUser.setUserName(null);
        loggedInUser.setPassword(null);
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