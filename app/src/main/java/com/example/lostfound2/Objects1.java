package com.example.lostfound2;

public class Objects1 {

    private String name,location,contact,question,date,username,category;
    public Objects1() {

    }

    public Objects1(String name, String location, String contact, String question, String date, String username, String category) {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.question = question;
        this.date = date;
        this.username = username;
        this.category = category;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


