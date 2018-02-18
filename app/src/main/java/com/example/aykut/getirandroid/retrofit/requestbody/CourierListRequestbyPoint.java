package com.example.aykut.getirandroid.retrofit.requestbody;

import com.example.aykut.getirandroid.retrofit.model.Location;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierListRequestbyPoint {

    @SerializedName("location")
    private Location location;

    public CourierListRequestbyPoint(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}