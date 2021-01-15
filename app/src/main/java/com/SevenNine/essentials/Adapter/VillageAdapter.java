package com.SevenNine.essentials.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.SevenNine.essentials.Fragment.Add_New_Address_Fragment;

import com.SevenNine.essentials.Bean.StateBean;
import com.SevenNine.essentials.R;

import java.util.List;

public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.HoblisMyViewHolder> {

    List<StateBean>stateBeans;
    public static int villageid;
    Activity activity;



    public VillageAdapter(List<StateBean> stateBeans, Activity activity) {
        this.stateBeans = stateBeans;
        this.activity = activity;
    }



    @NonNull
    @Override
    public HoblisMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateview=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_name,parent,false);


        return new HoblisMyViewHolder(stateview);
    }

    @Override
    public void onBindViewHolder(@NonNull final HoblisMyViewHolder holder, int position) {
        final StateBean stateBean=stateBeans.get(position);
        holder.statename.setText(stateBean.getName());

        holder.state_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Add_New_Address_Fragment.edit_village.setText(holder.statename.getText().toString());
                Add_New_Address_Fragment.drawer.closeDrawers();
//               villageid = stateBean.getId();
//                if (NewAddressFragment.address!=null){
//                    NewAddressFragment.ed_vill.setText(holder.statename.getText().toString());
//                    NewAddressFragment.drawer.closeDrawers();
//                }else {
//                    AddNewAddressFragment.ed_vill.setText(holder.statename.getText().toString());
//                    AddNewAddressFragment.drawer.closeDrawers();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateBeans.size();
    }

    public class HoblisMyViewHolder extends RecyclerView.ViewHolder{
        TextView statename;
        LinearLayout state_name_layout;
        public HoblisMyViewHolder(View itemView) {
            super(itemView);
            statename=itemView.findViewById(R.id.state_item);
            state_name_layout=itemView.findViewById(R.id.state_name_layout);

        }
    }
}
