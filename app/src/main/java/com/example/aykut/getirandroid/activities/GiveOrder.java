package com.example.aykut.getirandroid.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    ImageButton searchButton;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> autocompleteAdapter;
    ArrayList<String> list = new ArrayList<>();

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



        searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<orderList.size();i++){
                    if(orderList.get(i).getItemName().equalsIgnoreCase(autoCompleteTextView.getText().toString().trim())){
                        itemRecycler.getLayoutManager().scrollToPosition(i);
                        Log.d("aykut","ooo"+i);
                    }
                }
            }
        });

        orderButton = (Button) findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);





        autocompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autocompleteAdapter);


        prepareData();


    }

    public void prepareData(){

        list.add("cikolata");
        list.add("cips");
        list.add("gofret");
        list.add("tarak");
        list.add("gazoz");
        list.add("jilet");
        list.add("limon");
        list.add("mandalina");
        list.add("nergis");
        list.add("zerdali");
        list.add("zoli");



        orderList.add(new Order("cips",0));
        orderList.add(new Order("cikolata",0));
        orderList.add(new Order("gazoz",0));
        orderList.add(new Order("gofret",0));
        orderList.add(new Order("jilet",0));
        orderList.add(new Order("limon",0));
        orderList.add(new Order("mandalina",0));
        orderList.add(new Order("nergis",0));
        orderList.add(new Order("zerdali",0));
        orderList.add(new Order("zoli",0));
        orderList.add(new Order("tarak",0));


    }




}
