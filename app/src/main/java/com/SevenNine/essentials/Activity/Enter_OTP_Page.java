package com.SevenNine.essentials.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.SevenNine.essentials.R;


public class Enter_OTP_Page extends AppCompatActivity {

    LinearLayout regiter_backgrd;
    TextView timer,otpsenttxt,otp_sent_to;
    ProgressBar otp_sent;
    ImageView otpsentimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_otp_page);

        regiter_backgrd = findViewById(R.id.regiter_backgrd);
        timer = findViewById(R.id.timer);
        otp_sent = findViewById(R.id.otp_sent);
        otpsentimg = findViewById(R.id.otpsent);
        otpsenttxt = findViewById(R.id.otpsenttxt);
        otp_sent_to = findViewById(R.id.otp_sent_to);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                otpsentimg.setVisibility(View.VISIBLE);
                otpsenttxt.setVisibility(View.VISIBLE);
                otp_sent.setVisibility(View.GONE);


            }
        }, 8000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                otpsentimg.setVisibility(View.GONE);
                otpsenttxt.setVisibility(View.GONE);
                otp_sent.setVisibility(View.GONE);


            }
        }, 9000);



        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText("00 :" + millisUntilFinished / 1000);
            }


            public void onFinish() {

                //   timer.setText("RESEND");




                timer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

                //timer.setText("done!");
            }

        }.start();


        regiter_backgrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Enter_OTP_Page.this, FirmShopDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
