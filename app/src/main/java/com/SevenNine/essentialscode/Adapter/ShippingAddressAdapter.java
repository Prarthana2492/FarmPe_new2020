package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.Add_New_Address_Bean;
import com.SevenNine.essentialscode.Fragment.AddNewAddressFragment;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import java.util.List;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.MyViewHolder> {


    private List<Add_New_Address_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String add_id,fullname,address,pincode,full_address;
    SessionManager sessionManager;
    String status,message;
    public int mSelectedItem = -1;
    public static String edit_addr,add_id_d,address_nav_stat;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    LinearLayout linearLayout;

    public ShippingAddressAdapter(Activity activity, List<Add_New_Address_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item,linearLayout;
        public RadioButton radio_bt;
        public TextView addr_vw,name,landmark_vw,city_vww,area,state_vw,delete,modify;


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
            modify=view.findViewById(R.id.modify);
            delete=view.findViewById(R.id.delete);
            linearLayout=view.findViewById(R.id.bottom_sheet1);

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
        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_addr="edit";
                add_id =products.getAdd_id();
                address_nav_stat="profile";
                Bundle bundle = new Bundle();
                bundle.putString("Addr_name",products.getAdd_name());
                bundle.putString("Addr_mobile",products.getAdd_mobile());
                bundle.putString("Addr_pincode",products.getAdd_pincode());
                bundle.putString("Addr_Street",products.getAdd_street());
                bundle.putString("Addr_landmark",products.getAdd_landmark());
                // bundle.putString("Addr_Houseno",products.getAdd_door_no());
                // bundle.putString("Addr_landmark",products.getAdd_landmark());
                //bundle.putString("Addr_city",products.getAdd_city());

                bundle.putString("Addr_state",products.getAdd_state());
                bundle.putString("Addr_district",products.getAdd_district());
                bundle.putString("Addr_taluk",products.getAdd_taluk());
                // bundle.putString("Addr_hobli",products.getAdd_hobli());
                bundle.putString("Addr_village",products.getAdd_village());
                bundle.putString("Addr_stateId",products.getStateId());
                bundle.putString("Addr_districtId",products.getDistrictId());
                bundle.putString("Addr_blockId",products.getBlockId());
                bundle.putString("Addr_villageId",products.getVillageId());
                bundle.putString("Addr_pickup_from",products.getAdd_pickup_frm());
                System.out.println("edittttttttttttttttttttttttttttttttttttttttttt"+bundle);
                //bundle.putString("navigation_from","your_add");
                bundle.putString("Shipping","shipping_addr");
                selectedFragment = AddNewAddressFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                selectedFragment.setArguments(bundle);
                // transaction.addToBackStack("your_add");
                transaction.commit();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_id_d =products.getAdd_id();

                mBottomSheetDialog = new BottomSheetDialog(activity);
                sheetView = activity.getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView confirm = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.GONE);
                titleText.setText("Remove Address");
                descriptionText.setText("Are you sure you want to remove the Address?");
                confirm.setText("Confirm");
                TextView cancel = sheetView.findViewById(R.id.negetive_text);
                cancel.setText("Cancel");


                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        add_id =products.getAdd_id();

                        try{
                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("UserAddressId",add_id_d);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            Crop_Post.crop_posting(activity, Urls.Delete_Address_Details, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");
                                        message = result.getString("Message");

                                        if(status.equals("1")){
                                            productList.remove(position);
                                            notifyDataSetChanged();
                                            int duration = 1000;
                                            Snackbar snackbar = Snackbar
                                                    .make(linearLayout, "Your address is deleted successfully", duration);
                                            View snackbarView2 = snackbar.getView();
                                            TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                                            tv.setBackgroundColor(ContextCompat.getColor(activity, R.color.orange));
                                            tv.setTextColor(Color.WHITE);

                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                            } else {
                                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                            }

                                            snackbar.show();

                                        }




                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        mBottomSheetDialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();

            }
        });


    }





    @Override
    public int getItemCount() {
        return productList.size();
    }


}
