package com.SevenNine.essentials.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.SevenNine.essentials.Bean.SelectLanguageBean;
import com.SevenNine.essentials.R;

import java.util.List;

public class QuantityPrice_Adapter3 extends RecyclerView.Adapter<QuantityPrice_Adapter3.MyViewHolder> {

    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static int quantityprice_id;



    public QuantityPrice_Adapter3(Activity activity, List<SelectLanguageBean> moviesList) {
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



        holder.variety_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          // CInventory_Adapter.MyViewHolder viewHolder1 =(CInventory_Adapter.MyViewHolder) CInventory_Fragment.recyclerView.findViewHolderForAdapterPosition(CInventory_Adapter.selected_position);
               // TextView textView=viewHolder1.text2;
              //  viewHolder1.text2.setText(products.getVendor());
                quantityprice_id=products.getLanguageid();
                System.out.println("unitofmeasurementidddddd"+products.getLanguageid());


             /*   NewAddressFragment.state.setText(holder.statename.getText().toString());
                NewAddressFragment.drawer.closeDrawers();*//**/
            }
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }










}
