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
import android.widget.TextView;

import com.SevenNine.essentials.R;
import com.google.maps.android.ui.IconGenerator;

import org.json.JSONArray;
import org.json.JSONObject;


public class Shop_Current_Location_Edit_new extends Fragment {

    Fragment selectedFragment;
    LinearLayout Continue,back_feed,edit_loc,linearLayout,edit_capture_photo,select_location_onclick,continu_btn;
    ImageView capture_photo;
    TextView select_loc,capture_photo_txt,capture_shop_photo_text,proceed_text,camera_edit_text,edit_text;

    JSONArray get_location_array,imagelist_array;
    String location_id;
    TextView toolbar_title,select_shop_txt;
    JSONObject lngObject;

    //public static ClusterManager<Person> mClusterManager;
    private IconGenerator mIconGenerator;


    public static Shop_Current_Location_Edit newInstance() {
        Shop_Current_Location_Edit fragment = new Shop_Current_Location_Edit();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selectcurrentlocation2, container, false);

        //  Status_bar_change_singleton.getInstance().color_change(getActivity());



        Continue = view.findViewById(R.id.continu_btn);
        back_feed = view.findViewById(R.id.back_feed);
        select_loc = view.findViewById(R.id.select_loc);
        continu_btn = view.findViewById(R.id.continu_btn);
        // select_location = view.findViewById(R.id.select_location);
        capture_photo = view.findViewById(R.id.capture_photo);
        edit_capture_photo = view.findViewById(R.id.edit_capture_photo);
        edit_loc = view.findViewById(R.id.edit_loc);

        linearLayout = view.findViewById(R.id.main_layout);
        capture_photo_txt = view.findViewById(R.id.capture_photo_txt);
        select_location_onclick = view.findViewById(R.id.select_location_onclick);
        capture_shop_photo_text = view.findViewById(R.id.capture_shop_photo_text);


        toolbar_title = view.findViewById(R.id.toolbar_title);
        select_shop_txt = view.findViewById(R.id.select_shop_txt);
        camera_edit_text = view.findViewById(R.id.edit_text);
        proceed_text = view.findViewById(R.id.proceed_text);
        edit_text = view.findViewById(R.id.edit_text);



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("selected_shop_location", FragmentManager.POP_BACK_STACK_INCLUSIVE);


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
                        fm.popBackStack("selected_shop_location", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        continu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                selectedFragment = Home_Page_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                transaction.commit();


            }
        });






        edit_capture_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = Shop_Camera_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("shop_camera");
                transaction.commit();


            }
        });






        edit_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Bundle bundle = new Bundle();
//                bundle.putString("Edit_Fragment","curr_loc_edit");
//                bundle.putString("Edit_Location_Id",location_id);
//                selectedFragment = Shop_Current_Location_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                transaction.addToBackStack("map_locatn");
//                selectedFragment.setArguments(bundle);
//                transaction.commit();


            }
        });





        capture_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectedFragment = Shop_Camera_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("shop_camera");
                transaction.commit();


            }
        });


        return view;
    }



}

