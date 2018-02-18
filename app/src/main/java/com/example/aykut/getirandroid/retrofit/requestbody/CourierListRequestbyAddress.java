package com.example.aykut.getirandroid.retrofit.requestbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierListRequestbyAddress {

    @SerializedName("address")
    @Expose
    private String address;

    public CourierListRequestbyAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}