package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SevenNine.essentialscode.Bean.Sellbean;
import com.SevenNine.essentialscode.Fragment.HomeFragment;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class VegSectionAdapter1 extends RecyclerView.Adapter<VegSectionAdapter1.MyViewHolder> {

    private List<Sellbean> productList;
    Activity activity;
    Fragment selectedFragment;
    LinearLayout linear_layout;
    public static String sellingtypeid,sellingedit_id,prodid,upid,amount,quantity,status;
    SessionManager sessionManager;
    private ProductItemActionListener actionListener;


    public VegSectionAdapter1(Activity activity, List<Sellbean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }

    public void setActionListener(ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,prod_img_fix;
        public LinearLayout item;
        public TextView name,weight,price,actual_price,add_cart;


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
            linear_layout=view.findViewById(R.id.linear_layout);

            sessionManager=new SessionManager(activity);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.homepage_adapter_layout_hori, parent, false);
        return new MyViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Sellbean products = productList.get(position);
        sellingtypeid=products.getId();
        // holder.name.setText("Organic "+products.getName()+"-Gov.Gaurder");
        holder.name.setText(products.getName()+", "+products.getProd_descr());
        holder.weight.setText(products.getWeight()+" "+products.getUom());
        holder.price.setText("₹"+products.getPrice());
        holder.actual_price.setText(products.getActual_price());
        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.veg))
                .into(holder.image);
        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                //.crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.watermelon))
                .into(holder.prod_img_fix);
       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (products.getId().equals("123")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("sellingcatId", products.getId());
                    selectedFragment = CategoryProdDetailList.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_home, selectedFragment);
                    transaction.addToBackStack("spicescateory");
                    selectedFragment.setArguments(bundle);
                    transaction.commit();
                }else{

                }
            }
        });
*/ holder.add_cart.setOnClickListener(new View.OnClickListener() {
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

   /* public void blink(View view){
        Animation animation1 =
                AnimationUtils.loadAnimation(activity.getApplicationContext(),
                        R.anim.blink);
        HomeFragment.cart_count.startAnimation(animation1);
    }*/

    private void ComposeCategory() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartProductListId", 0);
            jsonObject.put("ProductId", prodid);
            jsonObject.put("SellingQuantity", quantity);
            jsonObject.put("UnitOfPriceId", upid);
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


                        if (status.equals("Success")) {



                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
    private void CartCount() {

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            // userRequestjsonObject.put("UserId","1");
            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);

            Login_post.login_posting(activity, Urls.GetReviewCount, userRequestjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss000lll" + result);
                    JSONArray jsonArray = new JSONArray();

                    try {

                        jsonArray = result.getJSONArray("ReviewListCount");
                        System.out.println("jsonarraylength"+jsonArray.length());


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            String Total = jsonObject1.getString("Total");

                            HomeFragment.cart_count.setVisibility(View.VISIBLE);
                            //   HomeFragment.cart_count.setText(Total);

                                HomeFragment.cart_count.setText(Total);



                           /* Animation animation1 =
                                    AnimationUtils.loadAnimation(activity.getApplicationContext(),
                                            R.anim.blink);
                            HomeFragment.cart_count.startAnimation(animation1);*/
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



                        }
                    } catch (JSONException e) {
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
