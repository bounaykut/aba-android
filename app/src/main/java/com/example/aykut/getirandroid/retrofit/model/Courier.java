package com.example.aykut.getirandroid.retrofit.model;

import android.content.Intent;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by aykut on 17.02.2018.
 */

public class Courier {

    @SerializedName("name")
    private String name;

    @SerializedName("itemLimit")
    private Integer itemLimit;

    @SerializedName("weightLimit")
    private Integer weightLimit;

    @SerializedName("location")
    private Location location;


    public Courier(String name, Integer itemLimit, Integer weightLimit, Location location) {
        this.name = name;
        this.itemLimit = itemLimit;
        this.weightLimit = weightLimit;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemLimit() {
        return itemLimit;
    }

    public void setItemLimit(Integer itemLimit) {
        this.itemLimit = itemLimit;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
