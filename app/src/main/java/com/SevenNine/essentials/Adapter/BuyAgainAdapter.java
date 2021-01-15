package com.SevenNine.essentials.Adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.SevenNine.essentials.Bean.Offers_static_bean;
import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Bean.OrderDetailBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.util.List;


public class BuyAgainAdapter extends RecyclerView.Adapter<BuyAgainAdapter.MyViewHolder>  {
    private List<Offers_static_bean> productList;
    Activity activity;
    Fragment selectedFragment;
    String name,prodid,upid,amount,quantity,status;
    SessionManager sessionManager;
    private BuyAgainAdapter.ProductItemActionListener actionListener;

    public static CardView cardView;
    public BuyAgainAdapter(Activity activity, List<Offers_static_bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }

    public void setActionListener(BuyAgainAdapter.ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView prod_name,quantity,amount,shipping_fee,shipping_iscount,mrp,buy_again,off_text;
        public ImageView image,next,prod_img_fix;
        View view_line;


        public MyViewHolder(View view) {
            super(view);
            image=view.findViewById(R.id.image);
            prod_img_fix=view.findViewById(R.id.prod_img_fix);
            prod_name=view.findViewById(R.id.prod_name);
            amount=view.findViewById(R.id.amount);
            quantity=view.findViewById(R.id.quantity);
            buy_again=view.findViewById(R.id.buy_again);
          //  shipping_fee=view.findViewById(R.id.shipping);
          //  shipping_iscount=view.findViewById(R.id.shipping_iscount);
            view_line=view.findViewById(R.id.view_line);
            mrp=view.findViewById(R.id.mrp);
            off_text=view.findViewById(R.id.off_text);


            sessionManager=new SessionManager(activity);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buy_again_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Offers_static_bean products = productList.get(position);


        holder.prod_name.setText(products.getName());
        holder.quantity.setText(products.getWeight()+" Kg");
        holder.amount.setText("Rs "+products.getRs());
        if (products.getMrp_discount().equals(products.getRs())){
            holder.amount.setVisibility(View.INVISIBLE);
            holder.mrp.setVisibility(View.INVISIBLE);
        }else{
            holder.amount.setText("₹"+products.getMrp_discount());
            holder.amount.setPaintFlags(holder.amount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        //  holder.actual_price.setText("₹"+products.getMrp());
        // holder.off_text.setText(products.getOffer_price()+"%"+"\n off");
//        double off_price_calcu=(((Double.parseDouble(products.getMrp_discount())-Double.parseDouble(products.getRs()))/(Double.parseDouble(products.getMrp())))*100);
//        System.out.println("jhfdiueshfr"+off_price_calcu);
//        int offer_per_int=(int)off_price_calcu;
//        String off_price_text=String.valueOf(offer_per_int);
        holder.off_text.setText("10"+"%");
//
//        if (products1.getProd_desc().equals("")){
//            holder.prod_name.setText(products1.getProd_name()+", "+products1.getCart_prodlistid());
//        }else{
//            holder.prod_name.setText(products1.getProd_name()+", "+products1.getProd_desc()+", "+products1.getBrand());
//        }
//     // holder.prod_name.setText(products1.getProd_name());
//      holder.quantity.setText("Quantity : "+products1.getQuantity()+" Kg");
//      holder.amount.setText("₹"+products1.getAmount()+".00");
//      holder.mrp.setText("₹"+products1.getMRP());
//      //  holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
////        if (products1.getShippng_iscount().equals("0")){
//            holder.off_text.setVisibility(View.GONE);
//            holder.amount.setText("Rs "+products1.getAmount());
//
//        }else{
//            holder.off_text.setVisibility(View.VISIBLE);
//            // holder.off_text.setText(products.getOfferPrice()+"%"+"\n off");
//            holder.amount.setText("Rs "+products1.getShippng_iscount());
//            double off_price_calcu=(((Double.parseDouble(products1.getMRP())-Double.parseDouble(products1.getShippng_iscount()))/(Double.parseDouble(products1.getMRP())))*100);
//            System.out.println("jhfdiueshfr"+off_price_calcu);
//            int offer_per_int=(int)off_price_calcu;
//            String off_price_text=String.valueOf(offer_per_int);
//            holder.off_text.setText(off_price_text+"%");
//        }
//
//        if (products1.getMRP().equals(products1.getAmount())){
//            holder.mrp.setVisibility(View.INVISIBLE);
//        }else{
//            holder.mrp.setText("₹"+products1.getMRP());
//            holder.mrp.setBackground(activity.getResources().getDrawable(R.drawable.line));
//        }
        //      holder.shipping_fee.setText("Shipping Fee: "+products1.getShipping_fee());
  //    holder.shipping_iscount.setText("Shipping iscount: "+products1.getShippng_iscount());
if (position==(productList.size()-1)){
    holder.view_line.setVisibility(View.GONE);
}
        Glide.with(activity).load(products.getImage1())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.image);

        Glide.with(activity).load(products.getImage1())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.prod_img_fix);

//        holder.buy_again.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                prodid=products1.getProductId();
//             //   upid=products1.get();
//                amount=products1.getAmount();
//                quantity=products1.getQuantity();
//                ComposeCategory();
//                if(actionListener!=null)
//                    actionListener.onItemTap(holder.image);
//            }
//        });

       /*holder.in_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = Order_Details_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("track1");
                transaction.commit();
            }
        });
*/
    }
    private void ComposeCategory() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartProductListId", 0);
            jsonObject.put("ProductId", prodid);
            jsonObject.put("SellingQuantity", quantity);
            jsonObject.put("UnitOfPriceId", 1);
            jsonObject.put("Amount", amount);
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


    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }
}