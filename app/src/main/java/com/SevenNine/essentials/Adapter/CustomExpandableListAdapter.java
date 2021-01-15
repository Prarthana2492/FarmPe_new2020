package com.SevenNine.essentials.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.SevenNine.essentials.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    public static String price_range,category,category_text;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.filter_expandable_item, null);
        }
        final TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        final CheckBox check_category = (CheckBox) convertView
                .findViewById(R.id.check_category);
        expandedListTextView.setText(expandedListText);
        expandedListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_category.setChecked(true);
                category_text=expandedListTextView.getText().toString();
                System.out.println("jsdfdjs"+expandedListTextView.getText().toString());
                if (expandedListTextView.getText().toString().equals("Less than Rs 20")){
                    category=null;
                    price_range="LessthanRs20";
                }else if (expandedListTextView.getText().toString().equals("Rs 21 to Rs 50")) {
                    category=null;
                    price_range = "Rs21toRs50";
                }else if (expandedListTextView.getText().toString().equals("Rs 51 to Rs 100")) {
                    category=null;
                    price_range = "Rs51toRs100";
                }else if (expandedListTextView.getText().toString().equals("Rs 101 to Rs 200")) {
                    category=null;
                    price_range = "Rs101toRs200";
                }
                else if (expandedListTextView.getText().toString().equals("Rs 201 to Rs 500")) {
                    category=null;
                    price_range = "Rs201toRs500";
                }else if (expandedListTextView.getText().toString().equals("Fruits & Vegetables")){
                    category = "Fruits_veg";

                }




            }
        });

        check_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("jsdfdjs"+expandedListTextView.getText().toString());
                if (expandedListTextView.getText().toString().equals("Less than Rs 20")){
                    category=null;
                    price_range="LessthanRs20";
                }else if (expandedListTextView.getText().toString().equals("Rs 21 to Rs 50")) {
                    category=null;
                    price_range = "Rs21toRs50";
                }else if (expandedListTextView.getText().toString().equals("Rs 51 to Rs 100")) {
                    category=null;
                    price_range = "Rs51toRs100";
                }else if (expandedListTextView.getText().toString().equals("Rs 101 to Rs 200")) {
                    category=null;
                    price_range = "Rs101toRs200";
                }
                else if (expandedListTextView.getText().toString().equals("Rs 201 to Rs 500")) {
                    category=null;
                    price_range = "Rs201toRs500";
                }else if (expandedListTextView.getText().toString().equals("Fruits & Vegetables")){
                    category = "Fruits_veg";

                }

            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.filter_expandable_title, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.NORMAL);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}