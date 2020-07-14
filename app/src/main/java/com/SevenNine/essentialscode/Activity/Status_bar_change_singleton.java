package com.SevenNine.essentialscode.Activity;

import android.app.Activity;
import android.os.Build;

import com.SevenNine.essentialscode.R;


public class Status_bar_change_singleton {


   public static Status_bar_change_singleton status_color;



    public static Status_bar_change_singleton getInstance(){


        if(status_color == null )
            status_color = new Status_bar_change_singleton();

      return status_color;

    }

    public void color_change (Activity activity){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_green, activity.getTheme()));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_green));
        }
    }


    public void home_change (Activity activity){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary, activity.getTheme()));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
        }
    }

}
