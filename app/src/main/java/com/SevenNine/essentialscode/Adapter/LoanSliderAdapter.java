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

import com.SevenNine.essentialscode.Bean.ListBean2;
import com.SevenNine.essentialscode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class LoanSliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<ListBean2> productList;
    Activity activity;
    Fragment selectedFragment;

    public static CardView cardView;
    public LoanSliderAdapter(Activity activity, List<ListBean2> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView loan_text;
        private LinearLayout loan_lay;
        private ImageView loan_img;


        public MyViewHolder(View view) {
            super(view);
            loan_text=view.findViewById(R.id.loan_text);
            loan_img=view.findViewById(R.id.loan_img);
            loan_lay=view.findViewById(R.id.loan_lay);
            // maore_lay=view.findViewById(R.id.maore_lay);
           // LandingPageActivity.loanid=0;

        }

    }



    public class MyViewHolderForMore extends RecyclerView.ViewHolder {

        private TextView morecount;

        public MyViewHolderForMore(View itemView) {
            super(itemView);
          //  morecount= itemView.findViewById(R.id.morecount);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if(viewType==0) {
            System.out.println("itemmmmmmm");

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loan_item, parent, false);

            //  int width =  parent.getMeasuredWidth();
            float height = (float) parent.getMeasuredHeight() /4;//(Width/Height)
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.height = Math.round(height);
            itemView.setLayoutParams(params);

            return new MyViewHolder(itemView);
        }
        else{
            System.out.println("moreeeee");
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loan_item_more, parent, false);
           /* int height = parent.getMeasuredHeight() / 3;
            itemView.setMinimumHeight(height);*/
            float height = (float) parent.getMeasuredHeight() /4;//(Width/Height)
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.height = Math.round(height);
            return new MyViewHolderForMore(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(holder.getItemViewType()==0) {
            MyViewHolder viewHolder0 = (MyViewHolder)holder;
            final ListBean2 products1 = productList.get(position);

           /* DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width_px = Resources.getSystem().getDisplayMetrics().widthPixels;
            int height_px =Resources.getSystem().getDisplayMetrics().heightPixels;
            int height_set=(int)(height_px*0.1);
            System.out.println("height&Width"+width_px+","+height_px);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width_px,height_set);
             viewHolder0.loan_lay.setLayoutParams(parms);*/
            viewHolder0.loan_text.setText(products1.getName());
            Glide.with(activity).load(products1.getImage())
                    .thumbnail(0.5f)
                    //.crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                   // .error(R.drawable.producemarketing)
                    .into(viewHolder0.loan_img);

            ((MyViewHolder) holder).loan_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*LandingPageActivity.loan_type_name=products1.getName();
                    System.out.println("loantypeee"+ LandingPageActivity.loan_type_name);
                    selectedFragment = FindBankOffers.newInstance();*/
                   /* switch (products1.getName()) {
                        case "Crop Loan":
                            selectedFragment = CropLoanDetails.newInstance();
                            break;
                        case "Kisan Credit Card":
                            selectedFragment = KisanCreditCard.newInstance();
                            break;

                        case "Vehicles Loan":
                            selectedFragment = CommercialVehicles.newInstance();
                            break;
                        case "Tractor Loan":
                            selectedFragment = LoanDetails1Fragment.newInstance();
                            break;
                        case "Assets Backed Loan":
                            selectedFragment = AssetsBackedLoan.newInstance();
                            break;
                        case "Produce Marketing":
                            selectedFragment = ProduceMarketingLoan.newInstance();
                            break;
                        case "Dairy Loan":
                            selectedFragment = DairyLoan.newInstance();
                            break;
                        case "Poultry Loan":
                            selectedFragment = PoultryLoan.newInstance();
                            break;
                        case "Combine Harvester":
                            selectedFragment = CombineHorvester.newInstance();
                            break;
                        case "Power Tiller":
                            selectedFragment = PowerTiller.newInstance();
                            break;
                        case "Gold Loan":
                            selectedFragment = GoldLoan.newInstance();
                            break;
                        case "Ware House":
                            selectedFragment = Warehouse.newInstance();
                            break;
                        case "Commercial Vehicles":
                            selectedFragment = CommercialVehicles.newInstance();
                            break;
                        case "Backhoe Loader":
                            selectedFragment = BackhoeLoader.newInstance();
                            break;
                        case "Pump set":
                            selectedFragment = PumpSet.newInstance();
                            break;
                    }*/
                   /* LandingPageActivity.loanid=products1.getLoanid();
                    LandingPageActivity.categoryid=products1.getId();
                    LandingPageActivity.loan_type_name =products1.getName();
*/
                   /* FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("lonssssoffers");
                    transaction.commit();*/
                }
            });



            /*if (products1.getName().equals("Crop Loan")) {
                viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoanAdapter.loan=null;
                        selectedFragment = CropLoanDetails.newInstance();
                        FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("home");
                        transaction.commit();
                    }
                });
            }else if (products1.getName().equals("Kisan Credit Card")){
                viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoanAdapter.kisan=null;
                        selectedFragment = KisanCreditCard.newInstance();
                        FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("home");
                        transaction.commit();
                    }
                });
            }*/

           /* viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedFragment = LoansListFragment.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("home");
                    transaction.commit();
                }
            });*/

           System.out.println("immmmmmmm "+products1.getImage());




          /*  Glide.with(activity).load(products1.getImage())

                    .thumbnail(0.5f)
                    //.crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder0.loan_img);*/
        }
        else{
            MyViewHolderForMore viewHolder2 = (MyViewHolderForMore)holder;
         //   viewHolder2.morecount.setText("+"+SliderPagerAdapter.morecount);

           /* viewHolder2.morecount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedFragment = LoansListFragment.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("home");
                    transaction.commit();
                }
            });*/

        }

    }

    @Override
    public int getItemViewType(int position) {

        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        System.out.println("jhdjsk"+position+" "+SliderPagerAdapter.morecount);
        if (position==11 & SliderPagerAdapter.morecount!=0) {
            System.out.println("my pos "+SliderPagerAdapter.morecount);
            return 1;
        }
        else return 0;
        //  return position % 2 * 2;
    }






    @Override
    public int getItemCount() {
        if(SliderPagerAdapter.morecount!=0)
            return productList.size()+1;
        else
            return productList.size();
    }
}