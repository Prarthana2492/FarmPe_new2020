package com.SevenNine.essentialscode.Fragment;

import android.animation.Animator;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

import static android.content.Context.INPUT_METHOD_SERVICE;


public class CategoryProdDetailListSearch extends Fragment {

    public static ArrayList<Sellbean> newOrderBeansList_subcat = new ArrayList<>();
    private List<Sellbean> searchresultAraaylist = new ArrayList<>();

    public static RecyclerView recyclerView_main;
    public static CategoryProdDetailAdapter livestock_types_adapter;
    JSONObject jsonObject1;
    Fragment selectedFragment = null;
    public static TextView toolbar_title,name;
    EditText search;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout,main_layout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
    public static String sellingcatId,sellnavigation;
    SessionManager sessionManager;
    private Handler mHandler= new Handler();

    public static CategoryProdDetailListSearch newInstance() {
        CategoryProdDetailListSearch fragment = new CategoryProdDetailListSearch();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_prod_recy_search, container, false);
        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));

       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/
        recyclerView_main=view.findViewById(R.id.recycler_cat_detail);
        search=view.findViewById(R.id.search);
        name=view.findViewById(R.id.name);
        sessionManager=new SessionManager(getActivity());
        linearLayout = view.findViewById(R.id.linearLayout);
        main_layout = view.findViewById(R.id.main_layout);
     //   getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mHandler.post(
                new Runnable() {
                    public void run() {
                        InputMethodManager inputMethodManager =  (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInputFromWindow(search.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
                        search.requestFocus();
                    }
                });
        /*InputMethodManager inputMethodManager =
                (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                main_layout.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);*/

//        toolbar_title.setText("Select Category");
        // sellingdetailsid=Inventory_Details_Fragment.SId;
        if (getArguments()==null){
            System.out.println("hommmmmmee");
            sellingcatId=HomeFragment.shop_cat_id;

        }else{
            sellingcatId=getArguments().getString("sellingCatId");

        }
        search.requestFocus();
        search.setCursorVisible(true);

        setupUI(main_layout);

       /* if (DiscoverCategoryFragment.search_st!=null){
            System.out.println("requesttttt");
            search.setCursorVisible(true);
        }*/
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

        newOrderBeansList_subcat.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
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
        try{

            //  newOrderBeansList_subcat_veg.clear();
            JSONObject jsonObject = new JSONObject();
           // jsonObject.put("SellingCategoryId",sellingcatId);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetProductDetailsFrom7NinePartner, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("ProductDetailsFromPartner");

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

        }catch (Exception e){
            e.printStackTrace();
        }

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sorting(s.toString());
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                search.requestFocus();
                search.setCursorVisible(true);

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

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

    public void setupUI(View view) {


        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {


        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try {
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        }
    }
   /* private void setFocusCursor(){
        mBinding.replyConversationsFooter.footerEditText.setFocusable(true);
        mBinding.replyConversationsFooter.footerEditText.setFocusableInTouchMode(true);
        mBinding.replyConversationsFooter.footerEditText.requestFocus();
    }*/
}
