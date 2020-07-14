package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.Add_New_Address_Bean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;

import java.util.List;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.MyViewHolder> {


    private List<Add_New_Address_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String add_id,fullname,address,pincode,full_address;
    SessionManager sessionManager;
    String status,message;
    public int mSelectedItem = -1;


    public ShippingAddressAdapter(Activity activity, List<Add_New_Address_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item,linearLayout;
        public RadioButton radio_bt;
        public TextView addr_vw,name,landmark_vw,city_vww,area,state_vw,remove,edit;


        public MyViewHolder(View view) {
            super(view);

           // item=view.findViewById(R.id.item);
          //  bankname=view.findViewById(R.id.bankname);
            name=view.findViewById(R.id.name_vw);
            addr_vw=view.findViewById(R.id.addr_vw);
            landmark_vw=view.findViewById(R.id.landmark_vw);
            city_vww=view.findViewById(R.id.city_vww);
            state_vw=view.findViewById(R.id.state_vw);
            radio_bt=view.findViewById(R.id.radio_bt);

            sessionManager = new SessionManager(activity);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.shipping_addr_lay, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Add_New_Address_Bean products = productList.get(position);
        holder.radio_bt.setChecked(position == mSelectedItem);


       // holder.bankname.setText(products.getBankname());
        holder.name.setText(products.getAdd_name());
        holder.addr_vw.setText(products.getAdd_street());
        holder.landmark_vw.setText(products.getAdd_landmark());
        holder.city_vww.setText(products.getAdd_city());
        holder.state_vw.setText(products.getAdd_state()+" "+products.getAdd_pincode());
      //  holder.phone_no.setText(products.getPhonenumber()+" | "+products.getIfsccode());
      //  holder.area.setText(products.getAdd_landmark()+" "+ products.getAdd_state()+","+products.getAdd_pincode());

holder.radio_bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mSelectedItem = holder.getAdapterPosition();
        notifyDataSetChanged();
        add_id = products.getAdd_id();
        address = products.getAdd_street();
        pincode = products.getAdd_pincode();
        fullname = products.getAdd_name();
        full_address=products.getAdd_street()+", "+products.getAdd_landmark() + ", "+products.getAdd_city()+ ", "+products.getAdd_hobli()+ ", "+products.getAdd_state()+ ", "+products.getAdd_pincode();
      //  full_address=+"\n"+products.getAdd_landmark()+"\n"+products.getAdd_city()+" "+products.getAdd_hobli()+"\n"+products.getAdd_state()+" "+products.getAdd_pincode();
        System.out.println("addressiddd"+full_address);

    }
});


    }





    @Override
    public int getItemCount() {
        return productList.size();
    }


}
