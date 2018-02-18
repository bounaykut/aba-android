package com.example.aykut.getirandroid.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aykut on 17.02.2018.
 */

public class Order {

    @SerializedName("courier")
    private String courier;

    @SerializedName("customer")
    private String customer;

    @SerializedName("item")
    private Item item;

    public Order(String courier, String customer, Item item) {
        this.courier = courier;
        this.customer = customer;
        this.item = item;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
