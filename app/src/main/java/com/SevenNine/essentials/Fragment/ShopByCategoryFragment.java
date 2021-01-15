package com.SevenNine.essentials.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.Adapter.SelectMainAdapter;
import com.SevenNine.essentials.Adapter.ShopByCategoryAdapterNext;
import com.SevenNine.essentials.Bean.MainAdapterBean1;
import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Adapter.ShopbyCategoryAdapter;
import com.SevenNine.essentials.Bean.MainVerticalBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.Urls;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.SevenNine.essentials.Fragment.DiscoverCategoryFragment.newOrderBeansList_main;


public class ShopByCategoryFragment extends Fragment {

    public static ArrayList<MainVerticalBean> newOrderBeansList = new ArrayList<>();

    public static RecyclerView recyclerView_main,recyc_view1,recy_veg,recy_food_grails,recyclerView_prod,recycler_cooking;
    public static ShopbyCategoryAdapter mainAdapter;
    public static ArrayList<MainVerticalBean> newOrderBeansList1 = new ArrayList<>();
    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;
    boolean doubleBackToExitPressedOnce = false;

    public static ShopByCategoryFragment newInstance() {
        ShopByCategoryFragment fragment = new ShopByCategoryFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_by_cat, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView_main=view.findViewById(R.id.recy_shopby);
     //   recy_veg=view.findViewById(R.id.recy_veg);
       // recy_food_grails=view.findViewById(R.id.recy_food_grails);
        back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.linearLayout);


     //   System.out.println("selleditiddd"+sellingdetailsid);

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("spicescateory");
                transaction.commit();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("spicescateory");
                    transaction.commit();
                }
                return true;
            }
        });
        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_main.setLayoutManager(mLayoutManager_farm1);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        MainVerticalBean beann = new MainVerticalBean("All","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(beann);
        MainVerticalBean bean = new MainVerticalBean("Vegetables","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean);
        MainVerticalBean bean1 = new MainVerticalBean("Fruits","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean1);
        MainVerticalBean bean2 = new MainVerticalBean("Groceries","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean2);
        MainVerticalBean bean3 = new MainVerticalBean("Cooking Oil","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean3);
        MainVerticalBean bean33 = new MainVerticalBean("Masala","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean33);
        MainVerticalBean bean34 = new MainVerticalBean("Masala","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean34);
        MainVerticalBean bean35 = new MainVerticalBean("Masala","1","",R.drawable.food_restaurant);
        newOrderBeansList.add(bean35);
        mainAdapter=new ShopbyCategoryAdapter(getActivity(),newOrderBeansList);
        recyclerView_main.setAdapter(mainAdapter);

        mainAdapter.notifyDataSetChanged();






//        try{
//
//            newOrderBeansList.clear();
//            JSONObject jsonObject = new JSONObject();
//
//
//            Crop_Post.lang_posting(getActivity(), Urls.GetSellingType, jsonObject, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//
//                    System.out.println("GetSellingTypeeeeeeee"+result);
//
//
//                    try{
//
//                        get_soiltype = result.getJSONArray("SellingTypeList");
//
//                        for(int i=0;i<get_soiltype.length();i++){
//
//                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
//                            MainVerticalBean bean = new MainVerticalBean(jsonObject1.getString("SellingTypeName"),jsonObject1.getString("SellingTypeId"),jsonObject1.getString("SellingTypeIcon"));
//                            newOrderBeansList.add(bean);
//                        }
//                        mainAdapter=new ShopbyCategoryAdapter(getActivity(),newOrderBeansList);
//                        recyclerView_main.setAdapter(mainAdapter);
//                        mainAdapter.notifyDataSetChanged();
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return view;
    }



}