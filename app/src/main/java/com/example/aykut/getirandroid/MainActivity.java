package com.example.aykut.getirandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.aykut.getirandroid.activities.AvailableCourier;
import com.example.aykut.getirandroid.activities.GiveOrder;
import com.example.aykut.getirandroid.activities.GoMyOrdersActivity;
import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.example.aykut.getirandroid.retrofit.model.Location;
import com.example.aykut.getirandroid.retrofit.requestbody.CourierListRequestbyAddress;
import com.example.aykut.getirandroid.retrofit.requestbody.CourierListRequestbyPoint;
import com.example.aykut.getirandroid.retrofit.rest.ApiClient;
import com.example.aykut.getirandroid.retrofit.rest.ApiInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    Button currentOrder;
    Button availableCourier;
    EditText searchBox;
    ImageButton searchButton;

    private GoogleMap mMap;

    private ArrayList<Courier> courierList = new ArrayList<>();

    LatLng lastKnownPoint;

    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentOrder = (Button) findViewById(R.id.currentOrdersButton);
        currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GoMyOrdersActivity.class);
                startActivity(i);
            }
        });

        availableCourier = (Button) findViewById(R.id.availableCouriersButton);
        availableCourier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AvailableCourier.class);
                startActivity(i);
            }
        });

        searchBox = (EditText) findViewById(R.id.searchBox);

        searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPointsAroundbyLocation();
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        apiService = ApiClient.getClient().create(ApiInterface.class);

        /*Call<CourierResponse> call = apiService.getCourier("5a889a900cd7e400148c7522");

        call.enqueue(new Callback<CourierResponse>() {
            @Override
            public void onResponse(Call<CourierResponse>call, Response<CourierResponse> response) {
                CourierResponse courierResponse = response.body();
                Log.d("aykut", "Courier received: " + response.body().getCourier().getName());
            }

            @Override
            public void onFailure(Call<CourierResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("aykut", t.toString());
            }
        });*/



    }


    public void findPointsAroundbyLocation(){

        Call<List<Courier>> call = apiService.getCouriersAroundLocation(new CourierListRequestbyAddress(searchBox.getText().toString()));
        call.enqueue(new Callback<List<Courier>>() {
            @Override
            public void onResponse(Call<List<Courier>>call, Response<List<Courier>> response) {
                List<Courier> courierResponse = response.body();

                if(response.body() != null){
                    mMap.clear();
                    courierList.clear();

                    for(int i=1;i<response.body().size();i++){
                        courierList.add(response.body().get(i));
                    }


                }

                //draw blue circle centered at the requested point
                LatLng point = new LatLng(response.body().get(courierResponse.size()-1).getLocation().getLat(),response.body().get(courierResponse.size()-1).getLocation().getLng());
                drawCircle(point);

                //add markers to points around the center point
                for (Courier courier : courierList) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(courier.getLocation().getLat(),courier.getLocation().getLng()))
                            .title(courier.getName())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map)));
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));

            }

            @Override
            public void onFailure(Call<List<Courier>>call, Throwable t) {
                // Log error here since request failed
                Log.e("aykut", t.toString());
            }
        });



    }


    public void findPointsAroundbyPoint(final Location location){

        Call<List<Courier>> call = apiService.getCouriersAroundPoint(new CourierListRequestbyPoint(location));
        call.enqueue(new Callback<List<Courier>>() {
            @Override
            public void onResponse(Call<List<Courier>>call, Response<List<Courier>> response) {
                List<Courier> courierResponse = response.body();

                if(response.body() != null){
                    for(Courier courier:response.body()){
                        Log.d("aykut", "size of couriers received: " + response.body().size());
                        courierList.add(courier);
                    }
                    //draw blue circle centered at the clicked point
                    drawCircle(new LatLng(location.getLat(),location.getLng()));


                    //add markers to points around the center point
                    for (Courier courier : courierList) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(courier.getLocation().getLat(),courier.getLocation().getLng()))
                                .title(courier.getName())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map)));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Courier>>call, Throwable t) {
                // Log error here since request failed
                Log.e("aykut", t.toString());
            }
        });



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (lastKnownPoint == null) {
            Log.d("aykut", "noooo");
            lastKnownPoint = new LatLng(41, 28);
        } else {
            Log.d("aykut", lastKnownPoint.toString());
        }

        //add marker to your last known location and move camera there
        Marker m = mMap.addMarker(new MarkerOptions().position(lastKnownPoint)
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation))
                .title("Me"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lastKnownPoint));

        //set marker click listener
        mMap.setOnMarkerClickListener(this);

        //set map click listener
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                //clear map first
                mMap.clear();
                courierList.clear();

                //add marker to clicked center position
                Marker m = mMap.addMarker(new MarkerOptions().position(point)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_package))
                        .title("Order Location")
                        .snippet("order center point"));

                //move camera to clicked point
                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));

                findPointsAroundbyPoint(new Location(point.latitude,point.longitude));


            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        //go to courier's order page
        Intent intent = new Intent(this, GiveOrder.class);
        //create mymarker class extending marker and add id field to it
        intent.putExtra("travellerId", 1);
        startActivity(intent);


        return false;
    }


    private void drawCircle(LatLng point) {

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(300000);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x500084d3);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

    }
}
