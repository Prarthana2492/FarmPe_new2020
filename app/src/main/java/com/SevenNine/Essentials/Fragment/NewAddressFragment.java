package com.SevenNine.Essentials.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.SevenNine.Essentials.Adapter.Address_Adapter;
import com.SevenNine.Essentials.Adapter.DistrictAdapter;
import com.SevenNine.Essentials.Adapter.HoblisAdapter;
import com.SevenNine.Essentials.Adapter.StateApdater;
import com.SevenNine.Essentials.Adapter.TalukAdapter;
import com.SevenNine.Essentials.Adapter.VillageAdapter;
import com.SevenNine.Essentials.Bean.StateBean;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;
import com.SevenNine.Essentials.Urls;
import com.SevenNine.Essentials.Volly_class.Crop_Post;
import com.SevenNine.Essentials.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class NewAddressFragment extends Fragment {
    RecyclerView recyclerView;
    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    static List<StateBean> talukBeanList = new ArrayList<>();
    static List<StateBean> hobliBeanList = new ArrayList<>();
    static List<StateBean> villageBeanList = new ArrayList<>();
    static List<StateBean> countryBeanList = new ArrayList<>();
    private List<StateBean> searchresultAraaylist = new ArrayList<>();
    StateApdater stateApdater;
    DistrictAdapter districtAdapter;
    TalukAdapter talukAdapter;
    HoblisAdapter hoblisAdapter;
    VillageAdapter villageAdapter;
    StateBean stateBean;
    Bundle bundle;
    RadioButton home_rd,work_rd;
    LinearLayout back_feed,country,state,district,tehsil,hobli,village,adrss_type_linear;
    public static DrawerLayout drawer,main_layout;
    public static TextView toolbar_titletxt,current_loc,ortext,skip,ed_state,ed_dstrt,ed_tsl,ed_vill;
    public  static EditText search,add_type;
    public static TextView save_1;
    public static String search_status="status";
    Fragment selectedFragment = null;
    boolean selected_addresstype;
    LinearLayout linearLayout;
    String s_addtype,entername,entermno,inncrtmno,enterstreetad,enterpincode,selectstate,selectdistrict,selecttaluk,selecthobli,selectvillage,newaddressadded,addnotadded ;
    public static EditText name,mobile,pincode_no,house_numb,street_name,select_address,landmrk,address_type;
    public static Dialog grade_dialog;
    int selected_id_time;
    TextView edit_state,edit_districr,block,nyay_panch,gram_panch,edit_village;
    public static String address,status;
    JSONArray tal_array,village_array,state_array,jsonArray,hobli_array,country_array;
    SessionManager sessionManager;
    int addr_type;
    JSONObject lngObject;

    public static NewAddressFragment newInstance() {
        NewAddressFragment fragment = new NewAddressFragment();
        return fragment;
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_your_region_profilelayout, container, false);


        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));


        name = view.findViewById(R.id.full_name);
        landmrk = view.findViewById(R.id.landmark);
        mobile = view.findViewById(R.id.mob_no);
        back_feed = view.findViewById(R.id.back_feed);
        pincode_no = view.findViewById(R.id.pincode);
        house_numb = view.findViewById(R.id.house_no);
        // street_name = view.findViewById(R.id.colny_street);
        search = view.findViewById(R.id.search);
        ed_state = view.findViewById(R.id.ed_state);
        ed_dstrt = view.findViewById(R.id.ed_dstrt);
        ed_tsl = view.findViewById(R.id.ed_tsl);
        ed_vill = view.findViewById(R.id.ed_vill);
        // address_type = view.findViewById(R.id.address_type);
        //  adrss_type_linear = view.findViewById(R.id.adrss_type_linear);
        edit_state = view.findViewById(R.id.ed_state);
        edit_districr = view.findViewById(R.id.ed_dstrt);
        block = view.findViewById(R.id.ed_tsl);
        // nyay_panch = view.findViewById(R.id.nyay_panch);
        // gram_panch = view.findViewById(R.id.gram_pancha);
        edit_village = view.findViewById(R.id.ed_vill);
        // ortext = view.findViewById(R.id.ortext);
        save_1 = view.findViewById(R.id.save_1);
        recyclerView = view.findViewById(R.id.recycler_view);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout_op);
        //  skip = view.findViewById(R.id.skip);
        // state = view.findViewById(R.id.state_1);
        //country = view.findViewById(R.id.country_lay);
        // hobli = view.findViewById(R.id.hobli1);
        // hobli_text = view.findViewById(R.id.hob_txt);
        // country_text = view.findViewById(R.id.country_text);
        // district = view.findViewById(R.id.district_1);
        // tehsil = view.findViewById(R.id.tehsil_1);
        // village = view.findViewById(R.id.village_1);
        linearLayout = view.findViewById(R.id.linear_layout);
        toolbar_titletxt=view.findViewById(R.id.toolbar_title);
        home_rd=view.findViewById(R.id.home_rd);
        work_rd=view.findViewById(R.id.work_rd);

        address="Add_new_addresss";
        sessionManager=new SessionManager(getActivity());
        setupUI(linearLayout);

        home_rd.setChecked(true);
        if (home_rd.isChecked()){
            addr_type=1;
        }
        home_rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addr_type=1;

            }
        });
        work_rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addr_type=0;

            }
        });

       /* System.out.println("iddddeditt "+Address_Adapter.edit_addr);
        if (Address_Adapter.edit_addr!=null) {
            System.out.println("enterd");
            name.setText(getArguments().getString("Addr_name"));
            System.out.println("selecteddddd_idddnz" + getArguments().getString("Addr_name"));
            mobile.setText(getArguments().getString("Addr_mobile"));
            pincode_no.setText(getArguments().getString("Addr_pincode"));
            // house_numb.setText(getArguments().getString("Addr_Houseno"));
            house_numb.setText(getArguments().getString("Addr_Street"));
            landmrk.setText(getArguments().getString("Addr_landmark"));
            //city.setText(getArguments().getString("Addr_city"));

            edit_state.setText(getArguments().getString("Addr_state"));
            edit_districr.setText(getArguments().getString("Addr_district"));
            ed_tsl.setText(getArguments().getString("Addr_taluk"));
            // block_txt.setText(getArguments().getString("Addr_hobli"));
            edit_village.setText(getArguments().getString("Addr_village"));
            // address_type.setText(getArguments().getString("Addr_pickup_from"));
            selected_addresstype = getArguments().getBoolean("Addr_pickup_from");
            if (selected_addresstype==true){
                home_rd.setChecked(true);
            }else{
                work_rd.setChecked(true);
            }
        }*/
        name.setFilters(new InputFilter[] { EMOJI_FILTER,new InputFilter.LengthFilter(30)});
        //house_numb.setFilters(new InputFilter[] { EMOJI_FILTER,new InputFilter.LengthFilter(30)});
        // landmrk.setFilters(new InputFilter[] { EMOJI_FILTER,new InputFilter.LengthFilter(50)});
        // street_name.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(50)});

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bundle=getArguments();
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("addr_list", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();

            }
        });
        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            toolbar_titletxt.setText(lngObject.getString("MyAddresses").replace("\n",""));
            home_rd.setText(lngObject.getString("Home").replace("\n",""));
            work_rd.setText(lngObject.getString("Work").replace("\n",""));
            name.setHint(lngObject.getString("Name").replace("\n",""));
            mobile.setHint(lngObject.getString("MobileNumber").replace("\n",""));
            house_numb.setHint(lngObject.getString("Address").replace("\n",""));
            landmrk.setHint(lngObject.getString("Landmark").replace("\n",""));
            edit_state.setHint(lngObject.getString("State").replace("\n",""));
            edit_districr.setHint(lngObject.getString("District").replace("\n",""));
            ed_tsl.setHint(lngObject.getString("Taluk").replace("\n",""));
            edit_village.setHint(lngObject.getString("Village").replace("\n",""));
            pincode_no.setHint(lngObject.getString("Pincode").replace("\n",""));
            save_1.setText(lngObject.getString("PROCEED").replace("\n",""));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        landmrk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // main_layout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // System.out.println(TAG+" s :"+s+ " start :"+start+" Before : "+before+" Count: "+count);
                String str = s.toString();

                if(str.equals(" "))
                {
                    //landmrk.setError("Space is not allowed");
                    landmrk.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                /*if (landmrk.getText().length() > 0)
                    inputLayoutname.setError(null);*/
            }
        });
        house_numb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // main_layout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // System.out.println(TAG+" s :"+s+ " start :"+start+" Before : "+before+" Count: "+count);
                String str = s.toString();

                if(str.equals(" "))
                {
                    //landmrk.setError("Space is not allowed");
                    house_numb.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                /*if (landmrk.getText().length() > 0)
                    inputLayoutname.setError(null);*/
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    System.out.println("lllllllllllllllllllllllll"+getArguments().getString("navigation_from"));
                       /* FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("addr_list", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/

                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();
                    return true;
                }
                return false;
            }
        });


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sorting(s.toString());
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

        ed_state.setOnClickListener(new View.OnClickListener() {
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


                /*StateBean bean=new StateBean("Karnataka","1");
                stateBeanList.add(bean);
                stateBeanList.add(bean);
                stateBeanList.add(bean);
                stateBeanList.add(bean);
                stateBeanList.add(bean);
                stateBeanList.add(bean);
                stateBeanList.add(bean);*/



                prepareStateData();


            }
        });


        ed_dstrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.END);

                search_status="district";
                search.setText("");
                districtBeanList.clear();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                   /* StateBean bean=new StateBean("Karnataka","1");
                   districtBeanList.add(bean);
                   districtBeanList.add(bean);
                   districtBeanList.add(bean);
                   districtBeanList.add(bean);
                   districtBeanList.add(bean);
                   districtBeanList.add(bean);
                   districtBeanList.add(bean);
*/

                prepareDistricData();

            }
        });


        ed_tsl.setOnClickListener(new View.OnClickListener() {
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
                   /* StateBean bean=new StateBean("Karnataka","1");
                    talukBeanList.add(bean);
                    talukBeanList.add(bean);
                    talukBeanList.add(bean);
                    talukBeanList.add(bean);
                    talukBeanList.add(bean);
                    talukBeanList.add(bean);
                    talukBeanList.add(bean);
                    talukAdapter = new TalukAdapter(talukBeanList, getActivity());
                    recyclerView.setAdapter(talukAdapter);*/

                prepareTalukData();

            }
        });

       /* hobli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                drawer.openDrawer(GravityCompat.END);
                search_status = "hobli";
                search.setText("");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                StateBean bean=new StateBean("Karnataka","1");
                talukBeanList.add(bean);
                talukBeanList.add(bean);
                talukBeanList.add(bean);
                talukBeanList.add(bean);
                talukBeanList.add(bean);
                talukBeanList.add(bean);
                talukBeanList.add(bean);
                hoblisAdapter = new HoblisAdapter(hobliBeanList, getActivity());
                recyclerView.setAdapter(hoblisAdapter);
                prepareHobliData();

            }
        });
*/
        ed_vill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                   /* StateBean bean=new StateBean("Karnataka","1");
                    villageBeanList.add(bean);
                    villageBeanList.add(bean);
                    villageBeanList.add(bean);
                    villageBeanList.add(bean);
                    villageBeanList.add(bean);
                    villageBeanList.add(bean);
                    villageBeanList.add(bean);*/


                prepareVillageData();
            }
        });


        save_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  selectedFragment = SelectBankFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("add_addre");
                transaction.commit();
*/
                if(name.getText().toString().equals("")) {
                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter your name",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();




                }else if(mobile.getText().toString().equals("")){
                    int duration = 1000;

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter your mobile number",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();


                }else if(mobile.length()<10){
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Please enter valid mobile number",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();


                }else if(house_numb.getText().toString().equals("")){
                    int duration = 1000;

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter your address",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();

                }else if(landmrk.getText().toString().equals("")){
                    int duration = 1000;

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter your landmark",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();


                }else if(edit_state.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Select State",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();


                }else if(edit_districr.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Select District",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();


                }/*else if(block.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Select Tehsil",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();



                }*//*else if(nyay_panch.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Select NyayPanchayat",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();



                }else if(gram_panch.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Select Tehsil",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();



                }*/else if(pincode_no.length()<6){
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Pincode",duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();


                }
                else {
                   /* selectedFragment = YourAddressList.newInstance();
                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();*/
                    ComposeCategory();

                }

            }
        });



        return view;

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

    private void prepareTalukData() {

        talukBeanList.clear();

        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject jsonpost = new JSONObject();
            jsonObject.put("DistrictId",DistrictAdapter.districtid);
            jsonpost.put("Blockobj",jsonObject);

            System.out.println("disssisiisi  "+jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Taluks, jsonpost, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("aaaaaaaaaaaaafffffffffffff"+result);
                    try{
                        talukBeanList.clear();
                        tal_array = result.getJSONArray("BlockList") ;
                        for(int i=0;i<tal_array.length();i++){
                            JSONObject jsonObject1 = tal_array.getJSONObject(i);
                            stateBean = new StateBean(jsonObject1.getString("BlockName"),jsonObject1.getInt("BlockId"));
                            talukBeanList.add(stateBean);

                        }
                        sorting(talukBeanList);
                        talukAdapter = new TalukAdapter(talukBeanList, getActivity());
                        recyclerView.setAdapter(talukAdapter);
                        talukAdapter.notifyDataSetChanged();
                        // grade_dialog.show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void prepareHobliData() {

        hobliBeanList.clear();

        try{

            final JSONObject jsonObject = new JSONObject();

            JSONObject json_post = new JSONObject();
            jsonObject.put("TalukId",TalukAdapter.talukid);
            json_post.put("Hobliobj",jsonObject);

            System.out.println("hhhhhhhssssskljhgfmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + json_post);


            Crop_Post.crop_posting(getActivity(), Urls.Hoblis, json_post, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("hhhhhhhssssskljhgfmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + result);

                    try{
                        hobliBeanList.clear();
                        hobli_array = result.getJSONArray("HobliList");
                        for(int i = 0;i<hobli_array.length();i++){
                            JSONObject jsonObject3 = hobli_array.getJSONObject(i);
                            stateBean = new StateBean(jsonObject3.getString("Hobli"),jsonObject3.getInt("HobliId"));
                            hobliBeanList.add(stateBean);

                        }
                        sorting(hobliBeanList);

                        hoblisAdapter.notifyDataSetChanged();
                        //  grade_dialog.show();



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void prepareVillageData() {

        villageBeanList.clear();

        try{
            JSONObject jsonObject = new JSONObject();
            JSONObject post_Object = new JSONObject();
            //jsonObject.put("HobliId",hoblisAdapter.hobliid);
            jsonObject.put("BlockId",TalukAdapter.talukid);
            post_Object.put("Villageobj",jsonObject);

            System.out.println("iffffddddd"+post_Object);
            Crop_Post.crop_posting(getActivity(), Urls.Villages, post_Object, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111vvv" + result);

                    try{
                        villageBeanList.clear();
                        village_array = result.getJSONArray("VillageListByBlock");
                        for(int i = 0;i<village_array.length();i++) {
                            JSONObject jsonObject1 = village_array.getJSONObject(i);
                            stateBean = new StateBean(jsonObject1.getString("Village"), jsonObject1.getInt("VillagId"));
                            villageBeanList.add(stateBean);
                        }

                        sorting(villageBeanList);
                        villageAdapter = new VillageAdapter(villageBeanList, getActivity());
                        recyclerView.setAdapter(villageAdapter);
                        villageAdapter.notifyDataSetChanged();
                        //   grade_dialog.show();



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void prepareStateData() {
        stateBeanList.clear();
        Add_NewBankDetails_Fragment.page=null;
      //  AddNewAddressPreview.page=null;


        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_jsonobject = new JSONObject();
            jsonObject.put("CountryId","91");
            post_jsonobject.put("Stateobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.State, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("11111ssss" + result);


                    try{
                        stateBeanList.clear();
                        state_array = result.getJSONArray("StateList");
                        for(int i =0;i<state_array.length();i++){
                            JSONObject jsonObject1 = state_array.getJSONObject(i);

                            stateBean = new StateBean(jsonObject1.getString("State"),jsonObject1.getInt("StateId"));
                            stateBeanList.add(stateBean);
                        }
                        sorting(stateBeanList);
                        stateApdater = new StateApdater(stateBeanList,getActivity());

                        recyclerView.setAdapter(stateApdater);
                        stateApdater.notifyDataSetChanged();
                        grade_dialog.show();



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    private void prepareDistricData() {

        districtBeanList.clear();


        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_jsonobject = new JSONObject();
            jsonObject.put("StateId",stateApdater.stateid);
            post_jsonobject.put("Districtobj",jsonObject);

            System.out.println("podistriiiii"+post_jsonobject);

            Crop_Post.crop_posting(getActivity(), Urls.Districts, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd11111" + result);
                    try{
                        districtBeanList.clear();
                        jsonArray = result.getJSONArray("DistrictList");
                        for(int i =0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            stateBean = new StateBean(jsonObject1.getString("District"),jsonObject1.getInt("DistrictId"));
                            districtBeanList.add(stateBean);
                        }

                        sorting(districtBeanList);
                        districtAdapter= new DistrictAdapter( districtBeanList,getActivity());
                        recyclerView.setAdapter(districtAdapter);

                        districtAdapter.notifyDataSetChanged();
                        grade_dialog.show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void ComposeCategory() {
        try{

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("MobileNo",mobile.getText().toString());
            jsonObject.put("FullName",name.getText().toString());
            jsonObject.put("AddressType",addr_type);
            jsonObject.put("PinCode",pincode_no.getText().toString());
            jsonObject.put("Address",house_numb.getText().toString());
            jsonObject.put("LandMark",landmrk.getText().toString());
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
            jsonObject.put("CustomerLatitude","");
            jsonObject.put("CustomerLongitude","");


            if (StateApdater.stateid==0){
                jsonObject.put("StateId",getArguments().getString("Addr_stateId"));
            }else{
                jsonObject.put("StateId",StateApdater.stateid);
            }
            if (DistrictAdapter.districtid==0){
                jsonObject.put("DistrictId",getArguments().getString("Addr_districtId"));
            }else{
                jsonObject.put("DistrictId",DistrictAdapter.districtid);
            }
            if (TalukAdapter.talukid==0){
                jsonObject.put("BlockId",getArguments().getString("Addr_blockId"));
            }else{
                jsonObject.put("BlockId",TalukAdapter.talukid);
            }
            if (VillageAdapter.villageid==0){
                jsonObject.put("VillageId",getArguments().getString("Addr_villageId"));
            }else{
                jsonObject.put("VillageId", VillageAdapter.villageid);
            }

            if(Address_Adapter.add_id!=null){
                jsonObject.put("UserAddressId",Address_Adapter.add_id);
            }else{
                jsonObject.put("UserAddressId","");

            }

            System.out.println("Add_New_Addressssssssssssssssslllllllllllllllllllllll"+jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Add_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    Bundle bundle=new Bundle();
                    Bundle bundle1=getArguments();
                    System.out.println("dddddAdd_New_Addressssssssssssssssslllllllllllllllllllllll"+result);

                    try{

                        status= result.getString("Status");
                        // message = result.getString("Message");

                     //   bundle.putString("add_id",status);

                     //   bundle.putString("streetname",  DistrictAdapter.district_name);


                        if(!(status.equals("0"))) {

                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Your address added successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                            tv.setTextColor(Color.WHITE);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();
                            selectedFragment = SelectShippingAddress.newInstance();
                            FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout1, selectedFragment);
                            transaction.commit();
                        }else{

                            int duration=1000;
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Your address has not added.",duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();



                        }

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();

        }
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


    public  void sorting(String filter_text){

        final String text = filter_text.toLowerCase();



        if (search_status.equals("state")){
            searchresultAraaylist.clear();
            for (int i = 0; i < stateBeanList.size(); i++) {

                if (stateBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(stateBeanList.get(i));

                }
            }
            stateApdater = new StateApdater(searchresultAraaylist,getActivity());
            recyclerView.setAdapter(stateApdater);
        }
        else if (search_status.equals("district")) {
            searchresultAraaylist.clear();
            for (int i = 0; i < districtBeanList.size(); i++) {

                if (districtBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(districtBeanList.get(i));

                }
            }
            districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
            recyclerView.setAdapter(districtAdapter);


        }
        else if (search_status.equals("taluk")){
            searchresultAraaylist.clear();
            for (int i = 0; i < talukBeanList.size(); i++) {

                if (talukBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(talukBeanList.get(i));

                }
            }
            talukAdapter = new TalukAdapter(searchresultAraaylist, getActivity());
            recyclerView.setAdapter(talukAdapter);
        }

        else if (search_status.equals("village")){
            searchresultAraaylist.clear();
            for (int i = 0; i < hobliBeanList.size(); i++) {

                if (hobliBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(hobliBeanList.get(i));

                }
            }
            hoblisAdapter = new HoblisAdapter(searchresultAraaylist, getActivity());
            recyclerView.setAdapter(hoblisAdapter);
        }
//        else if (search_status.equals("village")){
//            searchresultAraaylist.clear();
//            for (int i = 0; i < villageBeanList.size(); i++) {
//
//                if (villageBeanList.get(i).getName().toLowerCase().contains(text)) {
//                    searchresultAraaylist.add(villageBeanList.get(i));
//
//                }
//            }
////                    villageAdapter = new VillageAdapter(sdearcstateBeanList);
//            recyclerView.setAdapter(villageAdapter);
//        }

    }

    public void setupUI(View view) {


        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
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

            try {
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        }
    }
}
