package com.SevenNine.essentialscode.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Adapter.What_Areu_Looking_Adapter;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.Bean.Sellbean;

import org.json.JSONArray;

import java.util.ArrayList;


public class What_Are_looking extends Fragment {

    public static ArrayList<Sellbean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static What_Areu_Looking_Adapter livestock_types_adapter;
    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;

    public static What_Are_looking newInstance() {
        What_Are_looking fragment = new What_Are_looking();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.livestock_recy_layout, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.linearLayout);

       // sellingdetailsid=Inventory_Details_Fragment.SId;
        System.out.println("selleditiddd"+sellingdetailsid);

        if (getArguments() != null) {
            sellnavigation= String.valueOf(getArguments().getString("navg_from").equals("invtry_details"));
        }


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.commit();
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.commit();

                    return true;
                }

                return false;
            }
        });


       /* newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Sellbean bean = new Sellbean("Vegitables","1",R.drawable.veg);
        newOrderBeansList.add(bean);
        Sellbean bean1 = new Sellbean("Fruits","1",R.drawable.fruit);
        newOrderBeansList.add(bean1);
        Sellbean bean2 = new Sellbean("Groceries","1",R.drawable.groceries);
        newOrderBeansList.add(bean2);
        Sellbean bean3 = new Sellbean("Cooking Oils","1",R.drawable.cooking_oil);
        newOrderBeansList.add(bean3);
        Sellbean bean4 = new Sellbean("Masala","1",R.drawable.masala);
        newOrderBeansList.add(bean4);
        Sellbean bean5 = new Sellbean("Bakery","1",R.drawable.bakery);
        newOrderBeansList.add(bean5);
        Sellbean bean6 = new Sellbean("Dairy","1",R.drawable.dairy);
        newOrderBeansList.add(bean6);
        Sellbean bean7 = new Sellbean("Beverages","1",R.drawable.beverages);
        newOrderBeansList.add(bean7);
        livestock_types_adapter=new What_Areu_Looking_Adapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(livestock_types_adapter);*/

        return view;
    }



}
