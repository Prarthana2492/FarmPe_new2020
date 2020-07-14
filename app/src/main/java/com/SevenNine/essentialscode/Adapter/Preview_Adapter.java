package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.SevenNine.essentialscode.Bean.AddTractorBean;
import com.SevenNine.essentialscode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class Preview_Adapter extends RecyclerView.Adapter<Preview_Adapter.MyViewHolder>  {
    private List<AddTractorBean> productList;
    Activity activity;
    Fragment selectedFragment;


    public static CardView cardView;
    public Preview_Adapter(Activity activity, List<AddTractorBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lang_text,brand_text;
        public LinearLayout brand_lay;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            brand_lay=view.findViewById(R.id.brand_lay);
            image=view.findViewById(R.id.auth_img);
            brand_text=view.findViewById(R.id.brand_name);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.auth_brand_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddTractorBean products1 = productList.get(position);



        holder.brand_text.setText(products1.getProd_name());

       /* holder.brand_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = AuthorizationBrandFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("home");
                transaction.commit();
            }
        });
*/



        Glide.with(activity).load(products1.getImage())

                .thumbnail(0.5f)
              //  .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.image);

    }








    @Override
    public int getItemCount() {
        return productList.size();
    }
}