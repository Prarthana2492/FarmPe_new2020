package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.IrrigationTypeBean;
import com.SevenNine.essentialscode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class IrrigationtypesAdapter extends RecyclerView.Adapter<IrrigationtypesAdapter.MyViewHolder> {

    private List<IrrigationTypeBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static int selected_position=0;
    public static String IrrT_Id,IrrT_Icon,IrrT_Name;



    public IrrigationtypesAdapter(Activity activity, List<IrrigationTypeBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public LinearLayout item;
        public TextView name;


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
        final IrrigationTypeBean products = productList.get(position);
        holder.name.setText(products.getName());

        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.image);



     /*   if(selected_position == position){

            IrrT_Id = products.getId();
            IrrT_Icon = products.getImage();
            IrrT_Name = products.getName();

                holder.item.setBackgroundResource(R.drawable.border_1_layout);

        }else{

            holder.item.setBackgroundResource(R.drawable.request_price_white_border);
        }*/


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IrrT_Id = products.getId();
                IrrT_Icon = products.getImage();
                IrrT_Name = products.getName();

                for (int i = 0; i < productList.size(); i++) {
                    productList.get(i).setIsselected(false);
                }
                productList.get(position).setIsselected(true);
                notifyDataSetChanged();


               /* selected_position = position;
                 notifyItemChanged(position);*/

            }
        });

        if (productList.get(position).isselected()){
            holder.item.setBackgroundResource(R.drawable.border_1_layout);
        }else {
            holder.item.setBackgroundResource(R.drawable.request_price_white_border);
        }


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}
