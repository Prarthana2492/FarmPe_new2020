package com.SevenNine.essentialscode.Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.SevenNine.essentialscode.R;

public class Guidelines_Slider_Adapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        Activity activity;
        int[] myImageList;


        public Guidelines_Slider_Adapter(Activity activity, int[] myImageList) {
            this.activity = activity;
            this.myImageList = myImageList;
        }
        @SuppressLint("ClickableViewAccessibility")
        @Override



        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.sliding_layout, container, false);
            ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
            im_slider.setImageResource(myImageList[position]);

            im_slider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Toast.makeText(activity, "Clicked "+position, Toast.LENGTH_SHORT).show();
                }
            });

            container.addView(view);
            return view;
        }
        @Override
        public int getCount() {
            return myImageList.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }




