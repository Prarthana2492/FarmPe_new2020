package com.SevenNine.Essentials.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.Essentials.Adapter.MainCategoryAdapter;
import com.SevenNine.Essentials.Bean.MainAdapterBean;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.Urls;
import com.SevenNine.Essentials.Volly_class.Crop_Post;
import com.SevenNine.Essentials.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiscoverCategoryFragment_2 extends Fragment {

    public static ArrayList<MainAdapterBean> newOrderBeansList = new ArrayList<>();

    public static RecyclerView recyclerView_main;
    public static MainCategoryAdapter livestock_types_adapter;
    JSONObject jsonObject1;
    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;

    public static DiscoverCategoryFragment_2 newInstance() {
        DiscoverCategoryFragment_2 fragment = new DiscoverCategoryFragment_2();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_by_cate_frag2, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark1));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView_main=view.findViewById(R.id.recycler_main2);

        linearLayout = view.findViewById(R.id.linearLayout);
//        toolbar_title.setText("Select Category");
       // sellingdetailsid=Inventory_Details_Fragment.SId;
        System.out.println("selleditiddd"+sellingdetailsid);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();

                    return true;
                }

                return false;
            }
        });
        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm1);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        /*MainAdapterBean bean = new MainAdapterBean("Root Vegetables","1",1);
        newOrderBeansList.add(bean);
        MainAdapterBean bean1 = new MainAdapterBean("Leafy Vegetables","1",1);
        newOrderBeansList.add(bean1);
       *//* MainAdapterBean bean33 = new MainAdapterBean("Masala","1",1);
        newOrderBeansList.add(bean33);*//*
        livestock_types_adapter=new MainCategoryAdapter(getActivity(),newOrderBeansList);
        recyclerView_main.setAdapter(livestock_types_adapter);*/

        try{

            newOrderBeansList.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingTypeId",2);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetSellingCategoryList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("SellingCategoryList");

                        for(int i=0;i<get_soiltype.length();i++){

                            jsonObject1 = get_soiltype.getJSONObject(i);
                            if (jsonObject1.getString("SellingCategoryName").equals("POT")) {
                                MainAdapterBean sellbean = new MainAdapterBean(jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("SellingCategoryIcon"));

                                newOrderBeansList.add(sellbean);
                            }

                        }
                        livestock_types_adapter = new MainCategoryAdapter(getActivity(), newOrderBeansList);
                        recyclerView_main.setAdapter(livestock_types_adapter);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }



}
