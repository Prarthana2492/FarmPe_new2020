package com.SevenNine.essentialscode.TabLayoutAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.SevenNine.essentialscode.Fragment.PaymentDetailsFragment;
import com.SevenNine.essentialscode.Fragment.PaymentTodayFragment;


/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class PagerPayment extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PagerPayment(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs

        System.out.println("llllllllllllllllllll1"+position);

        switch (position) {
           /* case 0:
                ScheduledTabFragment scheduledTabFragment=new ScheduledTabFragment();
                return scheduledTabFragment;*/

            case 0:
                System.out.println("llllllllllllllllllll1");
                PaymentDetailsFragment tab1 = new PaymentDetailsFragment();
                return tab1;
            case 1:
                PaymentTodayFragment scheduledTabFragment=new PaymentTodayFragment();
                return scheduledTabFragment;
            case 2:
                PaymentTodayFragment tab2 = new PaymentTodayFragment();
                return tab2;
           /* case 5:
                FarmLocationItemFragment tab5=new FarmLocationItemFragment();
                return  tab5;*/

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}