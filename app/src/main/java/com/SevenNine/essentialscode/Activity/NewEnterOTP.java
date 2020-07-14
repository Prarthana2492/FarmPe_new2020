package com.SevenNine.essentialscode.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.ReadSms;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.SmsListener;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.chaos.view.PinView;

import org.json.JSONObject;


public class NewEnterOTP extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    TextView submit;

    PinView otpedittext;
    public  static String sessionId,otp_get_text,enter_otp_toast,invalid_otp_toast,exceed_toast,another_chance_toast,toast_otp,restr_sucess,toast_invalid_otp,toast_internet,toast_nointernet,exceed,Nflogin,register_txt,login_txt,resend_txt;

    BroadcastReceiver receiver;
    Vibrator vibe;
    TextView resendotp,timer,phone_number,otpsenttxt,otp_sent_to,didnt_receive_otp,enter_otp_here;
    JSONObject lngObject;
    LinearLayout linearLayout,back_feed;
    private Context mContext= NewEnterOTP.this;
    private static final int REQUEST=1;
    String number;
    ProgressBar otp_sent;
    ImageView otpsentimg;
    SessionManager sessionManager;
    LinearLayout left_arrow,regiter_backgrd;
    JSONObject verifictn_array;
    Fragment selectedFragment;


    public static boolean connectivity_check;

    ConnectivityReceiver connectivityReceiver;
    @Override
    protected void onStop()
    {
        unregisterReceiver(connectivityReceiver);
        super.onStop();
    }



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

                Toast toast = Toast.makeText(NewEnterOTP.this,toast_internet, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();

/*                Toast toast = Toast.makeText(NewEnterOTP.this, toast_internet, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                toastMessage.setTextColor(Color.WHITE); toast.getView().setBackgroundResource(R.drawable.black_curve_background); toast.show();*/
               /* int duration=1000;
                Snackbar snackbar = Snackbar.make(linearLayout,toast_internet, duration);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setBackgroundColor(ContextCompat.getColor(NewEnterOTP.this,R.color.orange));
                textView.setTextColor(Color.WHITE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                }

                snackbar.show();
*/

                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            connectivity_check=true;
            Toast toast = Toast.makeText(NewEnterOTP.this,toast_nointernet, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
            toast.show();



        }
    }


    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);

        MyApplication.getInstance().setConnectivityListener(this);

    }


   /* @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkConnection();

        setContentView(R.layout.new_otp);
        vibe = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);

        otpedittext = findViewById(R.id.pinView);
        linearLayout = findViewById(R.id.main_layout);
        submit = findViewById(R.id.submit);
        timer = findViewById(R.id.time);
        back_feed = findViewById(R.id.back_feed);
        phone_number = findViewById(R.id.ph_num);
        otp_sent = findViewById(R.id.otp_sent);
        otpsentimg = findViewById(R.id.otpsent);
        otpsenttxt = findViewById(R.id.otpsenttxt);
        otp_sent_to = findViewById(R.id.otp_sento);
        didnt_receive_otp = findViewById(R.id.did_receive);
        enter_otp_here = findViewById(R.id.etr_otp_here);
        regiter_backgrd = findViewById(R.id.register_bgrd);
        sessionId= getIntent().getStringExtra("otpnumber");

        sessionManager = new SessionManager(this);

      /*  if(getIntent().getStringExtra("FromLogin").equals("flogin")){

            submit.setText("Login");
        }

*/


        //    sessionManager.checkRegister();



        /*try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

            otp_sent_to.setText(lngObject.getString("OTPSentto"));
            otpsenttxt.setText(lngObject.getString("OTPSent").replace("\n",""));
            didnt_receive_otp.setText(lngObject.getString("DidntreceiveOTP"));
            enter_otp_here.setText(lngObject.getString("EnterOTPhere"));
            enter_otp_toast =(lngObject.getString("EntertheOTP"));
            invalid_otp_toast =(lngObject.getString("InvalidOTP"));
            another_chance_toast =(lngObject.getString("Youhaveanother1chancetosendOTP"));
            exceed_toast =(lngObject.getString("Youhaveexceededthelimitofresendingotp"));
            register_txt =(lngObject.getString("REGISTER"));
            login_txt =(lngObject.getString("LOGIN"));
            resend_txt =(lngObject.getString("Resend"));


        } catch (JSONException e) {
            e.printStackTrace();
        }*/





        number= sessionManager.getRegId("phone");
        phone_number.setText(number);

        setupUI(linearLayout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                otpsentimg.setVisibility(View.VISIBLE);
                otpsenttxt.setVisibility(View.VISIBLE);
                otp_sent.setVisibility(View.GONE);
            }
        }, 7000);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                otpsentimg.setVisibility(View.GONE);
                otpsenttxt.setVisibility(View.GONE);
                otp_sent.setVisibility(View.GONE);
            }
        }, 9000);



        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("00 :" + millisUntilFinished / 1000);
                String timer_limit=""+millisUntilFinished/1000;
                System.out.println("dhsjn"+timer_limit);
                if (timer_limit.length()==1){

                    timer.setText("00:"+"0"+millisUntilFinished / 1000);

                }

                /*if(millisUntilFinished == 1)
                {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timer.setText("( 00 )");
                        }
                    }, 1000);
                }*/

            }
            public void onFinish() {
                timer.setText("Resend");
                timer.setBackgroundResource(R.drawable.border_filled_red_time);
                timer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (timer.getText().toString().equals("Resend")){
                            timer.setBackgroundResource(R.drawable.border_filled_gray_time);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                otpsentimg.setVisibility(View.VISIBLE);
                                otpsenttxt.setVisibility(View.VISIBLE);
                                otp_sent.setVisibility(View.GONE);
                            }
                        }, 7000);




                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                otpsentimg.setVisibility(View.GONE);
                                otpsenttxt.setVisibility(View.GONE);
                                otp_sent.setVisibility(View.GONE);
                            }
                        }, 9000);



                        new CountDownTimer(30000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                timer.setText("00 :" + millisUntilFinished / 1000);
                                String timer_limit=""+millisUntilFinished/1000;
                                System.out.println("dhsjn"+timer_limit);
                                if (timer_limit.length()==1){

                                    timer.setText("00:"+"0"+millisUntilFinished / 1000);

                                }

                                /*if(millisUntilFinished == 1)
                                {

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            timer.setText("( 00 )");
                                        }
                                    }, 1000);
                                }*/

                            }
                            public void onFinish() {
                                timer.setText("Resend");
                                timer.setBackgroundResource(R.drawable.border_filled_red_time);
                            }


                    }.start();

                        try{
                            JSONObject postjsonObject = new JSONObject();
                            postjsonObject.put("PhoneNo", phone_number.getText().toString() );
                            System.out.println("rrrrrrrrrrrrrrrrrrrr" + postjsonObject);

                            Login_post.login_posting(NewEnterOTP.this, Urls.ResendOTP, postjsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {

                                    System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk" + result.toString());

                                    try{

                                        String  Otp = result.getString("OTP");
                                        sessionId=Otp;
                                        String  Message = result.getString("Message");
                                        int  status= result.getInt("Status");

                                        if (status==2){
                                            Toast toast = Toast.makeText(NewEnterOTP.this,exceed_toast, Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                                            toast.show();

                                            /*Toast toast = Toast.makeText(NewEnterOTP.this, exceed_toast, Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                                            TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                                            toastMessage.setTextColor(Color.WHITE); toast.getView().setBackgroundResource(R.drawable.black_curve_background); toast.show();*/

                                        }

                                        else {
                                            Toast toast = Toast.makeText(NewEnterOTP.this,another_chance_toast, Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                                            toast.show();

                                          /*  Toast toast = Toast.makeText(NewEnterOTP.this, another_chance_toast, Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                                            TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                                            toastMessage.setTextColor(Color.WHITE); toast.getView().setBackgroundResource(R.drawable.black_curve_background); toast.show();*/
                                        }
                                    }catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });


            }
        }.start();















        System.out.println("qwertyuio"+sessionId);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });



        ReadSms.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                System.out.println("llllllllllllllllllllllllllllllllllllllllllllotp"+messageText);

                otpedittext.setText(messageText);
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                    final String message = intent.getStringExtra("message");
                    // otpedittext.setText(message);
                }
            }
        };

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(NewEnterOTP.this,NewSignUpActivity.class);
                startActivity(intent);

            }
        });

       /* if(getIntent().getExtras().getString("register_status").equals("login_btn")){

            submit.setText(login_txt);


        }else if(getIntent().getExtras().getString("register_status").equals("register_btn")){

            submit.setText(register_txt);
        }*/


        otpedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {

                if(otpedittext.getText().toString().equals("")){

                    otp_sent.setVisibility(View.GONE);
                    submit.setBackgroundResource(R.drawable.grey_curved_border);

                }else if(!(otpedittext.getText().toString().equals(sessionId))){
                    otp_sent.setVisibility(View.GONE);
                    submit.setBackgroundResource(R.drawable.grey_curved_border);
                } else {
                    submit.setBackgroundResource(R.drawable.border_filled_red);
                    // otp_sent.setVisibility(View.VISIBLE);
                }
            }
        });



       /* otpedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (otpedittext.length()==4){
                    View view1 = NewEnterOTP.this.getCurrentFocus();
                    if (view1 != null) {
                        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(view1.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    VerifyOTP();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/


        regiter_backgrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp_get_text=otpedittext.getText().toString();
                System.out.println("entered_otp"+otp_get_text);


                if (otp_get_text.equals("")){
                    Toast toast = Toast.makeText(NewEnterOTP.this,enter_otp_toast, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();
                    /*Toast toast = Toast.makeText(NewEnterOTP.this, enter_otp_toast, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.WHITE); toast.getView().setBackgroundResource(R.drawable.black_curve_background); toast.show();*/

                }

                else if (otp_get_text.equals(sessionId)){



                    if(getIntent().getExtras().getString("register_status").equals("login_btn")){

                        Intent intent = new Intent(NewEnterOTP.this,LandingPageActivity.class);
                        startActivity(intent);

                    }else if(getIntent().getExtras().getString("register_status").equals("register_btn")){

                        Intent intent = new Intent(NewEnterOTP.this,LandingPageActivity.class);
                        startActivity(intent);

                    }

                   /* Intent intent=new Intent(NewEnterOTP.this,FirmShopDetailsActivity.class);
                    startActivity(intent);
                    sessionManager.createLoginSession(NewSignUpActivity.contact);*/



                }else{
                    Toast toast = Toast.makeText(NewEnterOTP.this,invalid_otp_toast, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();

                   /* Toast toast = Toast.makeText(NewEnterOTP.this, invalid_otp_toast, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.WHITE); toast.getView().setBackgroundResource(R.drawable.black_curve_background); toast.show();*/
                }
            }

        });
    }

    private void Verification() {

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(this, Urls.GetUserVerificationStatus, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GGetUserVerificationStatussssssssssssss"+result);

                    try{

                        verifictn_array = result.getJSONObject("VerificationStatus");


                        Boolean user_status =verifictn_array.getBoolean("IsUserUploaded");


                        if(user_status.equals(true)){

                            sessionManager.createLoginSession(sessionManager.getRegId("phone"));
                            Intent intent=new Intent(NewEnterOTP.this, LandingPageActivity.class);
                            startActivity(intent);

                        }else{
                            sessionManager.createLoginSession(sessionManager.getRegId("phone"));
                            Intent intent=new Intent(NewEnterOTP.this,Extra_Activity.class);
                            startActivity(intent);

                            // progress.setText("In Progress");

                        }




                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

//

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {


        Intent intent=new Intent(NewEnterOTP.this,NewSignUpActivity.class);
        startActivity(intent);

    }



    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(NewEnterOTP.this);
                    return false;
                }

            });
        }


        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {

        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try{
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
}
