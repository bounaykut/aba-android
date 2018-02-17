package com.example.aykut.getirandroid.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aykut on 17.02.2018.
 */

public class Location {

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lng")
    private Double lng;

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
