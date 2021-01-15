package com.SevenNine.essentials.Adapter;

import android.animation.Animator;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentials.Fragment.CategoryProdDetailList;
import com.SevenNine.essentials.Fragment.HomeFragment;
import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.Login_post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Bean.MainAdapterBean;
import com.SevenNine.essentials.Bean.Sellbean;
import com.SevenNine.essentials.Bean.Sellbean1;
import com.SevenNine.essentials.CircleAnimationUtil;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder> {

    private List<MainAdapterBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String sellingtypeid,itemid;
    public static ArrayList<Sellbean1> newOrderBeansList_subcat = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_subcat_veg = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_fruit = new ArrayList<>();
    public static VegSectionAdapter vegAdapter;
    public static VegSectionAdapter1 vegAdapter1;
    JSONArray get_soiltype;
    SessionManager sessionManager;

    public MainCategoryAdapter(Activity activity, List<MainAdapterBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public LinearLayout item,next;
        public TextView name;
        RecyclerView recyclerView_second;


        public MyViewHolder(View view) {
            super(view);

            //  image=view.findViewById(R.id.prod_img);
            item=view.findViewById(R.id.item);
            name=view.findViewById(R.id.name);
            next=view.findViewById(R.id.next);
            recyclerView_second=view.findViewById(R.id.recycler_maincat);
            sessionManager=new SessionManager(activity);

        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.discover_by_cate_second, parent, false);
        return new MyViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MainAdapterBean products = productList.get(position);
        sellingtypeid=products.getId();
        holder.name.setText(products.getName());

        newOrderBeansList_subcat.clear();

        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (products.getName().equals("Vegetables")||products.getName().equals("POT")){
                    selectedFragment = CategoryProdDetailList.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_home, selectedFragment);
                    transaction.addToBackStack("spicescateory");
                    transaction.commit();
                }
            }
        });
        if (products.getName().equals("Fruits")) {
            newOrderBeansList_fruit.clear();
            // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
            GridLayoutManager mLayoutManager_farm = new GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false);
            holder.recyclerView_second.setLayoutManager(mLayoutManager_farm);
            holder.recyclerView_second.setItemAnimator(new DefaultItemAnimator());

            try{

                newOrderBeansList_fruit.clear();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("SellingCategoryId",13);

                System.out.println("jhfdfdjc111"+jsonObject);
                Crop_Post.crop_posting(activity, Urls.GetProductDetailsList, jsonObject, new VoleyJsonObjectCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {

                        System.out.println("GetSellingTypeeeeeeee"+result);


                        try{

                            get_soiltype = result.getJSONArray("SellDetails");

                            for(int i=0;i<get_soiltype.length();i++){

                                JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                                Sellbean sellbean = new Sellbean(jsonObject1.getString("SellingListName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("SellingListIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),"","Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("UnitOfPriceId"),jsonObject1.getString("ProductId"),"","","");

                                newOrderBeansList_fruit.add(sellbean);
                            }
                            vegAdapter1=new VegSectionAdapter1(activity,newOrderBeansList_fruit);
                            holder.recyclerView_second.setAdapter(vegAdapter1);
                            vegAdapter1.setActionListener(new VegSectionAdapter1.ProductItemActionListener() {
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


            /*Sellbean1 bean4 = new Sellbean1("Fresh Mango Alphonso", "1", R.drawable.veg, "6 Pieces", "₹100", "₹120","");
            newOrderBeansList_subcat.add(bean4);
            Sellbean1 bean5 = new Sellbean1("Fresh Water Melon Kiran", "1", R.drawable.veg, "1 Piece", "₹100", "₹120","");
            newOrderBeansList_subcat.add(bean5);
            Sellbean1 bean6 = new Sellbean1("Fresho Guava Fruit", "1", R.drawable.veg, "500g", "₹100", "₹120","");
            newOrderBeansList_subcat.add(bean6);
            Sellbean1 bean7 = new Sellbean1("Fresho Guava Fruit", "1", R.drawable.veg, "250g", "₹100", "₹120","");
            newOrderBeansList_subcat.add(bean7);
            vegAdapter = new VegSectionAdapter(activity, newOrderBeansList_subcat);
            holder.recyclerView_second.setAdapter(vegAdapter);*/
        }
      /*  if (products.getName().equals("Cooking Oil")) {
            newOrderBeansList_subcat.clear();
            // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
            GridLayoutManager mLayoutManager_farm = new GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false);
            holder.recyclerView_second.setLayoutManager(mLayoutManager_farm);
            holder.recyclerView_second.setItemAnimator(new DefaultItemAnimator());
            Sellbean1 bean12 = new Sellbean1("Fortune Sunflower Oil","1", R.drawable.veg,"1ltr","₹100","₹120","");
            newOrderBeansList_subcat.add(bean12);
            Sellbean1 bean13= new Sellbean1("Sunpure Cooking Cooking Oil","1", R.drawable.veg,"1ltr","₹100","₹120","");
            newOrderBeansList_subcat.add(bean13);
            Sellbean1 bean14 = new Sellbean1("Gold Winner Cooking Oil","1", R.drawable.veg,"1ltr","₹100","₹120","");
            newOrderBeansList_subcat.add(bean14);
            Sellbean1 bean15 = new Sellbean1("Gold Winner Cooking Oil","1", R.drawable.veg,"1ltr","₹100","₹120","");
            newOrderBeansList_subcat.add(bean15);
            vegAdapter = new VegSectionAdapter(activity, newOrderBeansList_subcat);
            holder.recyclerView_second.setAdapter(vegAdapter);
        }*/
        if (products.getName().equals("Vegetables")||products.getName().equals("POT")) {


            newOrderBeansList_subcat_veg.clear();
            // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
            GridLayoutManager mLayoutManager_farm = new GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false);
            holder.recyclerView_second.setLayoutManager(mLayoutManager_farm);
            holder.recyclerView_second.setItemAnimator(new DefaultItemAnimator());
            // getVegitableProducts();

            try{

                //  newOrderBeansList_subcat_veg.clear();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("SellingCategoryId",123);

                System.out.println("jhfdfdjc111"+jsonObject);
                Crop_Post.crop_posting(activity, Urls.GetProductDetailsList, jsonObject, new VoleyJsonObjectCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {

                        System.out.println("GetSellingTypeeeeeeee"+result);


                        try{

                            get_soiltype = result.getJSONArray("SellDetails");

                            for(int i=0;i<get_soiltype.length();i++){

                                JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                                Sellbean sellbean = new Sellbean(jsonObject1.getString("ProductName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("SellingListIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),"","Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("UnitOfPriceId"),jsonObject1.getString("ProductId"),"","","");

                                newOrderBeansList_subcat_veg.add(sellbean);
                            }
                            vegAdapter1=new VegSectionAdapter1(activity,newOrderBeansList_subcat_veg);
                            holder.recyclerView_second.setAdapter(vegAdapter1);
                            vegAdapter1.setActionListener(new VegSectionAdapter1.ProductItemActionListener() {
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

           /* Sellbean1 bean45 = new Sellbean1("Fresh Carrot,\nOrrange, ","1",R.drawable.veg,"500g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean45);
            Sellbean1 bean55= new Sellbean1("Fresh Beet\nRoot, ","1",R.drawable.veg,"250g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean55);
            Sellbean1 bean65 = new Sellbean1("Fresh Radish,\nWhite, ","1",R.drawable.veg,"500g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean65);
            Sellbean1 bean75= new Sellbean1("Fresh Carrot,\nOrrange, ","1",R.drawable.veg,"250g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean75);
            vegAdapter = new VegSectionAdapter(activity, newOrderBeansList_subcat);
            holder.recyclerView_second.setAdapter(vegAdapter);*/
        }


       /* if (products.getName().equals("Groceries")) {
            newOrderBeansList_subcat.clear();
            // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
            GridLayoutManager mLayoutManager_farm = new GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false);
            holder.recyclerView_second.setLayoutManager(mLayoutManager_farm);
            holder.recyclerView_second.setItemAnimator(new DefaultItemAnimator());
            Sellbean1 bean8 = new Sellbean1("Daawat Rozana\nGold Basmati Rice\n(Medium Grain)","1", R.drawable.veg,"(5 kg)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean8);
            Sellbean1 bean9 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean9);
            Sellbean1 bean10 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean10);
            *//*Sellbean1 bean111 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1",R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean111);*//*
            vegAdapter = new VegSectionAdapter(activity, newOrderBeansList_subcat);
            holder.recyclerView_second.setAdapter(vegAdapter);
        }*/
        /*if (products.getName().equals("Leafy Vegetables")) {
            newOrderBeansList_subcat.clear();
            // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
            GridLayoutManager mLayoutManager_farm = new GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false);
            holder.recyclerView_second.setLayoutManager(mLayoutManager_farm);
            holder.recyclerView_second.setItemAnimator(new DefaultItemAnimator());
            Sellbean1 bean45 = new Sellbean1("Fresh Carrot,\nOrrange, ","1",R.drawable.veg,"500g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean45);
            Sellbean1 bean55= new Sellbean1("Fresh Beet\nRoot, ","1",R.drawable.veg,"250g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean55);
            Sellbean1 bean65 = new Sellbean1("Fresh Radish,\nWhite, ","1",R.drawable.veg,"500g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean65);
            Sellbean1 bean75= new Sellbean1("Fresh Carrot,\nOrrange, ","1",R.drawable.veg,"250g","₹100","₹120","");
            newOrderBeansList_subcat.add(bean75);
            vegAdapter = new VegSectionAdapter(activity, newOrderBeansList_subcat);
            holder.recyclerView_second.setAdapter(vegAdapter);
        }*/
        /*holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemid=products.getName();

                if (itemid.equals("Vegitables")){
                    selectedFragment = SelectSubCategoryFragment.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.discover_frame, selectedFragment);
                    transaction.addToBackStack("spicescateory");
                    transaction.commit();
                }
                *//*sellingtypeid=products.getId();
                Bundle bundle = new Bundle();
                bundle.putString("status",sellingtypeid);
                bundle.putString("navg_from1",What_Areu_Selling_Fragment.sellnavigation);
               // bundle.putString("ID", What_Areu_Selling_Fragment.sellingdetailsid);
                System.out.println("whatareusellingtypeID"+sellingtypeid);
                selectedFragment = Spices_Category_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("spicescateory");
                selectedFragment.setArguments(bundle);
                transaction.commit();*//*
            }
        });
*/


    }
    private void makeFlyAnimation(ImageView targetView) {


        new CircleAnimationUtil().attachActivity(activity).setTargetView(targetView).setMoveDuration(1000).setDestView(HomeFragment.destView).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                CartCount();
                Toast.makeText(activity, "Continue Shopping...", Toast.LENGTH_SHORT).show();
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

            Login_post.login_posting(activity, Urls.GetReviewCount, userRequestjsonObject, new VoleyJsonObjectCallback() {
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
    private void getVegitableProducts() {

        try{

            //  newOrderBeansList_subcat_veg.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingCategoryId",123);
            jsonObject.put("UserId",2);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(activity, Urls.GetProductDetailsList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("SellDetails");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            Sellbean sellbean = new Sellbean(jsonObject1.getString("SellingListName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("SellingListIcon"),jsonObject1.getString("Quantity"),jsonObject1.getString("Amount"),"","Kg",jsonObject1.getString("ProductDescription"),jsonObject1.getString("UnitOfPriceId"),jsonObject1.getString("ProductId"),"","",jsonObject1.getString("OfferPrice"));

                            newOrderBeansList_subcat_veg.add(sellbean);
                        }
                        vegAdapter1=new VegSectionAdapter1(activity,newOrderBeansList_subcat_veg);
                        //  holder.recyclerView_second.setAdapter(vegAdapter1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }










}
