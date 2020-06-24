package com.SevenNine.Essentials.Fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.Essentials.Activity.Status_bar_change_singleton;
import com.SevenNine.Essentials.Adapter.StoreAdapter;
import com.SevenNine.Essentials.Bean.StoreListBean;
import com.SevenNine.Essentials.R;
import com.SevenNine.Essentials.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Store extends Fragment {


    public static List<StoreListBean> modelBeanArrayList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static StoreAdapter farmadapter;
    JSONArray model_list_array;


    public static JSONArray tractorImplementsModelMasterList = null;
    public static JSONArray tractorAccessoriesModelMasterList = null;
    public static JSONArray harvesterModelMasterList = null;
    public static JSONArray jCBRFQModelList = null;
    public static JSONArray FarmMachineryModelMasterList = null;
    public static JSONArray FenceWireModelMasterList = null;
    public static JSONArray TyreModelMasterList = null;
    public static JSONArray MiniTruckModelMasterList = null;
    public static JSONArray BackhoeAttachmentModelMasterList = null;
    public static JSONArray PowerTillerModelMasterList = null;


     StoreListBean storeListBean;
     Fragment selectedFragment = null;
     TextView toolbar_title,continue_button,sub_label,toolbar_title1;
     LinearLayout back_feed;
     SessionManager sessionManager;
     public static LinearLayout linearLayout;
     ImageView filter_text;
     public static String text_box;



    public static Store newInstance() {
        Store fragment = new Store();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_model_recy, container, false);


       // Status_bar_change_singleton.getInstance().color_change(getActivity());
        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
       // continue_button=view.findViewById(R.id.continue_button);
        filter_text=view.findViewById(R.id.filter_text);
        linearLayout=view.findViewById(R.id.linearLayout);
      //  sub_label=view.findViewById(R.id.sub_label);
        sessionManager = new SessionManager(getActivity());
        Status_bar_change_singleton.getInstance().color_change(getActivity());

     //   text_box = getArguments().getString("status_home");



     //   b_arrow=view.findViewById(R.id.b_arrow);




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.commit();
            }
        });




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    selectedFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.commit();
                    return true;
                }

                return false;
            }
        });



        filter_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.layout_filterpopup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final TextView rec = (TextView) dialog.findViewById(R.id.recen_added);
                final TextView asce = (TextView)dialog.findViewById(R.id.sort_ascendi) ;
                final TextView desc = (TextView)dialog.findViewById(R.id.sort_desendi) ;
                //   final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;
                LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);


                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });




       // ModelList();
       // newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        modelBeanArrayList.clear();

        StoreListBean bean=new StoreListBean("Raja Rajeshwari Nagar","GSTIN 22AAAAAAA0000A1Z5","560098","Bangalore","");
        modelBeanArrayList.add(bean);
        modelBeanArrayList.add(bean);
        modelBeanArrayList.add(bean);
        modelBeanArrayList.add(bean);
        farmadapter = new StoreAdapter(getActivity(),modelBeanArrayList);
                    recyclerView.setAdapter(farmadapter);


        return view;
    }


   /* private void ModelList() {

        try{

          JSONObject jsonObject = new JSONObject();

            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
            jsonObject.put("LookingForDetailsId",AddBrandFragment.request_looking_id);
            jsonObject.put("BrandId",AddBrandAdapter.brandId);
            System.out.println("fgfggdfcxxg" + jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Model_List, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("fgfggdfcxxg" + result);

                    try{

                        modelBeanArrayList.clear();
                        model_list_array = result.getJSONArray("TractorModelMasterList");
                        tractorImplementsModelMasterList = result.getJSONArray("TractorImplementsModelMasterList");
                        tractorAccessoriesModelMasterList = result.getJSONArray("TractorAccessoriesModelMasterList");
                        harvesterModelMasterList = result.getJSONArray("HarvesterModelMasterList");
                        jCBRFQModelList = result.getJSONArray("JCBModelMasterList");
                        FarmMachineryModelMasterList = result.getJSONArray("FarmMachineryModelMasterList");
                        FenceWireModelMasterList = result.getJSONArray("FenceWireModelMasterList");
                        TyreModelMasterList = result.getJSONArray("TyreModelMasterList");
                        MiniTruckModelMasterList = result.getJSONArray("MiniTruckModelMasterList");
                        BackhoeAttachmentModelMasterList = result.getJSONArray("BackhoeAttachmentModelMasterList");
                        PowerTillerModelMasterList = result.getJSONArray("PowerTillerModelMasterList");



                        for(int i=0;i<model_list_array.length();i++){


                            JSONObject jsonObject1 = model_list_array.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);

                        }

                        System.out.println("fgfggdfcxxggggggggggggggggggggggggggg" + tractorAccessoriesModelMasterList);


                        for(int i=0;i<tractorImplementsModelMasterList.length();i++){
                            JSONObject jsonObject1 = tractorImplementsModelMasterList.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);


                        }   for(int i=0;i<tractorAccessoriesModelMasterList.length();i++){
                            JSONObject jsonObject1 = tractorAccessoriesModelMasterList.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);



                        }    for(int i=0;i<harvesterModelMasterList.length();i++){
                             JSONObject jsonObject1 = harvesterModelMasterList.getJSONObject(i);
                             modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                             modelBeanArrayList.add(modelBean);



                        }       for(int i=0;i<jCBRFQModelList.length();i++){
                                JSONObject jsonObject1 = jCBRFQModelList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);




                        }   for(int i=0;i<FarmMachineryModelMasterList.length();i++){
                            JSONObject jsonObject1 = FarmMachineryModelMasterList.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);



                       }
                                   for(int i=0;i<FenceWireModelMasterList.length();i++){
                                   JSONObject jsonObject1 = FenceWireModelMasterList.getJSONObject(i);
                                   modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                   modelBeanArrayList.add(modelBean);

                        }
                                  for(int i=0;i<TyreModelMasterList.length();i++){
                                  JSONObject jsonObject1 = TyreModelMasterList.getJSONObject(i);
                                  modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                  modelBeanArrayList.add(modelBean);

                        }

                                for(int i=0;i<MiniTruckModelMasterList.length();i++){
                                JSONObject jsonObject1 = MiniTruckModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                        }

                                for(int i=0;i<BackhoeAttachmentModelMasterList.length();i++){
                                JSONObject jsonObject1 = BackhoeAttachmentModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                        }

                                for(int i=0;i<PowerTillerModelMasterList.length();i++){
                                JSONObject jsonObject1 = PowerTillerModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                        }


                                farmadapter.notifyDataSetChanged();

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }

    }
*/

}
