package com.SevenNine.Essentials.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;

import org.json.JSONObject;

public class EmptyFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout backfeed,feedback_lay,main_layout,about_lay;
    TextView no_data,continue_text,toolbar_title;
    ImageView no_data_image;
    JSONObject lngObject;
    LinearLayout back_feed;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    SessionManager sessionManager;
    String description,status,message,fd_sucess,fd_failure,feeddesc;
    public static EmptyFragment newInstance() {
        EmptyFragment fragment = new EmptyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_layout, container, false);
       /* no_data_image=view.findViewById(R.id.no_dat_img);
        no_data=view.findViewById(R.id.no_data);
        continue_text=view.findViewById(R.id.continue_text);*/
        toolbar_title=view.findViewById(R.id.toolbar_title);
      //  back_feed=view.findViewById(R.id.back_feed);

        sessionManager=new SessionManager(getActivity());

       /* if (NewAddressDetails_Fragment.address==null){
            no_data_image.setVisibility(View.GONE);
            no_data.setText("No address is added");
        }else if (BankAccount_Fragment.bank_details==null){
            no_data_image.setVisibility(View.GONE);
            no_data.setText("No Bank detail is added");
        }
*/

       /* back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* selectedFragment = LoansListFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("dddff");
                transaction.commit();
*//*
            }
        });
*/
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   /* selectedFragment = LoansListFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("dddff");
                    transaction.commit();*/
                    return true;
                }
                return false;
            }
        });

        return view;
    }


    }
