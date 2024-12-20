package org.example.projectworkspace.GUI;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.control.*;

public class ForgotPasswordScreen extends Application implements EventHandler<ActionEvent>
{

    Stage stage;
    Label title;
    TextField answer;
    EnterButton enterButton;
    BackButton backButton;


    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);

        title = new Label("Forgot Password?");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");
        root.add(title, 0, 0, 2, 1);

        final ComboBox questions= new ComboBox();
        questions.getItems().addAll(
                "What is your favorite color?",
                "What is your birthday?",
                "What is the name of the city you were born?"
        );
        Label label1=new Label("Pick your security question");
        root.add(label1, 0, 1);
        root.add(questions, 0, 2);
        //adding a lable for password now
        Label label2=new Label("Enter your answer: ");
        root.add(label2, 0, 3);
        answer=new TextField();
        root.add(answer, 0, 4);
        // adding enter button and back button will implement ui for it when the db stuff works
        //also will add a popup for the password or when they hit enter!

        enterButton=new EnterButton();
        root.add(enterButton, 0, 5);
        backButton=new BackButton();
        root.add(backButton, 1, 5);
        // add the action event for the backbutton
        backButton.setOnAction(this);
        root.getChildren();
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Easy Flying - forgot password");
        stage.setScene(scene);

        // Set resizable as false for the login window
        stage.setResizable(false);
        stage.show();


    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == backButton){
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.start(new Stage());
            stage.close();
        }
    }
}
