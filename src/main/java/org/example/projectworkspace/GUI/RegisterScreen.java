package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class RegisterScreen extends Application {
    Label label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11;
    TextField First, Last, address, zip, state, username, password, email, ssn, security, answer;
    EnterButton enter = new EnterButton();
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        First = new TextField();
        Last = new TextField();
        address = new TextField();
        zip = new TextField();
        state = new TextField();
        ssn = new TextField();
        email = new TextField();
        security = new TextField();
        username = new TextField();
        password = new TextField();
        answer = new TextField();

        root.add(First, 1, 0);
        root.add(Last, 1, 1);
        root.add(address, 1, 2);
        root.add(zip, 1, 3);
        root.add(state, 1, 4);
        root.add(ssn, 1, 5);
        root.add(email, 1, 6);
        root.add(username, 1, 7);
        root.add(password, 1, 8);
        root.add(security, 1, 9);
        root.add(answer, 1, 10);
        root.add(enter, 1, 11);

        // Initialize labels
        label1 = new Label("Enter First Name");
        label2 = new Label("Enter Last Name");
        label3 = new Label("Enter Address");
        label4 = new Label("Enter Zip Code");
        label5 = new Label("Enter State");
        label6 = new Label("Enter Email");
        label7 = new Label("Enter SSN");
        label8 = new Label("Enter Username");
        label9 = new Label("Enter Password");
        label10 = new Label("Enter Security Question");
        label11 = new Label("Enter Answer");

        // Add labels to GridPane
        root.add(label1, 0, 0);
        root.add(label2, 0, 1);
        root.add(label3, 0, 2);
        root.add(label4, 0, 3);
        root.add(label5, 0, 4);
        root.add(label6, 0, 5);
        root.add(label7, 0, 6);
        root.add(label8, 0, 7);
        root.add(label9, 0, 8);
        root.add(label10, 0, 9);
        root.add(label11, 0, 10);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Welcome to Easy Flying!");
        stage.setScene(scene);

        // Set resizable as false
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}