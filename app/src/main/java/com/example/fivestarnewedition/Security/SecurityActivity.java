package com.example.fivestarnewedition.Security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.MQTT.MQTTMessageCenter;
import com.example.fivestarnewedition.R;


public class SecurityActivity extends AppCompatActivity {
    Button arm,disarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        initialize();
        updateIcon();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateIcon();
    }

    private void updateIcon() {
        Constant.subscribe("Secur");
        if (MQTTMessageCenter.getSecur().equals("SAL#")){
            armSet();
        }else if (MQTTMessageCenter.getSecur().equals("SDL#")){
            disarmSet();
        }
    }

    private void initialize() {
        arm = findViewById(R.id.armbtn);
        arm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.sendMessage("SAL#",getApplicationContext());
            }
        });

        disarm = findViewById(R.id.disarmbtn);
        disarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.sendMessage("SDL#",getApplicationContext());
            }
        });
    }

    public void armTime(View view) {
        Intent my = new Intent(SecurityActivity.this, SetTimeArmActivity.class);
        my.putExtra("type","arm");
        startActivity(my);
    }

    public void disarmTime(View view) {
        Intent my = new Intent(SecurityActivity.this, SetTimeArmActivity.class);
        my.putExtra("type","disarm");
        startActivity(my);
    }

    public void sendCode(View view) {
        try {
            switch (view.getId()) {
                case R.id.armbtn:
                    Constant.sendMessage("SAL#", getApplicationContext());
                    updateIcon();
                    Thread.sleep(1000);
                    updateIcon();
                    return;
                case R.id.disarmbtn:
                    Constant.sendMessage("SDL#", getApplicationContext());
                    updateIcon();
                    Thread.sleep(1000);
                    updateIcon();
                    return;
                case R.id.gasvalvebtn:
//                Constant.sendMessage();
                    return;
                case R.id.waterbtn:
//                Constant.sendMessage();
                    return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void armSet(){
        arm.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.arm,0,0);
        disarm.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.darm,0,0);
    }

    private void disarmSet(){
        disarm.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.disarm,0,0);
        arm.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.darm,0,0);
    }
}