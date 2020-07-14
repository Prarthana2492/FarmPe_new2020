package com.SevenNine.essentialscode.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.MainCategoryAdapter;
import com.SevenNine.essentialscode.Adapter.SelectMainAdapter;
import com.SevenNine.essentialscode.Adapter.VegSectionAdapter;
import com.SevenNine.essentialscode.Bean.MainVerticalBean;
import com.SevenNine.essentialscode.Bean.Sellbean;
import com.SevenNine.essentialscode.Bean.Sellbean1;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiscoverCategoryFragment extends Fragment {

    public static ArrayList<MainVerticalBean> newOrderBeansList = new ArrayList<>();
    public static ArrayList<Sellbean1> newOrderBeansList_main = new ArrayList<>();
    public static ArrayList<Sellbean1> newOrderBeansList_subcat = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_prod = new ArrayList<>();
    public static ArrayList<Sellbean> newOrderBeansList_cooking = new ArrayList<>();
    public static RecyclerView recyclerView_main,recy_veg,recy_food_grails,recyclerView_prod,recycler_cooking;
    public static MainCategoryAdapter livestock_types_adapter;
    public static VegSectionAdapter vegAdapter;
    public static SelectMainAdapter mainAdapter;
    Fragment selectedFragment = null;
    TextView toolbar_title;
    TextView search_home;
    public static String livestock_status,search_st;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;
    boolean doubleBackToExitPressedOnce = false;

    public static DiscoverCategoryFragment newInstance() {
        DiscoverCategoryFragment fragment = new DiscoverCategoryFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_by_cate_main, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView_main=view.findViewById(R.id.recycler_main);
        recy_veg=view.findViewById(R.id.recy_veg);
        recy_food_grails=view.findViewById(R.id.recy_food_grails);
        search_home=view.findViewById(R.id.search_home);
        /*recyclerView_prod=view.findViewById(R.id.recycler_prod);
        recycler_cooking=view.findViewById(R.id.recycler_cooking);*/
      //  toolbar_title=view.findViewById(R.id.setting_tittle);
     //   back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.linearLayout);
//        toolbar_title.setText("Select Category");
       // sellingdetailsid=Inventory_Details_Fragment.SId;
        System.out.println("selleditiddd"+sellingdetailsid);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (doubleBackToExitPressedOnce) {

                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
                        intent1.addCategory(Intent.CATEGORY_HOME);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent1);
                        getActivity().finish();                   }
                    // System.exit(0);

                    // home_img.setImageResource(R.drawable.ic_home_green);

                    doubleBackToExitPressedOnce = true;

                    Toast toast = Toast.makeText(getActivity(),"Please click back again to exit", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();
                    //   Toast.makeText(getApplicationContext(), toast_click_back, Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 3000);

                }

                return true;
            }
        });

        search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_st="home";
                selectedFragment = CategoryProdDetailListSearch.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("dhskss");
                transaction.commit();
            }
        });

        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm1);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
       /* MainAdapterBean1 beann = new MainAdapterBean1("All","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(beann);
        MainAdapterBean1 bean = new MainAdapterBean1("Vegetables","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean);
        MainAdapterBean1 bean1 = new MainAdapterBean1("Fruits","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean1);
        MainAdapterBean1 bean2 = new MainAdapterBean1("Groceries","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean2);
        MainAdapterBean1 bean3 = new MainAdapterBean1("Cooking Oil","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean3);
        MainAdapterBean1 bean33 = new MainAdapterBean1("Masala","1",R.drawable.food_restaurant);
        newOrderBeansList_main.add(bean33);
        mainAdapter=new SelectMainAdapter(getActivity(),newOrderBeansList_main);
        recyclerView_main.setAdapter(mainAdapter);*/

        newOrderBeansList_subcat.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
       recy_food_grails.setLayoutManager(mLayoutManager_farm);
        recy_food_grails.setItemAnimator(new DefaultItemAnimator());
        Sellbean1 bean8 = new Sellbean1("Daawat Rozana\nGold Basmati Rice\n(Medium Grain)","1", R.drawable.veg,"(5 kg)","₹100","₹120","");
        newOrderBeansList_subcat.add(bean8);
        Sellbean1 bean9 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_subcat.add(bean9);
        Sellbean1 bean10 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_subcat.add(bean10);
            /*Sellbean1 bean111 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1",R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean111);*/
        vegAdapter = new VegSectionAdapter(getActivity(), newOrderBeansList_subcat);
        recy_food_grails.setAdapter(vegAdapter);

        newOrderBeansList_main.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm2 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recy_veg.setLayoutManager(mLayoutManager_farm2);
        recy_veg.setItemAnimator(new DefaultItemAnimator());
        Sellbean1 bean11 = new Sellbean1("Fresh Onion, Organic\nGoverment Gaurden","1", R.drawable.veg,"(5 kg)","₹100","₹120","");
        newOrderBeansList_main.add(bean11);
        Sellbean1 bean12 = new Sellbean1("Fresh Onion, Organic\nGoverment Gaurden","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_main.add(bean12);
        Sellbean1 bean13 = new Sellbean1("Fresh Onion, Organic\nGoverment Gaurden","1", R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
        newOrderBeansList_main.add(bean13);
            /*Sellbean1 bean111 = new Sellbean1("Exo Touch & Shine\nRound Dishwash\nBar","1",R.drawable.veg,"(1.4 kg, Pack of 2)","₹100","₹120","");
            newOrderBeansList_subcat.add(bean111);*/
        vegAdapter = new VegSectionAdapter(getActivity(), newOrderBeansList_main);
        recy_veg.setAdapter(vegAdapter);

        try{

            newOrderBeansList.clear();
            JSONObject jsonObject = new JSONObject();


            Crop_Post.crop_posting(getActivity(), Urls.GetSellingType, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("SellingTypeList");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            MainVerticalBean bean = new MainVerticalBean(jsonObject1.getString("SellingTypeName"),jsonObject1.getString("SellingTypeId"),jsonObject1.getString("SellingTypeIcon"));
                            newOrderBeansList.add(bean);
                        }
                        mainAdapter=new SelectMainAdapter(getActivity(),newOrderBeansList);
                        recyclerView_main.setAdapter(mainAdapter);
                        mainAdapter.notifyDataSetChanged();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }

}
