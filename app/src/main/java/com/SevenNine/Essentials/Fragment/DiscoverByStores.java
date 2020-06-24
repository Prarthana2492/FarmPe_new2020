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

import com.SevenNine.Essentials.Adapter.SelectLocationAdapter;
import com.SevenNine.Essentials.Bean.SelectLanguageBean;
import com.SevenNine.Essentials.R;

import org.json.JSONArray;

import java.util.ArrayList;


public class DiscoverByStores extends Fragment {

    public static ArrayList<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    private SelectLocationAdapter mAdapter;
    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;

    public static DiscoverByStores newInstance() {
        DiscoverByStores fragment = new DiscoverByStores();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_by_store, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark1));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView=view.findViewById(R.id.recycler_store);
        linearLayout = view.findViewById(R.id.linearLayout);


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
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SelectLanguageBean stateBean=new SelectLanguageBean("Raja Rajeshwari Nagar,",0,"");
        newOrderBeansList.add(stateBean);
        SelectLanguageBean stateBean1=new SelectLanguageBean("Raja Rajeshwari Nagar,",1,"");
        newOrderBeansList.add(stateBean1);
        SelectLanguageBean stateBean2=new SelectLanguageBean("Raja Rajeshwari Nagar,",2,"");
        newOrderBeansList.add(stateBean2);
        newOrderBeansList.add(stateBean2);
        newOrderBeansList.add(stateBean2);
        newOrderBeansList.add(stateBean2);
        newOrderBeansList.add(stateBean2);
        //  Langauges();
        mAdapter = new SelectLocationAdapter(getActivity(), newOrderBeansList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }



}
