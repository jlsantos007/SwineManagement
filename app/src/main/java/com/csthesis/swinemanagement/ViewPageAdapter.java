package com.csthesis.swinemanagement;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by JL on 09/07/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragment = new ArrayList<>();
    ArrayList<String>tabTitles = new ArrayList<>();

    public void addFragments(Fragment fragments, String titles){
        this.fragment.add(fragments);
        this.tabTitles.add(titles);

    }

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}