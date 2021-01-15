package com.SevenNine.essentials.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.SevenNine.essentials.R;


public class Verify_KYC_Fragment extends Fragment {

    LinearLayout back_feed,main_layout,verify;
    TextView next;
    Fragment selectedFragment;
    EditText select_document_type,enter_id_nu,enter_name;

    public static DrawerLayout drawer;
    RecyclerView recyclerView;
    String status;




    public static Verify_KYC_Fragment newInstance() {
        Verify_KYC_Fragment fragment = new Verify_KYC_Fragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_kyc_layout, container, false);

        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));
        back_feed=view.findViewById(R.id.back_feed);
        select_document_type=view.findViewById(R.id.fullname);
        enter_id_nu=view.findViewById(R.id.relationship);
        enter_name=view.findViewById(R.id.relativename);
        verify=view.findViewById(R.id.continuebtn);


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });

        enter_name.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(30)});
    /*verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Addnewbankdetailswithifsc.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("add_new");
                transaction.commit();
            }
        });*/


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }

                return false;
            }
        });


        select_document_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), select_document_type);
                popup.getMenu().add("Adhar Card");
                popup.getMenu().add("Pan Card");
                popup.getMenu().add("Driving License");
                popup.getMenu().add("Voter ID");

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        System.out.println("teeexxtt"+item.getTitle());

                        select_document_type.setText(item.getTitle());

                        if (item.getTitle().equals("Adhar Card")) {
                            select_document_type.setText(item.getTitle());

                        }else if(item.getTitle().equals("Pan Card")){
                            select_document_type.setText(item.getTitle());

                        }else if(item.getTitle().equals("Driving License")){
                            select_document_type.setText(item.getTitle());
                            
                        }else if(item.getTitle().equals("Voter ID")){
                            select_document_type.setText(item.getTitle());
                        }

                        return true;
                    }
                });

                popup.show(); //showing
            }
        });


        select_document_type.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(select_document_type,enter_id_nu,enter_name);
                return false;
            }
        });

        enter_id_nu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(enter_id_nu,select_document_type,enter_name);
                return false;
            }
        });

        enter_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(enter_name,select_document_type,enter_id_nu);
                return false;
            }
        });


        return view;
    }



  public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3){

      selectdl1.setBackgroundResource(R.drawable.border_1_layout);
      l2.setBackgroundResource(R.drawable.request_price_white_border2);
      l3.setBackgroundResource(R.drawable.request_price_white_border2);


  }


    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            String specialChars = ".1/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥₹...%&+=€π|";
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL||type==Character.MATH_SYMBOL||specialChars.contains("" + source)) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }else if(Character.isDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;

            }

            if (keepOriginal)
                return null;

            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }
    };
}
