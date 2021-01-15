package com.SevenNine.essentials.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.SevenNine.essentials.Adapter.DistrictAdapter;
import com.SevenNine.essentials.Adapter.StateApdater;
import com.SevenNine.essentials.Adapter.TalukAdapter;
import com.SevenNine.essentials.Adapter.VillageAdapter;
import com.SevenNine.essentials.Bean.StateBean;
import com.SevenNine.essentials.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Add_New_Address_Fragment extends Fragment {

    RecyclerView recyclerView;


    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    static List<StateBean> talukBeanList = new ArrayList<>();
    static List<StateBean> hobliBeanList = new ArrayList<>();
    static List<StateBean> villageBeanList = new ArrayList<>();
    private List<StateBean> searchresultAraaylist = new ArrayList<>();
    StateApdater stateApdater;
    DistrictAdapter districtAdapter;
    TalukAdapter talukAdapter;
   // HoblisAdapter hoblisAdapter;
    VillageAdapter villageAdapter;

    LinearLayout back_feed,state,district,tehsil,block,village,adrss_type_linear;
    public static DrawerLayout drawer,main_layout;


       String state_id,district_id,tehsil_id,hobli_id;
       TextView toolbar_titletxt,current_loc,ortext,norecords;
       JSONArray jsonArray,state_array,tal_array,hobli_array,village_array;
       StateBean stateBean;
       String new_add_toast;
       EditText search,add_type;
       public static TextView save_1;
       public static String search_status="status";
       public static TextView add_new_address;
       Fragment selectedFragment = null;
       String selected_addresstype;
       JSONObject lngObject;
      LinearLayout linearLayout;
      public static TextView state_txt,district_txt,tehsil_txt,village_txt,block_txt,cancel_add;
      String s_addtype,entername,entermno,inncrtmno,enterstreetad,enterpincode,selectstate,selectdistrict,selecttaluk,selecthobli,selectvillage,newaddressadded,addnotadded ;
      public static EditText name,mobile,pincode_no,house_numb,street_name,select_address,landmrk,address_type,edit_state,edit_districr,pincode_edittxt,edit_village,name_txt,mobile_edit;
      String status,message;
      String Id;

      public static Dialog grade_dialog;
      int selected_id_time;


    public static Add_New_Address_Fragment newInstance() {
        Add_New_Address_Fragment fragment = new Add_New_Address_Fragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_your_region_layout, container, false);

        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


         select_address = view.findViewById(R.id.add_type);
         name = view.findViewById(R.id.full_name);
         mobile = view.findViewById(R.id.mob_no);
         back_feed = view.findViewById(R.id.back_feed);
         pincode_no = view.findViewById(R.id.pincode);
         house_numb = view.findViewById(R.id.house_no);
         street_name = view.findViewById(R.id.colny_street);
         mobile_edit = view.findViewById(R.id.mobile_edit);
         pincode_edittxt = view.findViewById(R.id.pincode_edittxt);
       // landmrk = view.findViewById(R.id.landmrk);
         search = view.findViewById(R.id.search);
        // main_layout = view.findViewById(R.id.drawer_layout_op);
        state_txt = view.findViewById(R.id.state_txt);
        district_txt = view.findViewById(R.id.district_txt);
        tehsil_txt = view.findViewById(R.id.tehsil_txt);
        // block_txt = view.findViewById(R.id.block_txt);
        village_txt = view.findViewById(R.id.village_txt);
        address_type = view.findViewById(R.id.address_type);
        adrss_type_linear = view.findViewById(R.id.adrss_type_linear);
        edit_state = view.findViewById(R.id.ed_state);
        edit_districr = view.findViewById(R.id.ed_dstrt);
        edit_village = view.findViewById(R.id.ed_vill);
        // add_type = view.findViewById(R.id.add_type);
        ortext = view.findViewById(R.id.ortext);
        save_1 = view.findViewById(R.id.save_1);
        recyclerView = view.findViewById(R.id.recycler_view);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout_op);
        state = view.findViewById(R.id.state_1);
        // city = view.findViewById(R.id.city_1);
        district = view.findViewById(R.id.district_1);
        tehsil = view.findViewById(R.id.tehsil_1);
        //  block = view.findViewById(R.id.block_1);
        village = view.findViewById(R.id.village_1);
        name_txt = view.findViewById(R.id.name_txt);
        cancel_add = view.findViewById(R.id.cancel_add);
        linearLayout = view.findViewById(R.id.linear_layout);
        norecords = view.findViewById(R.id.norecords);
        toolbar_titletxt=view.findViewById(R.id.setting_tittle);



        name.setFilters(new InputFilter[] { EMOJI_FILTER,new InputFilter.LengthFilter(30)});
        street_name.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(50)});

        setupUI(drawer);



            //     hobli_id = HoblisAdapter.hobliid;


       // sessionManager = new SessionManager(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        current_loc = view.findViewById(R.id.current_loc);


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                v = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                if (v == null) {
                    v = new View(getActivity());
                }
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
               // System.out.println("lllllllllllllllllllllllll"+getArguments().getString("navigation_from"));


            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    System.out.println("lllllllllllllllllllllllll"+getArguments().getString("navigation_from"));





//
                        selectedFragment = AaSettingFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout1, selectedFragment);
                        transaction.commit();



                    return true;
                }
                return false;
            }
        });




//        current_loc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedFragment = MapFragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("currentlocation");
//                transaction.commit();
//            }
//        });

        address_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.select_address_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);
                final TextView home =(TextView)dialog.findViewById(R.id.home_1);
                final TextView ware_house = (TextView)dialog.findViewById(R.id.ware_hus) ;
                final TextView farm = (TextView)dialog.findViewById(R.id.farm) ;
                final TextView others = (TextView)dialog.findViewById(R.id.othrs) ;
                final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;





                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(home.getText().toString());

                        selected_addresstype = "Home";

                        dialog.dismiss();
                    }
                });


                ware_house.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(ware_house.getText().toString());
                        selected_addresstype = "Warehouse";
                        dialog.dismiss();

                    }
                });

                farm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(farm.getText().toString());
                        selected_addresstype = "Farm";
                        dialog.dismiss();

                    }
                });

                others.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(others.getText().toString());
                        selected_addresstype = "Others";
                        dialog.dismiss();

                    }
                });


                dialog.show();

            }
        });


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  sorting(s.toString());
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });





        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

                drawer.openDrawer(GravityCompat.END);
                search_status="state";
                search.setText("");


                stateBeanList.clear();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


                StateBean statebean1=new StateBean("Karnataka","1");
                stateBeanList.add(statebean1);

                StateBean statebean2=new StateBean("Karnataka","2");
                stateBeanList.add(statebean2);

                StateBean statebean3=new StateBean("Karnataka","3");
                stateBeanList.add(statebean3);

                StateBean statebean4=new StateBean("Karnataka","4");
                stateBeanList.add(statebean4);

                StateBean statebean5=new StateBean("Karnataka","5");
                stateBeanList.add(statebean5);

                StateBean statebean6=new StateBean("Karnataka","6");
                stateBeanList.add(statebean6);

                StateBean statebean7=new StateBean("Karnataka","7");
                stateBeanList.add(statebean7);

                StateBean statebean8=new StateBean("Karnataka","8");
                stateBeanList.add(statebean8);



                stateApdater = new StateApdater(stateBeanList,getActivity());
                recyclerView.setAdapter(stateApdater);
                stateApdater.notifyDataSetChanged();




            }
        });


        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                drawer.openDrawer(GravityCompat.END);

                search_status = "district";
                search.setText("");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());



                StateBean statebean1=new StateBean("Bengaluru","1");
                districtBeanList.add(statebean1);


                StateBean statebean2=new StateBean("Bengaluru","2");
                districtBeanList.add(statebean2);


                StateBean statebean3=new StateBean("Bengaluru","3");
                districtBeanList.add(statebean3);


                StateBean statebean4=new StateBean("Bengaluru","4");
                districtBeanList.add(statebean4);


                StateBean statebean5=new StateBean("Bengaluru","5");
                districtBeanList.add(statebean5);


                StateBean statebean6=new StateBean("Bengaluru","6");
                districtBeanList.add(statebean6);


                StateBean statebean7=new StateBean("Bengaluru","7");
                districtBeanList.add(statebean7);


                StateBean statebean8=new StateBean("Bengaluru","8");
                districtBeanList.add(statebean8);


                districtAdapter = new DistrictAdapter(districtBeanList, getActivity());
                recyclerView.setAdapter(districtAdapter);
                districtAdapter.notifyDataSetChanged();

            }

        });


        tehsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    drawer.openDrawer(GravityCompat.END);
                    search_status = "taluk";
                    search.setText("");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                StateBean statebean1=new StateBean("Bengaluru","1");
                talukBeanList.add(statebean1);


                StateBean statebean2=new StateBean("Bengaluru","2");
                talukBeanList.add(statebean2);


                StateBean statebean3=new StateBean("Bengaluru","3");
                talukBeanList.add(statebean3);


                StateBean statebean4=new StateBean("Bengaluru","4");
                talukBeanList.add(statebean4);


                StateBean statebean5=new StateBean("Bengaluru","5");
                talukBeanList.add(statebean5);


                StateBean statebean6=new StateBean("Bengaluru","6");
                talukBeanList.add(statebean6);


                StateBean statebean7=new StateBean("Bengaluru","7");
                talukBeanList.add(statebean7);


                StateBean statebean8=new StateBean("Bengaluru","8");
                talukBeanList.add(statebean8);



                talukAdapter = new TalukAdapter(talukBeanList, getActivity());
                    recyclerView.setAdapter(talukAdapter);



            }
        });

//
//        hobli.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//                drawer.openDrawer(GravityCompat.END);
//
//                search_status = "hobli";
//                search.setText("");
//
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//                recyclerView.setLayoutManager(mLayoutManager);
//
//                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//                hoblisAdapter = new HoblisAdapter(hobliBeanList, getActivity());
//                recyclerView.setAdapter(hoblisAdapter);
//
//                prepareHobliData();
//
//            }
//        });


        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!district_txt.getText().toString().equals("")) {

                    // submit.setVisibility(View.VISIBLE);

                    drawer.openDrawer(GravityCompat.END);
                    search_status = "village";
                    search.setText("");
                    //   search.setQueryHint("");
                    // stateBeanList.clear();

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());



                    StateBean statebean1=new StateBean("Bengaluru","1");
                    villageBeanList.add(statebean1);


                    StateBean statebean2=new StateBean("Bengaluru","2");
                    villageBeanList.add(statebean2);


                    StateBean statebean3=new StateBean("Bengaluru","3");
                    villageBeanList.add(statebean3);


                    StateBean statebean4=new StateBean("Bengaluru","4");
                    villageBeanList.add(statebean4);


                    StateBean statebean5=new StateBean("Bengaluru","5");
                    villageBeanList.add(statebean5);


                    StateBean statebean6=new StateBean("Bengaluru","6");
                    villageBeanList.add(statebean6);


                    StateBean statebean7=new StateBean("Bengaluru","7");
                    villageBeanList.add(statebean7);


                    StateBean statebean8=new StateBean("Bengaluru","8");
                    villageBeanList.add(statebean8);


                    villageAdapter = new VillageAdapter(villageBeanList, getActivity());
                    recyclerView.setAdapter(villageAdapter);
                }

                }
        });



        save_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






            if(name.getText().toString().equals("")) {

                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();


                }else if(mobile.getText().toString().equals("")){


                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();


                }else if(mobile.length()<10){

                    Toast.makeText(getActivity(), "Please Enter 10 Didgit Mobile Number", Toast.LENGTH_SHORT).show();

                }

                else {

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                    view = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view == null) {
                        view = new View(getActivity());
                    }
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                }

            }
        });



        return view;

    }





    public void sorting(List<StateBean> arrayList){

        Collections.sort(arrayList, new Comparator() {
            @Override
            public int compare(Object state_name1, Object state_name2) {
                //use instanceof to verify the references are indeed of the type in question

                return ((StateBean)state_name1).getName()
                        .compareTo(((StateBean)state_name2).getName());
            }
        });
    }

    public static InputFilter EMOJI_FILTER1 = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)) {
                        filtered += character;
                    }
                }
                return filtered;
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
    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            String specialChars = ".1/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥₹...%&+=€π|";
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL||type== Character.MATH_SYMBOL||specialChars.contains("" + source)) {
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



    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/

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

//    public  void sorting(String filter_text){
//
//        final String text = filter_text.toLowerCase();
//
//
//
//        if (search_status.equals("state")){
//            searchresultAraaylist.clear();
//            for (int i = 0; i < stateBeanList.size(); i++) {
//
//                if (stateBeanList.get(i).getName().toLowerCase().contains(text)) {
//                    searchresultAraaylist.add(stateBeanList.get(i));
//
//                }
//        }
//
//            if (searchresultAraaylist.size()==0){
//                recyclerView.setVisibility(View.GONE);
//                norecords.setVisibility(View.VISIBLE);
//            }else {
//                recyclerView.setVisibility(View.VISIBLE);
//                norecords.setVisibility(View.GONE);
//                stateApdater = new StateApdater(searchresultAraaylist, getActivity());
//                recyclerView.setAdapter(stateApdater);
//            }
//
//            //            stateApdater = new StateApdater(searchresultAraaylist,getActivity());
////            recyclerView.setAdapter(stateApdater);
//        }
//
//
//        else if (search_status.equals("district")) {
//            searchresultAraaylist.clear();
//            for (int i = 0; i < districtBeanList.size(); i++) {
//
//                if (districtBeanList.get(i).getName().toLowerCase().contains(text)) {
//                    searchresultAraaylist.add(districtBeanList.get(i));
//
//                }
//            }
//
//            if (searchresultAraaylist.size()==0){
//                recyclerView.setVisibility(View.GONE);
//                norecords.setVisibility(View.VISIBLE);
//            }else {
//                recyclerView.setVisibility(View.VISIBLE);
//                norecords.setVisibility(View.GONE);
//
//
//                districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
//                recyclerView.setAdapter(districtAdapter);
//
//            }
//
////            districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
////            recyclerView.setAdapter(districtAdapter);
//
//
//        }
//
//        else if (search_status.equals("taluk")) {
//            searchresultAraaylist.clear();
//            for (int i = 0; i < talukBeanList.size(); i++) {
//
//                if (talukBeanList.get(i).getName().toLowerCase().contains(text)) {
//                    searchresultAraaylist.add(talukBeanList.get(i));
//
//                }
//            }
//
//            if (searchresultAraaylist.size() == 0) {
//
//                recyclerView.setVisibility(View.GONE);
//                norecords.setVisibility(View.VISIBLE);
//
//            } else {
//                recyclerView.setVisibility(View.VISIBLE);
//                norecords.setVisibility(View.GONE);
//
//                talukAdapter = new TalukAdapter(searchresultAraaylist, getActivity());
//                recyclerView.setAdapter(talukAdapter);
//            }
//        }
//
//        else if (search_status.equals("village")){
//            searchresultAraaylist.clear();
//
//            for (int i = 0; i < hobliBeanList.size(); i++) {
//
//                if (hobliBeanList.get(i).getName().toLowerCase().contains(text)) {
//                    searchresultAraaylist.add(hobliBeanList.get(i));
//
//                }
//            }
//
//            if (searchresultAraaylist.size()==0){
//
//                recyclerView.setVisibility(View.GONE);
//                norecords.setVisibility(View.VISIBLE);
//
//            }else {
//
////                recyclerView.setVisibility(View.VISIBLE);
////                norecords.setVisibility(View.GONE);
////
////                hoblisAdapter = new HoblisAdapter(searchresultAraaylist, getActivity());
////                recyclerView.setAdapter(hoblisAdapter);
//            }
//        }
//
//
////        else if (search_status.equals("village")){
////            searchresultAraaylist.clear();
////            for (int i = 0; i < villageBeanList.size(); i++) {
////
////                if (villageBeanList.get(i).getName().toLowerCase().contains(text)) {
////                    searchresultAraaylist.add(villageBeanList.get(i));
////
////                }
////            }
//////                    villageAdapter = new VillageAdapter(sdearcstateBeanList);
////            recyclerView.setAdapter(villageAdapter);
////        }
//
//    }
}



