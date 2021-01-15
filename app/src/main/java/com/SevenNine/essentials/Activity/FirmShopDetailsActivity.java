package com.SevenNine.essentials.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.SevenNine.essentials.Fragment.FirmShopDetailsFragment;
import com.SevenNine.essentials.Fragment.HomeFragment;
import com.SevenNine.essentials.R;


public class FirmShopDetailsActivity extends AppCompatActivity {

    Fragment selectedFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firm);


        selectedFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout1, selectedFragment);
        transaction.commit();
    }
}
