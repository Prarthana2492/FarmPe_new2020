package com.SevenNine.essentials.Adapter;

import android.app.Activity;
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

import com.SevenNine.essentials.Bean.Sellbean1;
import com.SevenNine.essentials.Fragment.Top10ProductDetailsPreview;
import com.SevenNine.essentials.Volly_class.Crop_Post;
import com.SevenNine.essentials.Volly_class.VoleyJsonObjectCallback;
import com.SevenNine.essentials.Bean.Sellbean;
import com.SevenNine.essentials.R;
import com.SevenNine.essentials.SessionManager;
import com.SevenNine.essentials.Urls;
import com.SevenNine.essentials.Utils.QuantityPicker;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.util.List;

public class VegSectionAdapter extends RecyclerView.Adapter<VegSectionAdapter.MyViewHolder> {

    private List<Sellbean1> productList;
    Activity activity;
    Fragment selectedFragment;
    private VegSectionAdapter.ProductItemActionListener actionListener;
    SessionManager sessionManager;
    public static String sellingtypeid,sellingedit_id,prodid,upid,amount,quantity,status,prod_name,brand,mrp,offer_price,prod_img;
    int selected_quant;


    public VegSectionAdapter(Activity activity, List<Sellbean1> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }

    public void setActionListener(VegSectionAdapter.ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,prod_img_fix;
        public LinearLayout item,item_click;
        public TextView name,weight,price,actual_price,add_cart,off_text,mrp_text;
        QuantityPicker quantityPicker;

        public MyViewHolder(View view) {
            super(view);

            image=view.findViewById(R.id.prod_img);
            prod_img_fix=view.findViewById(R.id.prod_img_fix);
            item=view.findViewById(R.id.item);
            name=view.findViewById(R.id.f_name);
            weight=view.findViewById(R.id.weight);
            price=view.findViewById(R.id.price);
            actual_price=view.findViewById(R.id.actual_price);
            mrp_text=view.findViewById(R.id.mrp_text);
            add_cart=view.findViewById(R.id.add_cart);
            off_text=view.findViewById(R.id.off_text);
            item_click=view.findViewById(R.id.item_click);
          //  quantityPicker=view.findViewById(R.id.quantityPicker);
            sessionManager=new SessionManager(activity);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.homepage_adapter_layout_hori1, parent, false);
        return new MyViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     final Sellbean1 products = productList.get(position);
      sellingtypeid=products.getId();
       // holder.quantityPicker.setQuantitySelected(1);
       /* holder.quantityPicker.setOnQuantityChangeListener(new QuantityPicker.OnQuantityChangeListener() {
            @Override
            public void onValueChanged(int quantity) {
                if (holder.quantityPicker.getQuantity()<1){
                    holder.quantityPicker.setQuantitySelected(1);
                }
            }
        });*/
        holder.name.setText(products.getName());
        holder.weight.setText(products.getWeight()+" "+products.getUom());
       // holder.price.setText("Rs "+products.getPrice());
       // holder.actual_price.setText("₹"+products.getActual_price());
        //holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (products.getActual_price().equals(products.getPrice())){
            holder.actual_price.setVisibility(View.INVISIBLE);
            holder.mrp_text.setVisibility(View.INVISIBLE);
        }else{
            holder.actual_price.setText("₹"+products.getActual_price());
           // holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.actual_price.setBackground(activity.getResources().getDrawable(R.drawable.line));
        }
//        if (products.getOfferPrice().equals("0")){
//            holder.off_text.setVisibility(View.GONE);
//            holder.price.setText("Rs "+products.getPrice());
//
//        }else{
//            holder.off_text.setVisibility(View.VISIBLE);
//            holder.price.setText("Rs "+products.getOfferPrice());
//
//            //  holder.off_text.setText(products.getOfferPrice()+"%"+"\n off");
//            double off_price_calcu=(((Double.parseDouble(products.getActual_price())-Double.parseDouble(products.getOfferPrice()))/(Double.parseDouble(products.getActual_price())))*100);
//            System.out.println("jhfdiueshfr"+off_price_calcu);
//            int offer_per_int=(int)off_price_calcu;
//            String off_price_text=String.valueOf(offer_per_int);
//            holder.off_text.setText(off_price_text+"%");
//        }
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
                        .error(R.drawable.veg))
                .into(holder.prod_img_fix);
        holder.item_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                prodid=products.getProdId();
//                upid=products.getUpid();
//                amount=products.getPrice();
//                quantity=products.getWeight();
//                prod_name=products.getName();
//                brand=products.getBrand();
//                mrp=products.getActual_price();
//                offer_price=products.getOfferPrice();
//                prod_img=products.getImage();

                selectedFragment = Top10ProductDetailsPreview.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("spicescateory");
                transaction.commit();

            }
        });

        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                prodid=products.getProdId();
//                upid=products.getUpid();
//                amount=products.getPrice();
//                quantity=products.getWeight();
//                prod_name=products.getName();
//                brand=products.getBrand();
//                mrp=products.getActual_price();
//                offer_price=products.getOfferPrice();
//                prod_img=products.getImage();
              //  selected_quant=holder.quantityPicker.getQuantity();
              //  ComposeCategory();
                if(actionListener!=null)
                    actionListener.onItemTap(holder.image);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }




    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }
    private void ComposeCategory() {
        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CartProductListId", 0);
            jsonObject.put("ProductId", prodid);
            jsonObject.put("SellingQuantity", 1);
            // jsonObject.put("SelectedQuantity", selected_quant);
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

}
