package com.example.prueba.Helper;

import java.io.Serializable;

/**
 * Created by Dell on 15/05/2018.
 */

public class Login implements Serializable {
    private String User;

    private String Password;

    public Login(){

    }

    public Login(String user, String password){
        User=user;
        Password=password;
    }

    public String getUser ()
    {
        return User;
    }

    public void setUser (String User)
    {
        this.User = User;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String Password)
    {
        this.Password = Password;
    }
}
