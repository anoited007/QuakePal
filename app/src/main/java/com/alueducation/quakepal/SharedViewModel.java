/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SharedViewModel extends ViewModel {
     static Map<String, List<Earthquake>> earthquakes;
     public SharedViewModel(){
         loadData();
     }

     Map<String, List<Earthquake>> getEarthquakes(){
         if (earthquakes == null){
             loadData();
             System.out.println(earthquakes);
         }
         return earthquakes ;
     }


//     remove type checking  warning when assigning result to earthquakes.
@SuppressWarnings("unchecked")
    private void loadData(){
         final String TAG = "SharedViewModel";
        try {
            earthquakes = new FeedParser().execute(new URL( "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml")).get();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            Log.e(TAG, e.toString());
        }
    }

}
