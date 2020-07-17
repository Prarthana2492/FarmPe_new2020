package com.SevenNine.essentialscode.Fragment;

import android.animation.Animator;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.CategoryProdDetailAdapter;
import com.SevenNine.essentialscode.Bean.Sellbean;
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
import java.util.List;


public class CategoryProdDetailList extends Fragment {

    public static ArrayList<Sellbean> newOrderBeansList_subcat = new ArrayList<>();
    private List<Sellbean> searchresultAraaylist = new ArrayList<>();

    public static RecyclerView recyclerView_main;
    public static CategoryProdDetailAdapter livestock_types_adapter;
    JSONObject jsonObject1;
    Fragment selectedFragment = null;
    public static TextView toolbar_title,name,filter,last_month_text;
    EditText search;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
    public static String sellingcatId,sellnavigation;
    SessionManager sessionManager;

    public static CategoryProdDetailList newInstance() {
        CategoryProdDetailList fragment = new CategoryProdDetailList();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_prod_recy, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView_main=view.findViewById(R.id.recycler_cat_detail);
      //  search=view.findViewById(R.id.search);
        name=view.findViewById(R.id.name);
        filter=view.findViewById(R.id.filter);
        last_month_text=view.findViewById(R.id.last_month_text);
sessionManager=new SessionManager(getActivity());
        linearLayout = view.findViewById(R.id.linearLayout);
//        toolbar_title.setText("Select Category");
        // sellingdetailsid=Inventory_Details_Fragment.SId;
        if (getArguments()==null){
            System.out.println("hommmmmmee");
            sellingcatId= HomeFragment.shop_cat_id;

        }else{
            sellingcatId=getArguments().getString("sellingCatId");

        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();

                    return true;
                }

                return false;
            }
        });
        last_month_text.setText("All");
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.layout_filterpopup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final TextView all = (TextView) dialog.findViewById(R.id.recen_added);
                final TextView price = (TextView)dialog.findViewById(R.id.sort_desendi);
                final TextView offers = (TextView)dialog.findViewById(R.id.sort_ascendi) ;
                //   final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;
                LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);


                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });

                offers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // status="Ascending";
                        dialog.dismiss();
                        offers_filter();

                    }
                });

                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // status="Ascending";
                        dialog.dismiss();
                        all_items_filter();

                    }
                });

                dialog.show();
            }
        });
        newOrderBeansList_subcat.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        all_items_filter();
       /* Sellbean1 bean45 = new Sellbean1("Fresh Carrot, Orrange ","1",R.drawable.veg,"500g","₹100","₹120","");
        newOrderBeansList_subcat.add(bean45);
        Sellbean1 bean55= new Sellbean1("Fresh Beet Root, ","1",R.drawable.veg,"250g","₹100","₹120","");
        newOrderBeansList_subcat.add(bean55);
        Sellbean1 bean65 = new Sellbean1("Fresh Radish, White, ","1",R.drawable.veg,"500g","₹100","₹120","");
        newOrderBeansList_subcat.add(bean65);
        Sellbean1 bean75= new Sellbean1("Fresh Carrot, Orrange, ","1",R.drawable.veg,"250g","₹100","₹120","");
        newOrderBeansList_subcat.add(bean75);
        livestock_types_adapter = new CategoryProdDetailAdapter(getActivity(), newOrderBeansList_subcat);
        recyclerView_main.setAdapter(livestock_types_adapter);*/

       /* search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sorting(s.toString());
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });*/

        return view;
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


    public  void sorting(String filter_text){

        final String text = filter_text.toLowerCase();

            searchresultAraaylist.clear();
            for (int i = 0; i < newOrderBeansList_subcat.size(); i++) {

                if (newOrderBeansList_subcat.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(newOrderBeansList_subcat.get(i));

                }
            }
        livestock_types_adapter=new CategoryProdDetailAdapter(getActivity(),searchresultAraaylist);
        recyclerView_main.setAdapter(livestock_types_adapter);
        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapter.ProductItemActionListener() {
            @Override
            public void onItemTap(ImageView imageView) {
                if (imageView != null)
                    makeFlyAnimation(imageView);
            }
        });
    }
    private void offers_filter() {
        try{

            newOrderBeansList_subcat.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingCategoryId",sellingcatId);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetProductDetailsList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);
                    last_month_text.setText("Offers");


                    try{

                        get_soiltype = result.getJSONArray("SellDetails");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);

                                name.setText(jsonObject1.getString("SellingCategoryName"));
                            if (jsonObject1.getBoolean("IsOfferAvailable")==true) {
                                Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),"Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("ProductId"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("Brand"),jsonObject1.getString("OfferPrice"));

                                newOrderBeansList_subcat.add(sellbean);
                                System.out.println("fdjfksjd"+jsonObject1.getBoolean("IsOfferAvailable"));
                                livestock_types_adapter = new CategoryProdDetailAdapter(getActivity(), newOrderBeansList_subcat);
                                recyclerView_main.setAdapter(livestock_types_adapter);
                            }else{

                            }

                        }

                        //   name.setText(jsonObject1.getString("SellingCategoryName"));
                        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapter.ProductItemActionListener() {
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

    private void all_items_filter() {
        try{

            newOrderBeansList_subcat.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingCategoryId",sellingcatId);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetProductDetailsList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);

                    last_month_text.setText("All");

                    try{

                        get_soiltype = result.getJSONArray("SellDetails");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),"Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("ProductId"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("Brand"),jsonObject1.getString("OfferPrice"));

                            newOrderBeansList_subcat.add(sellbean);
                            name.setText(jsonObject1.getString("SellingCategoryName"));
                        }
                        livestock_types_adapter=new CategoryProdDetailAdapter(getActivity(),newOrderBeansList_subcat);
                        recyclerView_main.setAdapter(livestock_types_adapter);
                        //   name.setText(jsonObject1.getString("SellingCategoryName"));
                        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapter.ProductItemActionListener() {
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

        }catch (Exception e) {
            e.printStackTrace();
        }}

    }
