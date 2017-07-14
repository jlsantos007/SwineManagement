package com.csthesis.swinemanagement;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
        PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i < tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }

        return inflatedView;
    }

    @Override
    public void onResume() { super.onResume();}

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    class PagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[]{"Breed", "Lactation", "Weanling", "Growers"};
        Context context;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new BreedingFragment();
                case 1:
                    return new LactationFragment();
                case 2:
                    return new WeanlingFragment();
            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return tabTitles[position];
        }

        public View getTabView(int position){
            View tab = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }
    }
}
