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

import com.SevenNine.essentials.Bean.MainAdapterBean1;
import com.SevenNine.essentials.Fragment.SelectCategoryFragment;
import com.SevenNine.essentials.Bean.MainVerticalBean;
import com.SevenNine.essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SelectMainAdapter extends RecyclerView.Adapter<SelectMainAdapter.MyViewHolder> {

    private List<MainAdapterBean1> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String sellingtypeid,itemid;
    public int mSelectedItem = -1;



    public SelectMainAdapter(Activity activity, List<MainAdapterBean1> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView main_img;
        public LinearLayout item;
        public TextView name;


        public MyViewHolder(View view) {
            super(view);

            main_img=view.findViewById(R.id.prod_img);
            item=view.findViewById(R.id.item);
            name=view.findViewById(R.id.f_name);

        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.select_main_layout1, parent, false);
        return new MyViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     final MainAdapterBean1 products = productList.get(position);



        holder.name.setText(products.getName());

      switch (position){
          case 0:
              Glide.with(activity).load(R.drawable.vegitablespng)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 1:
              Glide.with(activity).load(R.drawable.masala)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 2:
              Glide.with(activity).load(R.drawable.biscuit_milk_glucose)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 3:
              Glide.with(activity).load(R.drawable.beverages)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 4:
              Glide.with(activity).load(R.drawable.chips)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 5:
              Glide.with(activity).load(R.drawable.beauty)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 6:
              Glide.with(activity).load(R.drawable.households)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 7:
              Glide.with(activity).load(R.drawable.food)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 8:
              Glide.with(activity).load(R.drawable.dairy)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;
          case 9:
              Glide.with(activity).load(R.drawable.babycare)
                      .thumbnail(0.5f)
                      // .crossFade()
                      .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                              .error(R.drawable.veg))
                      .into(holder.main_img);
              break;


      }

      /*  if ((position%2)!=0){
            Glide.with(activity).load(R.drawable.beet_root)
                    .thumbnail(0.5f)
                    // .crossFade()
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                            .error(R.drawable.veg))
                    .into(holder.main_img);
        }else{
            Glide.with(activity).load(R.drawable.carrot)
                    .thumbnail(0.5f)
                    // .crossFade()
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                            .error(R.drawable.veg))
                    .into(holder.main_img);
        }*/
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             //   System.out.println("mselected_items"+mSelectedItem);

                    selectedFragment = SelectCategoryFragment.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_home, selectedFragment);
                    transaction.addToBackStack("spicescateory");
                    transaction.commit();
                }

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

        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }










}
