package com.example.aykut.getirandroid.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aykut on 17.02.2018.
 */

public class Item {

    @SerializedName("itemName")
    private String itemName;

    @SerializedName("price")
    private Double price;

    @SerializedName("weight")
    private Double weight;

    public Item(String itemName, Double price, Double weight) {
        this.itemName = itemName;
        this.price = price;
        this.weight = weight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
