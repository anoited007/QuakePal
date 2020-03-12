package com.alueducation.quakepal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IncidentFragment();

            case 1:
                return new StatisticsFragment();

            case 2:
                return new MapFragment() ;

            default:
//          This line is not likely to run but it's good to have a default in a switch case
//          and so we return just a normal fragment in that case.
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
