package com.SevenNine.essentials.Fragment;

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

import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Adapter.NewOrderAdapter;
import com.SevenNine.essentials.Bean.NewOrderBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.SevenNine.essentials.Volly_class.Login_post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewOrderFragmentAccount extends Fragment {

    public static List<NewOrderBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    NewOrderAdapter madapter;
    JSONObject lngObject;
    TextView toolbar_title;
    Fragment selectedFragment;

    public static NewOrderFragmentAccount newInstance() {
        NewOrderFragmentAccount fragment = new NewOrderFragmentAccount();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_order_recy1, container, false);
        recyclerView=view.findViewById(R.id.new_order_recy);
        back_feed=view.findViewById(R.id.back_feed);

        sessionManager=new SessionManager(getActivity());
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.color_2));

        System.out.println("orderrrrrrrrrrrrrr");
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
                   /* selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("dhsksw");
                    transaction.commit();*/

                    return true;
                }
                return false;

            }
        });
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
      //  OrderDetails();
        /*NewOrderBean bean=new NewOrderBean("Parle-G Gold Milk Glucose..","23-Apr-2020","");
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);


        madapter=new NewOrderAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);
*/

    //    LoanInformation();


        return view;
    }

//    private void OrderDetails() {
//        newOrderBeansList.clear();
//
//        try {
//            JSONObject userRequestjsonObject = new JSONObject();
//            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
//            // userRequestjsonObject.put("UserId","1");
//            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);
//
//            Login_post.login_posting(getActivity(), Urls.GetOrderList, userRequestjsonObject, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("statussssss000lll" + result);
//                    JSONArray jsonArray = new JSONArray();
//
//                    try {
//
//                        jsonArray = result.getJSONArray("OderList");
//                        for (int i=0;i<jsonArray.length();i++) {
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                            String SellingListName=jsonObject1.getString("ProductName");
//                            String SellingListIcon=jsonObject1.getString("ProductIcon");
//                            String CreatedOn=jsonObject1.getString("CreatedOn");
//                            String TxnId=jsonObject1.getString("TxnId");
//                            String Amount=jsonObject1.getString("Amount");
//                            String SelectedQuantity=jsonObject1.getString("SelectedQuantity");
//                            String SellingCategoryName=jsonObject1.getString("SellingCategoryName");
//                            String ProductInfo=jsonObject1.getString("CustAddress");
//                            String mode=jsonObject1.getString("mode");
//                            String mrp=jsonObject1.getString("MRP");
//                            String CustAddress=jsonObject1.getString("CustAddress");
//                            String OfferPrice = jsonObject1.getString("OfferPrice");
//                            String DeliveryCharges = jsonObject1.getString("DeliveryCharges");
//
//
//                            /*PreferedBranchBean bean=new PreferedBranchBean(Name,StreeAddress,StreeAddress1,State,Pincode,"",Id);
//                            newOrderBeansList.add(bean);*/
//
//                            NewOrderBean img1=new NewOrderBean(SellingListName,CreatedOn,SellingListIcon,TxnId,Amount,SelectedQuantity,mrp,SellingCategoryName,mode,
//                                    CustAddress,OfferPrice,DeliveryCharges);
//                            newOrderBeansList.add(img1);
//
//                          //  System.out.println("adreess_list_size"+newOrderBeansList.size());
//
//                        }
//                        madapter = new NewOrderAdapter(getActivity(), newOrderBeansList);
//                        recyclerView.setAdapter(madapter);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
