package com.example.fivestarnewedition.ADDSection;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.R;


public class EditDeviceActivity extends AppCompatActivity {
    private Boolean isEdit = false;
    private Device device;
    private EditText name, delay;
    private Button zonePopup, typePopup, onIcon, offIcon;
    private Switch notifySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);

        name = findViewById(R.id.edit_device_name);
        delay = findViewById(R.id.edit_device_delay);
        zonePopup = findViewById(R.id.edit_device_zone_popup);
        typePopup = findViewById(R.id.edit_device_type_popup);
        notifySwitch = findViewById(R.id.edit_device_notify_switch);
        onIcon = findViewById(R.id.edit_device_onicon);
        offIcon = findViewById(R.id.edit_device_officon);

        initializeZonePopups();
        initializeTypePopup();

        if (getIntent().getStringExtra("edit").equals("1")){
            initialize(Integer.valueOf(getIntent().getStringExtra("position")));
            isEdit = true;
        }
        else {

        }
    }

    private void initializeTypePopup() {
        typePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button vieww = (Button) view;
                PopupMenu menu = new PopupMenu(EditDeviceActivity.this,vieww);

                if (zonePopup.getText().toString().contains("Zone")){
                    menu.getMenu().add("NORMALLY CLOSE");
                    menu.getMenu().add("NORMALLY OPEN");
                }else {
                    menu.getMenu().add("Toggle");
                    menu.getMenu().add("Set");
                    menu.getMenu().add("Reset");
                    menu.getMenu().add("Delay");
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
    }

    private void initializeZonePopups() {
        zonePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button vieww = (Button) view;
                PopupMenu menu = new PopupMenu(EditDeviceActivity.this,vieww);
                for (int i = 0 ; i<52 ; i++){
                    menu.getMenu().add("Zone "+String.valueOf(i+1));
                }
                for (int i = 0 ; i<52 ; i++){
                    menu.getMenu().add("Out "+String.valueOf(i+1));
                }
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        vieww.setText(menuItem.getTitle());
                        initializeTypePopup();
                        return false;
                    }
                });
                menu.show();
            }
        });
    }

    private void initialize(Integer position) {
        this.device = Constant.getDevices(EditDeviceActivity.this ,position);
        name.setText(device.getName());
        delay.setText(String.valueOf(device.getDelay()));
        typePopup.setText(device.getDeviceType().toString().toLowerCase());
        zonePopup.setText(device.getZone().toLowerCase());
        notifySwitch.setChecked(device.getNotify());
    }

    public void onIcon(View view) {
        Constant.setOn(true);
        Intent my = new Intent(EditDeviceActivity.this,SelectIconActivity.class);
        startActivity(my);
    }

    public void offIcon(View view) {
        Constant.setOn(false);
        Intent my = new Intent(EditDeviceActivity.this,SelectIconActivity.class);
        startActivity(my);
    }

    public void save(View view) {
        try {
            if (isEdit) {
                Constant.changeDevice(EditDeviceActivity.this ,device, new Device(zonePopup.getText().toString(), name.getText().toString(),
                        typePopup.getText().toString(), Integer.valueOf(delay.getText().toString()), Boolean.valueOf(notifySwitch.isChecked()),
                        Constant.getImageOnIcon(), Constant.getImageOffIcon(), getStringCode()));
                return;
            } else {
                if (zonePopup.getText().toString().equals("Zone Popup")){
                    Toast.makeText(getApplicationContext(), "Choose Zone", Toast.LENGTH_SHORT).show();
                    zonePopup.requestFocus();
                    return;
                }
                if (typePopup.getText().toString().equals("Device Type popup")){
                    Toast.makeText(getApplicationContext(), "Choose Device Type", Toast.LENGTH_SHORT).show();
                    typePopup.requestFocus();
                    return;
                }
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name is Empty!!!", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                    return;
                }
                for (Device device:Constant.getDevices(EditDeviceActivity.this)){
                    if (name.getText().toString().equals(device.getName())){
                        Toast.makeText(getApplicationContext(), "Change name", Toast.LENGTH_SHORT).show();
                        name.requestFocus();
                        return;
                    }
                }
                Constant.addDevice(EditDeviceActivity.this ,new Device(zonePopup.getText().toString(), name.getText().toString(),
                        typePopup.getText().toString(), Integer.valueOf(delay.getText().toString()), Boolean.valueOf(notifySwitch.isChecked()),
                        Constant.getImageOnIcon(), Constant.getImageOffIcon(), getStringCode()));
            }
        } catch (NumberFormatException e) {
        }
        finish();
    }

    private String getStringCode() {
        String code = "";
        if (zonePopup.getText().toString().contains("Zone")) {
            code+="Z";
            for (int i = 1; i <=52 ; i++){
                if (zonePopup.getText().toString().contains(String.valueOf(i))){
                    code += getCode(i);
                }
            }
            if (typePopup.getText().toString().contains("CLOSE")){
                code+="C";
            }else if (typePopup.getText().toString().contains("OPEN")){
                code+="O";
            }
            if (delay.getText().toString().equals("0")){
                code+="A";
            }else {
                code+="D";
                if (delay.getText().toString().length()==1){
                    code+="00";
                    code+=delay.getText().toString();
                }else if (delay.getText().toString().length() == 2){
                    code+="0";
                    code+=delay.getText().toString();
                }else {
                    code+=delay.getText().toString();
                }
            }
        }
        else {
            code+="Out";
            for (int i = 1; i <=52 ; i++){
                if (zonePopup.getText().toString().contains(String.valueOf(i))){
                    code += getCode(i);
                }
            }
            if (delay.getText().toString().length()==1){
                code+="00";
                code+=delay.getText().toString();
            }else if (delay.getText().toString().length() == 2){
                code+="0";
                code+=delay.getText().toString();
            }else {
                code+=delay.getText().toString();
            }
            if (typePopup.getText().toString().contains("Toggle")){
                code+="A";
            }else if (typePopup.getText().toString().contains("Reset")){
                code+="b";
            }else if (typePopup.getText().toString().contains("Set")){
                code+="B";
            }else {
                code+="C";
            }
        }

        code+="#";
        return code;
    }

    private String getCode(int i){
        switch(i){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            case 11:
                return "K";
            case 12:
                return "L";
            case 13:
                return "M";
            case 14:
                return "N";
            case 15:
                return "O";
            case 16:
                return "P";
            case 17:
                return "Q";
            case 18:
                return "R";
            case 19:
                return "S";
            case 20:
                return "T";
            case 21:
                return "U";
            case 22:
                return "V";
            case 23:
                return "W";
            case 24:
                return "X";
            case 25:
                return "Y";
            case 26:
                return "Z";
            case 27:
                return "a";
            case 28:
                return "b";
            case 29:
                return "c";
            case 30:
                return "d";
            case 31:
                return "e";
            case 32:
                return "f";
            case 33:
                return "g";
            case 34:
                return "h";
            case 35:
                return "i";
            case 36:
                return "j";
            case 37:
                return "k";
            case 38:
                return "l";
            case 39:
                return "m";
            case 40:
                return "n";
            case 41:
                return "o";
            case 42:
                return "p";
            case 43:
                return "q";
            case 44:
                return "r";
            case 45:
                return "s";
            case 46:
                return "t";
            case 47:
                return "u";
            case 48:
                return "v";
            case 49:
                return "w";
            case 50:
                return "x";
            case 51:
                return "y";
            case 52:
                return "z";
        }
        return "A";
    }
}