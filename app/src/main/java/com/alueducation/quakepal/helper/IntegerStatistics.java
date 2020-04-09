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
        private int min = Integer.MIN_VALUE; //Initialise the minimum value to the lowest it can be.
        private int max = Integer.MAX_VALUE; //Initialise the maximum value to the highest it can be.

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
