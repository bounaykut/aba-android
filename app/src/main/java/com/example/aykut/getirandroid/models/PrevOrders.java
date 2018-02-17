package com.example.aykut.getirandroid.models;

public class PrevOrders {

    private String item;
    private String carrier;

    public PrevOrders(String item, String carrier){
        this.item = item;
        this.carrier = carrier;
    }

    public String getItem(){
        return item;
    }

    public void setItem(String item){
        this.item = item;
    }

    public String getCarrier(){
        return carrier;
    }

    public void setCarrier(String carrier){
        this.carrier = carrier;
    }
}
