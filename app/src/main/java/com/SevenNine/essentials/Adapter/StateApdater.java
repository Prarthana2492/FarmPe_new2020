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

public class StateApdater extends RecyclerView.Adapter<StateApdater.MyStateHolder> {
    List<StateBean>stateBeans;
    Activity activity;
   public static int stateid;


    public StateApdater(List<StateBean> stateBeans, Activity activity) {
        this.stateBeans = stateBeans;
        this.activity=activity;
    }


    @NonNull
    @Override
    public MyStateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateview=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_name,parent,false);
        return new MyStateHolder(stateview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyStateHolder holder, int position) {
    final StateBean stateBean=stateBeans.get(position);

         holder.statename.setText(stateBean.getName());


        holder.state_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   stateid=stateBean.getId();
                Add_New_Address_Fragment.state_txt.setText(holder.statename.getText().toString());
                Add_New_Address_Fragment.drawer.closeDrawers();
//                if (Add_NewBankDetails_Fragment.page!=null){
//                    Add_NewBankDetails_Fragment.state.setText(holder.statename.getText().toString());
//                    Add_NewBankDetails_Fragment.drawer.closeDrawers();
//
//                }else if (NewAddressFragment.address!=null){
//                    NewAddressFragment.ed_state.setText(holder.statename.getText().toString());
//                    NewAddressFragment.drawer.closeDrawers();
//                }else{
//                    AddNewAddressFragment.ed_state.setText(holder.statename.getText().toString());
//                    AddNewAddressFragment.drawer.closeDrawers();
//                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return stateBeans.size();
    }

    public class MyStateHolder extends RecyclerView.ViewHolder{

        TextView statename;
        LinearLayout state_name_layout;

        public MyStateHolder(View itemView) {
            super(itemView);
            statename=itemView.findViewById(R.id.state_item);
            state_name_layout=itemView.findViewById(R.id.state_name_layout);

        }
    }
}
