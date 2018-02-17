package com.example.aykut.getirandroid.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aykut on 17.02.2018.
 */

public class Order {

    @SerializedName("courier")
    private Courier courier;

    @SerializedName("customer")
    private User customer;

    @SerializedName("item")
    private Item item;

    public Order(Courier courier, User customer, Item item) {
        this.courier = courier;
        this.customer = customer;
        this.item = item;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
