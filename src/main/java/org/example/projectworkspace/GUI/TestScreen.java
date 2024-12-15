package org.example.projectworkspace.GUI;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Test screen to see if button is working

public class TestScreen extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        LoginButton loginButton = new LoginButton();
        root.getChildren().add(loginButton);

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Test Screen for LoginButton");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
