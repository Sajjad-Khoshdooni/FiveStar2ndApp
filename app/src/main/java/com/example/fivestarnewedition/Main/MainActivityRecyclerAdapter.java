package com.example.fivestarnewedition.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.R;

import java.util.List;

public class MainActivityRecyclerAdapter extends RecyclerView.Adapter<MainActivityRecyclerAdapter.ViewHolder> {
    private List<Device> devices;
    private AppCompatActivity appCompatActivity;

    public MainActivityRecyclerAdapter(AppCompatActivity appCompatActivity, List<Device> devices){
        this.appCompatActivity = appCompatActivity;
        this.devices = devices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainActivityRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Device device = devices.get(position);
        holder.name.setText(device.getName());
        if (Constant.isDeviceOn(device)) {
            holder.image.setImageResource(device.getOnIcon());
        }else {
            holder.image.setImageResource(device.getOffIcon());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.sendMessage(device.getSendCode(),appCompatActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.main_recycler_imageview);
            name = itemView.findViewById(R.id.main_recycler_textview);
        }
    }
}
