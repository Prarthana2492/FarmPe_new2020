package com.SevenNine.essentialscode.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class AaNotificationSetting extends Fragment {
    Fragment selectedFragment;
    LinearLayout backfeed,acc_info_lay,not_lay;
    TextView lang_title,all_text,all,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SwitchCompat switch_noti;
    JSONObject lngObject;
    SessionManager sessionManager;
    public static AaNotificationSetting newInstance() {
        AaNotificationSetting fragment = new AaNotificationSetting();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_n_notisetting_layout, container, false);

        backfeed=view.findViewById(R.id.back_feed);
        acc_info_lay=view.findViewById(R.id.acc_info_lay);
        switch_noti=view.findViewById(R.id.switch_noti);
        lang_title=view.findViewById(R.id.lang_title);
        all_text=view.findViewById(R.id.all_text);
        all=view.findViewById(R.id.all);

        switch_noti.setChecked(true);

        sessionManager=new SessionManager(getActivity());
        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("notifi", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("notifi", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            all_text.setText(lngObject.getString("All").replace("\n",""));
            all.setText(lngObject.getString("AllNotifications").replace("\n",""));
            lang_title.setText(lngObject.getString("Notifications").replace("\n",""));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }



}
