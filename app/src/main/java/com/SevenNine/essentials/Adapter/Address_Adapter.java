package com.SevenNine.essentials.Adapter;

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
import android.widget.TextView;


import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Bean.Add_New_Address_Bean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.MyViewHolder> {

    private List<Add_New_Address_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String edit_addr,add_id,add_id_d;
    public static String address_nav_stat;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    JSONObject lngObject;
    String deleted,status,message;
    SessionManager sessionManager;
    LinearLayout linearLayout;


    public Address_Adapter(Activity activity, List<Add_New_Address_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public TextView bankname,name,phone_no,ifsc,area,city,modify,delete;


        public MyViewHolder(View view) {
            super(view);

            // item=view.findViewById(R.id.item);
            bankname=view.findViewById(R.id.bankname);
            name=view.findViewById(R.id.name);
            modify=view.findViewById(R.id.modify);
            delete=view.findViewById(R.id.delete);
            linearLayout=view.findViewById(R.id.bottom_sheet1);
            //  phone_no=view.findViewById(R.id.ph_no);
            //  area=view.findViewById(R.id.area);
            sessionManager = new SessionManager(activity);

////////////hhhhhhhh
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.address_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Add_New_Address_Bean products = productList.get(position);

        System.out.println("nckxzlkdsaldc");

        if (products.getAdd_pickup_frm().equals("1")){
            holder.bankname.setText("Home");
        }else{
            holder.bankname.setText("Work");

        }
       /* try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            holder.modify.setText(lngObject.getString("Modify").replace("\n",""));
            holder.delete.setText(lngObject.getString("Remove").replace("\n",""));


        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        // holder.bankname.setText(products.getBankname());

        System.out.println("ajdjsdoisaj"+products.getAdd_name()+" "+products.getAdd_landmark());
        holder.name.setText(products.getAdd_name()+ ", "+ products.getAdd_street()+", "+products.getAdd_landmark()+", "+products.getAdd_city()+", "+ products.getAdd_state()+" - "+products.getAdd_pincode());
        //  holder.phone_no.setText(products.getPhonenumber()+" | "+products.getIfsccode());
        // holder.area.setText(products.getAdd_city()+", "+ products.getAdd_state()+","+products.getAdd_pincode());

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

//                selectedFragment = AddNewAddressFragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                selectedFragment.setArguments(bundle);
//                // transaction.addToBackStack("your_add");
//                transaction.commit();
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

                try {


                    lngObject = new JSONObject(sessionManager.getRegId("language"));
                    // popuptxt.setText(lngObject.getString("SelectanAddressType"));
                    confirm.setText(lngObject.getString("Confirm"));
                    cancel.setText(lngObject.getString("Cancel"));
                    deleted=lngObject.getString("Addressdeletedsuccessfully");
                    descriptionText.setText(lngObject.getString("Areyousureyouwanttoremovetheaddress"));
                    titleText.setText(lngObject.getString("Removeaddress"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


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

                                            int duration = 1000;
                                            Snackbar snackbar = Snackbar
                                                    .make(linearLayout, deleted, duration);
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


                                        productList.remove(position);
                                        notifyDataSetChanged();

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
