package com.SevenNine.essentialscode.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Adapter.SliderPagerAdapter;
import com.SevenNine.essentialscode.Bean.ListBean2;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Slider_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener  {

    int page_position = 0;
    ArrayList<ListBean2>apply_loan;
    LinearLayout ll_dots, cate1, cate2, cate3,main_layout;
    public static TextView proceed,banner,banner_desc;
    SessionManager sessionManager;
    public static   JSONObject lngObject;

    String toast_internet,toast_nointernet;

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

                int duration=1000;
                Snackbar snackbar = Snackbar.make(main_layout,toast_internet, duration);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setBackgroundColor(ContextCompat.getColor(Slider_Activity.this, R.color.orange));
                textView.setTextColor(Color.WHITE);
                snackbar.show();


                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;

            Snackbar.make(findViewById(android.R.id.content),toast_nointernet, duration).show();


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
        setContentView(R.layout.sliderlayoutviewpager);
        super.onCreate(savedInstanceState);

checkConnection();
        proceed = findViewById(R.id.proceed);
        banner = findViewById(R.id.banner);
        banner_desc = findViewById(R.id.banner_descr);
        main_layout = findViewById(R.id.layout);

        sessionManager = new SessionManager(this);

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

             proceed.setText(lngObject.getString("PROCEED").replace("\n",""));
            toast_nointernet =(lngObject.getString("NoInternetConnection"));
            toast_internet =(lngObject.getString("GoodConnectedtoInternet"));

//             banner.setText(lngObject.getString("MadeforFarmingCommunity"));
//            banner_desc.setText(lngObject.getString("Theconfluenceoffarmersandfairtrade"));



        } catch (JSONException e) {
            e.printStackTrace();
        }





        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        if (!hasPermissions(this, PERMISSIONS)) {
        }




        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Slider_Activity.this,NewSignUpActivity.class);
                startActivity(intent);
            }
        });



        apply_loan = new ArrayList<>();
        ListBean2 bean=new ListBean2("Vegitables",1, R.drawable.veg,1);
        apply_loan.add(bean);

        ListBean2 bean3=new ListBean2("Fruits",2, R.drawable.fruit,1);
        apply_loan.add(bean3);

        ListBean2 bean4=new ListBean2("Groceries",3, R.drawable.groceries,1);
        apply_loan.add(bean4);

        ListBean2 bean5=new ListBean2("Cooking Oils",4, R.drawable.cooking_oil,1);
        apply_loan.add(bean5);

        ListBean2 bean11=new ListBean2("Masala",5, R.drawable.masala,1);
        apply_loan.add(bean11);

        ListBean2 bean8=new ListBean2("Bakery",6, R.drawable.bakery,1);
        apply_loan.add(bean8);

        ListBean2 bean6=new ListBean2("Dairy",7, R.drawable.dairy,1);
        apply_loan.add(bean6);

        ListBean2 bean7=new ListBean2("Baverages",8, R.drawable.beverages,1);
        apply_loan.add(bean7);

        ListBean2 bean9=new ListBean2("Snacks",9, R.drawable.snacks,1);
        apply_loan.add(bean9);

        ListBean2 bean14=new ListBean2("Foods",10, R.drawable.food,1);
        apply_loan.add(bean14);
        ListBean2 bean15=new ListBean2("Beauty",11, R.drawable.beauty,1);
        apply_loan.add(bean15);
        apply_loan.add(bean15);
        apply_loan.add(bean15);
       /* ListBean2 bean16=new ListBean2("More",11,R.drawable.more_icon,1);
        apply_loan.add(bean16);*/
        final ViewPager vp_slider = (ViewPager) findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
        SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(Slider_Activity.this, apply_loan);


        vp_slider.setAdapter(sliderPagerAdapter);

        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        addBottomDots(0);
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == 3) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onBackPressed() {


        Intent intent=new Intent(Slider_Activity.this, SelectStoreLocation.class);
        startActivity(intent);
        // finish();
    }
    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[3];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(Slider_Activity.this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dots[i].setPadding(10, 0, 10, 0);
            dots[i].setTextColor(Color.parseColor("#DFDDDD"));
            ll_dots.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#E50914"));
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
showSnack(isConnected);
    }




  /*  private void getLang(int id) {

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",id);


            System.out.print("iiidddddd"+ id);

            Crop_Post.crop_posting(Slider_Activity.this, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvv" + result);

                    try{
                        sessionManager.saveLanguage(result.toString());
                        String lang_title1 = result.getString("PROCEED");

                        proceed.setText(lang_title1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
