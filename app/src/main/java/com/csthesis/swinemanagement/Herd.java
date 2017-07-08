package com.csthesis.swinemanagement;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Herd extends Fragment {


    public Herd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView   = inflater.inflate(R.layout.fragment_herd, container, false);
        ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabs);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getFragmentManager());

        viewPageAdapter.addFragments(new LactatingFragment(), "LACTATION");
        viewPageAdapter.addFragments(new WeanlingFragment(), "WEANLING");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return inflatedView;
    }

}
