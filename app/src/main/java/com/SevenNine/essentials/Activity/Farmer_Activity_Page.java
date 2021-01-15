package com.SevenNine.essentials.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.SevenNine.essentials.R;


public class Farmer_Activity_Page extends AppCompatActivity {

    LinearLayout txt_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_activity_page);

        txt_btn = findViewById(R.id.txt_btn);

        txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Farmer_Activity_Page.this,FirmShopDetailsActivity.class);
                startActivity(intent);

            }
        });

    }
}
