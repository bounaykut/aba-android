package com.example.aykut.getirandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.adapters.orderArrayAdapter;
import com.example.aykut.getirandroid.models.PrevOrders;

import java.util.ArrayList;

public class GoMyOrdersActivity extends AppCompatActivity {
    RecyclerView mrecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_patent);

        ArrayList<PrevOrders> orderList = new ArrayList<PrevOrders>();

        orderArrayAdapter morderArrayAdapter = new orderArrayAdapter(R.layout.recycle_myorders, orderList);

        mrecyclerView = (RecyclerView) findViewById(R.id.recycle_viewer);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(morderArrayAdapter);


        for(int i=0; i<40; i++) {
            orderList.add(new PrevOrders("item " + i , "carrier " + i));
        }

    }
}