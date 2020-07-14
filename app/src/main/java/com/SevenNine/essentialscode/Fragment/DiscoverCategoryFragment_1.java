package com.SevenNine.essentialscode.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.MainCategoryAdapter;
import com.SevenNine.essentialscode.Bean.MainAdapterBean;
import com.SevenNine.essentialscode.R;

import org.json.JSONArray;

import java.util.ArrayList;


public class DiscoverCategoryFragment_1 extends Fragment {

    public static ArrayList<MainAdapterBean> newOrderBeansList = new ArrayList<>();

    public static RecyclerView recyclerView_main;
    public static MainCategoryAdapter livestock_types_adapter;

    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;
    boolean doubleBackToExitPressedOnce = false;

    public static DiscoverCategoryFragment_1 newInstance() {
        DiscoverCategoryFragment_1 fragment = new DiscoverCategoryFragment_1();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_by_cate_frag2, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));
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
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (doubleBackToExitPressedOnce) {

                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
                        intent1.addCategory(Intent.CATEGORY_HOME);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent1);
                        getActivity().finish();                   }
                    // System.exit(0);

                    // home_img.setImageResource(R.drawable.ic_home_green);

                    doubleBackToExitPressedOnce = true;

                    Toast toast = Toast.makeText(getActivity(),"Please click back again to exit", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();
                    //   Toast.makeText(getApplicationContext(), toast_click_back, Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 3000);

                }

                return true;
            }
        });
        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm1);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        MainAdapterBean bean = new MainAdapterBean("Vegetables","1","");
        newOrderBeansList.add(bean);
        MainAdapterBean bean1 = new MainAdapterBean("Fruits","1","");
        newOrderBeansList.add(bean1);
        MainAdapterBean bean2 = new MainAdapterBean("Groceries","1","");
        newOrderBeansList.add(bean2);
        MainAdapterBean bean3 = new MainAdapterBean("Cooking Oil","1","");
        newOrderBeansList.add(bean3);
       /* MainAdapterBean bean33 = new MainAdapterBean("Masala","1",1);
        newOrderBeansList.add(bean33);*/
        livestock_types_adapter=new MainCategoryAdapter(getActivity(),newOrderBeansList);
        recyclerView_main.setAdapter(livestock_types_adapter);



        return view;
    }



}
