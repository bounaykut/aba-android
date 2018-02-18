package com.example.aykut.getirandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.adapters.orderArrayAdapter;
import com.example.aykut.getirandroid.models.PrevOrders;
import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.example.aykut.getirandroid.retrofit.model.Order;
import com.example.aykut.getirandroid.retrofit.responsebody.CourierListResponse;
import com.example.aykut.getirandroid.retrofit.responsebody.CourierResponse;
import com.example.aykut.getirandroid.retrofit.rest.ApiClient;
import com.example.aykut.getirandroid.retrofit.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoMyOrdersActivity extends AppCompatActivity {
    RecyclerView mrecyclerView;

    ApiInterface apiService;

    ArrayList<PrevOrders> orderList;
    orderArrayAdapter morderArrayAdapter;

    HashMap<String,String> couriers = new HashMap();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_patent);

        orderList = new ArrayList<PrevOrders>();

        morderArrayAdapter = new orderArrayAdapter(R.layout.recycle_myorders, orderList);

        mrecyclerView = (RecyclerView) findViewById(R.id.recycle_viewer);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CourierListResponse> call1 = apiService.getAllCouriersSortedByDate();

        call1.enqueue(new Callback<CourierListResponse>() {
            @Override
            public void onResponse(Call<CourierListResponse>call, Response<CourierListResponse> response) {
                CourierListResponse courierResponse = response.body();
                Log.d("aykut", "Courier receiveddddddddd: " + response.body().getCouriers().size());
                for(Courier courier : response.body().getCouriers()){
                    couriers.put(courier.getId(),courier.getName());
                }


                //5a883816e543c61708ed2bd8 aykut bozkurt
                Call<List<Order>> call1 = apiService.getCurrentOrders("5a883816e543c61708ed2bd8");

                call1.enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>>call, Response<List<Order>> response) {
                        List<Order> orderResponse = response.body();
                        if(response.body() != null){
                            Log.d("aykut", "Courier received: " + response.body().size());

                            for(final Order order : response.body()){
                                orderList.add(new PrevOrders("Item:" + order.getItem().getItemName() , "Carrier: " + couriers.get(order.getCourier())));
                            }
                            mrecyclerView.setAdapter(morderArrayAdapter);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Order>>call, Throwable t) {
                        // Log error here since request failed
                        Log.e("aykut", t.toString());
                    }
                });


            }

            @Override
            public void onFailure(Call<CourierListResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("aykut", t.toString());
            }
        });



    }
}