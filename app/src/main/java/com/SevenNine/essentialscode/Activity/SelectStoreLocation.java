package com.SevenNine.essentialscode.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.SelectLocationAdapter;
import com.SevenNine.essentialscode.Bean.SelectLanguageBean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectStoreLocation extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener  {
    private List<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SelectLocationAdapter mAdapter;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout linearLayout;
    public  static  TextView continue_lng,select_ur_language,selct_ur_lng;
    SessionManager sessionManager;
    public static   JSONObject lngObject;

    public static String toast_internet,toast_nointernet;

    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("loginonStart");
        //  sessionManager.checkLogin();
       // sessionManager.checkRegister();

    }

    @Override
    protected void onStop()
    {
        unregisterReceiver(connectivityReceiver);
        super.onStop();
    }
    ////
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

                Toast toast = Toast.makeText(SelectStoreLocation.this,toast_internet, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();


                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;

            Toast toast = Toast.makeText(SelectStoreLocation.this,toast_nointernet, Toast.LENGTH_LONG);
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
        setContentView(R.layout.a_a_selectlang);
        //  checkConnection();
        recyclerView =findViewById(R.id.recycler_view_lang);
        linearLayout= findViewById(R.id.main_layout);
        continue_lng= findViewById(R.id.continue_lang);
        select_ur_language= findViewById(R.id.selct_ur_lng);
        selct_ur_lng= findViewById(R.id.selct_ur_lng);

        selct_ur_lng.setText("Select 7Nine Store Locations");
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(SelectStoreLocation.this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SelectLanguageBean stateBean=new SelectLanguageBean("Raja Rajeshwari Nagar,",0,"");
        newOrderBeansList.add(stateBean);
        SelectLanguageBean stateBean1=new SelectLanguageBean("Raja Rajeshwari Nagar,",1,"");
        newOrderBeansList.add(stateBean1);
        SelectLanguageBean stateBean2=new SelectLanguageBean("Raja Rajeshwari Nagar,",2,"");
        newOrderBeansList.add(stateBean2);
        newOrderBeansList.add(stateBean2);
        //  Langauges();
        mAdapter = new SelectLocationAdapter(SelectStoreLocation.this, newOrderBeansList);
        recyclerView.setAdapter(mAdapter);
        sessionManager = new SessionManager(this); //check

        continue_lng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectStoreLocation.this, Slider_Activity.class);
                startActivity(intent);

            }
        });

       /* try {
            if ((sessionManager.getRegId("language")).equals("")) {
                getLang(1);

            } else {

                lngObject = new JSONObject(sessionManager.getRegId("language"));

                select_ur_language.setText(lngObject.getString("SelectYourLanguage").replace("\n",""));
                continue_lng.setText(lngObject.getString("PROCEED").replace("\n",""));
               toast_nointernet =(lngObject.getString("NoInternetConnection"));
               toast_internet =(lngObject.getString("GoodConnectedtoInternet"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }


    private void getLang(int id) {

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",id);


            System.out.print("iiidddddd"+ id);

            Crop_Post.crop_posting(SelectStoreLocation.this, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvv" + result);

                    try{
                        sessionManager.saveLanguage(result.toString());
                        String lang_title1 = result.getString("PROCEED").replace("\n","");
                        String select = result.getString("SelectYourLanguage").replace("\n","");
                        toast_nointernet =(lngObject.getString("NoInternetConnection"));
                        toast_internet =(lngObject.getString("GoodConnectedtoInternet"));


                        continue_lng.setText(lang_title1);
                        select_ur_language.setText(select);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Langauges() {
        try {

            JSONObject postjsonObject = new JSONObject();

            Login_post.login_posting(SelectStoreLocation.this, Urls.Languages,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss"+result);
                    JSONObject jsonObject;
                    try {
                        JSONArray jsonArray=result.getJSONArray("LanguagesList");
                        newOrderBeansList.clear();

                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String language=jsonObject1.getString("Language");
                            int langCode=jsonObject1.getInt("Id");
                            String langimg=jsonObject1.getString("ImageIcon");
                            SelectLanguageBean stateBean=new SelectLanguageBean(language,langCode,langimg);
                            newOrderBeansList.add(stateBean);

                        }

                        mAdapter = new SelectLocationAdapter(SelectStoreLocation.this, newOrderBeansList);
                        recyclerView.setAdapter(mAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SelectStoreLocation.this, ActivitySelectLang.class);
        startActivity(intent);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
