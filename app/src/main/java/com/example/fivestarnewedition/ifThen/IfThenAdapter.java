package com.example.fivestarnewedition.ifThen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.IfThen;
import com.example.fivestarnewedition.R;

import java.util.List;

public class IfThenAdapter extends RecyclerView.Adapter<IfThenAdapter.ViewHolder> {
    private List<IfThen> ifThens;
    private AppCompatActivity appCompatActivity;

    public IfThenAdapter(AppCompatActivity activity,List<IfThen> ifThens){
        appCompatActivity = activity;
        this.ifThens = ifThens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IfThenAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.if_then_activity_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IfThen a = ifThens.get(position);
        holder.aSwitch.setChecked(a.isActive());
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                a.setActive(isChecked);
                Constant.writeIfThen(appCompatActivity,ifThens);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ifThens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Switch aSwitch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aSwitch = itemView.findViewById(R.id.ifthen_switch);
        }
    }
}
