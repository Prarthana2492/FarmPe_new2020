package com.SevenNine.essentials.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.SevenNine.essentials.TabLayoutAdapter.PagerPayment;
import com.SevenNine.essentials.R;

import org.json.JSONObject;

public class PaymentsTabLayout extends Fragment implements TabLayout.OnTabSelectedListener {

    Fragment selectedFragment;
    FloatingActionButton compose_msg;

    JSONObject jsonObject;
    String bmmvendorstoreid;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    LinearLayout back_feed;

    public static PaymentsTabLayout newInstance() {
        PaymentsTabLayout fragment = new PaymentsTabLayout();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_tab_layout, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));

        //  compose_msg=view.findViewById(R.id.fab__compose);
        tabLayout = view.findViewById(R.id.simpleTabLayout_land);
        tabLayout.addTab(tabLayout.newTab().setText("Dashboard"));
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("This Week"));
        back_feed=view.findViewById(R.id.back_feed);

        tabLayout.setOnTabSelectedListener(this);

        viewPager = view.findViewById(R.id.simpleViewPager_tab);
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

                    return true;
                }
                return false;

            }
        });

        PagerPayment adapter = new PagerPayment(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
