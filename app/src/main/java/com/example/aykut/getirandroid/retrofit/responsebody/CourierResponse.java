package com.example.aykut.getirandroid.retrofit.responsebody;

import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierResponse {

    @SerializedName("courier")
    private Courier courier;

    public CourierResponse(Courier courier) {
        this.courier = courier;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}