package com.example.aykut.getirandroid.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.adapters.kuryeArrayAdapter;
import com.example.aykut.getirandroid.models.kurye;
import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.example.aykut.getirandroid.retrofit.responsebody.CourierListResponse;
import com.example.aykut.getirandroid.retrofit.rest.ApiClient;
import com.example.aykut.getirandroid.retrofit.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableCourier extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<kurye> kuryeList = new ArrayList<kurye>();
    kuryeArrayAdapter mkuryeArrayAdapter;

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_patent);

        mHandler = new Handler(Looper.getMainLooper());

        mkuryeArrayAdapter = new kuryeArrayAdapter(R.layout.recycle_kurye, kuryeList, this);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_viewer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CourierListResponse> call = apiService.getAllCouriersSortedByDate();

        call.enqueue(new Callback<CourierListResponse>() {
            @Override
            public void onResponse(Call<CourierListResponse> call, Response<CourierListResponse> response) {
                final CourierListResponse couriers = response.body();
                Log.d("aykut", "Number of couriers received: " + response.body().getCouriers().size());


                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (Courier courier : couriers.getCouriers()) {
                            Log.d("aykut", courier.getName());
                            kuryeList.add(new kurye(courier.getName()));
                        }
                        recyclerView.setAdapter(mkuryeArrayAdapter);
                    }
                });


            }

            @Override
            public void onFailure(Call<CourierListResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("aykut", t.toString());
            }
        });

        /*for(int i=0; i<20; i++) {
            kuryeList.add(new kurye("Kurye " + i));
        }*/
    }
}