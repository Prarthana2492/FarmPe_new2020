package com.SevenNine.essentials.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.Fragment.CategoryProdDetailList;
import com.SevenNine.essentials.Bean.MainVerticalBean;
import com.SevenNine.essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.MyViewHolder> {

    private List<MainVerticalBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String sellingtypeid,sellingedit_id;



    public SelectCategoryAdapter(Activity activity, List<MainVerticalBean> moviesList) {
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
     final MainVerticalBean products = productList.get(position);
      sellingtypeid=products.getId();

        holder.name.setText(products.getName());


        Glide.with(activity).load(products.getImage1())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                bundle.putString("sellingCatId",products.getId());
                selectedFragment = CategoryProdDetailList.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("spicescateory");
                transaction.commit();

                /*sellingtypeid=products.getId();
                Bundle bundle = new Bundle();
                bundle.putString("status",sellingtypeid);
                bundle.putString("navg_from1",What_Areu_Selling_Fragment.sellnavigation);
               // bundle.putString("ID", What_Areu_Selling_Fragment.sellingdetailsid);
                System.out.println("whatareusellingtypeID"+sellingtypeid);
                selectedFragment = Spices_Category_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("spicescateory");
                selectedFragment.setArguments(bundle);
                transaction.commit();*/
            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }










}
