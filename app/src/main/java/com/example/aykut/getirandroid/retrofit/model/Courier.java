package com.example.aykut.getirandroid.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Courier {

    @SerializedName("location")
    private Location location;

    @SerializedName("itemLimit")
    private int itemLimit;

    @SerializedName("weightLimit")
    private int weightLimit;

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("travelDate")
    private String travelDate;

    @SerializedName("arrivalDate")
    private String arrivalDate;

    @SerializedName("__v")
    private int v;


    public Courier(Location location, int itemLimit, int weightLimit, String id, String name, String travelDate, String arrivalDate, int v) {
        this.location = location;
        this.itemLimit = itemLimit;
        this.weightLimit = weightLimit;
        this.id = id;
        this.name = name;
        this.travelDate = travelDate;
        this.arrivalDate = arrivalDate;
        this.v = v;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getItemLimit() {
        return itemLimit;
    }

    public void setItemLimit(int itemLimit) {
        this.itemLimit = itemLimit;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}