package com.SevenNine.essentials.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.Activity.Status_bar_change_singleton;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;

import org.json.JSONObject;

public class VerificationFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout Continue,back_feed,linearLayout;
    String number;
    boolean doubleBackToExitPressedOnce = false;
    SessionManager sessionManager;
    EditText phone_no;
    JSONObject verifictn_array;
    String status;
    TextView progress,status_text,cntn_txt,only_status,toolbar_title,ph_no_text;
    ImageView status_image;
    JSONObject lngObject;
    public static String sucessful,inprogress,inprogress_full,sucessful_full;

    public static VerificationFragment newInstance() {
        VerificationFragment fragment = new VerificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_on_seven_nine, container, false);

        Status_bar_change_singleton.getInstance().color_change(getActivity());

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.commit();

                    return true;
                }
                return false;
            }
        });




        return view;
    }
}

//  scenior test