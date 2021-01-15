package com.SevenNine.essentials.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentials.R;


public class Login_Page extends AppCompatActivity {

    TextView register_btn,login_btn;
    EditText mobile_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        register_btn = findViewById(R.id.register_btn);
        login_btn = findViewById(R.id.login_btn);
        mobile_no = findViewById(R.id.phonenumber);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(mobile_no.equals("")){

                    Toast toast = Toast.makeText(Login_Page.this,"Please Enter Phone Number To Proceed", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER,0,0);
                    toast.show();

//                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter Phone Number To Proceed", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();


                }else if(mobile_no.length()<10){

                    Toast toast = Toast.makeText(Login_Page.this,"Please Enter 10 Digit Mobile Number", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER,0,0);
                    toast.show();

//                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter 10 Digit Mobile Number", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    TextView toastMessage1=(TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage1.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();


                }else{

                    Intent intent=new Intent(Login_Page.this, Enter_OTP_Page.class);
                    startActivity(intent);

                }



            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mobile_no.equals("")){

                    Toast toast = Toast.makeText(Login_Page.this,"Please Enter Phone Number To Proceed", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER,0,0);
                    toast.show();

//                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter Phone Number To Proceed", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();


                }else if(mobile_no.length()<10){

                    Toast toast = Toast.makeText(Login_Page.this,"Please Enter 10 Digit Mobile Number", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER,0,0);
                    toast.show();

//                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter 10 Digit Mobile Number", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    TextView toastMessage1=(TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage1.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();


                }else {

                    Intent intent = new Intent(Login_Page.this, Enter_OTP_Page.class);
                    startActivity(intent);

                }
                }
        });

    }
}
