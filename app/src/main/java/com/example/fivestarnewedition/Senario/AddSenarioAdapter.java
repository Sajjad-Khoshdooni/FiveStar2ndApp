package com.example.fivestarnewedition.Senario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.R;

import java.util.List;

public class AddSenarioAdapter extends RecyclerView.Adapter<AddSenarioAdapter.ViewHolder> {
    private List<Device> devices;
    private AppCompatActivity activity;

    public AddSenarioAdapter(AppCompatActivity activity){
        this.activity = activity;
        devices = Constant.getDevices(activity);
    }

    @NonNull
    @Override
    public AddSenarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new AddSenarioAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.senario_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddSenarioAdapter.ViewHolder viewHolder, int i) {
        Device device = devices.get(i);
        viewHolder.device.setText(device.getName());
        viewHolder.device.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Constant.addSenarioDevice(device);
                    notifyDataSetChanged();
                }else {
                    try {
                        Constant.removeSenarioDevice(device);
                        notifyDataSetChanged();
                    } catch (Exception e) {
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox device;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            device = itemView.findViewById(R.id.SenariocheckBox);
        }
    }
}
