package com.example.lostfound2;

public class model {

    String ItemName,Location, Date,Time;

    //blank constructor so that the data goes into the recycler
    model(){

    }

    //constructors
    public model(String ItemName, String Location, String Date, String Time) {
        this.ItemName = ItemName;
        this.Location= Location;
        this.Date = Date;
        this.Time = Time;
    }


    //getter and setter for all the variables
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}
