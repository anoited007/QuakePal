
/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alueducation.quakepal.R;
import com.alueducation.quakepal.view.IncidentFragment;
import com.alueducation.quakepal.view.MapFragment;
import com.alueducation.quakepal.view.StatisticsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private final Context context;
    private final int[] TAB_TITLES = { R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};


    public PagerAdapter(Context context, FragmentManager fm, int numOfTabs){
        super(fm);
        this.context = context;
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return IncidentFragment.newInstance();

            case 1:
                return StatisticsFragment.newInstance();

            case 2:
                return MapFragment.newInstance() ;

            default:
//          This line is not likely to run but it's good to have a default in a switch case
//          and so we return just a normal fragment in that case.
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
