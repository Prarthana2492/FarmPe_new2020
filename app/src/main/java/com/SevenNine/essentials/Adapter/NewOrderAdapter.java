package com.SevenNine.essentials.Adapter;

import android.app.Activity;
import android.os.Bundle;
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

import com.SevenNine.essentials.Bean.NewOrderBean;
import com.SevenNine.essentials.Bean.Order_Bean;
import com.SevenNine.essentials.Fragment.OrderDetailsFragment;
import com.SevenNine.essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class NewOrderAdapter extends RecyclerView.Adapter<NewOrderAdapter.MyViewHolder>  {
    private List<Order_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    String name,amount;
    double offerprice;

    public static CardView cardView;
    public NewOrderAdapter(Activity activity, List<Order_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView prod_name,dispatched_date,off_text;
        public ImageView image,next;


        public MyViewHolder(View view) {
            super(view);
            image=view.findViewById(R.id.image);
            prod_name=view.findViewById(R.id.prod_name);
            dispatched_date=view.findViewById(R.id.dispatched);
            next=view.findViewById(R.id.arrow);
            off_text=view.findViewById(R.id.off_text);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_new_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Order_Bean products1 = productList.get(position);

        System.out.println("ordreadapterrrr");
      //  if (products1.getOffer_price().equals("0")){
            holder.prod_name.setText(products1.getName()+", "+products1.getWeight()+" Kg"+", ₹"+products1.getCost());
       // }else{

//            offerprice = ((Double.parseDouble(products1.getMrp()))-(Double.parseDouble(products1.getMrp())) * ((Double.parseDouble(products1.getOffer_price())) / 100));
//            holder.prod_name.setText(products1.getProd_name()+", "+products1.getQuantity()+" Kg"+", ₹"+products1.getOffer_price());
//            System.out.println("hjfghdsgh"+offerprice);
       // }
      holder.dispatched_date.setText("Ordered on "+products1.getDate());
//        if (products1.getOffer_price().equals("0")){
//            holder.off_text.setVisibility(View.GONE);
//        }else{
//            holder.off_text.setVisibility(View.VISIBLE);
//          //  holder.off_text.setText(products1.getOffer_price()+"%"+"\n off");
//            double off_price_calcu=(((Double.parseDouble(products1.getMrp())-Double.parseDouble(products1.getOffer_price()))/(Double.parseDouble(products1.getMrp())))*100);
//            System.out.println("jhfdiueshfr"+off_price_calcu);
//            int offer_per_int=(int)off_price_calcu;
//            String off_price_text=String.valueOf(offer_per_int);
//            holder.off_text.setText(off_price_text+"%");
//        }
        Glide.with(activity).load(products1.getProd_image())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.veg))
                .into(holder.image);


       holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("ooooooooooog"+offerprice);
//                Bundle bundle=new Bundle();
//                bundle.putString("createdon",products1.getCreatedOn());
//                bundle.putString("Amount",products1.getAmount());
//                bundle.putString("ProdName",products1.getProd_name());
//                bundle.putString("quantity",products1.getQuantity());
//                bundle.putString("product_info",products1.getFirstname());
//                bundle.putString("prod_img",products1.getProd_img());
//                bundle.putString("pay_mode",products1.getMode());
//                bundle.putString("uom",products1.getMrp());
//                bundle.putString("txnId",products1.getTxnId());
//                bundle.putString("address",products1.getFirstname());
//                bundle.putString("cat_name",products1.getProductInfo());
//                bundle.putString("delivery_charges",products1.getDelivery_charges());
//                bundle.putString("offer_price",products1.getOffer_price());
//
//                System.out.println("xxxttatta"+products1.getTxnId());


                //  bundle.putString("uom",products1.getUom());
                selectedFragment = OrderDetailsFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("track2");
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}