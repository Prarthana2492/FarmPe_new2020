package com.SevenNine.essentialscode.Fragment;

import com.SevenNine.essentialscode.Adapter.ShopbyCategoryAdapter;
import com.SevenNine.essentialscode.Bean.MainVerticalBean;
import com.SevenNine.essentialscode.Urls;
import com.SevenNine.essentialscode.Volly_class.Crop_Post;
import com.SevenNine.essentialscode.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> category = new ArrayList<String>();
        category.add("Fruits & Vegetables");
        category.add("Foodgrains, Oil & Masala");
        category.add("Bakery, Cakes & Dairy");
        category.add("Beverages");
        category.add("Snacks & Branded Foods");
        category.add("Beauty & Hygiene");
        category.add("Cleaning & Household");
        category.add("Kitchen, Garden & Pets");
        category.add("Gournment & World Food");
        category.add("Baby Care");



        List<String> price = new ArrayList<String>();
        price.add("Less than Rs 20");
            price.add("Rs 21 to Rs 50");
        price.add("Rs 51 to Rs 100");
        price.add("Rs 101 to Rs 200");
        price.add("Rs 202 to Rs 500");
        price.add("More than Rs 501");

        List<String> offers = new ArrayList<String>();
        offers.add("Upto 5%");
        offers.add("5% to 10%");
        offers.add("10% to 15%");
        offers.add("15% to 25%");
        offers.add("More than 25%");

        expandableListDetail.put("Category", category);
        expandableListDetail.put("Price", price);
        expandableListDetail.put("Offers", offers);
        return expandableListDetail;
    }
}
