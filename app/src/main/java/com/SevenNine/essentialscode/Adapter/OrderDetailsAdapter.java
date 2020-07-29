package com.SevenNine.essentialscode.Adapter;

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

import com.SevenNine.essentialscode.Bean.OrderDetailBean;
import com.SevenNine.essentialscode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>  {
    private List<OrderDetailBean> productList;
    Activity activity;
    Fragment selectedFragment;
    String name,getamt;
    double save_amt,offer_price;

    public static CardView cardView;
    public OrderDetailsAdapter(Activity activity, List<OrderDetailBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView prod_name,quantity,amount,shipping_fee,shipping_iscount,mrp,off_text;
        public ImageView image,next;
        View view_line;

        public MyViewHolder(View view) {
            super(view);
            image=view.findViewById(R.id.image);
            prod_name=view.findViewById(R.id.prod_name);
            amount=view.findViewById(R.id.amount);
            quantity=view.findViewById(R.id.quantity);
            shipping_fee=view.findViewById(R.id.shipping);
            shipping_iscount=view.findViewById(R.id.shipping_iscount);
            view_line=view.findViewById(R.id.view_line);
            mrp=view.findViewById(R.id.mrp);
            off_text=view.findViewById(R.id.off_text);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_detail_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OrderDetailBean products1 = productList.get(position);
        holder.prod_name.setText(products1.getProd_name());

       /* if (products1.getProd_desc().equals("")){
            holder.prod_name.setText(products1.getProd_name());
        }else{
            holder.prod_name.setText(products1.getProd_name());
        }*/
     // holder.prod_name.setText(products1.getProd_name());
      holder.quantity.setText("Quantity : "+products1.getQuantity());
        if (products1.getShippng_iscount().equals("0")){
            holder.amount.setText("₹"+products1.getAmount());
        }else{
            offer_price = ((Double.parseDouble(products1.getMRP()))-(Double.parseDouble(products1.getMRP())) * ((Double.parseDouble(products1.getShippng_iscount())) / 100));
            String strDouble = String.format("%.2f", offer_price);
            holder.amount.setText("₹"+strDouble);
        }
        if (products1.getShippng_iscount().equals("0")){
            holder.off_text.setVisibility(View.GONE);
            holder.amount.setText("Rs "+products1.getAmount());

        }else{
            holder.off_text.setVisibility(View.VISIBLE);
            holder.amount.setText("Rs "+products1.getShippng_iscount());

            //  holder.off_text.setText(products1.getShippng_iscount()+"%"+"\n off");
            double off_price_calcu=(((Double.parseDouble(products1.getMRP()))-Double.parseDouble(products1.getShippng_iscount()))/(Double.parseDouble(products1.getMRP()))*100);
            System.out.println("jhfdiueshfr"+off_price_calcu);
            int offer_per_int=(int)off_price_calcu;
            String off_price_text=String.valueOf(offer_per_int);
            holder.off_text.setText(off_price_text+"%");
        }
     // holder.amount.setText("₹"+products1.getAmount());
      holder.mrp.setText("₹"+products1.getMRP());
      //  holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mrp.setBackground(activity.getResources().getDrawable(R.drawable.line));

        holder.shipping_fee.setText("Delivery Charges: "+products1.getShipping_fee());
        getamt=holder.amount.getText().toString().substring(3);
        save_amt=((Double.parseDouble(products1.getMRP()))-(Double.parseDouble(getamt)));
        String strDouble1 = String.format("%.2f", save_amt);
        holder.shipping_iscount.setText("save ₹"+strDouble1);
        if (position==(productList.size()-1)){
    holder.view_line.setVisibility(View.GONE);
}
        Glide.with(activity).load(products1.getProd_img())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.image);


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

    @Override
    public int getItemCount() {
        return productList.size();
    }
}