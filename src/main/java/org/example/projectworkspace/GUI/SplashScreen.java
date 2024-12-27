package org.example.projectworkspace.GUI;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Splash Screen Scene
        Label splashLabel = new Label("Welcome to Flying Easy!");
        splashLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        StackPane splashLayout = new StackPane(splashLabel);
        Scene splashScene = new Scene(splashLayout, 400, 300);

        // Sets splash screen as primary stage and shows it
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Flying Easy");
        primaryStage.show();

        // Breathing animation for text
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), splashLabel);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play();

        // PauseTransition to give it a delay
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            LoginScreen loginScreen = new LoginScreen();
            try {
                loginScreen.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}