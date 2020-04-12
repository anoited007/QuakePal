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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alueducation.quakepal.R;
import com.alueducation.quakepal.model.Earthquake;

import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {
    private List<Earthquake> earthquakeList;
    private Listener listener;

    public interface Listener{
        void onClick(int position);
    }

    public EarthquakeAdapter(List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView itemView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_recycler, parent, false);
        return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        Earthquake earthquake = earthquakeList.get(position);
        holder.title.setText(String.format(Locale.getDefault(), "Title: %s", earthquake.getTitle().split(":")[0]) );
        holder.location.setText(String.format(Locale.getDefault(), "Location: %s", earthquake.getLocation() ));
        holder.magnitude.setText(String.format(Locale.getDefault(), "Magnitude: %.2f", earthquake.getMagnitude()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }




    static class EarthquakeViewHolder extends RecyclerView.ViewHolder {
         TextView title, location, magnitude;
         CardView cardView;

         EarthquakeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView;
            title = itemView.findViewById(R.id.TextView_Title_Recycler);
            location = itemView.findViewById(R.id.TextView_Location_Recycler);
            magnitude = itemView.findViewById(R.id.TextView_Magnitude_Recycler);
        }


     }
}
