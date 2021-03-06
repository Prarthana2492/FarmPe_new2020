package com.SevenNine.essentials.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Adapter.OrderDetailsAdapter;
import com.SevenNine.essentials.Bean.OrderDetailBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;

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
    String delivery_charge;
    String txnid;
    LinearLayout cancel_lay;
    TextView toolbar_title,ordered_on,items_cost,before_tax,total_amt,total_sum_amt,item_count,name_vw,pay_mode,method,shipping_fee,cancel;
    Double rate_double1,before_tax_text,shipping_fee_double;
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
        cancel_lay=view.findViewById(R.id.cancel_lay);
        name_vw=view.findViewById(R.id.name_vw);
        pay_mode=view.findViewById(R.id.payment_mode);
        method=view.findViewById(R.id.method);
        cancel=view.findViewById(R.id.cancel);
        shipping_fee=view.findViewById(R.id.shipping_fee);


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));
        sessionManager=new SessionManager(getActivity());
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("order_details","order_details");
                order_details="order";
                /*FragmentManager fm = getFragmentManager();
                fm.popBackStack();*/
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("dhsksw");
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Bundle bundle=new Bundle();
                    bundle.putString("order_details","order_details");
                    order_details="order";
                /*FragmentManager fm = getFragmentManager();
                fm.popBackStack();*/
                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("dhsksw");
                    selectedFragment.setArguments(bundle);
                    transaction.commit();

                    return true;
                }
                return false;

            }
        });
        if (getArguments().getString("pay_mode").equals("PayU")){
            cancel_lay.setVisibility(View.GONE);
        }else {
            cancel_lay.setVisibility(View.VISIBLE);

        }
      txnid=getArguments().getString("txnId");
      System.out.println("ttatta"+txnid);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.delete_details_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                TextView ok = dialog.findViewById(R.id.ok);
                TextView cancel = dialog.findViewById(R.id.cancel);
                TextView details = dialog.findViewById(R.id.details);

                details.setText("Do you want to Cancel the order?");
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("PayUTransactionId", txnid);
                            jsonObject.put("IsPaymentCancelled", 1);
                            jsonObject.put("UserId", sessionManager.getRegId("userId"));

                            System.out.println("bank_dvvvvetails_iddds" + jsonObject);

                            Crop_Post.crop_posting(getActivity(), Urls.CancelCODOrders, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try {

                                        String status = result.getString("Status");

                                        if (status.equals("Success")) {
                                            order_details = "order";
                                            Bundle bundle=new Bundle();
                                            bundle.putString("order_details","order_details");
                                            selectedFragment = HomeFragment.newInstance();
                                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            transaction.replace(R.id.frame_layout1, selectedFragment);
                                            transaction.addToBackStack("dhsksw");
                                            selectedFragment.setArguments(bundle);
                                            transaction.commit();
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();

                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();

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
        if (getArguments().getString("offer_price").equals("0")){
            rate_double1= (Double.parseDouble(getArguments().getString("Amount")));
        }else{
            rate_double1= (Double.parseDouble(getArguments().getString("offer_price")));

            //  rate_double1 = ((Double.parseDouble(getArguments().getString("uom")))-(Double.parseDouble(getArguments().getString("uom"))) * ((Double.parseDouble(getArguments().getString("offer_price"))) / 100));
           // holder.amount.setText("₹"+offer_price);
            System.out.println("jdkjsdhfkj"+rate_double1);

        }
        shipping_fee_double=Double.parseDouble(getArguments().getString("delivery_charges"));
        String shippDouble = String.format("%.2f",shipping_fee_double );
        shipping_fee.setText("₹"+shippDouble);
        ordered_on.setText(getArguments().getString("createdon").substring(0,10));
        String strDouble = String.format("%.2f", rate_double1);
        items_cost.setText("₹"+strDouble);
        delivery_charge=getArguments().getString("delivery_charges");
        before_tax_text=((rate_double1)+(Double.parseDouble(delivery_charge)));
        String strDouble1 = String.format("%.2f", before_tax_text);

        before_tax.setText("₹"+strDouble1);
        total_amt.setText("₹"+strDouble1);
        total_sum_amt.setText("₹"+strDouble1);
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

        OrderDetailBean bean=new OrderDetailBean(getArguments().getString("ProdName"),getArguments().getString("quantity"),getArguments().getString("Amount"),getArguments().getString("delivery_charges"),getArguments().getString("offer_price"),getArguments().getString("prod_img"),getArguments().getString("cat_name"),"","","",getArguments().getString("uom"));
        newOrderBeansList.add(bean);

        madapter=new OrderDetailsAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);


    //    LoanInformation();


        return view;
    }


}
