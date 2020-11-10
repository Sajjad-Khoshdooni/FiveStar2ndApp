package com.example.fivestarnewedition.ADDSection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.Constant.Group;
import com.example.fivestarnewedition.R;

import java.util.List;

public class EditGroupAdapter extends RecyclerView.Adapter<EditGroupAdapter.ViewHolder> {
    private List<Device> devices;
    private Group group;
    private boolean edit = false;

    public EditGroupAdapter(AppCompatActivity activity){
        devices = Constant.getDevices(activity);
    }

    public EditGroupAdapter(AppCompatActivity activity,Group group){
        this.group = group;
        devices = Constant.getDevices(activity);
        edit = true;
        Constant.setGroupDeice(group.getDevices());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EditGroupAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edit_group_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Device device = devices.get(position);
        holder.name.setText(device.getName());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()){
                    Constant.addGroupDeice(device);
                }else{
                    Constant.removeGroupDeice(device);
                }
            }
        });
        if (edit == true){
            for (Device device1 :group.getDevices()){
                if (device.getName().equals(device1.getName())){
                    holder.checkBox.setChecked(true);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.edit_group_name);
            checkBox = itemView.findViewById(R.id.edit_group_checkbox);
        }
    }
}
