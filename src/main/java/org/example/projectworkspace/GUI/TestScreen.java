package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*

public class TestScreen extends Application {
    Label label1, label2, label3;
    TextField text1, text2;
    Stage stage;

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);

        // Initialize labels and text fields
        label1 = new Label("Flying Easy!");
        label1.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 24px; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        label2 = new Label("Username:");
        label3 = new Label("Password:");
        text1 = new TextField();
        text2 = new TextField();

        // Add controls to the grid
        root.add(label1, 0, 0, 2, 1);
        root.add(label2, 3, 1);
        root.add(text1, 4, 1);
        root.add(label3, 3, 2);
        root.add(text2, 4, 2);

        // Create and add LoginButton
        LoginButton loginButton = new LoginButton(text1, text2);
        root.add(loginButton, 7, 3);

        // Create and add RegisterButton with the current instance of TestScreen (this)
        RegisterButton registerButton = new RegisterButton(this);
        root.add(registerButton, 8, 3);

        // Set the scene and stage
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Easy Flying!");
        stage.setScene(scene);
        stage.setResizable(false); // Set resizable to false
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


 */