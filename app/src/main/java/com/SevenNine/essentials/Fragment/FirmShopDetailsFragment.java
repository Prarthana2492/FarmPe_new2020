package com.SevenNine.essentials.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.SevenNine.essentials.R;


public class FirmShopDetailsFragment extends Fragment {


    Fragment selectedFragment;
    LinearLayout Continue;
    EditText shopname,address_1;



    public static FirmShopDetailsFragment newInstance() {
        FirmShopDetailsFragment fragment = new FirmShopDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firmshopdetails_layout, container, false);



        Continue = view.findViewById(R.id.continuebtn);
        shopname = view.findViewById(R.id.shopname);
        address_1 = view.findViewById(R.id.address_1);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                  //  HomeMenuFragment.onBack_status = "farms";

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("list_address_firm", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (shopname.getText().toString().equals("")) {

                    Toast toast = Toast.makeText(getActivity(),"Enter Your Name", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();



                    //Toast.makeText(getActivity(), "Please Enter Shop Name", Toast.LENGTH_SHORT).show();

                } else if (address_1.getText().toString().equals("")) {


                    Toast toast = Toast.makeText(getActivity(),"Enter Your Address", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();



                } else {


                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                    view = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view == null) {
                        view = new View(getActivity());
                    }
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("list_address_firm");
                    transaction.commit();


                }



            }
        });







        return view;
    }
}

