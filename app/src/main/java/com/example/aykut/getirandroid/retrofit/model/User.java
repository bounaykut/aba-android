package com.example.aykut.getirandroid.retrofit.model;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aykut on 17.02.2018.
 */

public class User {

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private Location location;

    public User(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
