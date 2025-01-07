package org.example.projectworkspace.UserState;

import Database.DatabaseConnection;
import Database.Privateconnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;

public class UserStatus
{
    Privateconnection data = new Privateconnection();

    // this will be back end for users
    public boolean checkuserStatus(ActionEvent event, String Username, String Password){
        Connection connection=null;
        PreparedStatement prepared=null;
        ResultSet resultSet=null;
        Boolean status=false;
        try{
            connection= DriverManager.getConnection(data.getURL(), data.getUsername(),data.getPassword());
            prepared=connection.prepareStatement("select * from Users where Username=? and Password=?");
            prepared.setString(1,Username);
            prepared.setString(2,Password);
            resultSet=prepared.executeQuery();
            String retrieveusername=resultSet.getString("Username");
            String retrievepassword=resultSet.getString("Password");
            if(retrieveusername.equals(Username) && retrievepassword.equals(Password)){
                status=true;


            }
            else{
                System.out.println("User not found in database");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("User not found in database");
                alert.showAndWait();
                status=false;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return status;


    }

}
