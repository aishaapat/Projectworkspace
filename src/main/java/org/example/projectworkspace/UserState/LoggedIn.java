package org.example.projectworkspace.UserState;

import Database.Privateconnection;

import java.sql.*;

public class LoggedIn
{
    String UserName=null;
    String Password=null;
    Boolean LoggedIn=false;
    String FirstName=null;
    String type=null;


    public String getUserName(){
        return UserName;
    }
    public String getPassword(){
        return Password;
    }
    public Boolean getLoggedIn(){
        return LoggedIn;
    }
    public void setLoggedIn(Boolean LoggedIn){
        this.LoggedIn = LoggedIn;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
    public String getFirstName()
    {
        this.FirstName = FirstName;
        Privateconnection database = new Privateconnection();
        String query = "SELECT firstname FROM users WHERE username= ? AND password=?";

        try (Connection connection = DriverManager.getConnection(database.getURL(), database.getUsername(), database.getPassword());
             PreparedStatement statement = connection.prepareStatement(query)) {
            //now we prepare the prepared statement
            statement.setString(1, UserName);
            statement.setString(2, Password);
            ResultSet rs = statement.executeQuery();
            //
            if (rs.next()) {
                FirstName = rs.getString("firstname");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FirstName;
    }
    public String getType()
    {
        this.type = type;
        Privateconnection database = new Privateconnection();
        String query = "SELECT type FROM users WHERE username= ? AND password=?";

        try (Connection connection = DriverManager.getConnection(database.getURL(), database.getUsername(), database.getPassword());
             PreparedStatement statement = connection.prepareStatement(query)) {
            //now we prepare the prepared statement
            statement.setString(1, UserName);
            statement.setString(2, Password);
            ResultSet rs = statement.executeQuery();
            //
            if (rs.next()) {
                type = rs.getString("type");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return type;

    }
}
