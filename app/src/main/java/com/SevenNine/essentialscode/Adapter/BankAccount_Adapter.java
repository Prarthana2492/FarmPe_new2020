package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.BankBean;
import com.SevenNine.essentialscode.Fragment.Add_NewBankDetails_Fragment;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BankAccount_Adapter extends RecyclerView.Adapter<BankAccount_Adapter.MyViewHolder> {

    private List<BankBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String bankid,bank_id_ed,edit_addr;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    JSONObject lngObject;
    String deleted,status,message;
    SessionManager sessionManager;
    LinearLayout linearLayout;
    public BankAccount_Adapter(Activity activity, List<BankBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public TextView bankname,name,phone_no,ifsc,area,delete,edit_bank;


        public MyViewHolder(View view) {
            super(view);

           // item=view.findViewById(R.id.item);
            bankname=view.findViewById(R.id.bankname);
            name=view.findViewById(R.id.name);
            phone_no=view.findViewById(R.id.ph_no);
            area=view.findViewById(R.id.area);
            delete=view.findViewById(R.id.delete);
            linearLayout=view.findViewById(R.id.bottom_sheet1);
            edit_bank=view.findViewById(R.id.edit_bank);

            sessionManager=new SessionManager(activity);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.bankaccount_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BankBean products = productList.get(position);



        holder.bankname.setText(products.getBankname());
        holder.name.setText(products.getName());
        holder.area.setText(products.getArea()+", "+products.getCity());
        if (products.getIfsccode().equals("0")){
            holder.phone_no.setText(products.getPhonenumber());
        }else{
            holder.phone_no.setText(products.getPhonenumber()+" | "+products.getIfsccode());
        }
        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            holder.edit_bank.setText(lngObject.getString("Edit").replace("\n",""));
            holder.delete.setText(lngObject.getString("Delete").replace("\n",""));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.edit_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_addr="edit";
                bank_id_ed =products.getId();
                //address_nav_stat="profile";
                Bundle bundle = new Bundle();
                bundle.putString("BankNmae",products.getBankname());
                bundle.putString("HolderName",products.getName());
                bundle.putString("AccountNumber",products.getPhonenumber());
                bundle.putString("IfscNumber",products.getIfsccode());
                bundle.putString("Branch",products.getArea());
                bundle.putString("City",products.getCity());
                bundle.putString("State",products.getState());
                bundle.putString("IFSCExist",products.isIFSCCodeExist());
                bundle.putString("StateId",products.getStateId());
                bundle.putString("DistrictId",products.getDistrictId());



                selectedFragment = Add_NewBankDetails_Fragment.newInstance();
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
                bankid =products.getId();

                mBottomSheetDialog = new BottomSheetDialog(activity);
                sheetView = activity.getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView confirm = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.GONE);
                titleText.setText("Delete Bank Details");
                descriptionText.setText("Are you sure you want to Delete the Bank Details?");
                confirm.setText("Confirm");
                TextView cancel = sheetView.findViewById(R.id.negetive_text);
                cancel.setText("Cancel");
                /*try {


                    lngObject = new JSONObject(sessionManager.getRegId("language"));
                    // popuptxt.setText(lngObject.getString("SelectanAddressType"));
                    confirm.setText(lngObject.getString("Confirm"));
                    cancel.setText(lngObject.getString("Cancel"));
                    deleted=lngObject.getString("Addressdeletedsuccessfully");
                    descriptionText.setText(lngObject.getString("Areyousureyouwanttoremovetheaddress"));
                    titleText.setText(lngObject.getString("Removeaddress"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                /*try {


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
*/

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bankid =products.getId();

                        try{
                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("BankDetailsId",bankid);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            System.out.println("bank_details_iddd"+jsonObject);

                            Crop_Post.crop_posting(activity, Urls.DeleteBankDetails, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");
                                        message = result.getString("Message");

                                        if(status.equals("1")){
                                            mBottomSheetDialog.dismiss();

                                           /* int duration = 1000;
                                            Snackbar snackbar = Snackbar
                                                    .make(linearLayout, "Bank", duration);
                                            View snackbarView2 = snackbar.getView();
                                            TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                                            tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                                            tv.setTextColor(Color.WHITE);

                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                            } else {
                                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                            }

                                            snackbar.show();*/

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
       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = Spices_Category_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("spicescateory");
                transaction.commit();
            }
        });
*/


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }










}
