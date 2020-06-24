package com.SevenNine.Essentials.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.Essentials.Activity.LandingPageActivity;
import com.SevenNine.Essentials.Activity.Status_bar_change_singleton;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;

import org.json.JSONObject;

public class VerificationFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout Continue,back_feed,linearLayout;
    String number;
    boolean doubleBackToExitPressedOnce = false;
    SessionManager sessionManager;
    EditText phone_no;
    JSONObject verifictn_array;
    String status;
    TextView progress,status_text,cntn_txt,only_status,toolbar_title,ph_no_text;
    ImageView status_image;
    JSONObject lngObject;
    public static String sucessful,inprogress,inprogress_full,sucessful_full;

    public static VerificationFragment newInstance() {
        VerificationFragment fragment = new VerificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verification_layout, container, false);

        Status_bar_change_singleton.getInstance().color_change(getActivity());

       Continue = view.findViewById(R.id.cont_btn);
       cntn_txt = view.findViewById(R.id.next);
        linearLayout = view.findViewById(R.id.card_view);
        phone_no = view.findViewById(R.id.ph_no);
        progress = view.findViewById(R.id.status);
        status_text = view.findViewById(R.id.sts_text);
        only_status = view.findViewById(R.id.only_status);
        status_image = view.findViewById(R.id.imgggg);
        toolbar_title = view.findViewById(R.id.setting_tittle);
        ph_no_text = view.findViewById(R.id.phone_number);

        sessionManager = new SessionManager(getActivity());

        number=sessionManager.getRegId("phone");
        System.out.println("GGetUserVerificationStatussssssssssssss"+sessionManager.getRegId("phone"));
        phone_no.setText(number);


       /* try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

            cntn_txt.setText(lngObject.getString("PROCEED").replace("\n",""));
            toolbar_title.setText(lngObject.getString("Verification"));
            ph_no_text.setText(lngObject.getString("PhoneNumber"));
             only_status.setText(lngObject.getString("Status"));
              inprogress = (lngObject.getString("InProgress".replace("\n","")));
             sucessful= (lngObject.getString("Successful"));
              inprogress_full= (lngObject.getString("OuragentwillcallyousoontoverifyDetailsPleasebeavailableovercall".replace("\n","")));
             sucessful_full =(lngObject.getString("YourverificationissuccessfulClickproceedtostartexploring"));


        } catch (JSONException e) {
            e.printStackTrace();
        }*/



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        // this.finishAffinity();

                        if (doubleBackToExitPressedOnce) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            getActivity().finish();
                            System.exit(0);
                        }

                        doubleBackToExitPressedOnce = true;
                        // Toast.makeText(getActivity().getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                     /*   int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(linearLayout,"Please Click Back To Exit", duration);
                        View snackbarView = snackbar.getView();
                        TextView tv = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                        tv.setTextColor(Color.WHITE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        } else {
                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        snackbar.show();*/
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce = false;
                            }
                        }, 3000);
                    }

                    return true;
                }
                return false;
            }
        });


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* selectedFragment = LandingPageActivity.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.commit();*/
                Intent i = new Intent(getActivity(), LandingPageActivity.class);
                startActivity(i);

            }
        });

        /*try{


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.GetUserVerificationStatus, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GGetUserVerificationStatussssssssssssss"+result);

                    try{

                        verifictn_array = result.getJSONObject("VerificationStatus");


                            Boolean user_status =verifictn_array.getBoolean("IsUserUploaded");


                            if(user_status.equals(true)){
                               progress.setText(inprogress);
                               status_text.setText(inprogress_full);
                             //  Continue.setVisibility(View.GONE);
                              // status_image.setImageResource(R.drawable.ic_success);

                                Continue.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent i = new Intent(getActivity(), HomePage_With_Bottom_Navigation.class);
                                        startActivity(i);


                                    }
                                });

                            }else{
                                progress.setText(inprogress);
                                status_text.setText(inprogress_full);

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

*/


       /* back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("add_edit", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });*/

        return view;
    }
}

//  scenior test