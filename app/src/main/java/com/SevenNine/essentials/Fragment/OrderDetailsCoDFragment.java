package com.SevenNine.essentials.Fragment;

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

import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Adapter.OrderDetailsAdapter;
import com.SevenNine.essentials.Bean.OrderDetailBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.SevenNine.essentials.Volly_class.Login_post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OrderDetailsCoDFragment extends Fragment {

    public static List<OrderDetailBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    OrderDetailsAdapter madapter;
    JSONObject lngObject;
    public static String order_details;
    Fragment selectedFragment;
    String date_str;
    Double before_tax_text;
    TextView toolbar_title,ordered_on,items_cost,before_tax,total_amt,total_sum_amt,item_count,name_vw,pay_mode,method,shipping_fee;

    public static OrderDetailsCoDFragment newInstance() {
        OrderDetailsCoDFragment fragment = new OrderDetailsCoDFragment();
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
        shipping_fee=view.findViewById(R.id.shipping_fee);

        sessionManager=new SessionManager(getActivity());
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  order_details="order";
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

                   // order_details="order";
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


        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        OrderDetails();
       /* OrderDetailBean bean=new OrderDetailBean(getArguments().getString("ProdName"),getArguments().getString("quantity"),getArguments().getString("Amount"),"₹0","₹0",getArguments().getString("prod_img"),getArguments().getString("cat_name"),"","","",getArguments().getString("uom"));
        newOrderBeansList.add(bean);

        madapter=new OrderDetailsAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);*/


    //    LoanInformation();


        return view;
    }
    private void OrderDetails() {
        newOrderBeansList.clear();

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            // userRequestjsonObject.put("UserId","1");
            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.GetOrderList, userRequestjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss000lll" + result);
                    JSONArray jsonArray = new JSONArray();

                    try {

                        jsonArray = result.getJSONArray("OderList");

                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);

                            String SellingListName=jsonObject1.getString("ProductName");
                            String SellingListIcon=jsonObject1.getString("ProductIcon");
                            String CreatedOn=jsonObject1.getString("CreatedOn");
                            String TxnId=jsonObject1.getString("TxnId");
                            String Amount=jsonObject1.getString("Amount");
                            String SelectedQuantity=jsonObject1.getString("SelectedQuantity");
                            String SellingCategoryName=jsonObject1.getString("SellingCategoryName");
                            String ProductInfo=jsonObject1.getString("CustAddress");
                            String mode=jsonObject1.getString("mode");
                            String mrp=jsonObject1.getString("MRP");
                        String OfferPrice = jsonObject1.getString("OfferPrice");
                        String DeliveryCharges = jsonObject1.getString("DeliveryCharges");

                        String CustAddress=jsonObject1.getString("CustAddress");

                            /*PreferedBranchBean bean=new PreferedBranchBean(Name,StreeAddress,StreeAddress1,State,Pincode,"",Id);
                            newOrderBeansList.add(bean);*/

                        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);;
                        formatter .applyPattern("##,##,##,###");
                        double rate_double1=0;
                        if (OfferPrice.equals("0")){
                            rate_double1= (Double.parseDouble(Amount));

                        }else {
                             rate_double1= (Double.parseDouble(OfferPrice));

                        }
                        ordered_on.setText(CreatedOn.substring(0,10));
                        items_cost.setText("₹"+rate_double1+".00");
                       /* items_cost.setText("₹"+rate_double1+".00");
                        String shippDouble = String.format("%.2f",DeliveryCharges );
                        shipping_fee.setText("₹"+shippDouble);*/
                        before_tax_text=((rate_double1)+(Double.parseDouble(DeliveryCharges)));
                        String strDouble1 = String.format("%.2f", before_tax_text);

                        before_tax.setText("₹"+strDouble1);
                      //  before_tax.setText("₹"+formatter.format(rate_double1)+".00");
                        total_amt.setText("₹"+strDouble1);
                        total_sum_amt.setText("₹"+strDouble1);
                        item_count.setText("Items -"+"1 Item");
                        name_vw.setText(CustAddress);
                        pay_mode.setText("Mode: "+mode);
                        if (mode.equals("COD")){
                            method.setText("CoD");
                        }else{
                            method.setText("Online Payment");
                        }
                            OrderDetailBean img1=new OrderDetailBean(SellingListName,SelectedQuantity,Amount,"2","2",SellingListIcon,SellingCategoryName,"","","",mrp);
                            newOrderBeansList.add(img1);

                            //  System.out.println("adreess_list_size"+newOrderBeansList.size());


                        madapter = new OrderDetailsAdapter(getActivity(), newOrderBeansList);
                        recyclerView.setAdapter(madapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
