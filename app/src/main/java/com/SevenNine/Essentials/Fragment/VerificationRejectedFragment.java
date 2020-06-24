package com.SevenNine.Essentials.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.SevenNine.Essentials.Activity.Status_bar_change_singleton;
import com.SevenNine.Essentials.R;

public class VerificationRejectedFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout Continue,backfeed;

    public static VerificationRejectedFragment newInstance() {
        VerificationRejectedFragment fragment = new VerificationRejectedFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verifi_rejected_layout, container, false);

        Status_bar_change_singleton.getInstance().color_change(getActivity());

        Continue = view.findViewById(R.id.cont_btn);
        backfeed = view.findViewById(R.id.back_feed);

        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("verification", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                  //  HomeMenuFragment.onBack_status = "farms";

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("verification", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        /*Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = GuidelinesFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.commit();

            }
        });*/






        return view;
    }
}

