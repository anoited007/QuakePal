package com.alueducation.quakepal;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class SharedViewModel extends ViewModel {
     static MutableLiveData<Map<String, List>> earthquakes;

     public LiveData<Map<String, List>> getEarthquakes(){
         if (earthquakes == null){
             earthquakes = new MutableLiveData<>();
             loadData();
         }
         return earthquakes ;
     }



    private void loadData(){
         final String TAG = "SharedViewModel";
        try {
            new FeedParser().execute(new URL( "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml"));
        } catch (MalformedURLException e) {
            Log.e(TAG, e.toString());
        }
    }







}
