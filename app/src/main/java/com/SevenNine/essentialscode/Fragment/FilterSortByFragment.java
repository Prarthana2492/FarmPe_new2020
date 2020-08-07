package com.SevenNine.essentialscode.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;

import org.json.JSONObject;

public class FilterSortByFragment extends Fragment {
    Fragment selectedFragment;
    SessionManager sessionManager;
    TextView done;
    RadioButton price_high_low,price_low_high,alphabetic;
    public static String price_low_high_str,price_high_low_str,filter_str;
    public static FilterSortByFragment newInstance() {
        FilterSortByFragment fragment = new FilterSortByFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort_by_lay, container, false);
        done=view.findViewById(R.id.done);
        price_high_low=view.findViewById(R.id.price_high_low);
        price_low_high=view.findViewById(R.id.price_low_high);
        alphabetic=view.findViewById(R.id.alphabetic);
        FilterProductsExpandableFragment.filter_refine_by=null;
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
       /* view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    return true;
                }
                return false;
            }
        });*/

        price_low_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_low_high_str="Price_low_high";
            }
        });
        price_high_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_low_high_str="Price_High_Low";
            }
        });
        alphabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_low_high_str="alphabetic";

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_str="filter";
               /* switch(getId()) {
                    case R.id.price_low_high:
                      //  bundle.putString("Price_Low_High","Price_Low_High_V");
                        price_low_high_str="Price_Low_High";
                        break;
                    case R.id.price_high_low:
                      //  bundle.putString("Price_high_low","Price_high_low_V");
                        price_high_low_str="Price_High_Low";

                        break;
                    default:
                        break;
                }*/
                Bundle bundle=new Bundle();
                bundle.putString("filter_sort","filter_sort");
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("dddff");
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });

        return view;
    }


    }
