package com.example.lostfound2;

public class User {

    public String name, username, email;

    public User(){

    }

    public String getUsername() {
        return this.username;
    }

    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
}
