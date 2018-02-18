package com.example.aykut.getirandroid.retrofit.responsebody;

import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aykut on 18.02.2018.
 */

public class CourierListResponse {

    @SerializedName("courier")
    private List<Courier> couriers;

    public CourierListResponse(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }
}
