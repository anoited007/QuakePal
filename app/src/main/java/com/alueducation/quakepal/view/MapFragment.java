
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alueducation.quakepal.model.Earthquake;
import com.alueducation.quakepal.R;
import com.alueducation.quakepal.model.SharedViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private SharedViewModel mViewModel;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_map, container, false);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            final String API_KEY = "AIzaSyD_GFSmWk6irk0EAPE4Ei2XuHQTts0CzAU";
            mapViewBundle = savedInstanceState.getBundle(API_KEY);
        }
        mapView =  root.findViewById(R.id.map);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null){
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null){
            mapView.onPause();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null){
            mapView.onLowMemory();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView != null){
            mapView.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final String TAG = "Map Fragment";
        Collection<List<Earthquake>> earthquakes = mViewModel.getEarthquakes().values();
        Iterator<List<Earthquake>> earthquakeIterator =  earthquakes.iterator();

        while (earthquakeIterator.hasNext()){

                try {
                    for (int i = 0; i < earthquakeIterator.next().size(); i++) {
                        Earthquake earthquake = earthquakeIterator.next().get(i);
                        googleMap.addMarker(createMarker(earthquake));
                    }
                } catch (NoSuchElementException | IndexOutOfBoundsException ex){
                    Log.e(TAG, "The element is not available in collection");
                }
            }
        }


    static MarkerOptions createMarker(Earthquake earthquake){
        MarkerOptions options = new MarkerOptions();
        String geoCordinates = earthquake.getGeoCoordinates();
        double lat = Double.parseDouble(geoCordinates.split(",")[0]);
        double lng = Double.parseDouble(geoCordinates.split(",")[1]);
        LatLng latLng = new LatLng(lat, lng);
        options.position(latLng);
        options.title(earthquake.getTitle().split(",")[0]);

        return options;
    }

}
