package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.InventoryBean;
import com.SevenNine.essentialscode.Fragment.HomeFragment;
import com.SevenNine.essentialscode.Fragment.OfferPreviewDetails;
import com.SevenNine.essentialscode.Fragment.What_Are_looking;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private List<InventoryBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    LinearLayout linear_layout;
    public static String offer;
    public static String sellingtypeid,sellingedit_id,prodid,upid,amount,quantity,status,prod_name,brand,mrp,offer_price,prod_img;


    public OffersAdapter(Activity activity, List<InventoryBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,prod_img_fix;
        public LinearLayout item;
        public TextView name,weight,price,actual_price,edit,delete,off_text,mrp_text;


        public MyViewHolder(View view) {
            super(view);

            prod_img_fix=view.findViewById(R.id.prod_img_fix);
            item=view.findViewById(R.id.item);
            name=view.findViewById(R.id.f_name);
            weight=view.findViewById(R.id.weight);
            price=view.findViewById(R.id.price);
            actual_price=view.findViewById(R.id.actual_price);
          //  edit=view.findViewById(R.id.edit);
          //  delete=view.findViewById(R.id.delete);
            off_text=view.findViewById(R.id.off_text);
            mrp_text=view.findViewById(R.id.mrp_text);
            linear_layout=view.findViewById(R.id.linear_layout);

            sessionManager=new SessionManager(activity);

        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.offer_list_item, parent, false);
        return new MyViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     final InventoryBean products = productList.get(position);

        holder.name.setText(products.getProd_name());
        holder.weight.setText(products.getQuantity()+" Kg");
        holder.price.setText("Rs "+products.getOffer_price());
        if (products.getMrp().equals(products.getAmount())){
            holder.actual_price.setVisibility(View.INVISIBLE);
            holder.mrp_text.setVisibility(View.INVISIBLE);
        }else{
            holder.actual_price.setText("₹"+products.getMrp());
            holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
      //  holder.actual_price.setText("₹"+products.getMrp());
       // holder.off_text.setText(products.getOffer_price()+"%"+"\n off");
        double off_price_calcu=(((Double.parseDouble(products.getMrp())-Double.parseDouble(products.getOffer_price()))/(Double.parseDouble(products.getMrp())))*100);
        System.out.println("jhfdiueshfr"+off_price_calcu);
        int offer_per_int=(int)off_price_calcu;
        String off_price_text=String.valueOf(offer_per_int);
        holder.off_text.setText(off_price_text+"%");
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer="true";
                prodid=products.getProd_desc();
                amount=products.getAmount();
                quantity=products.getQuantity();
                prod_name=products.getProd_name();
                brand=products.getBrand();
                mrp=products.getMrp();
                offer_price=products.getOffer_price();
                prod_img=products.getProd_icon();
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("spicescateorye");
                transaction.commit();
            }
        });

      //  holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

       /* holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductId=products.getProd_desc();

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.delete_details_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                TextView ok=dialog.findViewById(R.id.ok);
                TextView cancel=dialog.findViewById(R.id.cancel);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("ProductId",ProductId);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            System.out.println("bank_dvvvvetails_iddd"+jsonObject);

                            Crop_Post.crop_posting(activity, Urls.DeleteProductDetails, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");

                                        if(status.equals("1")){

                                            productList.remove(position);
                                            notifyDataSetChanged();
                                            System.out.println("jdhjahdjkah"+productList.size());

                                        }

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });
*/
        Glide.with(activity).load(products.getProd_icon())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.prod_img_fix);
      /*  holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prod_name=products.getProd_name();
                brand=products.getBrand();
                quantity=products.getQuantity();
                amount=products.getAmount();
                mrp=products.getMrp();
                prod_id=products.getProd_desc();

                selectedFragment = What_Are_looking.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("spicescateorye");
                transaction.commit();

            }
        });

*/
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}
