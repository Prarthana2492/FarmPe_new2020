package com.SevenNine.essentialscode.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class NewEnterOTP extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    LinearLayout sele_loc,selfie_img,back_feed,main_layout;
    EditText otp1,otp2,otp3,otp4;
    TextView sent_text,mob_no,register_btn,timer,didnt,otp_send_to,enter_otp_here;
    ImageView sent_img;
    SessionManager sessionManager;
    String otp_string;
    String otp_generated;
    CountDownTimer cTimer = null;
    String otp_get;
    BroadcastReceiver receiver;
    String toast_internet,toast_nointernet;
    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;
    ProgressBar otp_sent;
    String mob;
    String IsUserUploaded;
    public static   JSONObject lngObject;

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }


    private void showSnack(boolean isConnected) {
        String message = null;
        int color=0;
        if (isConnected) {
            if(connectivity_check) {

                message = "Good! Connected to Internet";
                color = Color.WHITE;
                int duration=1000;
                Snackbar snackbar = Snackbar.make(main_layout,message, duration);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setBackgroundColor(ContextCompat.getColor(NewEnterOTP.this, R.color.orange));
                textView.setTextColor(Color.WHITE);
                snackbar.show();


                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;

            Snackbar.make(findViewById(android.R.id.content),message, duration).show();
        }
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("otpnumber"));
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);

        MyApplication.getInstance().setConnectivityListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_otp);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp_sent = findViewById(R.id.otp_sent);
        sent_img = findViewById(R.id.otpsent);
        //  progressBar = findViewById(R.id.progressBar_sent);
        sent_text = findViewById(R.id.otpsenttxt);
        mob_no = findViewById(R.id.ph_num);
        register_btn = findViewById(R.id.submit);
        timer = findViewById(R.id.time);
        back_feed = findViewById(R.id.back_feed);
        main_layout = findViewById(R.id.main_layout);
        didnt = findViewById(R.id.did_receive);
        otp_send_to = findViewById(R.id.otp_sento);
        enter_otp_here = findViewById(R.id.etr_otp_here);
        // submit=findViewById(R.id.submit);
        // VerifyAadhar.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sessionManager=new SessionManager(this);

        otp_get= getIntent().getStringExtra("otpnumber");
        // otp1.setText("1");
        mob = NewSignUpActivity.contact;

        main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = NewEnterOTP.this.getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager inputManager = (InputMethodManager) NewEnterOTP.this.getSystemService(NewEnterOTP.this.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view1.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            otp_send_to.setText(lngObject.getString("OTPSentto"));
            enter_otp_here.setText(lngObject.getString("EnterOTPhere"));
            didnt.setText(lngObject.getString("DidntreceiveOTP"));
            sent_text.setText(lngObject.getString("OTPSent"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewEnterOTP.this, NewSignUpActivity.class);
                startActivity(intent);
            }
        });
        if (getIntent().getStringExtra("register_status").equals("register_btn")){
            System.out.println("llllllogiiinn");
             register_btn.setText("REGISTER");
           /* try {

                lngObject = new JSONObject(sessionManager.getRegId("language"));

                register_btn.setText(lngObject.getString("LOGIN"));

            } catch (JSONException e) {
                e.printStackTrace();
            }*/

        }else{
             register_btn.setText("LOGIN");
           /* try {

                lngObject = new JSONObject(sessionManager.getRegId("language"));

                register_btn.setText(lngObject.getString("REGISTER"));

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
        mob_no.setText(sessionManager.getRegId("phone"));
        System.out.println("uuuuuuuuuu"+sessionManager.getRegId("phone"));
        /*sent_img.setVisibility(View.VISIBLE);
        sent_text.setVisibility(View.VISIBLE);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sent_img.setVisibility(View.VISIBLE);
                sent_text.setVisibility(View.GONE);
                otp_sent.setVisibility(View.GONE);
                if (otp_sent.getVisibility()==View.GONE){
                    sent_img.setVisibility(View.VISIBLE);
                    sent_text.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sent_text.setVisibility(View.GONE);
                            sent_img.setVisibility(View.GONE);
                        }
                    }, 1000);
                }
            }
        }, 3000);


        cTimer= new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("00:"+millisUntilFinished / 1000);
                String timer_limit=""+millisUntilFinished/1000;
                if (timer_limit.length()==1){
                    timer.setText("00:"+"0"+millisUntilFinished / 1000);

                }
                if (timer.getText().toString().equals("00:00")){
                    otp1.setFocusable(false);
                    otp2.setFocusable(false);
                    otp3.setFocusable(false);
                    otp4.setFocusable(false);
                }
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                if (otp1.getText().toString().equals("")||otp2.getText().toString().equals("")||otp3.getText().toString().equals("")||otp4.getText().toString().equals("")){
                    timer.setText("RESEND");
                    timer.setBackgroundResource(R.drawable.border_filled_red_time);
                    if (timer.getText().toString().equals("RESEND")){
                       /* otp_sent.setVisibility(View.INVISIBLE);
                        sent_img.setVisibility(View.INVISIBLE);
                        sent_text.setVisibility(View.INVISIBLE);*/
                    }
                }else{

                }

            }

        }.start();

otp1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        otp1.setBackgroundResource(R.drawable.border_green_empty);
        otp2.setBackgroundResource(R.drawable.border_grey_filled);
        otp3.setBackgroundResource(R.drawable.border_grey_filled);
        otp4.setBackgroundResource(R.drawable.border_grey_filled);

    }
});
        otp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp2.setBackgroundResource(R.drawable.border_green_empty);
                otp1.setBackgroundResource(R.drawable.border_grey_filled);
                otp3.setBackgroundResource(R.drawable.border_grey_filled);
                otp4.setBackgroundResource(R.drawable.border_grey_filled);

            }
        });

        otp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp3.setBackgroundResource(R.drawable.border_green_empty);
                otp1.setBackgroundResource(R.drawable.border_grey_filled);
                otp2.setBackgroundResource(R.drawable.border_grey_filled);
                otp4.setBackgroundResource(R.drawable.border_grey_filled);

            }
        });

        otp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp4.setBackgroundResource(R.drawable.border_green_empty);
                otp1.setBackgroundResource(R.drawable.border_grey_filled);
                otp2.setBackgroundResource(R.drawable.border_grey_filled);
                otp3.setBackgroundResource(R.drawable.border_grey_filled);

            }
        });


        otp1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                otp1.setBackgroundResource(R.drawable.border_green_empty);
                if (s.length() ==1) {
                    otp1.clearFocus();
                    otp1.setBackgroundResource(R.drawable.border_grey_filled);
                    otp3.setBackgroundResource(R.drawable.border_grey_filled);
                    otp4.setBackgroundResource(R.drawable.border_grey_filled);
                    otp2.setBackgroundResource(R.drawable.border_green_empty);

                    otp2 .requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp2.clearFocus();
                    otp2.setBackgroundResource(R.drawable.border_grey_filled);
                    otp1.setBackgroundResource(R.drawable.border_grey_filled);
                    otp4.setBackgroundResource(R.drawable.border_grey_filled);
                    otp3.setBackgroundResource(R.drawable.border_green_empty);

                    otp3.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp3.clearFocus();
                    otp3.setBackgroundResource(R.drawable.border_grey_filled);
                    otp2.setBackgroundResource(R.drawable.border_grey_filled);
                    otp1.setBackgroundResource(R.drawable.border_grey_filled);
                    otp4.setBackgroundResource(R.drawable.border_green_empty);

                    otp4.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp4.clearFocus();
                    otp1.setBackgroundResource(R.drawable.border_grey_filled);
                    otp2.setBackgroundResource(R.drawable.border_grey_filled);
                    otp3.setBackgroundResource(R.drawable.border_grey_filled);
                    otp4.setBackgroundResource(R.drawable.border_grey_filled);

                    otp1.clearFocus();
                    // otp1.setFocusable(false);
                    otp1.setCursorVisible(false);
                    // sent_img.setVisibility(View.INVISIBLE);
                    // sent_text.setVisibility(View.INVISIBLE);
                    otp_string=otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
                    register_btn.setBackgroundResource(R.drawable.border_filled_red);

                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });


        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sent_img.setVisibility(View.VISIBLE);
                        sent_text.setVisibility(View.GONE);
                        otp_sent.setVisibility(View.GONE);
                        if (otp_sent.getVisibility()==View.GONE){
                            sent_img.setVisibility(View.VISIBLE);
                            sent_text.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    sent_text.setVisibility(View.GONE);
                                    sent_img.setVisibility(View.GONE);
                                }
                            }, 1000);
                        }
                    }
                }, 3000);

                if (timer.getText().toString().equals("RESEND")) {
                    sent_img.setVisibility(View.VISIBLE);
                    sent_text.setVisibility(View.VISIBLE);
                    otp_sent.setVisibility(View.GONE);
                    otp1.setFocusableInTouchMode(true);
                    otp1.setFocusable(true);
                    otp1.setEnabled(true);
                    otp2.setFocusableInTouchMode(true);
                    otp2.setFocusable(true);
                    otp2.setEnabled(true);
                    otp3.setFocusableInTouchMode(true);
                    otp3.setFocusable(true);
                    otp3.setEnabled(true);
                    otp4.setFocusableInTouchMode(true);
                    otp4.setFocusable(true);
                    otp4.setEnabled(true);
                   /* otp2.setEnabled(true);
                    otp3.setEnabled(true);
                    otp4.setEnabled(true);*/
                    cTimer= new CountDownTimer(30000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setBackgroundResource(R.drawable.border_filled_red_time);

                            timer.setText("00:"+millisUntilFinished / 1000);
                            String timer_limit=""+millisUntilFinished/1000;
                            if (timer_limit.length()==1){

                                timer.setText("00:"+"0"+millisUntilFinished / 1000);

                            }
                            if (timer.getText().toString().equals("00:00")){
                                otp1.setFocusable(false);
                                otp2.setFocusable(false);
                                otp3.setFocusable(false);
                                otp4.setFocusable(false);
                            }
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            if (otp1.getText().toString().equals("")||otp2.getText().toString().equals("")||otp3.getText().toString().equals("")||otp4.getText().toString().equals("")){
                                timer.setText("RESEND");
                                timer.setBackgroundResource(R.drawable.border_filled_red_time);
                                if (timer.getText().toString().equals("RESEND")){
                                   /* otp_sent.setVisibility(View.INVISIBLE);
                                    sent_img.setVisibility(View.INVISIBLE);
                                    sent_text.setVisibility(View.INVISIBLE);*/
                                }
                            }else{

                            }

                        }

                    }.start();

                    try {
                        JSONObject postjsonObject = new JSONObject();
                        postjsonObject.put("PhoneNo", mob_no.getText().toString());
                        System.out.println("rrrrrrrrrrrrrrrrrrrr" + postjsonObject);

                        Login_post.login_posting(NewEnterOTP.this, Urls.ResendOTP, postjsonObject, new VoleyJsonObjectCallback() {
                            @Override
                            public void onSuccessResponse(JSONObject result) {

                                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk" + result.toString());

                                try {

                                    String Otp = result.getString("OTP");
                                    otp_get = Otp;
                                    String Message = result.getString("Message");
                                    int status = result.getInt("Status");

                                    if (status == 2) {
                                        Snackbar snackbar = Snackbar
                                                .make(main_layout, Message, Snackbar.LENGTH_LONG);
                                        //snackbar.setActionTextColor(R.color.colorAccent);
                                        View snackbarView = snackbar.getView();
                                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        tv.setBackgroundColor(ContextCompat.getColor(NewEnterOTP.this, R.color.orange));
                                        tv.setTextColor(Color.WHITE);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        } else {
                                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                        }
                                        snackbar.show();
                                    } else {
                                        // Toast.makeText(VerifyOTP.this, Message, Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

       /* ReadSms.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                System.out.println("llllllllllllllllllllllllllllllllllllllllllllotp"+messageText);
               // didnt.setText(messageText);
                otp_string=messageText;
                System.out.println("auto_otppp"+otp_string);
                System.out.println("otp1text"+otp_string.charAt(0));

               *//* otp1.setFocusable(false);
                otp2.setFocusable(false);
                otp3.setFocusable(false);
                otp4.setFocusable(false);*//*

                char ch1 =otp_string.charAt(0);
                char ch2 =otp_string.charAt(1);
                char ch3 =otp_string.charAt(2);
                char ch4 =otp_string.charAt(3);
                otp1.setText(String.valueOf(ch1));
                otp2.setText(String.valueOf(ch2));
                otp3.setText(String.valueOf(ch3));
                otp4.setText(String.valueOf(ch4));

                if (!(otp4.getText().toString().equals(""))) {
                    Intent intent=new Intent(VerifyOTP.this,VerifyAadhar.class);
                    startActivity(intent);
                }

                }
        });


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otpnumber")) {
                    final String message = intent.getStringExtra("message");
                    System.out.println("llllllllllllllllllllllllllllllllllllplllllllllotppppppppp");

                }
            }
        };*/


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otp_string==null){
                    Snackbar snackbar = Snackbar
                            .make(main_layout,"Please enter 4 digit OTP", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(NewEnterOTP.this, R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();
                }

                else if (!(otp_string.equals(otp_get))){
                   /* Snackbar snackbar = Snackbar
                            .make(main_layout,"Invalid OTP", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(VerifyOTP.this, R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();*/

                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Invalid OTP", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

              /*      LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout,
                            (ViewGroup) findViewById(R.id.toast_text));

// create a new Toast using context
                    TextView toast_text=layout.findViewById(R.id.toast_text);
                    toast_text.setText("Invalid OTP");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
                    toast.setView(layout); // set the inflated layout
                    toast.show(); // display the custom Toast*/
                    otp1.setText("");
                    otp2.setText("");
                    otp3.setText("");
                    otp4.setText("");

                }else{
                    if (getIntent().getStringExtra("register_status")!=null){
                        System.out.println("llllllogiiinn");
                        verify_status();
                      /*  JSONObject postjsonObject = new JSONObject();
                        JSONObject postjsonObject1 = new JSONObject();
                        try {

                            postjsonObject.put("PhoneNo",sessionManager.getRegId("phone"));
                            postjsonObject1.put("objUser",postjsonObject);
                            System.out.println("ffffffffffffff"+postjsonObject1);

                            Login_post.VerifyOTP(NewEnterOTP.this, postjsonObject1, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("llllllllllllllllllllllllllll"+result);
                                    try {
                                        System.out.println("nnnnnmnm" + result.toString());
                                        JSONObject responseobject = new JSONObject(result.toString());
                                        JSONObject response = responseobject.getJSONObject("Response");
                                        String status=response.getString("Status");
                                        String message=response.getString("Message");

                                        if(status.equals("0")){

                                            Toast.makeText(NewEnterOTP.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
                                        int duration=1000;
                                        Snackbar snackbar = Snackbar
                                                .make(linearLayout, "User Registered Successfully", duration);
                                        View snackbarView = snackbar.getView();
                                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        tv.setBackgroundColor(ContextCompat.getColor(EnterOTP.this,R.color.orange));
                                        tv.setTextColor(Color.WHITE);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        } else {
                                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                        }
                                        snackbar.show();


                                            System.out.println("fgddgdfgdfgffffffffff"+ message);

                                            Intent intent=new Intent(NewEnterOTP.this, LandingPageActivity.class);
                                            startActivity(intent);

                                        }
                                        else {
                                            Toast.makeText(NewEnterOTP.this,"OTP has not verified",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                    }else{
                        Intent intent=new Intent(NewEnterOTP.this,LandingPageActivity.class);
                        startActivity(intent);
                    }

                }

            }
        });

    }

    private void verify_status() {

        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("UserId", sessionManager.getRegId("userId"));

            System.out.println("alldetailssss"+jsonObject);


            Login_post.login_posting(NewEnterOTP.this, Urls.GetUserVerificationStatus, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111111user" + result);
                    JSONObject json1 = new JSONObject();

                    try {
                        json1 = result.getJSONObject("VerificationStatus");

                        IsUserUploaded = json1.getString("IsUserUploaded");

                        System.out.println("isssssuppploadeddd"+IsUserUploaded);
                        if (IsUserUploaded.equals(true)){
                            Intent intent=new Intent(NewEnterOTP.this,LandingPageActivity.class);
                            intent.putExtra("Loginsuccess","Login_success");
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(NewEnterOTP.this, Extra_Activity.class);
                            // intent.putExtra("verify_aadhar", "not_verified");
                            startActivity(intent);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NewEnterOTP.this, NewSignUpActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStop()
    {
        unregisterReceiver(connectivityReceiver);
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cTimer.cancel();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }

}
