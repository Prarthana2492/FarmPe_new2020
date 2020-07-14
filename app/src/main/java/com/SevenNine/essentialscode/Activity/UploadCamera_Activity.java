package com.SevenNine.essentialscode.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.SevenNine.essentialscode.G_Vision_Controller;

import com.SevenNine.essentialscode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;

public class  UploadCamera_Activity extends AppCompatActivity {
    static  String imageUri;
    ImageView imageView;
    Button upload;
    public Bitmap selectedImage;
    G_Vision_Controller g_vision_controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_fragment);
        g_vision_controller=G_Vision_Controller.getInstance();
        imageView= findViewById(R.id.reyclerview_message_list);
        upload= findViewById(R.id.button_upload);

        Intent intent = getIntent();
        String id = intent.getStringExtra("name");



        imageUri = id;
        Glide.with(getApplicationContext()).load("file://" + id)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)
                .skipMemoryCache(true))
                .into(imageView);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 selectedImage = BitmapFactory.decodeFile(String.valueOf(imageUri));
                // final InputStream imageStream =getContentResolver().openInputStream();
                // selectedImage = BitmapFactory.decodeStream(imageStream);


                ByteArrayOutputStream byteArrayOutputStreamObject ;

                byteArrayOutputStreamObject = new ByteArrayOutputStream();

                // Converting bitmap image to jpeg format, so by default image will upload in jpeg format.
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

                g_vision_controller.callCloudVision(selectedImage, UploadCamera_Activity.this,"farm");

            }
        });

    }
    @Override
    public void onBackPressed() {


        Intent intent=new Intent(UploadCamera_Activity.this,CameraActivity.class);
        startActivity(intent);
        finish();
    }

//red18tech
}
