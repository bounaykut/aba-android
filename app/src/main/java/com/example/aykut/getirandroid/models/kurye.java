package com.example.android.myapplication;

public class kurye{

    private String name;
    private String email;
    private String telNo;

    public kurye(String n){
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){ return  email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelNo(){ return telNo; }

    public void setTelNo(){ this.telNo = telNo; }
}
