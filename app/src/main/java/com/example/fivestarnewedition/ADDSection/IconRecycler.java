package com.example.fivestarnewedition.ADDSection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;

import java.util.ArrayList;
import java.util.List;

public class IconRecycler extends RecyclerView.Adapter<IconRecycler.ViewHolder> {
    private List<Integer> devices;
    private Boolean isOn = false;
    private AppCompatActivity appCompatActivity;

    public IconRecycler(AppCompatActivity activity){
        appCompatActivity = activity;
        isOn = Constant.isOn();
        try {
            devices = Constant.getImages();
        } catch (Exception e) {
            devices = new ArrayList();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IconRecycler.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.icon_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int device = devices.get(position);
        holder.imageView.setImageResource(device);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOn){
                    Constant.setImageOnIcon(device);
                    appCompatActivity.finish();
                }else {
                    Constant.setImageOffIcon(device);
                    appCompatActivity.finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon_image);
        }
    }
}
