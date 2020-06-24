package com.SevenNine.Essentials.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.Essentials.Bean.Sell_selected_bean;
import com.SevenNine.Essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Soil_Details_Adapter extends RecyclerView.Adapter<Soil_Details_Adapter.MyViewHolder> {

    private List<Sell_selected_bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String soilid;
    public static String image_icon;
    public static String soilname;



    public static int selected_position = 0;


    public Soil_Details_Adapter(Activity activity, List<Sell_selected_bean> moviesList) {

        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView name;
        public  LinearLayout item;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);

             image=view.findViewById(R.id.prod_img);
             item=view.findViewById(R.id.item);
             name=view.findViewById(R.id.f_name);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.homepage_adapter_layout, parent, false);
                return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Sell_selected_bean products = productList.get(position);


        holder.name.setText(products.getName());


        /*Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);*/

        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.image);

        if (selected_position == position) {

            //  if(selected_position == position){
            soilid = products.getId();
            soilname = products.getName();
            image_icon = products.getImage();

            holder.item.setBackgroundResource(R.drawable.border_1_layout);


        } else {

            holder.item.setBackgroundResource(R.drawable.request_price_white_border);

        }


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                holder.item.setBackgroundResource(R.drawable.border_1_layout);

                soilid = products.getId();
                soilname = products.getName();
                image_icon = products.getImage();
                selected_position = position;

                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
