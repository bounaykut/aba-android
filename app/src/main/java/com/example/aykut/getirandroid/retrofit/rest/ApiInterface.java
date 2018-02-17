package com.example.aykut.getirandroid.retrofit.rest;

/**
 * Created by aykut on 17.02.2018.
 */



import android.location.Location;

import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.example.aykut.getirandroid.retrofit.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {

    //courier routes
    @GET("couriers/")
    Call<List<Courier>> getAllCouriersSortedByDate();

    @POST("couriers/{id}")
    Call<Courier> getMovieDetails(@Path("id") int id);

    @POST("couriers/availableByLocation")
    Call<List<Courier>> getCouriersAroundPoint(@Path("id") int id, @Body Location location);

    //order routes
    @GET("orders/")
    Call<List<Order>> getCurrentOrders();

    @POST("orders/createOrder")
    Call<Order> createOrder(@Body Order order);


}