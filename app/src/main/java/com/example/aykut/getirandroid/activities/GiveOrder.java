package com.example.aykut.getirandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.adapters.OrderAdapter;
import com.example.aykut.getirandroid.models.Order;

import java.util.ArrayList;

public class GiveOrder extends Activity {

    int id;

    TextView name;
    TextView kiloLimit;
    RecyclerView itemRecycler;
    FloatingActionButton addItemButton;
    Button orderButton;

    OrderAdapter orderAdapter;
    ArrayList<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_order);

        Intent i = getIntent();
        i.getIntExtra("travellerId",0);

        name = (TextView) findViewById(R.id.name);

        kiloLimit = (TextView) findViewById(R.id.kiloLimit);

        itemRecycler = (RecyclerView) findViewById(R.id.itemList);
        orderAdapter = new OrderAdapter(orderList);
        itemRecycler.setAdapter(orderAdapter);
        itemRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        addItemButton = (FloatingActionButton) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        orderButton = (Button) findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        prepareData();


    }

    public void prepareData(){
        orderList.add(new Order("cips",4));
        orderList.add(new Order("cikolata",5));

    }



}
