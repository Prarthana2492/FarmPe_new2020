package com.SevenNine.Essentials.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.SevenNine.Essentials.Adapter.CartDetailsAdapter;
import com.SevenNine.Essentials.Adapter.ShippingAddressAdapter;
import com.SevenNine.Essentials.Bean.Add_New_Address_Bean;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;
import com.SevenNine.Essentials.Urls;
import com.SevenNine.Essentials.Volly_class.Crop_Post;
import com.SevenNine.Essentials.Volly_class.Login_post;
import com.SevenNine.Essentials.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SelectShippingAddress extends Fragment {

    public static List<Add_New_Address_Bean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    TextView add_new,delivery_addr,edit_addr;
    ShippingAddressAdapter madapter;
    JSONObject lngObject;
    TextView toolbar_title;
    Fragment selectedFragment;
    String status;
    public static String shipping_addr;

    public static SelectShippingAddress newInstance() {
        SelectShippingAddress fragment = new SelectShippingAddress();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipping_addr_recy, container, false);
        recyclerView=view.findViewById(R.id.recy_shipping);
        add_new=view.findViewById(R.id.add_new);
        delivery_addr=view.findViewById(R.id.delivery_addr);
        back_feed=view.findViewById(R.id.back_feed);
        edit_addr=view.findViewById(R.id.edit_addr);
 sessionManager=new SessionManager(getActivity());

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("cart_detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
HomeFragment.title.setText("Select Delivery Address");
HomeFragment.cart_icon.setVisibility(View.INVISIBLE);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("cart_detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;

            }
        });

        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = NewAddressFragment.newInstance();
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("track1");
                transaction.commit();
            }
        });
        delivery_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ShippingAddressAdapter.add_id!=null){
                    CheckoutDetails();
                }else {
                    Toast toast = Toast.makeText(getActivity(),"Select Shipping Address", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();
                }

            }
        });

        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        addressList();
        /*Add_New_Address_Bean bean=new Add_New_Address_Bean("Jagdish Kumar","#103,","Marvel Spring, Dr. Arunachalam Road","BEML 5th stage, RR Nagar","Bengaluru","560098","9898989898","","Karnataka","","","","","",true,"","","","");
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean);
     //   newOrderBeansList.add(bean);

        madapter=new ShippingAddressAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(madapter);
*/

    //    LoanInformation();


        return view;
    }

    private void addressList() {
        newOrderBeansList.clear();

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            // userRequestjsonObject.put("UserId","1");
            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.GetUserAddress, userRequestjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss000lll" + result);
                    JSONArray jsonArray = new JSONArray();

                    try {

                        jsonArray = result.getJSONArray("UserAddressList");
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            String Name=jsonObject1.getString("FullName");
                            String MobileNo=jsonObject1.getString("MobileNo");
                            String StreeAddress=jsonObject1.getString("Address");
                            String LandMark=jsonObject1.getString("LandMark");
                            String State=jsonObject1.getString("State");
                            String Taluk=jsonObject1.getString("BlockName");
                            String District=jsonObject1.getString("District");
                            String Village=jsonObject1.getString("Village");
                            String StateId=jsonObject1.getString("StateId");
                            String DistrictId=jsonObject1.getString("DistrictId");
                            String BlockId=jsonObject1.getString("BlockId");
                            String VillageId=jsonObject1.getString("VillageId");
                            String Pincode=jsonObject1.getString("Pincode");
                            String AddressType=jsonObject1.getString("AddressType");
                            String UserAddressId=jsonObject1.getString("UserAddressId");
                            /*PreferedBranchBean bean=new PreferedBranchBean(Name,StreeAddress,StreeAddress1,State,Pincode,"",Id);
                            newOrderBeansList.add(bean);*/

                            Add_New_Address_Bean img1=new Add_New_Address_Bean(Name,StreeAddress,LandMark,Taluk,Pincode,MobileNo,AddressType,State,
                                    District,Taluk,"",Village,UserAddressId, true,StateId,DistrictId,BlockId,VillageId);
                            newOrderBeansList.add(img1);


                            System.out.println("adreess_list_size"+newOrderBeansList.size());
                            if (newOrderBeansList.size()==0){
                                delivery_addr.setVisibility(View.GONE);
                                edit_addr.setVisibility(View.GONE);
                            }else {
                                delivery_addr.setVisibility(View.VISIBLE);
                                edit_addr.setVisibility(View.VISIBLE);
                                madapter = new ShippingAddressAdapter(getActivity(), newOrderBeansList);
                                recyclerView.setAdapter(madapter);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CheckoutDetails() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartCheckOutId", 0);
            jsonObject.put("TotalPrice", CartDetailsAdapter.total_prise_st);
            jsonObject.put("TotalCartItems", CartDetailsAdapter.total_cart_items);
            jsonObject.put("CartProductListId", CartDetailsAdapter.strlist);
            jsonObject.put("AddressId", ShippingAddressAdapter.add_id);
          //  jsonObject.put("CustomerLongitude", "");
          //  jsonObject.put("CustomerLatitude", "");
            jsonObject.put("CustAddress","");
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            System.out.println("Add_New_AddresssssssssssssssssjsonObject11" + jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.AddUpateCartCheckoutDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {


                    System.out.println("Add_New_Addresssssssssssssssssllllllllllllllllll888" + result);
                    try {

                        status = result.getString("Status");
                        // message = result.getString("Message");

                        //   bundle.putString("add_id",status);

                        //   bundle.putString("streetname",  DistrictAdapter.district_name);


                        if (status.equals("Success")) {
                            shipping_addr="present";
                            selectedFragment = SelectPaymentMethod.newInstance();
                            FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout1, selectedFragment);
                            transaction.addToBackStack("track1");
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
    }
}
