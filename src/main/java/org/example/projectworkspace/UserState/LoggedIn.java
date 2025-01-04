package org.example.projectworkspace.UserState;

public class LoggedIn
{
    String UserName=null;
    String Password=null;
    Boolean LoggedIn=false;


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
}
