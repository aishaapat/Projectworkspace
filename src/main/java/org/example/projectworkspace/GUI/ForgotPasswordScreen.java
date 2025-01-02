package org.example.projectworkspace.GUI;

import Database.DatabaseManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class ForgotPasswordScreen extends Application implements EventHandler<ActionEvent> {

    Stage stage;
    Label title;
    TextField usernameField;
    ComboBox<String> questions;  // <String> ensures that all the contents inside the combo box are strings
    TextField answer;
    EnterButton enterButton;
    BackButton backButton;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);

        title = new Label("Forgot Password?");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");
        root.add(title, 0, 0, 2, 1);

        // Username Field
        Label labelUsername = new Label("Enter your username:");
        root.add(labelUsername, 0, 1);
        usernameField = new TextField();
        root.add(usernameField, 0, 2);

        // Security Question Dropdown
        Label labelQuestion = new Label("Pick your security question");
        root.add(labelQuestion, 0, 3);
        questions = new ComboBox<>();  // Initialize the ComboBox here
        questions.getItems().addAll(
                "What is your favorite color?",
                "What is your birthday?",
                "What is the name of the city you were born?"
        );
        root.add(questions, 0, 4);

        // Answer Field
        Label labelAnswer = new Label("Enter your answer:");
        root.add(labelAnswer, 0, 5);
        answer = new TextField();
        root.add(answer, 0, 6);

        // Enter and Back Buttons
        enterButton = new EnterButton();
        root.add(enterButton, 0, 7);
        backButton = new BackButton();
        root.add(backButton, 1, 7);

        // Action Handlers
        backButton.setOnAction(this);
        enterButton.setOnAction(this);

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Easy Flying - Forgot Password");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backButton) {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.start(new Stage());
            stage.close();
        }

        if (actionEvent.getSource() == enterButton) {
            String enteredUsername = usernameField.getText();
            String selectedQuestion = questions.getValue();
            String enteredAnswer = answer.getText();

            if (enteredUsername.isEmpty() || selectedQuestion == null || enteredAnswer.isEmpty()) {
                showAlert("Error", "One or more of your fields is empty");
            } else {
                String password = verifySecurityQuestion(enteredUsername, selectedQuestion, enteredAnswer);
                if (password != null) {
                    showAlert("Success", "Your security question has been verified. Your password is: " + password);
                    // You can add further steps like allowing the user to reset their password here
                } else {
                    showAlert("Error", "Invalid username, question, or answer.");
                }
            }
        }
    }

    private String verifySecurityQuestion(String username, String question, String answer) {
        String sql = "SELECT question, answer, password FROM users WHERE username = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try (Connection connection = dbManager.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection failed");
                return null; // no password to be found if the connection fails
            }

            // Set the username parameter for the query
            stmt.setString(1, username);

            // Execute the query and store the result
            ResultSet resultSet = stmt.executeQuery();

            // Check if we got any result
            if (resultSet.next()) {
                String storedQuestion = resultSet.getString("question");
                String storedAnswer = resultSet.getString("answer");
                String password = resultSet.getString("password");

                // Verify if the question and answer match
                if (storedQuestion.equals(question) && storedAnswer.equals(answer)) {
                    return password;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // If any inputs don't match
    }


    // Creates an showAlert method that passes a title and message to be put at specific points where
    // a users input could be null or incorrect

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
