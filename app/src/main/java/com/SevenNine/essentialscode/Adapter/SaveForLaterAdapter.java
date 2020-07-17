package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.Sellbean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.util.List;

public class SaveForLaterAdapter extends RecyclerView.Adapter<SaveForLaterAdapter.MyViewHolder> {

    private List<Sellbean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String sellingtypeid,cart_prodlistid,prodid,upid,amount,quantity,status;
    SessionManager sessionManager;
    LinearLayout linear_layout;
    private SaveForLaterAdapter.ProductItemActionListener actionListener;


    public SaveForLaterAdapter(Activity activity, List<Sellbean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }
    public void setActionListener(SaveForLaterAdapter.ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,prod_img_fix;
        public LinearLayout item;
        public TextView name,weight,price,actual_price,add_cart,remove,off_text;


        public MyViewHolder(View view) {
            super(view);

            image=view.findViewById(R.id.prod_img);
            prod_img_fix=view.findViewById(R.id.prod_img_fix);
            item=view.findViewById(R.id.item);
            name=view.findViewById(R.id.f_name);
            weight=view.findViewById(R.id.weight);
            price=view.findViewById(R.id.price);
            actual_price=view.findViewById(R.id.actual_price);
            add_cart=view.findViewById(R.id.add_cart);
            remove=view.findViewById(R.id.remove);
            linear_layout=view.findViewById(R.id.linear_layout);
            off_text=view.findViewById(R.id.off_text);

            sessionManager=new SessionManager(activity);

        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.cat_prod_detai_item, parent, false);
        return new MyViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     final Sellbean products = productList.get(position);
      sellingtypeid=products.getId();
        holder.name.setText(products.getName());

      /*if (products.getProd_descr().equals("")){
          holder.name.setText(products.getName()+", "+", "+products.getBrand());
      }else{
          holder.name.setText(products.getName()+", "+products.getProd_descr()+", "+products.getBrand());
      }*/
     // holder.remove.setVisibility(View.VISIBLE);
        holder.weight.setText(products.getWeight()+" "+products.getUom());
        holder.price.setText("Rs "+products.getPrice());
        holder.actual_price.setText("₹"+products.getActual_price());
        holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
      //  CategoryProdDetailList.name.setText(products.getSelling_cat_name());


        if (products.getOfferPrice().equals("0")){
            holder.off_text.setVisibility(View.GONE);
        }else{
            holder.off_text.setVisibility(View.VISIBLE);
            holder.off_text.setText(products.getOfferPrice()+"%"+"\n off");

        }
        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.image);
        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_gallery__default))
                .into(holder.prod_img_fix);
       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = SelectSubCategoryFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("spicescateory");
                transaction.commit();

            }
        });*/

      /* holder.remove.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cart_prodlistid=products.getId();

               try{
                   JSONObject jsonObject  = new JSONObject();
                   jsonObject.put("CartProductListId",cart_prodlistid);
                   jsonObject.put("ProductId",products.getProdId());
                   jsonObject.put("IsShortlisted",0);
                   jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

                   System.out.println("bank_dvvvvetails_iddds"+jsonObject);

                   Crop_Post.crop_posting(activity, Urls.AddUpdateFavouriteCartList, jsonObject, new VoleyJsonObjectCallback() {
                       @Override
                       public void onSuccessResponse(JSONObject result) {
                           System.out.println("111111dddd" + result);

                           try{

                               status = result.getString("Status");

                               if(status.equals("Success")){

                                   productList.remove(position);
                                   notifyDataSetChanged();
                                   System.out.println("jdhjahdjkah"+productList.size());
                                  *//* if (productList.size()==0){
                                       selectedFragment = NoItemsFragment.newInstance();
                                       FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                                       transaction.replace(R.id.frame_layout_home, selectedFragment);
                                       transaction.addToBackStack("cart_detail");
                                       transaction.commit();

                                   }
*//*
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
       });*/
        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodid=products.getProdId();
                upid=products.getUpid();
                amount=products.getPrice();
                quantity=products.getWeight();
                ComposeCategory();
                if(actionListener!=null)
                    actionListener.onItemTap(holder.image);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    private void ComposeCategory() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartProductListId", 0);
            jsonObject.put("ProductId", prodid);
            jsonObject.put("SellingQuantity", quantity);
            jsonObject.put("UnitOfPriceId", 1);
            jsonObject.put("Amount", amount);
            jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            System.out.println("Add_New_AddresssssssssssssssssjsonObject" + jsonObject);

            Crop_Post.crop_posting(activity, Urls.AddUpdateCartProductDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {


                    System.out.println("Add_New_Addressssssssssssssssslllllllllllllllllllllll" + result);
                    try {

                        status = result.getString("Status");
                        // message = result.getString("Message");

                        //   bundle.putString("add_id",status);

                        //   bundle.putString("streetname",  DistrictAdapter.district_name);


                        /*if (status.equals("Success")) {

                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(linear_layout, "Product added successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(activity, R.color.orange));
                            tv.setTextColor(Color.WHITE);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();

                        }*/

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }






}
