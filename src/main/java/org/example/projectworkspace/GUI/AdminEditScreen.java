package org.example.projectworkspace.GUI;

import Database.Privateconnection;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminEditScreen extends Application implements EventHandler<ActionEvent>
{
    private LoggedIn login;

    Stage stage;
    TextField AdminUsername, AdminPassword;
    Button AddButton=new Button("Add");
    DeleteButton delete= new DeleteButton();
    Button manageflightsbutton=new Button("Manage Flights");
    BackButton back=new BackButton();



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
        //have to make sure I use the setOnAction function for the text fields
        AdminUsername.setOnAction(this);
        AdminPassword.setOnAction(this);
        //adding the rest of the things that are needed
        root.add(label1,0,2);
        root.add(label2,0,3);
        AddButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        root.add(AddButton,1,4);
        root.add(delete,3,4);
        // need to sent on action
        delete.setOnAction(this);
        root.add(back,4,5);
        back.setOnAction(this);
        manageflightsbutton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        root.add(manageflightsbutton,1,5);
        stage.show();
        manageflightsbutton.setOnAction(this);
        AddButton.setOnAction(this);

        // I am going to work on the delete button functions





    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        // Adding this so when admin clicks manageflights they will be sent to otherscreen
        if(actionEvent.getSource()==manageflightsbutton)
        {
            AdminManageFlightsScreen  admin=new AdminManageFlightsScreen();
            try {
                admin.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();
        }
        else if(actionEvent.getSource()==back){
            AdminMainMenu admin=new AdminMainMenu(login);
            admin.start(new Stage());
            stage.close();
        }
        else if(actionEvent.getSource()==delete){
            String username=AdminUsername.getText();
            String password=AdminPassword.getText();
            String query="DELETE from users where username=? AND password=?";
            // Parsing the texts to get the username and password for the prepared statements
            if(username.substring(0,5)!="admin"){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You are not allowed to delete this User/User is not an admin");
            }
            Privateconnection database=new Privateconnection();
            try (Connection connection = DriverManager.getConnection(database.getURL(), database.getUsername(), database.getPassword());
                 PreparedStatement statement = connection.prepareStatement(query))
            {
                statement.setString(1,username);
                statement.setString(2,password);
                statement.executeUpdate();
                int rows=statement.getUpdateCount();
                if(rows>0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Admin successfully deleted");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username or Password are incorrect");
                    alert.showAndWait();
                }


            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }

        }
        else if(actionEvent.getSource()==AddButton){
            RegisterAdmin showregister=new RegisterAdmin();
            try {
                showregister.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
