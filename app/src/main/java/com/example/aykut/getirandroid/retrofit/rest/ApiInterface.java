package com.example.aykut.getirandroid.retrofit.rest;

/**
 * Created by aykut on 17.02.2018.
 */




import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.example.aykut.getirandroid.retrofit.requestbody.CourierListRequestbyAddress;
import com.example.aykut.getirandroid.retrofit.requestbody.CourierListRequestbyPoint;
import com.example.aykut.getirandroid.retrofit.responsebody.CourierListResponse;
import com.example.aykut.getirandroid.retrofit.model.Order;
import com.example.aykut.getirandroid.retrofit.responsebody.CourierResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {

    //courier routes
    @GET("couriers/")
    Call<CourierListResponse> getAllCouriersSortedByDate();

    @GET("couriers/{id}")
    Call<CourierResponse> getCourier(@Path("id") String id);

    @POST("couriers/availableByLocation")
    Call<List<Courier>> getCouriersAroundLocation(@Body CourierListRequestbyAddress courierListRequestbyAddress);

    @POST("couriers/availableByPoint")
    Call<List<Courier>> getCouriersAroundPoint(@Body CourierListRequestbyPoint courierListRequestbyPoint);

    //user routes
    @GET("users/{id}/orders")
    Call<List<Order>> getCurrentOrders(@Path("id") String id);

    @POST("users/{id}/createOrder")
    Call<Order> createOrder(@Path("id") String id, @Body Order order);


}