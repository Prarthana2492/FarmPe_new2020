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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.Bean.Cart_Bean;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Adapter.CartDetailsAdapter;
import com.SevenNine.essentials.Bean.OrderDetailBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.SevenNine.essentials.Volly_class.Login_post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CartDetailsFragment extends Fragment {

  //  public static List<OrderDetailBean> newOrderBeansList = new ArrayList<>();
    public static List<Cart_Bean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed,buy_btn,full_layout,default_name_sec;
    SessionManager sessionManager;
    CartDetailsAdapter madapter;
    JSONObject lngObject;
    View view_line;
    ImageView right_arrow;
    public static TextView toolbar_title,total_amount,main_total_amount,total_before_tax,total_items,items_cost,total_without_disc,name,delivery_charges;
    Fragment selectedFragment;

    public static CartDetailsFragment newInstance() {
        CartDetailsFragment fragment = new CartDetailsFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_detail_recy, container, false);
        recyclerView=view.findViewById(R.id.cart_det_recy);
        buy_btn=view.findViewById(R.id.buy_btn);
        view_line=view.findViewById(R.id.view_line);
        default_name_sec=view.findViewById(R.id.default_name_sec);
        total_amount=view.findViewById(R.id.total_amount);
        main_total_amount=view.findViewById(R.id.main_total_amount);
        total_before_tax=view.findViewById(R.id.total_before_tax);
        total_items=view.findViewById(R.id.total_items);
        items_cost=view.findViewById(R.id.items_cost);
        full_layout=view.findViewById(R.id.full_layout);
        name=view.findViewById(R.id.name);
        delivery_charges=view.findViewById(R.id.delivery_charges);
        total_without_disc=view.findViewById(R.id.total_without_disc);
        right_arrow=view.findViewById(R.id.right_arrow);

sessionManager=new SessionManager(getActivity());
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                   /* FragmentManager fm = getFragmentManager();
                    fm.popBackStack();*/
                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("cart_detail");
                    transaction.commit();

                    return true;
                }

                return false;
            }
        });

        if (sessionManager.getRegId("default_address").equals("")){
            default_name_sec.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);
        }else{
            name.setText(sessionManager.getRegId("default_address"));

        }

        right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = CurrentLocation.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("cart_detail");
                transaction.commit();
            }
        });
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
     //   cartDetails();
       // total_amount.setText("₹"+CartDetailsAdapter.totalPrice);
        Cart_Bean bean=new Cart_Bean("Parle-G Gold Milk Glucose..","1","₹100","₹2","₹2","3",R.drawable.biscuit_milk_glucose);
        Cart_Bean bean1=new Cart_Bean("Biscuits.","1","₹100","₹2","₹2","3",R.drawable.parle_hide_sick);
        Cart_Bean bean2=new Cart_Bean("Cookies","1","₹100","₹2","₹2","3",R.drawable.biscuit_cookies);

        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean1);
        newOrderBeansList.add(bean2);

        madapter=new CartDetailsAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);


        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionManager.getRegId("default_address").equals("")){
                    selectedFragment = CurrentLocation.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("cart_detail");
                    transaction.commit();
                }else {
                    selectedFragment = SelectPaymentMethod.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("cart_detail");
                    transaction.commit();
                }
              /*  Intent i = new Intent(getActivity(), GetLocationActivity.class);
                startActivity(i);*/
            }
        });

    //    LoanInformation();


        return view;
    }


//    private void cartDetails() {
//        newOrderBeansList.clear();
//
//        try {
//            JSONObject userRequestjsonObject = new JSONObject();
//            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
//            // userRequestjsonObject.put("UserId","1");
//            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);
//
//            Login_post.login_posting(getActivity(), Urls.GetCartProductDetails, userRequestjsonObject, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("statussssss000lll" + result);
//                    JSONArray jsonArray = new JSONArray();
//
//                    try {
//
//                        jsonArray = result.getJSONArray("ProductDetails");
//                        System.out.println("jsonarraylength"+jsonArray.length());
//                        if (jsonArray.length()==0){
//                            selectedFragment = NoItemsFragment.newInstance();
//                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                            transaction.replace(R.id.frame_layout_home, selectedFragment);
//                            transaction.addToBackStack("cart_detail");
//                            transaction.commit();
//                        }else {
//                            full_layout.setVisibility(View.VISIBLE);
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                                String ProductName = jsonObject1.getString("ProductName");
//                                String ProductDescription = jsonObject1.getString("ProductDescription");
//                                String Amount = jsonObject1.getString("Amount");
//                                String MRP = jsonObject1.getString("MRP");
//                                String SellingListIcon = jsonObject1.getString("ProductIcon");
//                                String SellingQuantity = jsonObject1.getString("SellingQuantity");
//                                String Brand = jsonObject1.getString("Brand");
//                                String CartProductListId = jsonObject1.getString("CartProductListId");
//                                String ProductId = jsonObject1.getString("ProductId");
//                                String OfferPrice = jsonObject1.getString("OfferPrice");
//                                String DeliveryCharges = jsonObject1.getString("DeliveryCharges");
//
//                            /*PreferedBranchBean bean=new PreferedBranchBean(Name,StreeAddress,StreeAddress1,State,Pincode,"",Id);
//                            newOrderBeansList.add(bean);*/
//
//                                OrderDetailBean bean = new OrderDetailBean(ProductName, SellingQuantity, Amount, DeliveryCharges, OfferPrice, SellingListIcon, CartProductListId, ProductDescription,ProductId,Brand,MRP);
//                                newOrderBeansList.add(bean);
//                                System.out.println("adreess_list_size" + newOrderBeansList.size());
//                                madapter = new CartDetailsAdapter(getActivity(), newOrderBeansList);
//                                recyclerView.setAdapter(madapter);
//                            }
//                        }
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
