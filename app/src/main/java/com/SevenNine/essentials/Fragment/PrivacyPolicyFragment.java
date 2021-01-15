package com.SevenNine.essentials.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;


public class PrivacyPolicyFragment extends Fragment {
    Fragment selectedFragment;

    LinearLayout  back_feed;
    public static String status;

    JSONObject lngObject;
    TextView privacypolicytxt;
    WebView terms;
SessionManager sessionManager;
    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        return fragment;
    }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_policy, container, false);

      //   HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);
       //  Status_bar_change_singleton.getInstance().color_change(getActivity());
        back_feed=view.findViewById(R.id.back_feed);
        privacypolicytxt=view.findViewById(R.id.setting_tittle);
        privacypolicytxt.setText("Privacy Policy");
        terms=view.findViewById(R.id.web_terms);
       // terms.loadUrl("http://farmpe.in/privacy.html");
        terms.loadUrl("http://farmpe.in");
        sessionManager = new SessionManager(getActivity());


   // System.out.println("eewqewqe" + getArguments().getString("status"));

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


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                fm.popBackStack();

            }
        });

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            privacypolicytxt.setText(lngObject.getString("PrivacyPolicy"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}

