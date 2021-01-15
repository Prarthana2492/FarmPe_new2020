package com.SevenNine.essentials.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentials.Bean.SelectLanguageBean;
import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Activity.ActivitySelectLang;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.util.List;


public class AdapterSelectLanguage extends RecyclerView.Adapter<AdapterSelectLanguage.MyViewHolder>  {

    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    String lng_list;



    public static int selected_position=0;



    public static CardView cardView;
    public AdapterSelectLanguage(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager = new SessionManager(activity);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView lang_text;
        public ImageView tick_image;
        public LinearLayout language;
        public ImageView lang_icon;
//        public RadioButton lang_txt;


        public MyViewHolder(View view) {
            super(view);

            lang_text = view.findViewById(R.id.lang_text);
            language = view.findViewById(R.id.main_layout);
            tick_image = view.findViewById(R.id.tick_image);
            lang_icon = view.findViewById(R.id.lang_icon);
//            lang_txt = view.findViewById(R.id.radioButton1);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lang_test_layout, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products1 = productList.get(position);


        holder.lang_text.setText(products1.getVendor());
        lng_list = products1.getVendor();

        if (lng_list.equals(sessionManager.getRegId("language_name"))) {
            holder.tick_image.setImageResource(R.drawable.ic_verified_green);
            holder.lang_text.setTypeface(null, Typeface.BOLD);

        } else {
            System.out.println("sfdsdfsdxvvvv" + sessionManager.getRegId("language_name"));

            if ((sessionManager.getRegId("language_name").equals(""))) {
                if (position == 0) {
                    holder.tick_image.setImageResource(R.drawable.ic_verified_green);
                    holder.lang_text.setTypeface(null, Typeface.BOLD);
                }

            } else {

                holder.tick_image.setImageResource(R.drawable.ic_verified_grey);
                holder.lang_text.setTypeface(null, Typeface.NORMAL);
            }
        }


        System.out.println("iiiddddddmmmmmmmmmmmmmmmmm" + products1.getLanguageid());


//        if (sessionManager.getRegId("language_name").equals(products1.getVendor())){
//            // holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
//            //  holder.lng_rad_but.setBackgroundColor(Color.GREEN);
//            holder.right_img.setVisibility(View.VISIBLE);
//
//        }else {
//
//            holder.right_img.setVisibility(View.GONE);
//
//            //// holder.right_img.setImageResource(R.drawable.filled_grey_circle);
//
////            holder.right_img.setImageResource(R.drawable.v);
//
//            //  holder.lng_rad_but.setBackgroundColor(Color.WHITE);
//
//
//        }
//
//

        Glide.with(activity).load(products1.getImageicon())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_alphabet_a))
                .into(holder.lang_icon);


        holder.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("iiiddddddkkkkkkkkkkkkkkkkkkkkkkkkkkk" + products1.getLanguageid());

                sessionManager.saveLanguage_name(products1.getVendor());
                getLang(products1.getLanguageid());
                lng_list = products1.getVendor();
                selected_position = position;
                notifyDataSetChanged();

            }
        });
    }


    private void getLang(int id) {

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", id);


            System.out.print("iiidddddd" + id);

            Crop_Post.lang_posting(activity, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvvhhhhhhhhhhhh" + result);


                    try {

                        sessionManager.saveLanguage(result.toString());
                        String select_title = result.getString("SelectYourLanguage").replace("\n", "");
                        String prcd = result.getString("PROCEED").replace("\n", "");
                        ActivitySelectLang.toast_internet = result.getString("GoodConnectedtoInternet");
                        ActivitySelectLang.toast_nointernet = result.getString("NoInternetConnection");

                        ActivitySelectLang.select_ur_language.setText(select_title);
                        ActivitySelectLang.continue_lng1.setText(prcd);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
}