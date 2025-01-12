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
    int userID=0;
//this class is to record and save the logged in data

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

    //helps search db to look for user first name
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
    public int getUserID()
    {
        this.userID = userID;
        Privateconnection database = new Privateconnection();
        String query = "SELECT id FROM users WHERE username= ? AND password=?";

        try (Connection connection = DriverManager.getConnection(database.getURL(), database.getUsername(), database.getPassword());
             PreparedStatement statement = connection.prepareStatement(query)) {
            //now we prepare the prepared statement
            statement.setString(1, UserName);
            statement.setString(2, Password);
            ResultSet rs = statement.executeQuery();
            //
            if (rs.next()) {
                userID = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userID;

    }

}
