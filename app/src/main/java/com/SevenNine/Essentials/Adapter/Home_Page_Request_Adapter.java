package com.SevenNine.Essentials.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.SevenNine.Essentials.Bean.Request_Class_HomePage_Bean;
import com.SevenNine.Essentials.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Date;
import java.util.List;


public class Home_Page_Request_Adapter extends RecyclerView.Adapter<Home_Page_Request_Adapter.MyViewHolder>  {
    private List<Request_Class_HomePage_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String accept_trip;
    Date o_date;
    //    SessionManager session;
    public static CardView cardView;
    public Home_Page_Request_Adapter(Activity activity, List<Request_Class_HomePage_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView pay_img;
        public TextView name;
        // public TextView confirmbutton;
        //public TextView accept;
        //  LinearLayout location_layout;

        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.crop_loan);
            pay_img=view.findViewById(R.id.pay_img);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homepage_requestprice_adapter_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Request_Class_HomePage_Bean products = productList.get(position);

        if (position==0){
            holder.name.setText("Tractor");
           // holder.pay_img.setImageResource(R.drawable.tractor);
            image_loading(R.drawable.farm_banner2, holder.pay_img);
        }



//        holder.name.setText(products.getVeg_name());
      /*  Glide.with(activity).load("http://bookmymoment.azurewebsites.net"+products.getProductImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.prod_image);*/
       /* holder.name.setText(products.getName());
        holder.area.setText(products.getArea());*/

       /* holder.location_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment= TripDetailsFragment.newInstance();
                FragmentTransaction ft = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                //ft.addToBackStack("shoplist");
                ft.replace(R.id.frame_layout, selectedFragment);
                ft.commit();
            }
        });*/


    }

    private void image_loading(int request_image,ImageView imageView) {

        Glide.with(activity).load(request_image)
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(imageView);


    }

    @Override
    public int getItemCount() {
        return 12;
    }

}
