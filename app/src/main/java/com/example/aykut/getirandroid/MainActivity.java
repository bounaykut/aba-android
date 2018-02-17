package com.example.aykut.getirandroid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Movie;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.aykut.getirandroid.activities.AvailableCourier;
import com.example.aykut.getirandroid.activities.GiveOrder;
import com.example.aykut.getirandroid.activities.GoMyOrdersActivity;
import com.example.aykut.getirandroid.retrofit.model.Courier;
import com.example.aykut.getirandroid.retrofit.rest.ApiClient;
import com.example.aykut.getirandroid.retrofit.rest.ApiInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

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
    private ArrayList<Marker> markerInCircle = new ArrayList<>();

    LatLng lastKnownPoint;


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

            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Courier>> call = apiService.getAllCouriersSortedByDate();

        call.enqueue(new Callback<List<Courier>>() {
            @Override
            public void onResponse(Call<List<Courier>>call, Response<List<Courier>> response) {
                List<Courier> couriers = response.body();
                Log.d("aykut", "Number of couriers received: " + couriers.size());
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

                //add marker to clicked center position
                Marker m = mMap.addMarker(new MarkerOptions().position(point)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_package))
                        .title("Order Location")
                        .snippet("order center point"));
                markerInCircle.add(m);

                //move camera to clicked point
                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));

                //draw blue circle centered at the clicked point
                drawCircle(point);


                //add markers to points around the center point
                ArrayList<LatLng> pointsInCircle = new ArrayList<>();
                pointsInCircle.add(new LatLng(-33, 152));//test data
                pointsInCircle.add(new LatLng(-32, 152));
                pointsInCircle.add(new LatLng(-33, 150));
                for (LatLng latlng : pointsInCircle) {
                    mMap.addMarker(new MarkerOptions().position(latlng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map))
                            .snippet("Ali Armagan"));
                }


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
