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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.alueducation.quakepal.helper.DoubleStatistics;
import com.alueducation.quakepal.model.Earthquake;
import com.alueducation.quakepal.helper.IntegerStatistics;
import com.alueducation.quakepal.R;
import com.alueducation.quakepal.model.SharedViewModel;
import com.alueducation.quakepal.model.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsFragment extends Fragment {

    private DoubleStatistics magnitudeStats;
    private IntegerStatistics depthStats;
    private SharedViewModel mViewModel;
    private RecyclerView statistics;


    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View statisticsRoot =  inflater.inflate(R.layout.fragment_statistics, container, false);
        statistics = statisticsRoot.findViewById(R.id.RecyclerView_Statistics);
        return statisticsRoot;
    }

    private Integer depthToInt(String depth){
        depth = depth.replace(" km", "").trim();
        try {
             return Integer.parseInt(depth);
        }catch (Exception ex){
            Log.e("Parsing", ex.toString());
        }
        return -1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        List<Statistics> statisticsList = new ArrayList<>();
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        Map<String, List<Earthquake>> earthquakes = mViewModel.getEarthquakes();
        List<Earthquake> earthquakeList = IncidentFragment.toList(earthquakes.values());
//        Use lambda expression to get the data and compute summary stats.
//        Abandoning lambda because it require API level 24 at least.
//        magnitudeStats = earthquakeLIst.stream().mapToDouble((earthquake) -> earthquake.getMagnitude()).summaryStatistics();

        magnitudeStats = new DoubleStatistics();
        depthStats = new IntegerStatistics();

        for (Earthquake earthquake : earthquakeList) {
            magnitudeStats.accept(earthquake.getMagnitude());

            if (depthToInt(earthquake.getDepth()) != -1){
                depthStats.accept(depthToInt(earthquake.getDepth()));
            }
        }




    }
}
