package com.SevenNine.essentials.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SevenNine.essentials.volleypost.VolleySingletonQuee;
import com.SevenNine.essentials.Activity.MyApplication;
import com.SevenNine.essentials.AppEnvironment;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.payumoney.core.PayUmoneyConstants;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class Payfragment extends Fragment {

    private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    StringRequest stringRequest;
    String paymenttransid,paymentmode,paymentstatus,UnMappedStatus,key,transactionid,transactionfee,
            amount,CardCategory,Discount,AddedOn,ProductInfo,FirstName,Email,Phone,PaymentVendor,CreatedBy,
            Currency,UserType,BankTxnId,PaymentDeskId,RESPMsg;
    SessionManager sessionManager;
    int amount_to_be_paid;
    Fragment selectedFragment;
    public static final String TAG = "MainActivity : ";
    public static Payfragment newInstance() {
        Payfragment itemOnFragment = new Payfragment();
        return itemOnFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_payload, container, false);

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        AppEnvironment appEnvironment = ((MyApplication) getActivity().getApplication()).getAppEnvironment();
        PayUmoneySdkInitializer.PaymentParam.Builder builder = new
                PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount("1.04")
                // Payment amount0
                .setTxnId(System.currentTimeMillis() + "")                                             // Transaction ID
                .setPhone("9492737031")                                           // User Phone number
                .setProductName("Sellerhub")                   // Product Name or description
                .setFirstName(sessionManager.getRegId("name"))                              // User First name
                .setEmail("himabindhu.narra@gmail.com")                                            // User Email ID
                .setsUrl(appEnvironment.surl())                    // Success URL (surl)"https://www.payumoney.com/mobileapp/payumoney/success.php"
                .setfUrl(appEnvironment.furl())                     //Failure URL (furl)https://www.payumoney.com/mobileapp/payumoney/failure.php
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(false)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(appEnvironment.merchant_Key())                        // Merchant key
                .setMerchantId(appEnvironment.merchant_ID());
        System.out.println("ghh"+builder);
        System.out.println("merchantid"+appEnvironment.merchant_ID()+appEnvironment.merchant_Key());
        try {
            //declare paymentParam object
            mPaymentParams = builder.build();
            //System.out.println("abcdddddddddd"+builder.build());
            mPaymentParams = calculateServerSideHashAndInitiatePayment1(mPaymentParams);
        }catch(Exception e){
            //some exception occurred
            System.out.println("ddddddddddddddd"+e.toString());
        }
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   /* selectedFragment = Home_Menu_Fragment.newInstance();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout, selectedFragment);
                    ft.commit();*/
                    return true;
                }
                return false;
            }
        });
        return view;
    }
    public static String hashCal(String str) {
        byte[] hashseq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString.toString();
    }


    private PayUmoneySdkInitializer.PaymentParam calculateServerSideHashAndInitiatePayment1(final PayUmoneySdkInitializer.PaymentParam paymentParam) {

        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> params = paymentParam.getParams();
        stringBuilder.append(params.get(PayUmoneyConstants.KEY) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.TXNID) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF1) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF2) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF3) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF4) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF5) + "||||||");

        AppEnvironment appEnvironment = ((MyApplication)getActivity(). getApplication()).getAppEnvironment();
        stringBuilder.append(appEnvironment.salt());

        String hash = hashCal(stringBuilder.toString());
        paymentParam.setMerchantHash(hash);
// Invoke the following function to open the checkout page.
        PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam,
                getActivity(), R.style.AppTheme_default,true);
        return paymentParam;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result Code is -1 send from Payumoney activity
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
        System.out.println("pyajopaymenthhhhhhhhhhhhhhhhhhhhh");
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra( PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE );
            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if(transactionResponse.getTransactionStatus().equals( TransactionResponse.TransactionStatus.SUCCESSFUL )){
                    System.out.println("payment"+transactionResponse.payuResponse);
                    String payuResponse = transactionResponse.getPayuResponse();
                    String merchantResponse = transactionResponse.getTransactionDetails();
                    System.out.println("gfgfdgfdgafd"+payuResponse);

                    try {
                        JSONObject jsonObject = new JSONObject(payuResponse);
                        JSONObject jsonObject1 =new JSONObject(jsonObject.getString("result"));
                        paymenttransid=jsonObject1.getString("paymentId");
                        paymentmode=jsonObject1.getString("mode");
                        paymentstatus=jsonObject1.getString("status");
                        UnMappedStatus=jsonObject1.getString("unmappedstatus");
                        key=jsonObject1.getString("key");
                        transactionid=jsonObject1.getString("txnid");
                        transactionfee=jsonObject1.getString("additionalCharges");
                        amount=jsonObject1.getString("amount");
                        CardCategory="";
                        Discount=jsonObject1.getString("discount");
                        AddedOn=jsonObject1.getString("addedon");
                        ProductInfo=jsonObject1.getString("productinfo");
                        FirstName=jsonObject1.getString("firstname");
                        Email=jsonObject1.getString("email");
                        Phone=jsonObject1.getString("phone");
                        PaymentVendor=jsonObject1.getString("bankcode");
                        Currency="INR";
                        UserType="U";
                        BankTxnId=jsonObject1.getString("bank_ref_num");
                        PaymentDeskId=jsonObject1.getString("payuMoneyId");
                        RESPMsg=jsonObject1.getString("field9");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SaveDateTOServer();
                    //  placeOrder();
                    //Success Transaction
                    /*selectedFragment = OrdersFragment.newInstance();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout, selectedFragment);
                    ft.commit();*/

                } else{
                  /*  selectedFragment = NoOffersFragment.newInstance();
                    FragmentTransaction ft = ((FragmentActivity)getActivity().getApplicationContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout, selectedFragment);
                    ft.commit();*/
                    //Failure Transaction
                }
            }  else if (resultModel != null && resultModel.getError() != null) {
                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }
        }else if (requestCode==10000&&resultCode==0){

           /* selectedFragment = Inventory_Fragment.newInstance();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, selectedFragment);
            ft.commit();*/

        }
    }
    private void SaveDateTOServer() {
        final String totalAmount = String.valueOf(amount_to_be_paid);
        JSONObject  params = new JSONObject();


        try {
            params.put("Amount",totalAmount);  // amount
            params.put("ProductInfo","Bank");  // amount


            params.put("TransactionFees","100");  //transaction fees

            params.put("DiscountAmount",Discount);

            params.put("mode","4");

            params.put("CreatedBy",CreatedBy);
            params.put("UserType","C");
            params.put("id",paymenttransid);
            params.put("status",paymentstatus); //using status
            params.put("UnMappedStatus",UnMappedStatus);
            params.put("key",key);
            params.put("TxnId",transactionid);  //tarnsaction id
            params.put("CardCategory",CardCategory);
            params.put("PaymentDate",AddedOn);  //added on
            params.put("FirstName",FirstName);
            params.put("Email",Email);
            params.put("Phone",Phone);
            params.put("PaymentVendor","payu");
            params.put("Currency",Currency);
            params.put("BankTxnId",BankTxnId);
            params.put("PaymentDeskId",PaymentDeskId);
            params.put("RESPMsg",RESPMsg);
            System.out.println("fffffffffffffffffffffffffffffff"+params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest saveData = new JsonObjectRequest(Request.Method.POST, Urls.PayuMoneyAdd, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String status=jsonObject.getString("Status");

                            if (status.equals("Success")){
                                // placeOrder();
                                /*selectedFragment = Home_Menu_Fragment.newInstance();
                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.frame_layout, selectedFragment);
                                ft.commit();
                                Toast.makeText(getActivity(), "Successfully completed your transaction", Toast.LENGTH_SHORT).show();*/
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("fffffffffffffffffffffffffffffff"+jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("fffffffffffffffffffffffffffffffvolleyError");
                    }
                });
        VolleySingletonQuee.getInstance(getActivity().getApplicationContext()).addToRequestQueue(saveData);
    }
}
// URL=/payment/makePayment/4654D357C468BE5708050068B7E33550?bankCode=PHONEPE&revisedCashbackReceivedStatus=0&guestCheckout=false&PG=CASHCARD&sourceAmountMap={"PAYU":1.04}&isMobile=1&platform=Android_PayUmoneyAppTime=529