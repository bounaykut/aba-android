package com.example.aykut.getirandroid;

import com.shalan.mohamed.itemcounterview.IncDecView;

/**
 * Created by aykut on 17.02.2018.
 */

public class Order {
    String itemName;
    int counter;

    public Order(){}

    public Order(String itemName, int counter) {
        this.itemName = itemName;
        this.counter = counter;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
