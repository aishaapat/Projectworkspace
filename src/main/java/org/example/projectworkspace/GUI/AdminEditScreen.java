package org.example.projectworkspace.GUI;

import javafx.application.Application;
import javafx.stage.Stage;
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
import org.example.projectworkspace.UserState.LoggedIn;

public class AdminEditScreen extends Application implements EventHandler<ActionEvent>
{
    LoggedIn login=new LoggedIn();
    Stage stage;
    TextField AdminUsername, AdminPassword;
    Button AddButton=new Button("Add");
    DeleteButton delete= new DeleteButton();
    Button manageflightsbutton=new Button("Manage Flights");



    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage=stage;
        GridPane root = new GridPane();
        root.getStyleClass().add("background-primary");
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        AdminUsername = new TextField();
        AdminUsername.setPromptText("Username");
        AdminPassword = new TextField();
        AdminPassword.setPromptText("Password");
        Label label1=new Label("Enter Admin Username: ");
        Label label2=new Label("Enter Admin Password: ");
        Label title = new Label("Welcome Admin!");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4CAF50; -fx-padding: 15px;");
        root.add(title, 0, 0, 2, 1);
        // I want to create an area that allows the user tto enter a username and password and it'll add the username and password
        // as a new user or it can query the database to delete the admin
        Scene scene=new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Admin Edit Screen");
        Label editAdminusers=new Label("Add or Delete admin Users here: ");
        root.add(editAdminusers, 0, 1);
        root.add(AdminUsername, 1, 2);
        root.add(AdminPassword, 1, 3);
        root.add(label1,0,2);
        root.add(label2,0,3);
        AddButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        root.add(AddButton,1,4);
        root.add(delete,3,4);
        manageflightsbutton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        root.add(manageflightsbutton,1,5);
        stage.show();
        manageflightsbutton.setOnAction(this);




    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        // Adding this so when admin clicks manageflights they will be sent to otherscreen
        if(actionEvent.getSource()==manageflightsbutton){
            AdminManageFlightsScreen  admin=new AdminManageFlightsScreen();
            try {
                admin.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();
        }
    }
}
