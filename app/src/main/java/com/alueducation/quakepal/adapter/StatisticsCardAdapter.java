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
import com.alueducation.quakepal.model.Statistics;

import java.util.List;

public class StatisticsCardAdapter extends RecyclerView.Adapter<StatisticsCardAdapter.StatisticsViewHolder> {
    private List<Statistics> summaryStatistics;

    public StatisticsCardAdapter(List<Statistics> summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
    }

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_card, parent, false);
        return new StatisticsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsCardAdapter.StatisticsViewHolder holder, int position) {
        Statistics statistics = summaryStatistics.get(position);
        holder.value.setText(statistics.getValue().toString());
        holder.description.setText(statistics.getDescription());
    }

    @Override
    public int getItemCount() {
        return summaryStatistics.size();
    }

    class StatisticsViewHolder extends RecyclerView.ViewHolder{
        TextView value, description;

         StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.TextView_StatsValue);
            description = itemView.findViewById(R.id.TextView_StatsDescription);
        }
    }
}
