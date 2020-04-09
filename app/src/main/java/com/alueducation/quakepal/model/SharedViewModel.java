/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.alueducation.quakepal.helper.FeedParser;
import com.alueducation.quakepal.view.IncidentFragment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SharedViewModel extends ViewModel {
     public static Map<String, List<Earthquake>> earthquakes;
     private List<Earthquake> sortedByDepth;
     private List<Earthquake> sortedByMagnitude;


     public SharedViewModel(){
         loadData();
     }

     public Map<String, List<Earthquake>> getEarthquakes(){
         if (earthquakes == null){
             loadData();
             System.out.println(earthquakes);
         }
         return earthquakes ;
     }


//     remove type checking  warning when assigning result to earthquakes.
private void loadData(){
         final String TAG = "SharedViewModel";
        List<Earthquake> earthquakeList;
        try {
            earthquakes = new FeedParser().execute(new URL( "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml")).get();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            Log.e(TAG, e.toString());
        }
        sortedByMagnitude =  IncidentFragment.toList(earthquakes.values());
        sortedByDepth = sortedByMagnitude;
        Collections.sort(sortedByMagnitude);
        Collections.sort(sortedByDepth, new Comparator<Earthquake>() {
            @Override
            public int compare(Earthquake o1, Earthquake o2) {
                return Integer.parseInt(o1.getDepth().split(" ")[1]) - Integer.parseInt(o2.getDepth().split(" ")[1]);
            }
        });
    }

    public List<Earthquake> getSortedByDepth() {
        return sortedByDepth;
    }

    public List<Earthquake> getSortedByMagnitude() {
        return sortedByMagnitude;
    }
}
