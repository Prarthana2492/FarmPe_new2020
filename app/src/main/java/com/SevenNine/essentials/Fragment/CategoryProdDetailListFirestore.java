package com.SevenNine.essentials.Fragment;

import android.animation.Animator;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Adapter.CategoryProdDetailAdapterFirestore;
import com.SevenNine.essentials.Adapter.CustomExpandableListAdapter;
import com.SevenNine.essentials.Bean.Sellbean;
import com.SevenNine.essentials.CircleAnimationUtil;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.SevenNine.essentials.Volly_class.Login_post;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CategoryProdDetailListFirestore extends Fragment {

    public static ArrayList<Sellbean> newOrderBeansList_subcat = new ArrayList<>();
    private List<Sellbean> searchresultAraaylist = new ArrayList<>();

    public static RecyclerView recyclerView_main;
    public static CategoryProdDetailAdapterFirestore livestock_types_adapter;
    JSONObject jsonObject1;
    Fragment selectedFragment = null;
    public static TextView toolbar_title,name,filter,last_month_text;
    EditText search;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
    public static String sellingcatId,sellnavigation,status;
    SessionManager sessionManager;
    private boolean ascending = true;
    String sort_str;
   // static FirebaseFirestore db;

    public static CategoryProdDetailListFirestore newInstance() {
        CategoryProdDetailListFirestore fragment = new CategoryProdDetailListFirestore();
       // db = FirebaseFirestore.getInstance();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_prod_recy, container, false);
        Window window = getActivity().getWindow();
    //    window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));
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

                   /* FragmentManager fm = getFragmentManager();
                    fm.popBackStack();*/
                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("change_passrr");
                    transaction.commit();
                    return true;
                }

                return false;
            }
        });
        last_month_text.setText("All");

        newOrderBeansList_subcat.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        System.out.println("kjdhfs"+CustomExpandableListAdapter.category);
        last_month_text.setText("All");
       /* db.collection("versionControl")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                //document.getData().get("Date").toString();
                                //document.getData().get("Version").toString();
                                //List<String> dungeonGroup = (List<String>) document.get("Details");
                                //ArrayList<String> documentGroup = (ArrayList<String>) document.get("Details");

                                Sellbean updateMessage = new Sellbean(
                                        document.getData().get("ProductName").toString(),
                                        "",
                                       "",
                                        document.getData().get("ProductQuantity").toString(),
                                        document.getData().get("Price").toString(),
                                        document.getData().get("MRP").toString(),
                                       "Kg",
                                        document.getData().get("ProductDescription").toString(),
                                       "",
                                        document.getData().get("ProductId").toString(),
                                        "",
                                        document.getData().get("ProductBrand").toString(),
                                        document.getData().get("OfferPrice").toString());


                                newOrderBeansList_subcat.add(updateMessage);

                                //Log.d(TAG, "onComplete: ");
                            }
                        }
                    }
                });*/
       /* if (FilterProductsExpandableFragment.filter_refine_by!=null){
            if (CustomExpandableListAdapter.category!=null){
                all_items_filter();

            }else{
                price_offer_filter();

            }
        }
        else{
            all_items_filter();

        }*/

       /* if (FilterSortByFragment.price_low_high_str!=null){
            String sort_str=FilterSortByFragment.price_low_high_str;
            if (sort_str.equals("Price_low_high")){
                sortData(ascending);
                ascending = !ascending;
            }else if (sort_str.equals("Price_High_Low")){
                //sort_api="des";
                Collections.reverse(newOrderBeansList_subcat);
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
            }
        }*/
        /*filter.setOnClickListener(new View.OnClickListener() {
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
                        last_month_text.setText("Offers");
                        status="OfferAvailable";
                        dialog.dismiss();
                        price_offer_filter();

                    }
                });

                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // status="Ascending";
                        last_month_text.setText("All");
                        dialog.dismiss();
                        all_items_filter();

                    }
                });
                price.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                                final Dialog dialog1 = new Dialog(getActivity());
                                dialog1.setContentView(R.layout.range_filter_popup);
                                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                final TextView less_than_20 = (TextView) dialog1.findViewById(R.id.less_than_20);
                                final TextView range_21_50 = (TextView)dialog1.findViewById(R.id.range_21_50);
                                final TextView range_51_100 = (TextView)dialog1.findViewById(R.id.range_51_100) ;
                                final TextView range_101_200 = (TextView)dialog1.findViewById(R.id.range_101_200) ;
                                final TextView range_201_500 = (TextView)dialog1.findViewById(R.id.range_201_500) ;
                                //   final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;
                                LinearLayout image = (LinearLayout) dialog1.findViewById(R.id.close_popup);


                                image.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        dialog1.dismiss();

                                    }
                                });

                                less_than_20.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        last_month_text.setText("Less than 20");
                                        status="LessthanRs20";
                                        dialog1.dismiss();
                                        price_offer_filter();

                                    }
                                });

                                range_21_50.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        last_month_text.setText("Rs 21 to 50");
                                        status="Rs21toRs50";
                                        dialog1.dismiss();
                                        price_offer_filter();

                                    }
                                });
                                range_51_100.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        last_month_text.setText("Rs 51 to 100");
                                        status="Rs51toRs100";
                                        dialog1.dismiss();
                                        price_offer_filter();

                                    }
                                });
                                range_101_200.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        last_month_text.setText("Rs 101 to 200");
                                        status="Rs101toRs200";
                                        dialog1.dismiss();
                                        price_offer_filter();
                                    }
                                });
                                range_201_500.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        last_month_text.setText("Rs 201 to 500");
                                        status="Rs201toRs500";
                                        dialog1.dismiss();
                                        price_offer_filter();
                                    }
                                });


                                dialog1.show();

                    }
                });

                dialog.show();
            }
        });
*/


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = FilterTabFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("change_passrr");
                transaction.commit();
            }
        });
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

    private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(newOrderBeansList_subcat, new Comparator<Sellbean>() {
                @Override
                public int compare(Sellbean item1, Sellbean item2) {
                    System.out.println("jhdsjsh"+FilterSortByFragment.price_high_low_str);

                    if (sort_str.equals("alphabetic")) {
                        System.out.println("jhdsjsh"+newOrderBeansList_subcat.size());

                        return item1.getName().compareToIgnoreCase(item2.getName());

                    } else {
                        return item1.getActual_price().compareToIgnoreCase(item2.getActual_price());
                    }
                }
            });
        }
    /*else
    {
        Collections.reverse(newOrderBeansList);
    }*/
        //ADAPTER
        System.out.println("jjjjjjjjjj"+newOrderBeansList_subcat.size());

        livestock_types_adapter=new CategoryProdDetailAdapterFirestore(getActivity(),newOrderBeansList_subcat);
        recyclerView_main.setAdapter(livestock_types_adapter);
        //   name.setText(jsonObject1.getString("SellingCategoryName"));
        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapterFirestore.ProductItemActionListener() {
            @Override
            public void onItemTap(ImageView imageView) {
                if (imageView != null)
                    makeFlyAnimation(imageView);
            }
        });
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
        livestock_types_adapter=new CategoryProdDetailAdapterFirestore(getActivity(),searchresultAraaylist);
        recyclerView_main.setAdapter(livestock_types_adapter);
        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapterFirestore.ProductItemActionListener() {
            @Override
            public void onItemTap(ImageView imageView) {
                if (imageView != null)
                    makeFlyAnimation(imageView);
            }
        });
    }
    private void price_offer_filter() {
        newOrderBeansList_subcat.clear();

        try{

            JSONObject jsonObject = new JSONObject();
           // jsonObject.put("SellingCategoryId",sellingcatId);
            System.out.println("kjdshfkjdsh"+CustomExpandableListAdapter.price_range);
            jsonObject.put("Status", CustomExpandableListAdapter.price_range);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetFiltersforProductDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);
                    last_month_text.setText(CustomExpandableListAdapter.category_text);


                    try{

                        get_soiltype = result.getJSONArray("filterforProductdetails");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);

                                name.setText(jsonObject1.getString("SellingCategoryName"));

                                Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),"Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("ProductId"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("Brand"),jsonObject1.getString("OfferPrice"));

                                newOrderBeansList_subcat.add(sellbean);
                            name.setText(jsonObject1.getString("SellingCategoryName"));

                            System.out.println("fdjfksjd"+jsonObject1.getBoolean("IsOfferAvailable"));


                        }
                        livestock_types_adapter = new CategoryProdDetailAdapterFirestore(getActivity(), newOrderBeansList_subcat);
                        recyclerView_main.setAdapter(livestock_types_adapter);
                        //   name.setText(jsonObject1.getString("SellingCategoryName"));
                        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapterFirestore.ProductItemActionListener() {
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
      //  newOrderBeansList_subcat.clear();

        try{

            JSONObject jsonObject = new JSONObject();
            if (DiscoverCategoryFragment.home_arrow!=null){
                jsonObject.put("SellingCategoryId",1);

            }else{
                jsonObject.put("SellingCategoryId",1);

            }

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetProductDetailsList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);

                   // last_month_text.setText("All");

                    try{

                        get_soiltype = result.getJSONArray("SellDetails");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("ProductIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),jsonObject1.getString("MRP"),"Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("ProductId"),jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("Brand"),jsonObject1.getString("OfferPrice"));

                            newOrderBeansList_subcat.add(sellbean);
                            name.setText(jsonObject1.getString("SellingCategoryName"));
                            System.out.println("vvvvvvvvvvvv"+newOrderBeansList_subcat.size());

                        }
                        if (FilterSortByFragment.price_low_high_str!=null){
                            // name.setText(jsonObject1.getString("SellingCategoryName"));
                            sort_str=FilterSortByFragment.price_low_high_str;
                            if (sort_str.equals("Price_low_high")){
                                last_month_text.setText("Price - Low to High");
                                System.out.println("listtttttt"+newOrderBeansList_subcat.size());
                                sortData(ascending);
                                ascending = !ascending;
                            }else if (sort_str.equals("Price_High_Low")){
                                //sort_api="des";
                                last_month_text.setText("Price - High to Low");
                                System.out.println("listtttttt"+newOrderBeansList_subcat.size());
                                Comparator c = Collections.reverseOrder(new Sortbyroll());
                                Collections.sort(newOrderBeansList_subcat, c);
                              //  Collections.sort(newOrderBeansList_subcat, Collections.reverseOrder());

                               // Collections.reverse(newOrderBeansList_subcat);
                                System.out.println("listtttttt"+newOrderBeansList_subcat.size());

                                livestock_types_adapter=new CategoryProdDetailAdapterFirestore(getActivity(),newOrderBeansList_subcat);
                                recyclerView_main.setAdapter(livestock_types_adapter);
                                //   name.setText(jsonObject1.getString("SellingCategoryName"));
                                livestock_types_adapter.setActionListener(new CategoryProdDetailAdapterFirestore.ProductItemActionListener() {
                                    @Override
                                    public void onItemTap(ImageView imageView) {
                                        if (imageView != null)
                                            makeFlyAnimation(imageView);
                                    }
                                });
                            }else if (sort_str.equals("alphabetic")){
                                System.out.println("jjjjjjjjj"+sort_str);
                                last_month_text.setText("Alphabetic");

                                sortData(ascending);
                                ascending = !ascending;
                            }
                        }else if (CustomExpandableListAdapter.category!=null){
                            last_month_text.setText(CustomExpandableListAdapter.category_text);
                            System.out.println("kdjhjgjsk");
                            livestock_types_adapter=new CategoryProdDetailAdapterFirestore(getActivity(),newOrderBeansList_subcat);
                            recyclerView_main.setAdapter(livestock_types_adapter);
                            //   name.setText(jsonObject1.getString("SellingCategoryName"));
                            livestock_types_adapter.setActionListener(new CategoryProdDetailAdapterFirestore.ProductItemActionListener() {
                                @Override
                                public void onItemTap(ImageView imageView) {
                                    if (imageView != null)
                                        makeFlyAnimation(imageView);
                                }
                            });
                        }
                        else{
                            // name.setText(jsonObject1.getString("SellingCategoryName"));
                            last_month_text.setText("All");
                            System.out.println("kdjhjgjsk");
                            livestock_types_adapter=new CategoryProdDetailAdapterFirestore(getActivity(),newOrderBeansList_subcat);
                            recyclerView_main.setAdapter(livestock_types_adapter);
                            //   name.setText(jsonObject1.getString("SellingCategoryName"));
                            livestock_types_adapter.setActionListener(new CategoryProdDetailAdapterFirestore.ProductItemActionListener() {
                                @Override
                                public void onItemTap(ImageView imageView) {
                                    if (imageView != null)
                                        makeFlyAnimation(imageView);
                                }
                            });
                        }
                       /* livestock_types_adapter=new CategoryProdDetailAdapter(getActivity(),newOrderBeansList_subcat);
                        recyclerView_main.setAdapter(livestock_types_adapter);
                        //   name.setText(jsonObject1.getString("SellingCategoryName"));
                        livestock_types_adapter.setActionListener(new CategoryProdDetailAdapter.ProductItemActionListener() {
                            @Override
                            public void onItemTap(ImageView imageView) {
                                if (imageView != null)
                                    makeFlyAnimation(imageView);
                            }
                        });*/
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }}

    }
