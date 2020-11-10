package com.example.fivestarnewedition.Senario;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.Constant.Senario;
import com.example.fivestarnewedition.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Senario> senarios;
    private Context context;

    public RecyclerAdapter(Context context, List<Senario> senarios){
        this.context = context;
        this.senarios = senarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.senario_activity_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Senario senario = senarios.get(position);
        holder.name.setText(senario.getName());
        holder.icon.setImageResource(senario.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my = new Intent(context, SenarioThingActivity.class);
                my.putExtra("position",position);
                context.startActivity(my);
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Device device:senario.getDevices()){
                    Constant.sendMessage(device.getSendCode(),context);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return senarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText name;
        ImageView icon;
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.senario_recycler_textview);
            icon = itemView.findViewById(R.id.senario_recycler_imageview);
            btn = itemView.findViewById(R.id.senario_recycler_run_btn);
        }
    }
}
