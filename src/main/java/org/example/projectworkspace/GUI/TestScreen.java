package org.example.projectworkspace.GUI;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

// Test screen to see if button is working

public class TestScreen extends Application {
    Label label1,label2, label3;
    TextField text1,text2;



    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        label1 = new Label("Flying Easy!");
        label2 = new Label("Username:");
        label3 = new Label("Password:");

        text1 = new TextField();
        text2 = new TextField();

        // Add controls to the grid
        root.add(label1, 0, 0, 2, 1);  // spanning over two columns for centering
        root.add(label2, 0, 1);
        root.add(text1, 1, 1);
        root.add(label3, 0, 2);
        root.add(text2, 1, 2);

        LoginButton loginButton = new LoginButton();
        root.add(loginButton, 1, 3);
        root.getChildren();

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Welcome to Easy Flying!");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
