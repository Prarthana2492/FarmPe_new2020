
package com.SevenNine.essentialscode.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.ShopByCategoryAdapterNext;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
Fragment selectedFragment;
    public static DrawerLayout drawer;
    public static ImageView cart_icon,search_home;
    LinearLayout menu,prof_tab;
    String userid;
    CircleImageView image_acc;
    TextView home,shop_cat,sell_on,disc_store,my_orders,list_prod,cart,account,buy_again,wishlist,offers;
    public static TextView title;
    public static String shop_cat_id,search_st;
    public static TextView cart_count,user_name_menu;
static boolean fragloaded;
DrawerLayout drawer_layout;
public static RelativeLayout destView;
    boolean doubleBackToExitPressedOnce = false;
    SessionManager sessionManager;

static Fragment myloadingfragment;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_navigation_menu, container, false);

        menu=view.findViewById(R.id.menu);
        home=view.findViewById(R.id.home);
        shop_cat=view.findViewById(R.id.shop_cat);
        my_orders=view.findViewById(R.id.my_orders);
        account=view.findViewById(R.id.account);
        user_name_menu=view.findViewById(R.id.user_name_menu);
        prof_tab=view.findViewById(R.id.prof_tab);
        offers=view.findViewById(R.id.offers);
      //  list_prod=view.findViewById(R.id.list_prod);
        buy_again=view.findViewById(R.id.buy_again);
        wishlist=view.findViewById(R.id.wishlist);
        cart_icon=view.findViewById(R.id.cart_icon);
        title=view.findViewById(R.id.title);
        cart_count=view.findViewById(R.id.cart_count);
        drawer_layout=view.findViewById(R.id.drawer_layout);
       // logout=view.findViewById(R.id.logout);
        destView=view.findViewById(R.id.destView);
        image_acc=view.findViewById(R.id.image_acc);
        sell_on=view.findViewById(R.id.sell_on);
        search_home=view.findViewById(R.id.search);

        sessionManager=new SessionManager(getActivity());

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark1));
        drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        CartCount();
        NavigationView navigationView = (NavigationView)view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (doubleBackToExitPressedOnce) {

                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
                        intent1.addCategory(Intent.CATEGORY_HOME);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent1);
                        getActivity().finish();                   }
                    // System.exit(0);

                    // home_img.setImageResource(R.drawable.ic_home_green);

                    doubleBackToExitPressedOnce = true;

                    Toast toast = Toast.makeText(getActivity(),"Please click back again to exit", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();
                    //   Toast.makeText(getApplicationContext(), toast_click_back, Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 3000);

                }

                return true;
            }
        });
        if (OrderDetailsFragment.order_details!=null){
            selectedFragment = NewOrderFragment.newInstance();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout_home, selectedFragment);
            transaction.addToBackStack("dhsks");
            transaction.commit();
        }else if (ShopByCategoryAdapterNext.shop_cat_home!=null){
            shop_cat_id=getArguments().getString("sellingCatId_shop");
            selectedFragment = CategoryProdDetailList.newInstance();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout_home, selectedFragment);
            transaction.addToBackStack("dhsks");
            transaction.commit();
        }else{
            selectedFragment = DiscoverCategoryFragment.newInstance();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout_home, selectedFragment);
            transaction.addToBackStack("dhsks");
            transaction.commit();
            drawer.closeDrawers();
        }
        search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  search_st="home";
                selectedFragment = CategoryProdDetailListSearch.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("dhskss");
                transaction.commit();
            }
        });
        cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = CartDetailsFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.addToBackStack("dhsks");
                transaction.commit();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = DiscoverCategoryFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });
                account.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = AaSettingFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout1, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });

                my_orders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = NewOrderFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });
                sell_on.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = VerificationFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout1, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });
                wishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = SaveForLaterList.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });
                offers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = OffersListFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });
               /* logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sessionManager.logoutUser();
                        getActivity().finish();
                        drawer.closeDrawers();
                    }
                });
*/
                buy_again.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = BuyAgain.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });
                shop_cat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = ShopByCategoryFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout1, selectedFragment);
                        transaction.addToBackStack("dhsks");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });


            }
        });


        try{
            JSONObject jsonObject = new JSONObject();
            JSONObject post_object = new JSONObject();

            jsonObject.put("Id",sessionManager.getRegId("userId"));
            post_object.put("objUser",jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details1, post_object, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggpgpgpg" + result);

                    try{

                        JSONObject jsonObject1 = result.getJSONObject("user");
                        String ProfileName1 = jsonObject1.getString("UserName");
                        System.out.println("11111" + jsonObject1.getString("FullName"));
                        String ProfilePhone = jsonObject1.getString("PhoneNo");
                        String ProfileEmail = jsonObject1.getString("EmailId");
                        String ProfileImage = jsonObject1.getString("ProfilePic");
                        System.out.println("11111" + ProfileName1);



                        //  name.setText(ProfileName1);
                       // phone_no.setText(ProfilePhone);
                      //  user_name_menu.setText(ProfileName1);

                       // user_name_menu.setFilters(new InputFilter[]{EMOJI_FILTER});
                      //  phone_no.setFilters(new InputFilter[]{EMOJI_FILTER});
                        //profile_mail.setFilters(new InputFilter[]{EMOJI_FILTER});


                        if (!(ProfileImage.equals(""))){
                            Glide.with(getActivity()).load(ProfileImage)

                                    .thumbnail(0.5f)
                                    //  .crossFade()
                                    .error(R.drawable.avatarmale)
                                    .into(image_acc);
                        }else{
                            try {

                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("UserId", sessionManager.getRegId("userId"));


                                Crop_Post.crop_posting(getActivity(), Urls.GetCImagelist, jsonObject, new VoleyJsonObjectCallback() {
                                    @Override
                                    public void onSuccessResponse(JSONObject result) {
                                        System.out.println("dhfjfjd" + result);


                                        try {

                                            JSONArray imagelist_array = result.getJSONArray("captureImagelist");

                                            for (int i = 0; i < imagelist_array.length(); i++) {


                                                JSONObject jsonObject1 = imagelist_array.getJSONObject(i);
                                                String image_view = jsonObject1.getString("Image1");



                                                Glide.with(getActivity()).load(image_view)
                                                        .thumbnail(0.5f)
                                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                        .error(R.drawable.avatarmale)
                                                        .into(image_acc);
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
                       /* Glide.with(getActivity()).load(ProfileImage)

                                .thumbnail(0.5f)
                                //  .crossFade()
                                .error(R.drawable.avatarmale)
                                .into(prod_img);*/


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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
    private void CartCount() {

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            // userRequestjsonObject.put("UserId","1");
            System.out.println("uiuuuuuussseeettttiiinnnngg"+userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.GetReviewCount, userRequestjsonObject, new VoleyJsonObjectCallback() {
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

                            if (Total.equals("0")){
                                cart_count.setVisibility(View.GONE);
                            }else {
                                HomeFragment.cart_count.setText(Total);
                            }

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

}

