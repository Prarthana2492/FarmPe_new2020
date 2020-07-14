package com.SevenNine.essentialscode.Adapter;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.StoreListBean;
import com.SevenNine.essentialscode.Fragment.StoreViewDetails;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder>  {
    private List<StoreListBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
   String sssss;
    LinearLayout linearLayout;
    public static String first,tractor_id,model_id,looking_for_id;

    public static CardView cardView;
    ImageView fav_request;

    String brochure_pdf,toast_message;
    Boolean shortlisted  = false;


    public StoreAdapter(Activity activity, List<StoreListBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;

        sessionManager = new SessionManager(activity);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public TextView area,select,gst_no,city,view_details;



        public MyViewHolder(View view) {
            super(view);

            area=view.findViewById(R.id.area);
            gst_no=view.findViewById(R.id.gst_no);
           // hp_power=view.findViewById(R.id.hp_power);
            image=view.findViewById(R.id.imageff);
           // select=view.findViewById(R.id.selectt);
            view_details=view.findViewById(R.id.brochure);
            city=view.findViewById(R.id.city);
         //   fav_request=view.findViewById(R.id.fav_request);
            linearLayout=view.findViewById(R.id.layout);
          //  default_img=view.findViewById(R.id.default_img);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);
        return new MyViewHolder(itemView);

    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final StoreListBean products = productList.get(position);

          holder.area.setText(products.getArea());
          holder.gst_no.setText(products.getGst_no());
          holder.city.setText(products.getCity()+" -"+products.getPincode());

        Glide.with(activity).load(R.drawable.store_name)
                .thumbnail(0.5f)
                //   .crossFade()
                .error(R.drawable.ic_gallery__default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
holder.view_details.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        selectedFragment = StoreViewDetails.newInstance();
        FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout1, selectedFragment);
        transaction.addToBackStack("shop_locatn");
        transaction.commit();

    }
});


    }




    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}