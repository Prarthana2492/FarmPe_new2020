package com.SevenNine.essentialscode.Fragment;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.CategoryProdDetailAdapter;
import com.SevenNine.essentialscode.Adapter.CustomExpandableListAdapter;
import com.SevenNine.essentialscode.Adapter.MainCategoryAdapter;
import com.SevenNine.essentialscode.Adapter.SelectMainAdapter;
import com.SevenNine.essentialscode.Adapter.VegSectionAdapter;
import com.SevenNine.essentialscode.Bean.MainVerticalBean;
import com.SevenNine.essentialscode.Bean.Sellbean;
import com.SevenNine.essentialscode.Bean.Sellbean1;
import com.SevenNine.essentialscode.CircleAnimationUtil;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiscoverCategoryFragment extends Fragment {

    public static ArrayList<MainVerticalBean> newOrderBeansList = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_main = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_subcat = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_prod = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_cooking = new ArrayList<>();
    public static RecyclerView recyclerView_main,recy_veg,recy_food_grails,recyclerView_prod,recycler_cooking;
    public static MainCategoryAdapter livestock_types_adapter;
    public static VegSectionAdapter vegAdapter;
    public static SelectMainAdapter mainAdapter;
    Fragment selectedFragment = null;
    ImageView arrow;
    TextView toolbar_title,fruits_veg,food_grains;
    TextView search_home;
    SessionManager sessionManager;
    public static String livestock_status,search_st;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation,home_arrow;
    boolean doubleBackToExitPressedOnce = false;

    public static DiscoverCategoryFragment newInstance() {
        DiscoverCategoryFragment fragment = new DiscoverCategoryFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_by_cate_main, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView_main=view.findViewById(R.id.recycler_main);
        recy_veg=view.findViewById(R.id.recy_veg);
        fruits_veg=view.findViewById(R.id.fruits_veg);
        food_grains=view.findViewById(R.id.food_grains);
        recy_food_grails=view.findViewById(R.id.recy_food_grails);
        arrow=view.findViewById(R.id.arrow);
      //  search_home=view.findViewById(R.id.search_home);
        /*recyclerView_prod=view.findViewById(R.id.recycler_prod);
        recycler_cooking=view.findViewById(R.id.recycler_cooking);*/
      //  toolbar_title=view.findViewById(R.id.setting_tittle);
     //   back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.linearLayout);
//        toolbar_title.setText("Select Category");
       // sellingdetailsid=Inventory_Details_Fragment.SId;
        System.out.println("selleditiddd"+sellingdetailsid);
        sessionManager=new SessionManager(getActivity());
       // fruits_veg.setVisibility(View.GONE);
       // fruits_veg.setVisibility(View.GONE);
        FilterSortByFragment.price_low_high_str=null;
        FilterProductsExpandableFragment.filter_refine_by=null;
        CustomExpandableListAdapter.category_text=null;
        CustomExpandableListAdapter.category=null;
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (doubleBackToExitPressedOnce) {

                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
                        intent1.addCategory(Intent.CATEGORY_HOME);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent1);
                        getActivity().finish();                   }
                    // System.exit(0);

                    // home_img.setImageResource(R.drawable.ic_home_green);

                    doubleBackToExitPressedOnce = true;

                    Toast toast = Toast.makeText(getActivity(),"Please click back again to exit", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();
                    //   Toast.makeText(getApplicationContext(), toast_click_back, Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 3000);

                }

                return true;
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_arrow="true";
                selectedFragment = CategoryProdDetailList.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("dhskssv");
                transaction.commit();
            }
        });
       /* search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_st="home";
                selectedFragment = CategoryProdDetailListSearch.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("dhskss");
                transaction.commit();
            }
        });*/

        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm1);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        try{
            newOrderBeansList.clear();

            //   newOrderBeansList_main.clear();
           //   newOrderBeansList_subcat.clear();
          //  newOrderBeansList.clear();
            JSONObject jsonObject = new JSONObject();


            Crop_Post.crop_posting(getActivity(), Urls.GetSellingType, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("SellingTypeList");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            MainVerticalBean bean = new MainVerticalBean(jsonObject1.getString("SellingTypeName"),jsonObject1.getString("SellingTypeId"),jsonObject1.getString("SellingTypeIcon"));
                            newOrderBeansList.add(bean);
                        }
                        mainAdapter=new SelectMainAdapter(getActivity(),newOrderBeansList);
                        recyclerView_main.setAdapter(mainAdapter);
                        mainAdapter.notifyDataSetChanged();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
       /* MainAdapterBean1 beann = new MainAdapterBean1("All","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(beann);
        MainAdapterBean1 bean = new MainAdapterBean1("Vegetables","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean);
        MainAdapterBean1 bean1 = new MainAdapterBean1("Fruits","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean1);
        MainAdapterBean1 bean2 = new MainAdapterBean1("Groceries","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean2);
        MainAdapterBean1 bean3 = new MainAdapterBean1("Cooking Oil","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean3);
        MainAdapterBean1 bean33 = new MainAdapterBean1("Masala","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean33);
        mainAdapter=new SelectMainAdapter(getActivity(),newOrderBeansList_main);
        recyclerView_main.setAdapter(mainAdapter);*/

       // newOrderBeansList_subcat.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
       recy_food_grails.setLayoutManager(mLayoutManager_farm);
        recy_food_grails.setItemAnimator(new DefaultItemAnimator());
        food_grain();
       /* Sellbean1 bean8 = new Sellbean1("Daawat Rozana\nGold Basmati Rice\n(Medium Grain)","1", R.drawable.veg,"(5 kg)","₹100","₹120","");
        newOrderBeansList_subcat.add(bean8);
        Sellbean1 bean9 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_subcat.add(bean9);
        Sellbean1 bean10 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_subcat.add(bean10);
            Sellbean1 bean111 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1",R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean111);
        vegAdapter = new VegSectionAdapter(getActivity(), newOrderBeansList_subcat);
        recy_food_grails.setAdapter(vegAdapter);
*/
      //  newOrderBeansList_main.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm2 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recy_veg.setLayoutManager(mLayoutManager_farm2);
        recy_veg.setItemAnimator(new DefaultItemAnimator());
        veg_fruit();
       /* Sellbean1 bean11 = new Sellbean1("Fresh Onion, Organic\nGoverment Gaurden","1", R.drawable.veg,"(5 kg)","₹100","₹120","");
        newOrderBeansList_main.add(bean11);
        Sellbean1 bean12 = new Sellbean1("Fresh Onion, Organic\nGoverment Gaurden","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_main.add(bean12);
        Sellbean1 bean13 = new Sellbean1("Fresh Onion, Organic\nGoverment Gaurden","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_main.add(bean13);
            *//*Sellbean1 bean111 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1",R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean111);*//*
        vegAdapter = new VegSectionAdapter(getActivity(), newOrderBeansList_main);
        recy_veg.setAdapter(vegAdapter);*/




        return view;
    }

    private void food_grain(){
        try{
                newOrderBeansList.clear();
                newOrderBeansList_main.clear();
            //  newOrderBeansList_subcat.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingTypeId",2);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetTop10listItem, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("Top10list");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),"Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("ProductId"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("Brand"),jsonObject1.getString("OfferPrice"));

                            newOrderBeansList_subcat.add(sellbean);
                          //  name.setText(jsonObject1.getString("SellingCategoryName"));
                        }
                        vegAdapter = new VegSectionAdapter(getActivity(), newOrderBeansList_subcat);
                        recy_food_grails.setAdapter(vegAdapter);
                        //   name.setText(jsonObject1.getString("SellingCategoryName"));
                        vegAdapter.setActionListener(new VegSectionAdapter.ProductItemActionListener() {
                            @Override
                            public void onItemTap(ImageView imageView) {
                                if (imageView != null)
                                    makeFlyAnimation(imageView);
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void veg_fruit(){
        try{
           // newOrderBeansList.clear();
           // newOrderBeansList_subcat.clear();
            //  newOrderBeansList_main.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingTypeId",1);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetTop10listItem, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("Top10list");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),"Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("ProductId"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("Brand"),jsonObject1.getString("OfferPrice"));

                            newOrderBeansList_main.add(sellbean);
                            //  name.setText(jsonObject1.getString("SellingCategoryName"));
                        }
                        vegAdapter=new VegSectionAdapter(getActivity(),newOrderBeansList_main);
                        recy_veg.setAdapter(vegAdapter);
                        //   name.setText(jsonObject1.getString("SellingCategoryName"));
                        vegAdapter.setActionListener(new VegSectionAdapter.ProductItemActionListener() {
                            @Override
                            public void onItemTap(ImageView imageView) {
                                if (imageView != null)
                                    makeFlyAnimation(imageView);
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void makeFlyAnimation(ImageView targetView) {


        new CircleAnimationUtil().attachActivity(getActivity()).setTargetView(targetView).setMoveDuration(1000).setDestView(HomeFragment.destView).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                CartCount();
                Toast.makeText(getActivity(), "Continue Shopping...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();


    }
    private void CartCount() {

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            // userRequestjsonObject.put("UserId","1");
            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.GetReviewCount, userRequestjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss000lll" + result);
                    JSONArray jsonArray = new JSONArray();

                    try {

                        jsonArray = result.getJSONArray("ReviewListCount");
                        System.out.println("jsonarraylength"+jsonArray.length());


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            String Total = jsonObject1.getString("Total");

                            HomeFragment.cart_count.setVisibility(View.VISIBLE);
                            //   HomeFragment.cart_count.setText(Total);

                            HomeFragment.cart_count.setText(Total);



                           /* Animation animation1 =
                                    AnimationUtils.loadAnimation(activity.getApplicationContext(),
                                            R.anim.blink);
                            HomeFragment.cart_count.startAnimation(animation1);*/
                            /*int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(linear_layout, "Product added successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(activity, R.color.orange));
                            tv.setTextColor(Color.WHITE);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();

*/

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

}
