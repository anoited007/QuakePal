/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alueducation.quakepal.R;
import com.alueducation.quakepal.model.Earthquake;

import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {
    private List<Earthquake> earthquakeList;

    public EarthquakeAdapter(List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_recycler, parent, false);
        return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = earthquakeList.get(position);
        holder.title.setText(earthquake.getTitle());
        holder.location.setText(earthquake.getLocation());
        holder.magnitude.setText(String.valueOf(earthquake.getMagnitude()));
    }

    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }

     class EarthquakeViewHolder extends RecyclerView.ViewHolder {
         TextView title, location, magnitude;

         EarthquakeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView_Title_Recycler);
            location = itemView.findViewById(R.id.TextView_Location_Recycler);
            magnitude = itemView.findViewById(R.id.TextView_Magnitude_Recycler);
        }
    }
}
