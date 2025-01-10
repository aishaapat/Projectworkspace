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

public class mainMenu extends Application
{
    private final LoggedIn loggedIn;

    mainMenu(LoggedIn loggedIn)
    {
        this.loggedIn=loggedIn;

    }

    Stage stage;


    String firstname;


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        // Create the layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        root.setHgap(10);
        Text title = new Text("Main Menu");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //adding log in to check if the user state stays logged in
        // I wanted to double check what their name will be etc
        Text name = new Text("Welcome "+loggedIn.getFirstName());

        name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        root.add(name,1,1, 1, 1);


        // we will add this when connection details work properly
       // Text welcometitle1 = new Text("Welcome " + loggedInUser.getFirstName());
        //welcometitle1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

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

        root.add(manageBookingsButton, 0, 2);
        root.add(logoutButton, 1, 2);

        // Set up the scene and stage
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    // Handle Manage Bookings button click
    private void handleManageBookings(ActionEvent event) {
        // Open the Manage Bookings screen
        ManageBookingsScreen manageBookingsScreen = new ManageBookingsScreen(loggedIn);
        manageBookingsScreen.start(new Stage()); // Open Manage Bookings in a new stage
        stage.close(); // Close the current Main Menu screen
    }

    // Handle Logout button click
    private void handleLogout(ActionEvent event) {
        //adding logic where loggedin state will be closed
        loggedIn.setLoggedIn(false);
        loggedIn.setUserName(null);
        loggedIn.setPassword(null);
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