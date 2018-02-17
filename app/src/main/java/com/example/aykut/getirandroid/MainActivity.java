package com.example.aykut.getirandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.aykut.getirandroid.activities.GiveOrder;
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

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    Button currentOrder;
    Button availableCourier;
    EditText searchBox;
    ImageButton searchButton;

    private GoogleMap mMap;
    private ArrayList<Marker> markerInCircle = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        currentOrder = (Button) findViewById(R.id.currentOrdersButton);
        currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        availableCourier = (Button) findViewById(R.id.availableCouriersButton);
        availableCourier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        searchBox = (EditText) findViewById(R.id.searchBox);

        searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.setOnMarkerClickListener(this);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                mMap.clear();

                Marker m = mMap.addMarker(new MarkerOptions().position(point)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_package))
                        .title("Order Location")
                        .snippet("order center point"));
                markerInCircle.add(m);

                drawCircle(point);


                //post the clicked point to backend and get points around radius with traveller infos(id,name), when click on marker
                // go to traveller order screen, start activity and send id of clicked traveller with the intent bundle
                ArrayList<LatLng> pointsInCircle = new ArrayList<>();
                pointsInCircle.add(new LatLng(-33, 152));//test data
                pointsInCircle.add(new LatLng(-32, 152));
                pointsInCircle.add(new LatLng(-33, 150));
                for(LatLng latlng : pointsInCircle){
                    mMap.addMarker(new MarkerOptions().position(latlng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map))
                            .snippet("Ali Armagan"));
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 5.0f ) );
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Intent intent = new Intent(this,GiveOrder.class);
        //create mymarker class extending marker and add id field to it
        intent.putExtra("travellerId",1);
        startActivity(intent);


        return false;
    }


    private void drawCircle(LatLng point){

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
