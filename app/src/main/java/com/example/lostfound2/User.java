package com.example.lostfound2;

public class User {

    public String name, username, email;

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    /*public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }*/
}
