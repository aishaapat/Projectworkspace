package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class RegisterScreen extends Application implements EventHandler<ActionEvent> {

    Label title, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11;
    TextField First, Last, address, zip, state, username, password, email, ssn, answer;
    ComboBox<String> security;
    RegisterButton register;
    BackButton back;
    Stage stage;

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        this.stage = stage;

        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        // Header Label (Title)
        title = new Label("Welcome to Easy Flying!");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");
        root.add(title, 0, 0, 2, 1);

        // Initialize fields
        First = new TextField();
        Last = new TextField();
        address = new TextField();
        zip = new TextField();
        state = new TextField();
        ssn = new TextField();
        email = new TextField();
        username = new TextField();
        password = new TextField();
        answer = new TextField();
        security = new ComboBox<>();
        security.getItems().addAll(
                "What is your favorite color?",
                "What is your birthday?",
                "What is the name of the city you were born?"
        );

        // Add input fields and labels
        root.add(new Label("Enter First Name"), 0, 1);
        root.add(First, 1, 1);
        root.add(new Label("Enter Last Name"), 0, 2);
        root.add(Last, 1, 2);
        root.add(new Label("Enter Address"), 0, 3);
        root.add(address, 1, 3);
        root.add(new Label("Enter Zip Code"), 0, 4);
        root.add(zip, 1, 4);
        root.add(new Label("Enter State"), 0, 5);
        root.add(state, 1, 5);
        root.add(new Label("Enter SSN"), 0, 6);
        root.add(ssn, 1, 6);
        root.add(new Label("Enter Email"), 0, 7);
        root.add(email, 1, 7);
        root.add(new Label("Enter Username"), 0, 8);
        root.add(username, 1, 8);
        root.add(new Label("Enter Password"), 0, 9);
        root.add(password, 1, 9);
        root.add(new Label("Pick your Security Question"), 0, 10);
        root.add(security, 1, 10);
        root.add(new Label("Enter Answer"), 0, 11);
        root.add(answer, 1, 11);

        // Add buttons
        register = new RegisterButton(this);
        root.add(register, 1, 12);

        back = new BackButton();
        root.add(back, 2, 12);
        back.setOnAction(this);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Welcome to Easy Flying!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == back) {
            LoginScreen login = new LoginScreen();
            try {
                login.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        }
    }
}