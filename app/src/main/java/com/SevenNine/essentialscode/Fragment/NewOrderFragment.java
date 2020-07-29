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

import com.SevenNine.essentialscode.Adapter.NewOrderAdapter;
import com.SevenNine.essentialscode.Bean.NewOrderBean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewOrderFragment extends Fragment {

    public static List<NewOrderBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    NewOrderAdapter madapter;
    JSONObject lngObject;
    TextView toolbar_title,filter,time;
    Fragment selectedFragment;
    String status;
    public static NewOrderFragment newInstance() {
        NewOrderFragment fragment = new NewOrderFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_order_recy, container, false);
        recyclerView=view.findViewById(R.id.new_order_recy);
        filter=view.findViewById(R.id.filter);
        time=view.findViewById(R.id.time);

        sessionManager=new SessionManager(getActivity());
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        OrderDetailsFragment.order_details=null;
        System.out.println("orderrrrrrrrrrrrrr");
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
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
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       /* status="Last6Month";
        FilterOrderList();*/
       // OrderDetails();
        /*NewOrderBean bean=new NewOrderBean("Parle-G Gold Milk Glucose..","23-Apr-2020","");
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);


        madapter=new NewOrderAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);
*/

    //    LoanInformation();
        try {



            Bundle bundle = this.getArguments();
            if (bundle != null) {

                String order_text = bundle.getString("setText");
                String bundlestatus=bundle.getString("bundlestatus");

                time.setText(order_text);

                if (bundlestatus.equals("1")){
                    // System.out.println("bundlestatus"+bundlestatus);
                    //CANCEL
                    status="CancelOrders";
                    FilterOrderList();
                }/*else if (bundlestatus.equals("2")) {
                    //All
                    status="";
                }*/  else if (bundlestatus.equals("3")) {
                    //OPEN
                    status="OpenOrders";
                    FilterOrderList();

                } else if (bundlestatus.equals("6month")){
                    status="Last6Month";
                    FilterOrderList();

                }else if (bundlestatus.equals("30days")){
                    status="OneMonth";
                    FilterOrderList();

                }else if (bundlestatus.equals("year")){
                    status="YearWisedata";
                    FilterOrderList();

                }else if (bundlestatus.equals("year1")){
                    status="";
                    FilterOrderList();

                }else if (bundlestatus.equals("year2")){
                    status="";
                    FilterOrderList();

                }else{
                 //All
                    status="AllOrders";
                    FilterOrderList();
                }
               /* if(order_text.equals("Open Orders")){
                    System.out.println("order_texttttt" + order_text);
                // Orderlist();

                time.setText(order_text);

            }else if (order_text.equals("Open Orders")){
                    time.setText(order_text);
                }
            else{

                time.setText("Last 6 months");
               // AllOrders();
            }*/}else{
                status="AllOrders";
                FilterOrderList();
            }
        }catch (Exception e){

        }
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = FilterFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("orderss");
                transaction.commit();
            }
        });
        return view;
    }

  /*  private void OrderDetails() {
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
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

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
                            String CustAddress=jsonObject1.getString("CustAddress");
                            String DeliveryCharges = jsonObject1.getString("DeliveryCharges");

                            *//*PreferedBranchBean bean=new PreferedBranchBean(Name,StreeAddress,StreeAddress1,State,Pincode,"",Id);
                            newOrderBeansList.add(bean);*//*

                            NewOrderBean img1=new NewOrderBean(SellingListName,CreatedOn,SellingListIcon,TxnId,Amount,SelectedQuantity,mrp,SellingCategoryName,mode,
                                    CustAddress,OfferPrice,DeliveryCharges);
                            newOrderBeansList.add(img1);

                          //  System.out.println("adreess_list_size"+newOrderBeansList.size());

                        }
                        madapter = new NewOrderAdapter(getActivity(), newOrderBeansList);
                        recyclerView.setAdapter(madapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    private void FilterOrderList() {
        newOrderBeansList.clear();

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            userRequestjsonObject.put("Status",status);
            // userRequestjsonObject.put("UserId","1");
            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.GetFiltersforOrderDetails, userRequestjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss000lll" + result);
                    JSONArray jsonArray = new JSONArray();

                    try {

                        jsonArray = result.getJSONArray("filterfororderdetails");
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            String SellingListName=jsonObject1.getString("ProductName");
                            String SellingListIcon=jsonObject1.getString("ProductIcon");
                            String CreatedOn=jsonObject1.getString("CreatedOn");
                            String TxnId=jsonObject1.getString("PayUTransactionId");
                            String Amount=jsonObject1.getString("Amount");
                            String SelectedQuantity=jsonObject1.getString("SelectedQuantity");
                            String SellingCategoryName=jsonObject1.getString("SellingCategoryName");
                            String ProductInfo=jsonObject1.getString("CustAddress");
                            String mode=jsonObject1.getString("mode");
                            String mrp=jsonObject1.getString("MRP");
                            String OfferPrice = jsonObject1.getString("OfferPrice");
                            String CustAddress=jsonObject1.getString("CustAddress");
                            String DeliveryCharges = jsonObject1.getString("DeliveryCharges");

                            /*PreferedBranchBean bean=new PreferedBranchBean(Name,StreeAddress,StreeAddress1,State,Pincode,"",Id);
                            newOrderBeansList.add(bean);*/

                            NewOrderBean img1=new NewOrderBean(SellingListName,CreatedOn,SellingListIcon,TxnId,Amount,SelectedQuantity,mrp,SellingCategoryName,mode,
                                    CustAddress,OfferPrice,DeliveryCharges);
                            newOrderBeansList.add(img1);

                            //  System.out.println("adreess_list_size"+newOrderBeansList.size());

                        }
                        madapter = new NewOrderAdapter(getActivity(), newOrderBeansList);
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
