package com.SevenNine.essentialscode.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.TabLayoutAdapter.PagerFilter;
import com.SevenNine.essentialscode.TabLayoutAdapter.PagerOrder;

import org.json.JSONObject;

public class FilterTabFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    Fragment selectedFragment;
    FloatingActionButton compose_msg;

    JSONObject jsonObject;
    String bmmvendorstoreid;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    LinearLayout back_feed;

    public static FilterTabFragment newInstance() {
        FilterTabFragment fragment = new FilterTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_tab_lay, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        tabLayout = view.findViewById(R.id.simpleTabLayout_land);
        tabLayout.addTab(tabLayout.newTab().setText("Refine by"));
        tabLayout.addTab(tabLayout.newTab().setText("Sort by"));

        tabLayout.setOnTabSelectedListener(this);

        viewPager = view.findViewById(R.id.simpleViewPager_tab);

        PagerFilter adapter = new PagerFilter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();
                    /*selectedFragment = OffersListFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("track24");
                    transaction.commit();*/
                    return true;
                }
                return false;

            }
        });


        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
