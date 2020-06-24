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

import com.SevenNine.Essentials.Bean.AddTractorBean3;
import com.SevenNine.Essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class PreviewCrop_Adapter extends RecyclerView.Adapter<PreviewCrop_Adapter.MyViewHolder>  {
    private List<AddTractorBean3> productList;
    Activity activity;
    Fragment selectedFragment;



    public PreviewCrop_Adapter(Activity activity, List<AddTractorBean3> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dealer_text;
        public LinearLayout dealer_lay;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            dealer_text=view.findViewById(R.id.dealer_text);
            image=view.findViewById(R.id.dealer_img);
            dealer_lay=view.findViewById(R.id.dealer_lay);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.soil_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddTractorBean3 products1 = productList.get(position);

        holder.dealer_text.setText(products1.getProd_name());

       /* holder.dealer_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = ProductsDealerFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("home");
                transaction.commit();
            }
        });*/

        Glide.with(activity).load(products1.getImage())

                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.image);

    }








    @Override
    public int getItemCount() {
        return productList.size();
    }
}