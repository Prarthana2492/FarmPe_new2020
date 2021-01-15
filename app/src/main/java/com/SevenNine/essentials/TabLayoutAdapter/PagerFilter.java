package com.SevenNine.essentials.TabLayoutAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.SevenNine.essentials.Fragment.FilterProductsExpandableFragment;
import com.SevenNine.essentials.Fragment.FilterSortByFragment;


/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class PagerFilter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PagerFilter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs

        System.out.println("llllllllllllllllllll1888"+" "+position);

        switch (position) {
           /* case 0:
                ScheduledTabFragment scheduledTabFragment=new ScheduledTabFragment();
                return scheduledTabFragment;*/

            case 0:
                FilterProductsExpandableFragment tab1 = new FilterProductsExpandableFragment();
                return tab1;
            case 1:
                FilterSortByFragment scheduledTabFragment=new FilterSortByFragment();
                return scheduledTabFragment;
           /* case 2:
                NewOrderFragment tab2 = new NewOrderFragment();
                return tab2;*/
           /* case 3:
                NewOrderFragment tab3 = new NewOrderFragment();
                return tab3;*/
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