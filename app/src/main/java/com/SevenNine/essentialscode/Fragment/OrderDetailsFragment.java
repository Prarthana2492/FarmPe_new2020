package com.SevenNine.essentialscode.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import com.SevenNine.essentialscode.Adapter.OrderDetailsAdapter;
import com.SevenNine.essentialscode.Bean.OrderDetailBean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OrderDetailsFragment extends Fragment {

    public static List<OrderDetailBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    OrderDetailsAdapter madapter;
    JSONObject lngObject;
    public static String order_details;
    Fragment selectedFragment;
    String date_str;
    TextView toolbar_title,ordered_on,items_cost,before_tax,total_amt,total_sum_amt,item_count,name_vw,pay_mode,method;

    public static OrderDetailsFragment newInstance() {
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_details_layout, container, false);
        recyclerView=view.findViewById(R.id.new_order_recy);
        back_feed=view.findViewById(R.id.back_feed);
        ordered_on=view.findViewById(R.id.ordered_on);
        items_cost=view.findViewById(R.id.items_cost);
        before_tax=view.findViewById(R.id.before_tax);
        total_sum_amt=view.findViewById(R.id.total_sum_amt);
        total_amt=view.findViewById(R.id.total_amt);
        item_count=view.findViewById(R.id.item_count);
        name_vw=view.findViewById(R.id.name_vw);
        pay_mode=view.findViewById(R.id.payment_mode);
        method=view.findViewById(R.id.method);


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_details="order";
                /*FragmentManager fm = getFragmentManager();
                fm.popBackStack();*/
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("dhsksw");
                transaction.commit();
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    order_details="order";
                /*FragmentManager fm = getFragmentManager();
                fm.popBackStack();*/
                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("dhsksw");
                    transaction.commit();

                    return true;
                }
                return false;

            }
        });
/*date_str=getArguments().getString("createdon");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try {
            Date date = format.parse(date_str);
            System.out.println(date);
            ordered_on.setText(date.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);;
        formatter .applyPattern("##,##,##,###");
        double rate_double1= (Double.parseDouble(getArguments().getString("Amount")));
        ordered_on.setText(getArguments().getString("createdon").substring(0,10));
        items_cost.setText("₹"+formatter.format(rate_double1)+".00");
        before_tax.setText("₹"+formatter.format(rate_double1)+".00");
        total_amt.setText("₹"+formatter.format(rate_double1)+".00");
        total_sum_amt.setText("₹"+formatter.format(rate_double1)+".00");
        item_count.setText("Items -"+"1 Item");
        name_vw.setText(getArguments().getString("address"));
        pay_mode.setText("Mode: "+getArguments().getString("pay_mode"));
        if (getArguments().getString("pay_mode").equals("COD")){
            method.setText("COD");
        }else{
            method.setText("Online Payment");
        }
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        OrderDetailBean bean=new OrderDetailBean(getArguments().getString("ProdName"),getArguments().getString("quantity"),getArguments().getString("Amount"),"₹0","₹0",getArguments().getString("prod_img"),getArguments().getString("cat_name"),"","","",getArguments().getString("uom"));
        newOrderBeansList.add(bean);

        madapter=new OrderDetailsAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);


    //    LoanInformation();


        return view;
    }


}
