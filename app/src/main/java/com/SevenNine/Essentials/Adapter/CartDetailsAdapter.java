package com.SevenNine.Essentials.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.SevenNine.Essentials.Bean.OrderDetailBean;
import com.SevenNine.Essentials.Fragment.CartDetailsFragment;
import com.SevenNine.Essentials.Fragment.HomeFragment;
import com.SevenNine.Essentials.Fragment.NoItemsFragment;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;
import com.SevenNine.Essentials.Urls;
import com.SevenNine.Essentials.Utils.QuantityPicker;
import com.SevenNine.Essentials.Volly_class.Crop_Post;
import com.SevenNine.Essentials.Volly_class.Login_post;
import com.SevenNine.Essentials.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CartDetailsAdapter extends RecyclerView.Adapter<CartDetailsAdapter.MyViewHolder>  {
    private List<OrderDetailBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String name,total_prise_st,cart_prod_listId,cart_prodlistid,status;
    public static int total_cart_items;
    SessionManager sessionManager;
    public static CardView cardView;
    public static String productidlist;
    public static String strlist;

    public static ArrayList<Integer> intlist = new ArrayList<Integer>();

    public CartDetailsAdapter(Activity activity, List<OrderDetailBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView prod_name,quantity,amount,shipping_fee,shipping_iscount,quant_count,delete,save_for_later,mrp;
        public ImageView image,next;
        View view_line;
        QuantityPicker quantityPicker;


        public MyViewHolder(View view) {
            super(view);
            image=view.findViewById(R.id.image);
            prod_name=view.findViewById(R.id.prod_name);
            amount=view.findViewById(R.id.amount);
            quantity=view.findViewById(R.id.quantity);
            shipping_fee=view.findViewById(R.id.shipping);
            shipping_iscount=view.findViewById(R.id.shipping_iscount);
            view_line=view.findViewById(R.id.view_line);
          //  quant_count=view.findViewById(R.id.quant_count);
            quantityPicker= view.findViewById(R.id.quantityPicker);

            delete=view.findViewById(R.id.delete);
            save_for_later=view.findViewById(R.id.save_for_later);
            mrp=view.findViewById(R.id.mrp);

            sessionManager=new SessionManager(activity);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_det_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OrderDetailBean products1 = productList.get(position);
        holder.prod_name.setText(products1.getProd_name());

       /* if (products1.getProd_desc().equals("")){
            holder.prod_name.setText(products1.getProd_name()+", "+products1.getBrand());
        }else{
            holder.prod_name.setText(products1.getProd_name()+", "+products1.getProd_desc()+", "+products1.getBrand());
        }*/
    //  holder.prod_name.setText(products1.getProd_name()+", "+products1.getProd_desc()+", "+products1.getBrand());
    //  holder.quantity.setText("Quantity : "+products1.getQuantity());
      holder.amount.setText("Rs "+products1.getAmount());
    //  holder.quant_count.setText(products1.getQuantity());
        int qt= Integer.parseInt(products1.getQuantity());
        holder.quantityPicker.setQuantitySelected(qt);
      holder.shipping_fee.setText("Shipping Fee: "+products1.getShipping_fee());
      holder.shipping_iscount.setText("Shipping discount: "+products1.getShippng_iscount());
      holder.mrp.setText("₹"+products1.getMRP());
        holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

/*if (position==(productList.size()-1)){
    holder.view_line.setVisibility(View.GONE);
}*/
        Glide.with(activity).load(products1.getProd_img())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.veg))
                .into(holder.image);

         int totalPrice = 0;

        for (int i = 0; i<productList.size(); i++)
        {
            totalPrice += Integer.parseInt(productList.get(i).getAmount());
        }
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);;
        formatter .applyPattern("##,##,##,###");
        double rate_double1= (totalPrice);
       /* double rate_double2= (totalPrice);
        double rate_double3= (totalPrice);
        double rate_double4= (totalPrice);
        double rate_double5= (totalPrice);*/

        formatter.format(rate_double1);

      //  System.out.println("lllllllllllllllllllllll"+ formatter.format(rate_double));
       // loan_amount.setText(": ₹ "+formatter.format(rate_double));

            System.out.println("total_amounttt" + totalPrice);
            CartDetailsFragment.main_total_amount.setText("₹" + formatter.format(rate_double1)+".00");
            CartDetailsFragment.total_before_tax.setText("₹" + formatter.format(rate_double1)+".00");
            CartDetailsFragment.items_cost.setText("₹" + formatter.format(rate_double1)+".00");
            CartDetailsFragment.total_without_disc.setText("₹" + formatter.format(rate_double1)+".00");
            CartDetailsFragment.total_items.setText("Subtotal(" + (productList.size()) + " Items):");

            CartDetailsFragment.total_amount.setText("₹" + formatter.format(rate_double1));


        intlist.clear();

        for(int i = 0 ; i < productList.size(); i++) {
            intlist.add(Integer.parseInt(productList.get(i).getCart_prodlistid()));
        }
        StringBuilder sbString = new StringBuilder("");
        //iterate through ArrayList
        for(int language : intlist){
            //append ArrayList element followed by comm
            sbString.append(language).append(",");
        }
        //convert StringBuffer to String
        productidlist=sbString.toString();
        strlist=  productidlist.substring(0, productidlist.length() - 1);
        System.out.println("productidlisttesting" + strlist);

        cart_prod_listId=products1.getCart_prodlistid();
        total_prise_st=String.valueOf(totalPrice);
        total_cart_items=productList.size();
       holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart_prodlistid=products1.getCart_prodlistid();

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.delete_details_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                TextView ok=dialog.findViewById(R.id.ok);
                TextView cancel=dialog.findViewById(R.id.cancel);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("CartProductListId",cart_prodlistid);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            System.out.println("bank_dvvvvetails_iddd"+jsonObject);

                            Crop_Post.crop_posting(activity, Urls.DeleteReviewCartList, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");

                                        if(status.equals("1")){

                                            productList.remove(position);
                                            CartCount();
                                            notifyDataSetChanged();
                                            System.out.println("jdhjahdjkah"+productList.size());
                                            if (productList.size()==0){
                                                selectedFragment = NoItemsFragment.newInstance();
                                                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                                                transaction.replace(R.id.frame_layout_home, selectedFragment);
                                                transaction.addToBackStack("cart_detail");
                                                transaction.commit();

                                            }

                                        }

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });

holder.save_for_later.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cart_prodlistid=products1.getCart_prodlistid();

        try{
            JSONObject jsonObject  = new JSONObject();
            jsonObject.put("CartProductListId",cart_prodlistid);
            jsonObject.put("ProductId",products1.getProductId());
            jsonObject.put("IsShortlisted",1);
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            System.out.println("bank_dvvvvetails_iddds"+jsonObject);

            Crop_Post.crop_posting(activity, Urls.AddUpdateFavouriteCartList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111111dddd" + result);

                    try{

                        status = result.getString("Status");

                        if(status.equals("Success")){

                            productList.remove(position);
                            notifyDataSetChanged();
                            System.out.println("jdhjahdjkah"+productList.size());
                            if (productList.size()==0){
                                selectedFragment = NoItemsFragment.newInstance();
                                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame_layout_home, selectedFragment);
                                transaction.addToBackStack("cart_detail");
                                transaction.commit();

                            }

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }
});
        /*JSONArray array=new JSONArray();

        for(int i=0;i<filter_items.size();i++){
            JSONObject obj=new JSONObject();
            try {
                obj.put("filterId",filter_items.get(i));
                obj.put("typeName","CAT_ID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
        }*/

       /* holder.quantityPicker.setOnQuantityChangeListener(new QuantityPicker.OnQuantityChangeListener() {
            @Override
            public void onValueChanged(int quantity) {

                try {

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("CartProductListId", 0);
                    jsonObject.put("ProductId", products1.getProductId());
                    jsonObject.put("SellingQuantity",products1.getQuantity());
                    jsonObject.put("UnitOfPriceId", 1);
                    jsonObject.put("Amount", products1.getAmount());
                    jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));
                    jsonObject.put("UserId", sessionManager.getRegId("userId"));
                    System.out.println("Add_New_AddresssssssssssssssssjsonObject" + jsonObject);

                    Crop_Post.crop_posting(activity, Urls.AddUpdateCartProductDetails, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {


                            System.out.println("Add_New_Addressssssssssssssssslllllllllllllllllllllll" + result);
                            try {

                                status = result.getString("Status");
                                // message = result.getString("Message");

                                //   bundle.putString("add_id",status);

                                //   bundle.putString("streetname",  DistrictAdapter.district_name);


                        *//*if (status.equals("Success")) {

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

                        }*//*

                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });*/


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
                            if (Total.equals("0")){
                                HomeFragment.cart_count.setVisibility(View.GONE);
                            }else {
                                HomeFragment.cart_count.setText(Total);
                            }
                           // HomeFragment.cart_count.setText(Total);


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

    @Override
    public int getItemCount() {
        return productList.size();
    }
}