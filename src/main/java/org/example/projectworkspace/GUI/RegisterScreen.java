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

public class RegisterScreen extends Application
{
    Label label1,label2, label3;
    TextField First,Last,address,zip,state,username,password,email,ssn,security;
    EnterButton enter=new EnterButton();
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception
    {
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        First=new TextField();
        Last=new TextField();
        address=new TextField();
        zip=new TextField();
        state=new TextField();
        ssn=new TextField();
        email=new TextField();
        security=new TextField();
        username = new TextField();
        password = new TextField();

        root.add(First,0,0);
        root.add(Last,0,1);
        root.add(address,0,2);
        root.add(zip,0,3);
        root.add(state,0,4);
        root.add(ssn,0,5);
        root.add(email,0,6);
        root.add(username,0,7);
        root.add(password,0,8);
        root.add(security,0,9);
        root.add(enter,0,10);

        root.getChildren();

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Welcome to Easy Flying!");
        stage.setScene(scene);

        //set resizable as false
        stage.setResizable(false);
        stage.show();



    }
    public static void main(String args[]) {
        launch(args);
    }
}
