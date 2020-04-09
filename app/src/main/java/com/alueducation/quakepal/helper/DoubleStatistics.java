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
    private double min = Double.NEGATIVE_INFINITY; //Initialise the minimum value to the lowest it can be.
    private double max = Double.POSITIVE_INFINITY; //Initialise the maximum value to the highest it can be.

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
        return min;
    }

    public double getMax() {
        return max;
    }
    public final double getAverage() {
        return getCount() > 0 ? getSum() / getCount() : 0.0;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "{count=%d, sum=%f, min=%f, average=%f, max=%f}",
                getCount(),
                getSum(),
                getMin(),
                getAverage(),
                getMax());
    }

}
