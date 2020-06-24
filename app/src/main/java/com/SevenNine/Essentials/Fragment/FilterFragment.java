package com.SevenNine.Essentials.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class FilterFragment extends Fragment {

    private RadioGroup order_type,time_radio_group;
    private RadioButton order_radio_btn;
    LinearLayout time_layout;
    TextView apply;
    Fragment selectedFragment;
    SessionManager sessionManager;
    String userid,stat;
    String from_date,todate;
    int year,month,day;
    RadioButton current_year,previous_year,two_years_back;
    public static String date_status;


    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_orders, container, false);
        order_type=view.findViewById(R.id.order_type);
        time_radio_group=view.findViewById(R.id.time_radio_group);
        time_layout=view.findViewById(R.id.time_layout);
        apply=view.findViewById(R.id.apply);
        order_radio_btn=view.findViewById(R.id.order_radio_btn);
        current_year=view.findViewById(R.id.current_year);
        previous_year=view.findViewById(R.id.pre_year);
        two_years_back=view.findViewById(R.id.pre_pre_year);
        order_radio_btn.setChecked(true);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    /*selectedFragment = HomeLandingPageFragment.newInstance();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("orderss", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;*/
                }
                return false;
            }
        });


        stat="orders";

        final Date date = new Date();

        final Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH)+1;

        day = calendar.get(Calendar.DAY_OF_MONTH);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        todate = dateFormat.format(calendar.getTime());

        current_year.setText(year+"");
        previous_year.setText((year-1)+"");
        two_years_back.setText((year-2)+"");

        System.out.println("yearrsss"+year+" "+(year-1)+" "+(year-2));

        System.out.println("gdhj = "+ todate);

        sessionManager = new SessionManager(getActivity());
        userid=sessionManager.getRegId("userId");

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (stat.equals("open")) {
                    //Orderlist();
                    bundle.putString("setText", "Open orders");
                    bundle.putString("bundlestatus", "3");

                }else if (stat.equals("cancel")){
                    //CancelOrders();
                    bundle.putString("setText", "Canceled orders");
                    bundle.putString("bundlestatus", "1");
                }else if (stat.equals("Last 30 Days")){
                   // AllOrders();
                    bundle.putString("bundlestatus", "2");
                    bundle.putString("setText", "Last 30 Days");
                }else if (stat.equals("Last 6 months")){
                  //  AllOrders();
                    bundle.putString("bundlestatus", "2");
                    bundle.putString("setText", "Last 6 months");
                }else if (stat.equals((year)+"")){
                   // AllOrders();
                    bundle.putString("bundlestatus", "2");
                    bundle.putString("setText", year+"");
                }else if (stat.equals((year-1)+"")){
                    //AllOrders();
                    bundle.putString("bundlestatus", "2");
                    bundle.putString("setText", (year-1)+"");
                }else if (stat.equals((year-2)+"")){
                   // AllOrders();
                    bundle.putString("bundlestatus", "2");
                    bundle.putString("setText", (year-2)+"");
                }
                else
                {
                   // AllOrders();
                    bundle.putString("setText", "Orders");
                    bundle.putString("bundlestatus", "2");
                    date_status="6_months";
                }

                selectedFragment = NewOrderFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });

        order_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = group.findViewById(checkedId);

                if(radioButton.getTag().toString().equals("2")) {
                    stat="open";
                    time_layout.setVisibility(View.GONE);

                }else if (radioButton.getTag().toString().equals("3")){
                    stat="cancel";
                    time_layout.setVisibility(View.GONE);
                }
                else
                    time_layout.setVisibility(View.VISIBLE);

            }

        });

        time_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // System.out.println("current_year");
                RadioButton radioButton = group.findViewById(checkedId);
                if(radioButton.getTag().toString().equals("5")) {
                    // System.out.println("current_year");
                    stat="Last 6 months";
                    date_status="6_months";

                }else if (radioButton.getTag().toString().equals("6")){
                    stat=year+"";
                    date_status="current";
                    from_date = year+"-01-01";
                    System.out.println("current_year"+from_date);

                }else if (radioButton.getTag().toString().equals("4")){
                    stat="Last 30 Days";
                    date_status="30_days";

                }else if (radioButton.getTag().toString().equals("7")){
                    stat=(year-1)+"";
                    date_status="previous";

                }else if (radioButton.getTag().toString().equals("8")){
                    stat=(year-2)+"";
                    date_status="last_previous";
                }

            }

        });

        return view;
    }

}
