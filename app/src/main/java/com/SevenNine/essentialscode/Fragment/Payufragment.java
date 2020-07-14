package com.SevenNine.essentialscode.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.android.volley.toolbox.StringRequest;
import com.payumoney.core.PayUmoneySdkInitializer;

public class Payufragment extends Fragment {

    private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    StringRequest stringRequest;
    String paymenttransid,paymentmode,paymentstatus,UnMappedStatus,key,transactionid,transactionfee,
            amount,CardCategory,Discount,AddedOn,ProductInfo,FirstName,Email,Phone,PaymentVendor,CreatedBy,
            Currency,UserType,BankTxnId,PaymentDeskId,RESPMsg;
    SessionManager sessionManager;
    int amount_to_be_paid;
    EditText email,tramoount,phone;
    Button paybutton;
    Fragment selectedFragment;
    public static final String TAG = "MainActivity : ";
    LinearLayout back_feed;


    public static Payufragment newInstance() {
        Payufragment itemOnFragment = new Payufragment();
        return itemOnFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payu_money_main_activity, container, false);


       /*email = view.findViewById(R.id.email_et);
        tramoount = view.findViewById(R.id.amount_et);
        phone = view.findViewById(R.id.mobile_et);*/
        paybutton = view.findViewById(R.id.payButton);
        back_feed=view.findViewById(R.id.back_feed);


        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Payfragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            //    transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("pay");
                transaction.commit();
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("payu", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("payu", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }

                return false;
            }
        });
        return view;
    }
}