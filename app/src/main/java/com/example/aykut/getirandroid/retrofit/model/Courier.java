package com.example.aykut.getirandroid.retrofit.model;

import android.content.Intent;
import android.location.Location;

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

    @SerializedName("travelDate")
    private Date travelDate;

    @SerializedName("arrivalDate")
    private Date arrivalDate;

    public Courier(String name, Integer itemLimit, Integer weightLimit, Location location, Date travelDate, Date arrivalDate) {
        this.name = name;
        this.itemLimit = itemLimit;
        this.weightLimit = weightLimit;
        this.location = location;
        this.travelDate = travelDate;
        this.arrivalDate = arrivalDate;
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

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
