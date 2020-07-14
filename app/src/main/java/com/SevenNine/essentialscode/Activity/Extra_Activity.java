package com.SevenNine.essentialscode.Activity;

import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Bean.FarmsImageBean;
import com.SevenNine.essentialscode.Fragment.VerificationFragment;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Extra_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;

    TextView toolbar_title;
    NestedScrollView profile_view;
    Fragment selectedFragment;
    String toast_internet,toast_nointernet;
    JSONObject lngObject;
    SessionManager sessionManager;
    LinearLayout back_feed,main_layout;



    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onStop()
    {
        unregisterReceiver(connectivityReceiver);
        super.onStop();
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }


    private void showSnack(boolean isConnected) {
        String message = null;
        int color=0;
        if (isConnected) {
            if(connectivity_check) {
                message = "Good! Connected to Internet";
                color = Color.WHITE;

                Toast toast = Toast.makeText(Extra_Activity.this,toast_internet, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();


                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;

            Toast toast = Toast.makeText(Extra_Activity.this,toast_nointernet, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
            toast.show();


        }
    }

    @Override
    public void onResume() {
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);
        MyApplication.getInstance().setConnectivityListener(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        checkConnection();
        sessionManager = new SessionManager(this);

        back_feed=findViewById(R.id.back_feed);
        main_layout=findViewById(R.id.main_layout);
        toolbar_title=findViewById(R.id.toolbar_title);
;

        ;
        //profile_view.setVisibility(View.GONE)





        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));



            toast_internet = lngObject.getString("GoodConnectedtoInternet");
            toast_nointernet = lngObject.getString("NoInternetConnection");



        } catch (JSONException e) {
        e.printStackTrace();
        }


        selectedFragment = VerificationFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout1, selectedFragment);
        transaction.commit();

        }

@Override
public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

        }
        }