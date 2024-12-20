package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreen extends Application implements EventHandler<ActionEvent> {
    Label label1, label2, label3;
    TextField text1;
    PasswordField text2;
    Button loginButton, registerButton, forgotPasswordButton;
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Create the root layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(15);
        root.setHgap(10);
        root.setPadding(new Insets(20, 20, 20, 20));  // Add padding around the grid

        // Style the background color
        root.setStyle("-fx-background-color: #f4f4f4;");

        // Header Label (Title)
        label1 = new Label("Welcome to Easy Flying!");
        label1.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");

        // Username and Password Labels
        label2 = new Label("Username:");
        label3 = new Label("Password:");

        // TextFields for Username and Password
        text1 = new TextField();
        text1.setPromptText("Enter your username");
        text1.setStyle("-fx-border-color: #4CAF50; -fx-border-radius: 5px; -fx-font-size: 14px;");

        text2 = new PasswordField();
        text2.setPromptText("Enter your password");
        text2.setStyle("-fx-border-color: #4CAF50; -fx-border-radius: 5px; -fx-font-size: 14px;");

        // Login Button
        loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 16px;");
        loginButton.setOnAction(this::handleLogin);

        // Register Button
        registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 16px;");
        registerButton.setOnAction(this);

        // Forgot Password Button
        forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.setStyle("-fx-text-fill: #FF5722; -fx-font-size: 14px;");
        forgotPasswordButton.setOnAction(this);

        // Add components to layout
        root.add(label1, 0, 0, 2, 1);
        root.add(label2, 0, 1);
        root.add(text1, 1, 1);
        root.add(label3, 0, 2);
        root.add(text2, 1, 2);
        root.add(loginButton, 0, 3, 2, 1);
        root.add(registerButton, 0, 4);
        root.add(forgotPasswordButton, 1, 4);

        // Set up the scene and stage
        Scene scene = new Scene(root, 400, 350);
        stage.setTitle("Easy Flying - Login");
        stage.setScene(scene);

        // Set resizable as false for the login window
        stage.setResizable(false);
        stage.show();
    }

    // Handle Login Button click
    private void handleLogin(ActionEvent event) {
        // Placeholder logic for handling login
        String username = text1.getText();
        String password = text2.getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Please enter both username and password.");
            return;  // Or show an alert
        }

        // Add your login validation logic here

        // On successful login, proceed to the next screen (MainMenuScreen)
        mainMenu mainMenuScreen = new mainMenu();
        mainMenuScreen.start(new Stage());
        stage.close(); // Close the current login screen
    }

    // Handle Register Button click


    // Handle Forgot Password Button click
    private void handleForgotPassword(ActionEvent event) {
        // Logic to open a Forgot Password screen
        System.out.println("Opening Forgot Password Screen...");
        // You can create and show a Forgot Password screen here
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void handle(ActionEvent actionEvent)
    {
        if(actionEvent.getSource()==registerButton) {
            RegisterScreen registerScreen = new RegisterScreen();
            try {
                registerScreen.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();


        }

    }
}