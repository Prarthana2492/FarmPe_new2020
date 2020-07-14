package com.SevenNine.essentialscode.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.SevenNine.essentialscode.Activity.MyApplication;
import com.SevenNine.essentialscode.Adapter.CartDetailsAdapter;
import com.SevenNine.essentialscode.AppEnvironment;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
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
import static android.content.ContentValues.TAG;


public class PayUMoneyMainActivity extends Fragment {

    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
    private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    PayUmoneySdkInitializer.PaymentParam paymentParam = null;
    StringRequest stringRequest;
    RadioButton cod,paytm,payu,wallet;
    RadioGroup transaction_selection,amount_transfer;
    double amount = Double.parseDouble("1.00");
    String paymenttransid,paymentmode,paymentstatus,UnMappedStatus,key,transactionid,transactionfee,
           CardCategory,Discount,AddedOn,ProductInfo,FirstName,Email,Phone,PaymentVendor,CreatedBy,
            Currency,UserType,BankTxnId,PaymentDeskId,RESPMsg;
    AppEnvironment appEnvironment;
    SessionManager sessionManager;
    Double strtext;
    Fragment selectedFragment;
    //  public static final String TAG = "MainActivity : ";
    public static PayUMoneyMainActivity newInstance() {
        PayUMoneyMainActivity itemOnFragment = new PayUMoneyMainActivity();
        return itemOnFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payu_money_main_activity, container, false);
        sessionManager = new SessionManager(getActivity());
        appEnvironment = ((MyApplication) getActivity().getApplication()).getAppEnvironment();
        AppEnvironment appEnvironment = ((MyApplication) getActivity().getApplication()).getAppEnvironment();
       // strtext = getArguments().getDouble("pricestrtext2");
        strtext = Double.parseDouble("1");
                // Payment amount0
        PayUmoneySdkInitializer.PaymentParam.Builder builder = new
                PayUmoneySdkInitializer.PaymentParam.Builder();
       //builder.setAmount(Double.parseDouble("1.00"))
       /* if (SelectShippingAddress.shipping_addr!=null){
            builder.setAmount(String.valueOf(strtext))
                    .setTxnId(System.currentTimeMillis() + "")                                             // Transaction ID
                    .setPhone(sessionManager.getRegId("phone"))                                           // User Phone number
                    .setProductName(ShippingAddressAdapter.full_address)                   // Product Name or description
                    .setFirstName(ShippingAddressAdapter.fullname)                              // User First name
                    .setEmail("farmpe.renewin@gmail.com")                                            // User Email ID
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
        }else{*/
            builder.setAmount(String.valueOf(strtext))
                    .setTxnId(System.currentTimeMillis() + "")                                             // Transaction ID
                    .setPhone(sessionManager.getRegId("phone"))                                           // User Phone number
                    .setProductName(CurrentLocation.latitude+","+CurrentLocation.longitude)                   // Product Name or description
                    .setFirstName("Priya")                              // User First name
                    .setEmail("farmpe.renewin@gmail.com")                                            // User Email ID
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
       /* }*/

        System.out.println("purushottam"+builder);
        try {
            mPaymentParams = builder.build();
            mPaymentParams = calculateServerSideHashAndInitiatePayment1(mPaymentParams);
        }catch (Exception e) {
            System.out.println("ddddddddddddddd"+e.toString());
        }
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

     AppEnvironment appEnvironment = ((MyApplication) getActivity().getApplication()).getAppEnvironment();
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
                    System.out.println("pyajopayment"+transactionResponse.payuResponse);
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
                        amount= Double.parseDouble(jsonObject1.getString("amount"));
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
                    AddMoney();
                    //  placeOrder();
                    //Success Transaction
                } else{
                    //Failure Transaction
                }
            }  else if (resultModel != null && resultModel.getError() != null) {
                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
                selectedFragment = SelectPaymentMethod.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("cart_detail");
                transaction.commit();
            } else {
                Log.d(TAG, "Both objects are null!");
                selectedFragment = SelectPaymentMethod.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("cart_detail");
                transaction.commit();
            }
        }else if (requestCode==10000&&resultCode==0){
            selectedFragment = SelectPaymentMethod.newInstance();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout1, selectedFragment);
            transaction.addToBackStack("cart_detail");
            transaction.commit();
        }
    }

    public void  AddMoney(){
        JSONObject params = new JSONObject();
        try {
            params.put("UserId",sessionManager.getRegId("userId"));
            params.put("Amount",strtext);  // amount
            params.put("ProductInfo",ProductInfo);  // amount
            params.put("TransactionFees",transactionfee);  //transaction fees
            params.put("DiscountAmount",Discount);
            params.put("mode","PayU");
            params.put("PaymentMethod",2);
            params.put("UserType","C");
            params.put("Status","0"); //using status
            params.put("UnMappedStatus",UnMappedStatus);
            params.put("key",key);
            params.put("TxnId",transactionid);  //tarnsaction id
            params.put("CardCategory",CardCategory);
            params.put("FirstName",FirstName);
            params.put("Email","farmpe.renewin@gmail.com");
            params.put("Phone",Phone);
            params.put("PaymentVendor",PaymentVendor);
            params.put("Currency","1");
            params.put("BankTxnId",BankTxnId);
            params.put("PaymentDeskId",PaymentDeskId);
            params.put("CreatedBy",sessionManager.getRegId("userId"));
            params.put("IsActive",1);
            params.put("RESPMsg",RESPMsg);
            params.put("CustLongitude",CurrentLocation.longitude);
            params.put("CustLatitude",CurrentLocation.latitude);
            params.put("CustAddress",CurrentLocation.current_addr);
            params.put("CartProductListIds", CartDetailsAdapter.strlist);
            System.out.println("RESPMsgdsfadf"+params);
            Login_post.login_posting(getActivity(), Urls.PayuMoneyAdd, params, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("llllllllllllllllllllllllllll"+result);
                    try {
                        System.out.println("nnnnnmnm" + result.toString());
                        String status=result.getString("Status");
                      //  if(status.equals("Success")){
                        if(status.equals("Success")){
                            Toast toast = Toast.makeText(getActivity(),"Transaction Successfully Completed", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();
                         //   Toast.makeText(getActivity(),"Transaction Successfully Completed",Toast.LENGTH_LONG).show();
                            selectedFragment = HomeFragment.newInstance();
                            FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout1, selectedFragment);
                            transaction.commit();
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity(),"Transaction Incomplete", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();
                          //  Toast.makeText(getActivity(),"Transaction Incomplete",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

