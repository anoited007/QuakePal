/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.helper;

import androidx.annotation.NonNull;

import java.util.Locale;

public class DoubleStatistics {
    private int count;
    private double sum;
    // Ensure the initial minimum value is set to the maximum double available to ensure that the next element becomes the min
    // and hence force further comparisons.
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;

    public DoubleStatistics(){

    }


    public void accept(double value) {
        ++count;
        sum += value;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public final int getCount(){
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getMin() {
        return Math.round(min);
    }

    public double getMax() {
        return Math.round(max);
    }
    public final double getAverage() {
        return Math.round(getCount() > 0 ? getSum() / getCount() : 0.0);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "{count=%d, sum=%.2f, min=%.2f, average=%.2f, max=%.2f}",
                getCount(),
                getSum(),
                getMin(),
                getAverage(),
                getMax());
    }

}
