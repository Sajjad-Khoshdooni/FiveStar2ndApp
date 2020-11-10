package com.example.fivestarnewedition.Drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.BlueTooth.BluetoothTestActivity;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.ControlEnum;
import com.example.fivestarnewedition.MQTT.MQTT;
import com.example.fivestarnewedition.R;
import com.example.fivestarnewedition.Security.SetPasswordActivity;


public class SettingPanelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_panel);
    }

    public void backup(View view) {
        Constant.backup();
        Toast.makeText(getApplicationContext(),"Successfully Backup",Toast.LENGTH_SHORT).show();
    }

    public void restore(View view) {
        Constant.restore();
        Toast.makeText(getApplicationContext(),"Successfully Restore",Toast.LENGTH_SHORT).show();
    }

    public void changePassword(View view) {
        Intent my = new Intent(SettingPanelActivity.this, SetPasswordActivity.class);
        my.putExtra("code","0");
        startActivity(my);
    }

    public void connection(View view) {
        if (Constant.getMainAccount(SettingPanelActivity.this) == null){
            Toast.makeText(getApplicationContext(),"There is no Account",Toast.LENGTH_SHORT).show();
            return;
        }
        new AlertDialog.Builder(SettingPanelActivity.this).setTitle("Connection Type").
                setMessage("Chosse connection type")
                .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MQTT mqtt = MQTT.getInstance(SettingPanelActivity.this);
                        mqtt.connect(Constant.getMainAccount(SettingPanelActivity.this).getAddress(),
                                Constant.getMainAccount(SettingPanelActivity.this).getID());
                        Constant.setControlEnum(ControlEnum.INTERNET);
                        Constant.setMqtt(mqtt);
                    }
                }).setNegativeButton("Bluetooth", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent my = new Intent(SettingPanelActivity.this, BluetoothTestActivity.class);
                startActivity(my);
            }
        })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
