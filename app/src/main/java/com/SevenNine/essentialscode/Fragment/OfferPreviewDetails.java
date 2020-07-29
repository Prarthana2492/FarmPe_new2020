package com.SevenNine.essentialscode.Fragment;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.OffersAdapter;
import com.SevenNine.essentialscode.CircleAnimationUtil;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Utils.QuantityPicker;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OfferPreviewDetails extends Fragment {
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    JSONObject lngObject;
    Fragment selectedFragment;
    TextView toolbar_title,prod_name,price,actual_price,offer_perc,abt_product,add_cart;
    ImageView prod_img,prod_img_fix;
    private OfferPreviewDetails.ProductItemActionListener actionListener;
    public static String sellingtypeid,sellingedit_id,prodid,upid,amount,quantity,status,brand,mrp,offer_price;
    QuantityPicker quantityPicker;
    public static OfferPreviewDetails newInstance() {
        OfferPreviewDetails fragment = new OfferPreviewDetails();
        return fragment;
    }
   /* public static void setActionListener(OffersAdapter.ProductItemActionListener actionListener) {
        actionListener = actionListener;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_prod_details, container, false);
     //   back_feed=view.findViewById(R.id.back_feed);
        prod_name=view.findViewById(R.id.prod_name);
        price=view.findViewById(R.id.price);
        actual_price=view.findViewById(R.id.actual_price);
        offer_perc=view.findViewById(R.id.offer_perc);
        abt_product=view.findViewById(R.id.abt_product);
        prod_img=view.findViewById(R.id.prod_img);
        add_cart=view.findViewById(R.id.add_cart);
        prod_img_fix=view.findViewById(R.id.prod_img_fix);
        quantityPicker=view.findViewById(R.id.quantityPicker);

        sessionManager=new SessionManager(getActivity());
        quantityPicker.setQuantitySelected(1);
        quantityPicker.setOnQuantityChangeListener(new QuantityPicker.OnQuantityChangeListener() {
            @Override
            public void onValueChanged(int quantity) {
                if (quantityPicker.getQuantity()<1){
                    quantityPicker.setQuantitySelected(1);
                }
            }
        });


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
       /* back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });*/


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                   /* FragmentManager fm = getFragmentManager();
                    fm.popBackStack();*/
                    selectedFragment = OffersListFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("track24");
                    transaction.commit();
                    return true;
                }
                return false;

            }
        });

      /*  prod_name.setText(getArguments().getString("product_name_st"));
        price.setText(getArguments().getString("product_price_st"));
        actual_price.setText(getArguments().getString("product_mrp_st"));
        abt_product.setText(getArguments().getString("product_name_st")+", "+getArguments().getString("prod_brand_st")+", "+getArguments().getString("prod_quant"));
*/
        prod_name.setText(OffersAdapter.prod_name);
      //  price.setText("Rs"+OffersAdapter.product_price_st);
        actual_price.setText("₹"+OffersAdapter.mrp);
        actual_price.setBackground(getActivity().getResources().getDrawable(R.drawable.line));

        if (OffersAdapter.offer_price.equals("")){
            price.setText("Rs "+OffersAdapter.amount);
            double off_price_calcu=(((Double.parseDouble(OffersAdapter.mrp)-Double.parseDouble(OffersAdapter.amount))/(Double.parseDouble(OffersAdapter.mrp)))*100);
            System.out.println("jhfdiueshfr"+off_price_calcu);
            int offer_per_int=(int)off_price_calcu;
            String off_price_text=String.valueOf(offer_per_int);
            offer_perc.setText(off_price_text+"%");
        }else{
            price.setText("Rs "+OffersAdapter.offer_price);
            double off_price_calcu=(((Double.parseDouble(OffersAdapter.mrp)-Double.parseDouble(OffersAdapter.offer_price))/(Double.parseDouble(OffersAdapter.mrp)))*100);
            System.out.println("jhfdiueshfr"+off_price_calcu);
            int offer_per_int=(int)off_price_calcu;
            String off_price_text=String.valueOf(offer_per_int);
            offer_perc.setText(off_price_text+"%");
        }
        if (OffersAdapter.brand.equals("")){
            abt_product.setText(OffersAdapter.prod_name+", "+OffersAdapter.quantity+" Kg");

        }else{
            abt_product.setText(OffersAdapter.prod_name+", "+OffersAdapter.brand+", "+OffersAdapter.quantity+" Kg");

        }

       /* double off_price_calcu=(((Double.parseDouble(OffersAdapter.product_mrp_st)-Double.parseDouble(OffersAdapter.product_price_st))/(Double.parseDouble(OffersAdapter.product_mrp_st)))*100);
        System.out.println("jhfdiueshfr"+off_price_calcu);
        int offer_per_int=(int)off_price_calcu;
        String off_price_text=String.valueOf(offer_per_int);
        offer_perc.setText(off_price_text+"%");*/

        Glide.with(getActivity()).load(OffersAdapter.prod_img)
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(prod_img);
        Glide.with(getActivity()).load(OffersAdapter.prod_img)
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(prod_img_fix);
    //    LoanInformation();


        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("jhfsdkjhkj"+OffersAdapter.prodid);
                prodid=OffersAdapter.prodid;
                upid=OffersAdapter.upid;
                amount=OffersAdapter.amount;
                quantity=OffersAdapter.quantity;

                //  selected_quant=holder.quantityPicker.getQuantity();
                ComposeCategory();
                if(actionListener!=null)
                    actionListener.onItemTap(prod_img);
            }
        });
        return view;
    }
    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }
    private void ComposeCategory() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartProductListId", 0);
            jsonObject.put("ProductId", prodid);
            jsonObject.put("SellingQuantity", quantityPicker.getQuantity());
            // jsonObject.put("SelectedQuantity", selected_quant);
            jsonObject.put("UnitOfPriceId", 1);
            jsonObject.put("Amount", amount);
            jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            System.out.println("Add_New_AddresssssssssssssssssjsonObject" + jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.AddUpdateCartProductDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {


                    System.out.println("Add_New_Addressssssssssssssssslllllllllllllllllllllll" + result);
                    try {

                        status = result.getString("Status");

                                if (prod_img != null) {
                                    makeFlyAnimation(prod_img);
                                }

                        // message = result.getString("Message");

                        //   bundle.putString("add_id",status);

                        //   bundle.putString("streetname",  DistrictAdapter.district_name);


                        /*if (status.equals("Success")) {

                            int duration = 1000;
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

                        }*/

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            });


        } catch (Exception e) {
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
