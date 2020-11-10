package com.example.fivestarnewedition.ADDSection;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.R;

import java.util.List;

public class AddDeviceAdapter extends RecyclerView.Adapter<AddDeviceAdapter.ViewHolder> {
    private List<Device> devices;
    private AppCompatActivity activity;

    public AddDeviceAdapter(AppCompatActivity activity, List<Device> devices){
        this.activity = activity;
        this.devices = devices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddDeviceAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_device_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Device device = devices.get(position);
        holder.image.setImageResource(device.getOnIcon());
        holder.name.setText(device.getName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my = new Intent(activity, EditDeviceActivity.class);
                my.putExtra("edit" , "1");
                my.putExtra("position",String.valueOf(position));
                activity.startActivity(my);
                notifyDataSetChanged();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(activity).setTitle("Delete entry").setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Constant.removeDevice(activity ,device);
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
        ImageButton delete,edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.add_device_recycler_image);
            name = itemView.findViewById(R.id.add_device_recycler_name);
            delete = itemView.findViewById(R.id.add_device_recycler_delete);
            edit = itemView.findViewById(R.id.add_device_recycler_edit);


        }
    }
}
