package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.SelectLanguageBean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class SelectLocationAdapter extends RecyclerView.Adapter<SelectLocationAdapter.MyViewHolder>  {

    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    String lng_list;



    public static int selected_position,storeid=0;



    public static CardView cardView;
    public SelectLocationAdapter(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager = new SessionManager(activity);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView lang_text;
        public ImageView tick_image;
        public LinearLayout language;
       public ImageView lang_icon;
       CircleImageView image_acc;
//        public RadioButton lang_txt;


        public MyViewHolder(View view) {
            super(view);

            lang_text=view.findViewById(R.id.lang_text);
            language=view.findViewById(R.id.main_layout);
            tick_image=view.findViewById(R.id.tick_image);
            image_acc=view.findViewById(R.id.image_acc);
          //  lang_icon = view.findViewById(R.id.lang_icon);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_loc_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products1 = productList.get(position);


        holder.lang_text.setText(products1.getVendor());
      //  holder.lang_letter.setText(products1.getLang_letter());
        lng_list = products1.getVendor();





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

       /* Glide.with(activity).load(R.drawable.shrungagiritower)
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.shrungagiritower))
                .into(holder.image_acc);*/


        Glide.with(activity).load(R.drawable.shrungagiritower)

                .thumbnail(0.5f)
                //  .crossFade()
                .error(R.drawable.shrungagiritower)
                .into(holder.image_acc);


        holder.language.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("iiiddddddkkkkkkkkkkkkkkkkkkkkkkkkkkk" + products1.getLanguageid());

          //  sessionManager.saveLanguage_name(products1.getVendor());
           // getLang(products1.getLanguageid());
            lng_list = products1.getVendor();
            storeid=products1.getLanguageid();
            selected_position = position;
            if (selected_position==storeid) {
                holder.tick_image.setImageResource(R.drawable.ic_verified_green);

            } else{

                holder.tick_image.setImageResource(R.drawable.ic_verified_grey);
                //  holder.lang_text.setTypeface(null, Typeface.NORMAL);
            }
            selected_position=0;
            notifyDataSetChanged();

        }
    });
  }


    private void getLang(int id) {

        try{

              JSONObject jsonObject = new JSONObject();
              jsonObject.put("Id",id);


               System.out.print("iiidddddd"+ id);

               Crop_Post.crop_posting(activity, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvvhhhhhhhhhhhh" + result);



                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}