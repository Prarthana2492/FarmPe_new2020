package com.SevenNine.essentials.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.SevenNine.essentials.R;


public class Shop_Location_Fragment extends Fragment {



    Fragment selectedFragment;
    LinearLayout Continue,back_feed;
    ImageView select_location,capture_image;



    public static Shop_Location_Fragment newInstance() {
        Shop_Location_Fragment fragment = new Shop_Location_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_location_layout, container, false);

        //  Status_bar_change_singleton.getInstance().color_change(getActivity());

        Continue = view.findViewById(R.id.continu_btn);
        back_feed = view.findViewById(R.id.back_feed);
        select_location = view.findViewById(R.id.select_location);
        capture_image = view.findViewById(R.id.capture_image);




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("list_address_firm", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    HomeMenuFragment.onBack_status = "farms";
//
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("list_address_firm", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });


        select_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectedFragment = Shop_Current_Location_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("shop_locatn");
                transaction.commit();

            }
        });



        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = Shop_Camera_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("shop_camera");
                transaction.commit();


            }
        });


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                selectedFragment = GuidelinesFragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                transaction.commit();

            }
        });



        return view;
    }
}

