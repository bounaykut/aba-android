package com.example.aykut.getirandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.adapters.kuryeArrayAdapter;
import com.example.aykut.getirandroid.models.kurye;

import java.util.ArrayList;

public class AvailableCourier extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<kurye> kuryeList = new ArrayList<kurye>();
    kuryeArrayAdapter mkuryeArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_patent);

        mkuryeArrayAdapter = new kuryeArrayAdapter(R.layout.recycle_kurye, kuryeList, this);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_viewer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mkuryeArrayAdapter);

        for(int i=0; i<20; i++) {
            kuryeList.add(new kurye("Kurye " + i));
        }
    }
}