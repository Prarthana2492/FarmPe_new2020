package com.SevenNine.Essentials.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.SevenNine.Essentials.Adapter.SelectCategoryAdapter;
import com.SevenNine.Essentials.Bean.MainVerticalBean;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.Urls;
import com.SevenNine.Essentials.Volly_class.Crop_Post;
import com.SevenNine.Essentials.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SelectCategoryFragment extends Fragment {

    public static ArrayList<MainVerticalBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static SelectCategoryAdapter livestock_types_adapter;
    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status,sellingTypeId;
    LinearLayout back_feed,linearLayout;
    JSONArray get_categorylist_array;
    JSONArray get_soiltype;
   public static String sellingdetailsid,sellnavigation;

    public static SelectCategoryFragment newInstance() {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.livestock_recy_layout, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
       /* Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottonsheet.setVisibility(View.GONE);
        HomePage_With_Bottom_Navigation.view.setVisibility(View.GONE);*/

        recyclerView=view.findViewById(R.id.recycler_what_looking);
      //  toolbar_title=view.findViewById(R.id.setting_tittle);
     //   back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.linearLayout);
    //    toolbar_title.setText("Select Category");
       // sellingdetailsid=Inventory_Details_Fragment.SId;
      //  System.out.println("selleditiddd"+sellingdetailsid);

        if (getArguments() != null) {
            sellingTypeId= getArguments().getString("sellingTypeId");
        }


      /*  back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });
*/


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();

                    return true;
                }

                return false;
            }
        });


        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*Sellbean bean = new Sellbean("Biscuits","1",R.drawable.biscuits);
        newOrderBeansList.add(bean);
        Sellbean bean1 = new Sellbean("Chips","1",R.drawable.chips);
        newOrderBeansList.add(bean1);
        Sellbean bean2 = new Sellbean("Namkeen","1",R.drawable.namkeen);
        newOrderBeansList.add(bean2);
        Sellbean bean3 = new Sellbean("Snacks","1",R.drawable.snacks_category);
        newOrderBeansList.add(bean3);
        livestock_types_adapter=new SelectCategoryAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(livestock_types_adapter);*/
        try{

            //  newOrderBeansList.clear();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SellingTypeId",sellingTypeId);

            System.out.println("jhfdfdjc111"+jsonObject);
            Crop_Post.crop_posting(getActivity(), Urls.GetSellingCategoryList, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("GetSellingTypeeeeeeee"+result);


                    try{

                        get_soiltype = result.getJSONArray("SellingCategoryList");

                        for(int i=0;i<get_soiltype.length();i++){

                            JSONObject jsonObject1 = get_soiltype.getJSONObject(i);
                            MainVerticalBean sellbean = new MainVerticalBean(jsonObject1.getString("SellingCategoryName"),jsonObject1.getString("SellingCategoryId"),jsonObject1.getString("SellingCategoryIcon"));

                            newOrderBeansList.add(sellbean);
                        }
                        livestock_types_adapter=new SelectCategoryAdapter(getActivity(),newOrderBeansList);
                        recyclerView.setAdapter(livestock_types_adapter);
                        livestock_types_adapter.notifyDataSetChanged();

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
