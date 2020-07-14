package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.SevenNine.essentialscode.Bean.AddTractorBean2;
import com.SevenNine.essentialscode.R;

import java.util.List;

public class ListEdit_Adapter extends RecyclerView.Adapter<ListEdit_Adapter.MyViewHolder> {

private List<AddTractorBean2> productList;
        Activity activity;


public ListEdit_Adapter(Activity activity, List<AddTractorBean2> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


        }


public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;


    public MyViewHolder(View view) {
        super(view);

        image=view.findViewById(R.id.image);

    }

}
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.listedit_adapter_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddTractorBean2 products = productList.get(position);
        holder.image.setImageResource(products.getImage());



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
