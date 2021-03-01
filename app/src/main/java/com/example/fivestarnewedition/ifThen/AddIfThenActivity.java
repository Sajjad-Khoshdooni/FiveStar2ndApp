package com.example.fivestarnewedition.ifThen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.fivestarnewedition.ADDSection.EditDeviceActivity;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.Constant.IfThen;
import com.example.fivestarnewedition.R;

import java.util.List;

public class AddIfThenActivity extends AppCompatActivity {
    Button ifBtn;
    Button andBtn;
    Button thenBtn;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_if_then);

        if (Constant.getDevices(AddIfThenActivity.this).size()==0){
            Toast.makeText(getApplicationContext(),"First Create Device",Toast.LENGTH_LONG).show();
            AddIfThenActivity.this.finish();
        }

        init();
    }

    private void init() {
        ifBtn = findViewById(R.id.if_btn);
        ifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button vieww = (Button) view;
                PopupMenu menu = new PopupMenu(AddIfThenActivity.this,vieww);

                for (Device device: Constant.getDevices(AddIfThenActivity.this)){
                    menu.getMenu().add(device.getName());
                }

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        vieww.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });
        andBtn = findViewById(R.id.and_btn);
        andBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button vieww = (Button) view;
                PopupMenu menu = new PopupMenu(AddIfThenActivity.this,vieww);

                for (Device device: Constant.getDevices(AddIfThenActivity.this)){
                    menu.getMenu().add(device.getName());
                }

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        vieww.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });
        thenBtn = findViewById(R.id.then_btn);
        thenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button vieww = (Button) view;
                PopupMenu menu = new PopupMenu(AddIfThenActivity.this,vieww);

                for (Device device: Constant.getDevices(AddIfThenActivity.this)){
                    menu.getMenu().add(device.getName());
                }

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        vieww.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });
        save = findViewById(R.id.save_if_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Device> devices = Constant.getDevices(AddIfThenActivity.this);
                Device ifDevice = devices.get(0);
                Device andDevice = devices.get(0);
                Device thenDevice = devices.get(0);
                for (Device device:devices){
                    if (device.getName().equals(ifBtn.getText().toString())){
                        ifDevice = device;
                    }else if (device.getName().equals(andBtn.getText().toString())){
                        andDevice = device;
                    }else if (device.getName().equals(thenBtn.getText().toString())){
                        thenDevice = device;
                    }
                }
                Constant.addIfThens(AddIfThenActivity.this, new IfThen(ifDevice,andDevice,thenDevice));
                AddIfThenActivity.this.finish();
            }
        });
    }
}