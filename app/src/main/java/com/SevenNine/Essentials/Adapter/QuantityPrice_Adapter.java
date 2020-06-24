package com.SevenNine.Essentials.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.SevenNine.Essentials.Bean.SelectLanguageBean;
import com.SevenNine.Essentials.R;

import java.util.List;

public class QuantityPrice_Adapter extends RecyclerView.Adapter<QuantityPrice_Adapter.MyViewHolder> {

    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static int quantityprice_id;



    public QuantityPrice_Adapter(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RadioGroup variety;
        public RadioButton variety_txt;


        public MyViewHolder(View view) {
            super(view);


            variety=view.findViewById(R.id.radioGroup);
            variety_txt=view.findViewById(R.id.radioButton1);

        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.quqlity_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products = productList.get(position);

quantityprice_id=products.getLanguageid();

      holder.variety_txt.setText(products.getVendor());

    /* holder.variety_txt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             AddCrops_Fragment.unit_of_price.setText(products.getVendor());
             AddCrops_Fragment.dialog.dismiss();
         }
     });*/
       /* Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.avatarmale))
                .into(holder.image);*/

       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = Spices_Details_Fragment.newInstance();
                FragmentTransaction transaction =  ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("details");
                transaction.commit();
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }










}
