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

public class IntegerStatistics {

        private int count;
        private int sum;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;

        public IntegerStatistics(){

        }

        public void accept(int value) {
            ++count;
            sum += value;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        public final int getCount(){
            return count;
        }

        public int getSum() {
            return sum;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
        public final double getAverage() {
            return Math.round(getCount() > 0 ? (double)getSum() / getCount() : 0.0) ;
        }

        @NonNull
        @Override
        public String toString() {
            return String.format(Locale.getDefault(),
                    "{count=%d, sum=%d, min=%d, average=%.2f, max=%d}",
                    getCount(),
                    getSum(),
                    getMin(),
                    getAverage(),
                    getMax());
        }

    }
