/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.alueducation.quakepal.R;
import com.alueducation.quakepal.helper.MenuActivity;
import com.alueducation.quakepal.model.Earthquake;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Locale;

public class EarthquakeDetailActivity extends MenuActivity implements OnMapReadyCallback {

    private MapView mapView;
    Earthquake earthquake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         TextView  location, category, cordinates, link, magnitude, depth;
        final String TAG = "EARTHQUAKE DETAILS";
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_earthquake_detail);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        location = findViewById(R.id.TextView_Location_Detail);
        category = findViewById(R.id.TextView_Category_Detail);
        cordinates = findViewById(R.id.TextView_GeoCordinates_Detail);
        link = findViewById(R.id.TextView_Link_Detail);
        magnitude = findViewById(R.id.TextView_Magnitude_Detail);
        depth = findViewById(R.id.TextView_Depth_Detail);

       try {
           earthquake = (Earthquake) getIntent().getSerializableExtra("earthquake");
           if (earthquake != null) {
               location.setText(String.format(Locale.getDefault(), "Location: %s ", earthquake.getLocation()));
               category.setText(String.format(Locale.getDefault(), "Category: %s ", earthquake.getCategory()));
               cordinates.setText(String.format(Locale.getDefault(), "Geo-cordinates: %s ", earthquake.getGeoCoordinates()));
               link.setText(String.format(Locale.getDefault(), "Link: %s ", earthquake.getLink()));
               magnitude.setText(String.format(Locale.getDefault(), "Magnitude: %f ", earthquake.getMagnitude()));
               depth.setText(String.format(Locale.getDefault(), "Depth: %s ", earthquake.getDepth()));
               mapView = findViewById(R.id.MapView_Map_Details);

           }

       }catch (ClassCastException ex) {
           Log.e(TAG, ex.toString());
       }

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            final String API_KEY = "AIzaSyD_GFSmWk6irk0EAPE4Ei2XuHQTts0CzAU";
            mapViewBundle = savedInstanceState.getBundle(API_KEY);
        }
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (earthquake != null) {
            googleMap.addMarker(MapFragment.createMarker(earthquake));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
