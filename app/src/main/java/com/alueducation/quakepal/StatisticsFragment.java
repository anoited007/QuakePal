/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class StatisticsFragment extends Fragment {

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

}
