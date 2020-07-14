package com.SevenNine.essentialscode.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.SevenNine.essentialscode.R;

import org.json.JSONObject;


public class Addnewbankdetailswithifsc extends Fragment {



    TextView toolbar_title;
    LinearLayout back_feed,continuebtn;
    Fragment selectedFragment;
    JSONObject lngObject;
    EditText ifsc,ifsc2,sav,sav2;
    public static Addnewbankdetailswithifsc newInstance() {
        Addnewbankdetailswithifsc fragment = new Addnewbankdetailswithifsc();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addnewbank_layout, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        ifsc=view.findViewById(R.id.ifsc_code);
        ifsc2=view.findViewById(R.id.enterifsc);
        sav=view.findViewById(R.id.savingsbank);
        sav2=view.findViewById(R.id.confirmsavings);
        continuebtn=view.findViewById(R.id.continuebtn);


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if(getArguments().getString("status_ifsc").equals("do_u_have_ifsc.getText().toString()")){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }else{*/
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);
              //  }
            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });


        ifsc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(ifsc,ifsc2);
                return false;
            }
        });

        ifsc2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(ifsc2,ifsc);
                return false;
            }
        });

        sav.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(sav,sav2);
                return false;
            }
        });

        sav2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(sav2,sav);
                return false;
            }
        });


        return view;
    }

    public void linear_layout_selection(EditText selectdl1, EditText l2){
        selectdl1.setBackgroundResource(R.drawable.border_1_layout);
        l2.setBackgroundResource(R.drawable.request_price_white_border);
    }

}
