package com.SevenNine.essentials.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.Bean.AddTractorBean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    TextView addfarm,addnewfarms;
    Fragment selectedFragment = null;
    RecyclerView recyclerView;
    public static ArrayList<AddTractorBean> newOrderBeansList = new ArrayList<>();
    AddTractorBean addTractorBean;
    JSONArray get_array;
    SessionManager sessionManager;
    LinearLayout no_farms,farms;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                dismiss();
            }
        }
        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.my_qa, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();

        addfarm=contentView.findViewById(R.id.addnewfarms);
       // addnewfarms=contentView.findViewById(R.id.addnewfarms1);
       // no_farms=contentView.findViewById(R.id.no_requests);
       // farms=contentView.findViewById(R.id.farms);
      //  recyclerView=contentView.findViewById(R.id.recycler_farms);

        addfarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  selectedFragment = FarmerDetailsFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("about");
                transaction.commit();
                dialog.dismiss();*/
            }
        });
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }


       /* addnewfarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = Camera_Fragment2.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("about");
                transaction.commit();
            }
        });


        // newOrderBeansList.clear();
        addingFarms_adapter=new AddingFarms_Adapter(getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(addingFarms_adapter);

        try{
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.GetAddNewFarms, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggggggggggaaaaaaa"+result);
                    try{
                        newOrderBeansList.clear();


                        get_array = result.getJSONArray("FarmNameList");




                        for(int i=0;i<get_array.length();i++){
                            JSONObject jsonObject1 = get_array.getJSONObject(i);
//
                            addTractorBean = new AddTractorBean(jsonObject1.getString("FarmPhoto"),jsonObject1.getString("Name"),jsonObject1.getString("Id"));

                            newOrderBeansList.add(addTractorBean);

                        }

                        if(newOrderBeansList.isEmpty()){
                            Toast.makeText(getActivity(), "dfgdfgdfg", Toast.LENGTH_SHORT).show();
                            no_farms.setVisibility(View.VISIBLE);
                            farms.setVisibility(View.GONE);
                        }else {
                            farms.setVisibility(View.VISIBLE);
                            no_farms.setVisibility(View.GONE);

                        }
                        addingFarms_adapter.notifyDataSetChanged();





                    }catch (Exception e){
                        e.printStackTrace();
                    }




                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }*/





    }
}