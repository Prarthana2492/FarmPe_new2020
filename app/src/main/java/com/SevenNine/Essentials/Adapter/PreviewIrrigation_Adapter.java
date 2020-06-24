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

import com.SevenNine.Essentials.Bean.IrrigationBean;
import com.SevenNine.Essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class PreviewIrrigation_Adapter extends RecyclerView.Adapter<PreviewIrrigation_Adapter.MyViewHolder>  {
    private List<IrrigationBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public PreviewIrrigation_Adapter(Activity activity, List<IrrigationBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView type_text,method_text,coverage_text;
        public LinearLayout dealer_lay;
        public ImageView type_icon,method_icon,coverage_icon;


        public MyViewHolder(View view) {
            super(view);
            type_text=view.findViewById(R.id.type_txt);
            type_icon=view.findViewById(R.id.type_icon);
            method_text=view.findViewById(R.id.douse_text);
            method_icon=view.findViewById(R.id.douse_img);
            coverage_text=view.findViewById(R.id.cvrg_text);
            coverage_icon=view.findViewById(R.id.cvrg_icon);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.irrigation_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final IrrigationBean products1 = productList.get(position);

       holder.type_text.setText(products1.getType_name());
       holder.method_text.setText(products1.getMethod_name());
       holder.coverage_text.setText(products1.getCoverage_name());

        Glide.with(activity).load(products1.getType_image())

                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.type_icon);

        Glide.with(activity).load(products1.getMethod_image())

                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.method_icon);

        Glide.with(activity).load(products1.getCoverage_image())

                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.coverage_icon);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}