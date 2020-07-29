package com.SevenNine.essentialscode.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.SevenNine.essentialscode.Adapter.CartDetailsAdapter;
import com.SevenNine.essentialscode.Adapter.OrderDetailsAdapter;
import com.SevenNine.essentialscode.Bean.OrderDetailBean;
import com.SevenNine.essentialscode.R;
import com.SevenNine.essentialscode.SessionManager;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Login_post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SelectPaymentMethod extends Fragment {

    public static List<OrderDetailBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed,buy_btn;
    SessionManager sessionManager;
    OrderDetailsAdapter madapter;
    JSONObject lngObject;
    TextView toolbar_title,online_payment,cod;
    Fragment selectedFragment;
    RadioButton upi_radio,hdfc_radio,icici_radio,payu;
    CheckBox wallet_check;
    String btn_status;
    Double strtext;

    public static SelectPaymentMethod newInstance() {
        SelectPaymentMethod fragment = new SelectPaymentMethod();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_method_main, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        online_payment=view.findViewById(R.id.online_pay);
        cod=view.findViewById(R.id.cod);
        sessionManager=new SessionManager(getActivity());

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));
    //    strtext = getArguments().getDouble("pricestrtext2");
     //   strtext = Double.parseDouble("1");

       /* wallet_check=view.findViewById(R.id.wallet_check);
        upi_radio=view.findViewById(R.id.upi_radio);
        hdfc_radio=view.findViewById(R.id.hdfc_radio);
        icici_radio=view.findViewById(R.id.icic_radio);
        buy_btn=view.findViewById(R.id.buy_btn);
        payu=view.findViewById(R.id.payu);*/

/*

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
*/
 /* wallet_check.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          upi_radio.setChecked(false);
          hdfc_radio.setChecked(false);
          icici_radio.setChecked(false);
          payu.setChecked(false);
          wallet_check.setChecked(true);
          buy_btn.setBackgroundResource(R.drawable.border_grey_filled_nob);
      }
  });
        hdfc_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upi_radio.setChecked(false);
                wallet_check.setChecked(false);
                icici_radio.setChecked(false);
                payu.setChecked(false);
                hdfc_radio.setChecked(true);
                buy_btn.setBackgroundResource(R.drawable.border_grey_filled_nob);

            }
        });
        icici_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upi_radio.setChecked(false);
                hdfc_radio.setChecked(false);
                wallet_check.setChecked(false);
                payu.setChecked(false);
                icici_radio.setChecked(true);
                buy_btn.setBackgroundResource(R.drawable.border_grey_filled_nob);

            }
        });
        upi_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upi_radio.setChecked(true);
                hdfc_radio.setChecked(false);
                wallet_check.setChecked(false);
                icici_radio.setChecked(false);
                payu.setVisibility(View.VISIBLE);



            }
        });
        payu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_status="1";
                buy_btn.setBackgroundResource(R.drawable.border_filled_red_not_curved);

            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_status.equals("1")){
                    Bundle bundle = new Bundle();
                    bundle.putDouble("pricestrtext2", Double.parseDouble(CartDetailsAdapter.total_prise_st));
                    selectedFragment = PayUMoneyMainActivity.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout1, selectedFragment);
                    transaction.addToBackStack("cart_detail");
                    transaction.commit();
                    selectedFragment.setArguments(bundle);
                    System.out.println("gadshkjshjksahkd");
                }
            }
        });*/

        online_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putDouble("pricestrtext2", Double.parseDouble(CartDetailsAdapter.total_prise_st));
                selectedFragment = PayUMoneyMainActivity.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("cart_detail");
                transaction.commit();
                selectedFragment.setArguments(bundle);
            }
        });
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();


            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();

                    return true;
                }
                return false;

            }
        });

        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* selectedFragment = PayUMoneyMainActivity.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("cart_detail");
                transaction.commit();*/
                AddMoney();
            }
        });


        return view;
    }

    public void  AddMoney(){
        JSONObject params = new JSONObject();
        try {
            params.put("UserId",sessionManager.getRegId("userId"));
            params.put("Amount", CartDetailsAdapter.total_prise_st);  // amount
            params.put("ProductInfo", sessionManager.getRegId("default_address"));  // amount
            params.put("TransactionFees","0.00");  //transaction fees
            params.put("DiscountAmount","0.00");
            params.put("mode","COD");
            params.put("PaymentMethod",2);
            params.put("UserType","C");
            params.put("Status","0"); //using status
            params.put("UnMappedStatus","0");
            params.put("key","0");
            params.put("TxnId","0");  //tarnsaction id
            params.put("CardCategory","abc");
            params.put("FirstName","Priya");
            params.put("Email","farmpe.renewin@gmail.com");
            params.put("Phone",sessionManager.getRegId("phone"));
            params.put("PaymentVendor","vendor");
            params.put("Currency","1");
            params.put("BankTxnId","1234");
            params.put("PaymentDeskId","1234");
            if (CartDetailsAdapter.quantity_pick==0){
                params.put("SelectedQuantity", CartDetailsAdapter.quant_zero);
            }else{
                params.put("SelectedQuantity", CartDetailsAdapter.quantity_pick);
            }
            params.put("CreatedBy",sessionManager.getRegId("userId"));
            if (CurrentLocation.latitude_post!=0){
                params.put("CustLongitude", CurrentLocation.longitude_post);
                params.put("CustLatitude", CurrentLocation.latitude_post);
            }else{
                params.put("CustLongitude",sessionManager.getRegId("lng"));
                params.put("CustLatitude",sessionManager.getRegId("lat"));
            }

            if (CurrentLocation.current_addr!=null){
                params.put("CustAddress", CurrentLocation.current_addr);
            }else{
                params.put("CustAddress",sessionManager.getRegId("default_address"));

            }
            params.put("IsActive",1);
            params.put("RESPMsg","RESMsg");
            params.put("CartProductListIds", CartDetailsAdapter.strlist);
            System.out.println("RESPMsgdsfadf"+params);
            Login_post.login_posting(getActivity(), Urls.PayuMoneyAdd, params, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("llllllllllllllllllllllllllll"+result);
                    try {
                        System.out.println("nnnnnmnm" + result.toString());
                        String status=result.getString("Status");
                        //  if(status.equals("Success")){
                        if(status.equals("Success")){
                            Toast toast = Toast.makeText(getActivity(),"Your product is ordered successfully", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();
                            //   Toast.makeText(getActivity(),"Transaction Successfully Completed",Toast.LENGTH_LONG).show();
                           /* selectedFragment = HomeFragment.newInstance();
                            FragmentTransaction transaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout1, selectedFragment);
                            transaction.commit();*/

                            selectedFragment = OrderDetailsCoDFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout1, selectedFragment);
                            transaction.addToBackStack("cart_detailt");
                            transaction.commit();
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity(),"Something went wrong", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();
                            //  Toast.makeText(getActivity(),"Transaction Incomplete",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
