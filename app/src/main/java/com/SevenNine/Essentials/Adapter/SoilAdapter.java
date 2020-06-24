package com.SevenNine.Essentials.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.SevenNine.Essentials.Bean.StateBean;
import com.SevenNine.Essentials.R;

import java.util.List;

public class SoilAdapter extends RecyclerView.Adapter<SoilAdapter.MyViewHolder> {
    public static    List<StateBean> productList;
    Activity activity;
    Fragment selectedFragment;


    public SoilAdapter(Activity activity, List<StateBean> moviesList) {
        this.productList = moviesList;
        this.activity = activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public  TextView soil_text;

        public MyViewHolder(View view) {
            super(view);

            soil_text = view.findViewById(R.id.soil_txt);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.soiltype_adtr_lyt, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final StateBean products = productList.get(position);

        holder.soil_text.setText(products.getName());

    }




    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh" + productList.size());
        return productList.size();
    }


}