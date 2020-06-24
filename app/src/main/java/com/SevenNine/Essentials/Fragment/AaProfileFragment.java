//package com.FarmPe.SellerHub.Fragment;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.support.design.widget.BottomSheetDialog;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.content.ContextCompat;
//import android.text.InputFilter;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.FarmPe.SellerHub.Activity.HomePage_With_Bottom_Navigation;
//import com.FarmPe.SellerHub.Activity.Status_bar_change_singleton;
//import com.FarmPe.SellerHub.G_Vision_Controller;
//import com.FarmPe.SellerHub.R;
//import com.FarmPe.SellerHub.SessionManager;
//import com.FarmPe.SellerHub.Urls;
//import com.FarmPe.SellerHub.Volly_class.Crop_Post;
//import com.FarmPe.SellerHub.Volly_class.VoleyJsonObjectCallback;
//import com.FarmPe.SellerHub.volleypost.VolleyMultipartRequest;
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.RequestOptions;
//
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//import static android.app.Activity.RESULT_OK;
//import static com.FarmPe.SellerHub.Activity.LandingPageActivity.selectedImage;
//import static com.android.volley.VolleyLog.TAG;
//
//
//public class AaProfileFragment extends Fragment {
//
//
//    BottomSheetDialog mBottomSheetDialog;
//    View sheetView;
//    public  static  CircleImageView prod_img;
//    Fragment selectedFragment;
//    EditText abt_text,userInput;
//    LinearLayout backfeed,acc_info_lay,linearLayout,about_lay;
//    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle,aboutText;
//    SessionManager sessionManager;
//    public static EditText profile_phone,profname;
//    JSONObject lngObject;
//    Bitmap bitmap;
//    G_Vision_Controller g_vision_controller;
//    public  static String ProfilePhone;
//
//    public static AaProfileFragment newInstance() {
//        AaProfileFragment fragment = new AaProfileFragment();
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.a_a_profile_layout, container, false);
//
//        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
//        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);
//        Status_bar_change_singleton.getInstance().color_change(getActivity());
//
//        backfeed=view.findViewById(R.id.back_feed);
//        acc_info_lay=view.findViewById(R.id.acc_info_lay);
//        linearLayout=view.findViewById(R.id.main_layout);
//        about_lay=view.findViewById(R.id.about_lay);
//        profname = view.findViewById(R.id.prof_name);
//        profile_phone = view.findViewById(R.id.phone_text);
//        aboutText = view.findViewById(R.id.about_text);
//
//        prod_img = view.findViewById(R.id.prod_imgg);
//        sessionManager = new SessionManager(getActivity());
//        setupUI(linearLayout);
//        getActivity().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//
//        backfeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*if (getArguments().getString("status").equals("HOME_IMG")){
//
//                    selectedFragment = HomeMenuFragment.newInstance();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, selectedFragment);
//                    // transaction.addToBackStack("looking");
//                    transaction.commit();
//
//                }else*/ if(getArguments().getString("status").equals("ACC_IMG")){
//                    selectedFragment = AaAccountFragment.newInstance();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, selectedFragment);
//                    // transaction.addToBackStack("looking");
//                    transaction.commit();
//                }
//
//
//       /* FragmentManager fm = getActivity().getSupportFragmentManager();
//        fm.popBackStack ("aaAccount", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        */
//            }
//        });
//
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                   /* if (getArguments().getString("status").equals("HOME_IMG")){
//                        selectedFragment = HomeMenuFragment.newInstance();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, selectedFragment);
//                        // transaction.addToBackStack("looking");
//                        transaction.commit();
//                    }else */if(getArguments().getString("status").equals("ACC_IMG")){
//                        selectedFragment = AaAccountFragment.newInstance();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, selectedFragment);
//                        // transaction.addToBackStack("looking");
//                        transaction.commit();
//                    }
//
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        linearLayout.setBackgroundColor(Color.parseColor("#f5f5f5"));
//        //  mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        prod_img.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("NewApi")
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // to go to gallery
//                startActivityForResult(i, 100); // on activity method will execute*/
//
//            }
//        });
//
//        acc_info_lay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                  mBottomSheetDialog = new BottomSheetDialog(getActivity());
//                  sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);
//
//                  new KeyboardUtil(getActivity(), sheetView);
//                  TextView positiveText = sheetView.findViewById(R.id.positive_text);
//                  TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
//                  TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
//                  userInput = sheetView.findViewById(R.id.user_text);
//
//
//              //  yourEditText.setSelection(startPosition, endPosition);
//
////                userInput.setFilters(new InputFilter[] {
////                        new InputFilter.LengthFilter(30) {
////                            public CharSequence filter(CharSequence src, int start,
////                                                       int end, Spanned dst, int dstart, int dend) {
////
////                                for (int i = start; i < end; i++) {
////                                    if (Character.isWhitespace(src.charAt(i))) {
////                                        if (dstart == 0)
////                                            return "";
////                                    }
////                                }
////
////                                if(src.toString().matches("[a-zA-Z ]+")){
////                                    return src;
////                                }
////                                return "";
////                            }
////                        }
////                });
//
//
//                userInput.setVisibility(View.VISIBLE);
//                userInput.setText(profname.getText().toString());
//                userInput.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(30)});
//                descriptionText.setVisibility(View.GONE);
//                titleText.setText("Enter your name");
//                descriptionText.setText("Are you sure you want to exit?");
//                positiveText.setText("Save");
//                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
//                negetiveText.setText("Cancel");
//
//
//
//                positiveText.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        if (userInput.getText().toString().equals("")) {
//
//                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                            //Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
//                            int duration = 1000;
//                            Snackbar snackbar = Snackbar
//                                    .make(mBottomSheetDialog.getWindow().getDecorView(), "Name Field Cannot Be Empty", duration);
//                            View snackbarView = snackbar.getView();
//                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                            tv.setTextColor(Color.WHITE);
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                            } else {
//                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                            }
//                            snackbar.show();
//                            mBottomSheetDialog.show();
//
//
//
//                        } else {
//
//
//                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.Update_Profile_Details,
//                                    new Response.Listener<String>() {
//                                        @Override
//                                        public void onResponse(String response) {
//
//                                          //  sessionManager.save_name(userInput.getText().toString(),sessionManager.getRegId("phone"),"ProfileImage");
//
//                                            System.out.println("fsdfsdfff" + response);
//                                            int duration = 1000;
//                                            Snackbar snackbar = Snackbar
//                                                    .make(linearLayout, "Profile Details Updated Successfully", duration);
//                                            View snackbarView = snackbar.getView();
//                                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
//                                            tv.setTextColor(Color.WHITE);
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//
//                                            } else {
//                                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                                            }
//
//                                            snackbar.show();
//                                            mBottomSheetDialog.dismiss();
//                                            Bundle bundle = new Bundle();
//                                            bundle.putString("status", "HOME_IMG");
//                                            selectedFragment = AaProfileFragment.newInstance();
//                                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                            transaction.replace(R.id.frame_layout, selectedFragment);
//                                            selectedFragment.setArguments(bundle);
//                                            transaction.commit();
//                                        }
//                                    },
//                                    new Response.ErrorListener() {
//                                        @Override
//                                        public void onErrorResponse(VolleyError error) {
//                                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//                                        }
//                                    }) {
//                                @Override
//                                protected Map<String, String> getParams() {
//                                    Map<String, String> params = new HashMap<String, String>();
//                                    params.put("UserId", sessionManager.getRegId("userId"));
//                                    params.put("FullName", userInput.getText().toString());
//                                    params.put("PhoneNo", sessionManager.getRegId("phone"));
//                                    System.out.println("fhsdfhjf" + params);
//                                    return params;
//                                }
//
//                            };
//                            Volley.newRequestQueue(getActivity()).add(stringRequest);
////  Toast.makeText(getActivity(),"Save was clicked",Toast.LENGTH_LONG).show();
//
//                            //  Toast.makeText(getActivity(),"Save was clicked",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                negetiveText.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mBottomSheetDialog.dismiss();
//                    }
//                });
//                mBottomSheetDialog.setContentView(sheetView);
//                mBottomSheetDialog.show();
//            }
//        });
//
//
//
//        about_lay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBottomSheetDialog = new BottomSheetDialog(getActivity());
//                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);
//
//                new KeyboardUtil(getActivity(), sheetView);
//                TextView positiveText = sheetView.findViewById(R.id.positive_text);
//                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
//                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
//                abt_text = sheetView.findViewById(R.id.user_text);
//                abt_text.setFilters(new InputFilter[]{EMOJI_FILTER, new InputFilter.LengthFilter(50)});
//
//                abt_text.setVisibility(View.VISIBLE);
//                abt_text.setText(aboutText.getText().toString());
//                descriptionText.setVisibility(View.GONE);
//                titleText.setText("Add about");
//                descriptionText.setText("Are you sure you want to exit?");
//                positiveText.setText("Save");
//                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
//                negetiveText.setText("Cancel");
//                positiveText.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if(abt_text.getText().toString().equals("")){
//
//
//                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                            //Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
//                            int duration = 1000;
//                            Snackbar snackbar = Snackbar
//                                    .make(mBottomSheetDialog.getWindow().getDecorView(), "About Field Cannot Be Empty", duration);
//                            View snackbarView = snackbar.getView();
//                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                            tv.setTextColor(Color.WHITE);
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                            } else {
//                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                            }
//                            snackbar.show();
//                            mBottomSheetDialog.show();
//
//
//                        }else{
//
//
//                            save_description();
//
//
//                        }
//
//
//                    }
//                });
//
//
//                negetiveText.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mBottomSheetDialog.dismiss();
//                    }
//                });
//
//                mBottomSheetDialog.setContentView(sheetView);
//                mBottomSheetDialog.show();
//            }
//        });
//
//
//
//        try{
//            JSONObject jsonObject = new JSONObject();
//            JSONObject post_object = new JSONObject();
//            jsonObject.put("Id",sessionManager.getRegId("userId"));
//            post_object.put("objUser",jsonObject);
//            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details, post_object, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("ggpgpgpg" + result);
//
//
//                    try{
//                        JSONObject jsonObject1 = result.getJSONObject("user");
//                        String profnamestr = jsonObject1.getString("FullName");
//                        System.out.println("ggpgpgpg" + profnamestr);
//                        ProfilePhone = jsonObject1.getString("PhoneNo");
//                        //String ProfileEmail = jsonObject1.getString("EmailId");
//                        String ProfileImage = jsonObject1.getString("ProfilePic");
//                       ////// String profile_description = jsonObject1.getString("About");
//                        aboutText.setText("Farming is a way of life");
//
//                    /*    profname.setText(profnamestr);
//                        if(profile_description.equalsIgnoreCase("")){
//                            aboutText.setText("Farming is a way of life");
//                        }else{
//                            aboutText.setText(profile_description);
//                        }*/
//
//                        profile_phone.setText(ProfilePhone); // masking + deleting last line
//
//
//                       // profname.setFilters(new InputFilter[]{EMOJI_FILTER});
//                        profname.setText(profnamestr);
//                        profile_phone.setFilters(new InputFilter[]{EMOJI_FILTER});
//                        aboutText.setFilters(new InputFilter[]{EMOJI_FILTER});
//
//
//                      Glide.with(getActivity()).load(ProfileImage)
//                                .thumbnail(0.5f)
//                                //.crossFade()
//                              .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
//                                .error(R.drawable.ic_gallery__default))
//                                .into(prod_img);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return view;
//    }
//
//
//
//
//
//
//
//    private void save_description() {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.Update_Profile_Details,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        int duration = 1000;
//                        Snackbar snackbar = Snackbar
//                                .make(linearLayout, "Profile Details Updated Successfully", duration);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//
//                        snackbar.show();
//                        mBottomSheetDialog.dismiss();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("status","HOME_IMG");
//                        selectedFragment = AaProfileFragment.newInstance();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, selectedFragment);
//                        selectedFragment.setArguments(bundle);
//                        transaction.commit();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("UserId",sessionManager.getRegId("userId"));
//                params.put("FullName",profname.getText().toString());
//                params.put("PhoneNo",sessionManager.getRegId("phone"));
//                params.put("About",abt_text.getText().toString());
//                return params;
//            }
//        };
//        Volley.newRequestQueue(getActivity()).add(stringRequest);
//
//    }
//
//
//
//    public static InputFilter EMOJI_FILTER = new InputFilter() {
//        @Override
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//            boolean keepOriginal = true;
//            StringBuilder sb = new StringBuilder(end - start);
//            for (int index = start; index < end; index++) {
//                int type = Character.getType(source.charAt(index));
//                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
//                    return "";
//                }
//                for (int i = start; i < end; i++) {
//                    if (Character.isWhitespace(source.charAt(i))) {
//                        if (dstart == 0)
//                            return "";
//                    }
//                }
//                return null;
//          /*  char c = source.charAt(index);
//            if (isCharAllowed(c))
//                sb.append(c);
//            else
//                keepOriginal = false;*/
//            }
//
//            if (keepOriginal)
//                return null;
//            else {
//                if (source instanceof Spanned) {
//                    SpannableString sp = new SpannableString(sb);
//                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
//                    return sp;
//                } else {
//                    return sb;
//                }
//            }
//        }
//    };
//
//
//    public static InputFilter EMOJI_FILTER1 = new InputFilter() {
//        @Override
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//            boolean keepOriginal = true;
//            StringBuilder sb = new StringBuilder(end - start);
//            for (int index = start; index < end; index++) {
//                int type = Character.getType(source.charAt(index));
//                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
//                    return "";
//                }
//                for (int i = start; i < end; i++) {
//                    if (Character.isWhitespace(source.charAt(i))) {
//                        if (dstart == 0)
//                            return "";
//                    }
//                }
//
//                return null;
//          /*  char c = source.charAt(index);
//            if (isCharAllowed(c))
//                sb.append(c);
//            else
//                keepOriginal = false;*/
//            }
//
//            if (keepOriginal)
//                return null;
//            else {
//                if (source instanceof Spanned) {
//                    SpannableString sp = new SpannableString(sb);
//                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
//                    return sp;
//                } else {
//                    return sb;
//                }
//            }
//        }
//    };
//
//
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
//            g_vision_controller = G_Vision_Controller.getInstance( );
//
//            //getting the image Uri
//            Uri imageUri = data.getData();
//
//
//            final InputStream imageStream;
//            try {
//                imageStream = getActivity().getContentResolver().openInputStream(imageUri);
//                selectedImage = BitmapFactory.decodeStream(imageStream);
//                g_vision_controller.callCloudVision(selectedImage,getActivity(),"profile");
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//
//
//
//
//
//
//            //    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
//
//               // prod_img.setImageBitmap(bitmap);
//                //uploadImage(getResizedBitmap(g_vision_controller.callCloudVision(selectedImage,getActivity(),"profile"),100,100));
//                int duration = 1000;
//                Snackbar snackbar = Snackbar
//                        .make(linearLayout, "You Changed Your Profile Photo", duration);
//                View snackbarView = snackbar.getView();
//                TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                tv.setTextColor(Color.WHITE);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                } else {
//                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                }
//                snackbar.show();
//                //  Toast.makeText(getActivity(),"Your Changed Your Profile Photo", Toast.LENGTH_SHORT).show();
//
//
//
//        }
//    }
//
//    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }
//    private void uploadImage(final Bitmap bitmap){
//        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "",
//                "Loading....Please wait.");
//        progressDialog.show();
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.Update_Profile_Details,
//                new Response.Listener<NetworkResponse>(){
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        Log.e(TAG,"afaeftagsbillvalue"+response);
//                        Log.e(TAG,"afaeftagsbillvalue"+response);
//                        progressDialog.dismiss();
///*
//                        if(profile_passwrd.getText().toString().length()<=12 && profile_passwrd.getText().toString().length()>=6){
//                            if(myDb.isEmailExists(profile_phone.getText().toString().substring(3))) {
//                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_passwrd.getText().toString());
//                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_phone.getText().toString());
//                                // AddData(profile_phone.getText().toString(), profile_passwrd.getText().toString());
//                                myDb.updateContact(profile_phone.getText().toString().substring(3),profile_passwrd.getText().toString());
//                            }
//                        }
//                        else{
//                        }
//*/
//                        HomeMenuFragment.prod_img.setImageBitmap(bitmap);
//                        HomeMenuFragment.prod_img1.setImageBitmap(bitmap);
//                        // sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
//                        int duration = 1000;
//                        Snackbar snackbar = Snackbar
//                                .make(linearLayout, "Profile Details Updated Successfully", duration);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//                        snackbar.show();
//                       // Toast.makeText(getActivity(),"Profile Details Updated Successfully", Toast.LENGTH_SHORT).show();
//                    /*    selectedFragment = SettingFragment.newInstance();
//                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.frame_layout,selectedFragment);
//                        ft.commit();*/
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("UserId",sessionManager.getRegId("userId"));
//                params.put("FullName",sessionManager.getRegId("name"));
//                params.put("PhoneNo",sessionManager.getRegId("phone"));
//                //  params.put("EmailId","abcd@gmail.com");
//                //    params.put("Password",profile_passwrd.getText().toString());
//                Log.e(TAG,"afaeftagsparams"+params);
//                return params;
//            }
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//                Log.e(TAG,"Im here " + params);
//                if (bitmap!=null) {
//                    params.put("File", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
//                }
//                return params;
//            }
//        };
//        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        //adding the request to volley
//        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
//    }
//
//    public void setupUI(View view) {
//        //Set up touch listener for non-text box views to hide keyboard.
//        if (!(view instanceof EditText)) {
//            view.setOnTouchListener(new View.OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    hideSoftKeyboard(getActivity());
//                    return false;
//                }
//            });
//        }
//        //If a layout container, iterate over children and seed recursion.
//        if (view instanceof ViewGroup) {
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                View innerView = ((ViewGroup) view).getChildAt(i);
//                setupUI(innerView);
//            }
//        }
//    }
//    public static void hideSoftKeyboard(Activity activity) {
//        /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/
//
//        InputMethodManager inputManager = (InputMethodManager)
//                activity.getSystemService(
//                        Context.INPUT_METHOD_SERVICE);
//        View focusedView = activity.getCurrentFocus();
//
//        if (focusedView != null) {
//
//            try {
//                assert inputManager != null;
//                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
//                        InputMethodManager.HIDE_NOT_ALWAYS);
//            } catch (AssertionError e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//
//
//
//    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
//        if (bm == null) {
//
//            return null;
//        } else {
//            int width = bm.getWidth();
//            int height = bm.getHeight();
//            float scaleWidth = ((float) newWidth) / width;
//            float scaleHeight = ((float) newHeight) / height;
//            // CREATE A MATRIX FOR THE MANIPULATION
//            Matrix matrix = new Matrix();
//            // RESIZE THE BIT MAP
//            matrix.postScale(scaleWidth, scaleHeight);
//
//            // "RECREATE" THE NEW BITMAP
//            Bitmap resizedBitmap = Bitmap.createBitmap(
//                    bm, 0, 0, width, height, matrix, false);
//            bm.recycle();
//            return resizedBitmap;
//        }
//    }
//}
