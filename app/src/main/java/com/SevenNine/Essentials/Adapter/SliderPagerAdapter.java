package com.SevenNine.Essentials.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.SevenNine.Essentials.Bean.ListBean2;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;


public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<ListBean2> slider_text;
    RecyclerView recyclerView;
   // LoanAdapter madapter;
    public static int morecount;
    public static   JSONObject lngObject;
    SessionManager sessionManager;
    TextView banner,banner_desc,how_it_works,register,register_by,order,order_text,deliver,deliver_text,pay,pay_text,what_we_sell;


    public SliderPagerAdapter(Activity activity, ArrayList<ListBean2> slider_text) {
        this.activity = activity;
        this.slider_text = slider_text;
        sessionManager = new SessionManager(activity);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = null;
       // sessionManager = new SessionManager(activity);

        switch (position){
            case 0: view=  layoutInflater.inflate(R.layout.sliderlogofirst, container, false);
            banner=view.findViewById(R.id.banner);
            banner_desc=view.findViewById(R.id.banner_descr);

                /*try {
                    lngObject = new JSONObject(sessionManager.getRegId("language"));

                    banner.setText(lngObject.getString("MadeforFarmingCommunity"));
                   banner_desc.setText(lngObject.getString("Theconfluenceoffarmersandfairtrade"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

            break;

            case 1: view=  layoutInflater.inflate(R.layout.how_it_works_slider, container, false);
                how_it_works=view.findViewById(R.id.howit);
                register=view.findViewById(R.id.register);
                register_by=view.findViewById(R.id.registerby);
                order=view.findViewById(R.id.order);
                order_text=view.findViewById(R.id.ord_txt);
                deliver=view.findViewById(R.id.deliver);
                deliver_text=view.findViewById(R.id.dlr_txt);
                pay=view.findViewById(R.id.pay);
                pay_text=view.findViewById(R.id.pay_txt);

               /* try {
                    lngObject = new JSONObject(sessionManager.getRegId("language"));

                    how_it_works.setText(lngObject.getString("HowitWorks"));
                    register.setText("1. "+lngObject.getString("Register"));
                  //  register_by.setText(lngObject.getString("Registerbyenteringtherequireddetails"));
                  //  order.setText("2. "+lngObject.getString("AddInventory"));
                  //  order_text.setText(lngObject.getString("PlaceanInventoryintheappbyselectingtherequireditems"));
                  //  deliver.setText("3. "+lngObject.getString("Delivery"));
                  //  deliver_text.setText(lngObject.getString("Gettherequireditemsatyourshopdeliveredinthementioneddate"));
                  //  pay.setText("4. "+lngObject.getString("Pay"));
                 //  pay_text.setText(lngObject.getString("Payonlinepostorderplacement"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

            break;
//

            case 2: view=  layoutInflater.inflate(R.layout.whatwesell_slider, container, false);
                      what_we_sell =view.findViewById(R.id.wt_vsell);
/*
                try {
                    lngObject = new JSONObject(sessionManager.getRegId("language"));

                    what_we_sell.setText(lngObject.getString("WhatyoucanSell"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

              CartSliderAdapter madapter = new CartSliderAdapter(activity, slider_text);
                recyclerView=view.findViewById(R.id.rv);
                System.out.println("size "+slider_text.size());
                recyclerView.setHasFixedSize(true);
                GridLayoutManager mLayoutManager5 = new GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(mLayoutManager5);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                System.out.println("sizeeeeeee"+slider_text.size());
                madapter = new CartSliderAdapter(activity, slider_text);
                recyclerView.setAdapter(madapter);


                if(slider_text.size()>12)
                {
                    madapter = new CartSliderAdapter(activity, slider_text.subList(0,11));
                    recyclerView.setAdapter(madapter);
                    morecount = slider_text.size()-11;
                    System.out.println("morecount "+morecount);
                }
                else {
                    madapter = new CartSliderAdapter(activity, slider_text);
                    recyclerView.setAdapter(madapter);
                }
        }
        container.addView(view);



        return view;
    }

    @Override
    public int getCount() {
        return 3 ;

    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);

    }

}


