package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.FarmersNearMeCropBean;
import com.SevenNine.essentialscode.Fragment.Payufragment;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Inventory_Adapter extends RecyclerView.Adapter<Inventory_Adapter.MyViewHolder>  {
    private List<FarmersNearMeCropBean> productList1;
    Activity activity;
    Fragment selectedFragment;

    public static String selected_sub_category_name,selected_sub_category_img;
    Date o_date;
    public static TextView name,variety,grade,quantity,uom,price,edit,remove;
    public static String variety_string,loc_string,grade_string,quantity_string,uom_string,price_string,total_price_string;
    public static String id1;
    public static String addressId;

    public static CardView cardView;
    DecimalFormat formatter;
    SessionManager sessionManager;

    public Inventory_Adapter(Activity activity, List<FarmersNearMeCropBean> moviesList) {
        this.productList1 = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView cart_img;
        public LinearLayout edit_img;
        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.crop_name_1);
            cart_img=view.findViewById(R.id.cart_img);
         //   quantity=view.findViewById(R.id.quantity);
            price=view.findViewById(R.id.price_1);
            edit=view.findViewById(R.id.edit_1);
            sessionManager = new SessionManager(activity);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventory_crops_list_layout, parent, false);
       formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);;
        formatter .applyPattern("##,##,##,###");
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position1) {
        final FarmersNearMeCropBean products = productList1.get(position1);
        try {
            Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));

            Log.e("vhgvhgvh",format.format(new BigDecimal(products.getPrice())));

            double rate_double= (Integer.parseInt(products.getPrice())*Integer.parseInt(products.getQuantity()));
              double rate_double1= (Integer.parseInt(products.getPrice()));
             formatter.format(rate_double);
           price.setText(format.format(new BigDecimal(rate_double)));
           name.setText(products.getVeg_name()+" - "  +products.getVeriety() +"("+products.getCat_name()+")"+" , " + products.getQuantity() + " " + products.getUom()+ " , " + " Unit Cost - "+format.format(new BigDecimal(products.getPrice())));
              name.setText(products.getVeg_name()+" - " + products.getVeriety() +" , " + products.getQuantity() + " " + products.getUom()+ " , " + " Rs. "+formatter.format(rate_double1)+ "/" +products.getUom() );
            total_price_string =  formatter.format(rate_double);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("lllllllllllllllll"+products.getVeg_name());
         System.out.println("vvvvvvvvvariety"+products.getVeriety());
        System.out.println("locationnn"+loc_string);

        Glide.with(activity).load(products.getCat_icon())
                .thumbnail(0.5f)
                .into(holder.cart_img);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Payufragment.newInstance();
                FragmentTransaction transaction1 =((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
             //   transaction1.replace(R.id.frame_layout, selectedFragment);
                transaction1.addToBackStack("payu");
                transaction1.commit();
            }
            });

    }

    @Override
    public int getItemCount() {
        System.out.println("sssssss" + productList1.size());
        return productList1.size();

    }


}