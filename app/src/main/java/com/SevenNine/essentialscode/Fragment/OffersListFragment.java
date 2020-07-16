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

import com.SevenNine.essentialscode.Activity.Status_bar_change_singleton;
import com.SevenNine.essentialscode.Adapter.OffersAdapter;
import com.SevenNine.essentialscode.Bean.InventoryBean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class OffersListFragment extends Fragment {

    public static ArrayList<InventoryBean> newOrderBeansList_subcat = new ArrayList<>();

    public static RecyclerView recyclerView_main;
    public static OffersAdapter livestock_types_adapter;
    JSONObject jsonObject1;
    Fragment selectedFragment = null;
    public static TextView toolbar_title,name;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
    public static String sellingcatId,sellnavigation;
    SessionManager sessionManager;

    public static OffersListFragment newInstance() {
        OffersListFragment fragment = new OffersListFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventory_prod_recy, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark1));
        Status_bar_change_singleton.getInstance().color_change(getActivity());


        recyclerView_main=view.findViewById(R.id.recycler_cat_detail);
        name=view.findViewById(R.id.name);
sessionManager=new SessionManager(getActivity());
        linearLayout = view.findViewById(R.id.linearLayout);
        toolbar_title = view.findViewById(R.id.toolbar_title);
      //  back_feed = view.findViewById(R.id.back_feed);
//        toolbar_title.setText("Select Category");
        // sellingdetailsid=Inventory_Details_Fragment.SId;
    //    sellingcatId=getArguments().getString("sellingCatId");
        toolbar_title.setText("Offers");
       /* back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.commit();
            }
        });*/
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.commit();
                    return true;
                }

                return false;
            }
        });
        newOrderBeansList_subcat.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());

        try{

            //  newOrderBeansList_subcat_veg.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetOfferDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("offerdetails");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            InventoryBean sellbean = new InventoryBean(jsonObject1.getString("ProductName"),jsonObject1.getString("ProductId"),jsonObject1.getString("Brand"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("SellingListIcon"),"","","",jsonObject1.getString("OfferPrice"),jsonObject1.getString("DeliveryCharges"),jsonObject1.getString("OfferExpiresOn"),jsonObject1.getString("IsOfferAvailable"),"");

                            newOrderBeansList_subcat.add(sellbean);
                        }
                        livestock_types_adapter=new OffersAdapter(getActivity(),newOrderBeansList_subcat);
                        recyclerView_main.setAdapter(livestock_types_adapter);
                     //   name.setText(jsonObject1.getString("SellingCategoryName"));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }



}
