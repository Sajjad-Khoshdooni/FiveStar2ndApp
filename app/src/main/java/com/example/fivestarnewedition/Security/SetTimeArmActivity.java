package com.example.fivestarnewedition.Security;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.R;


public class SetTimeArmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_arm);

        if (getIntent().getStringExtra("type").equals("arm")){
            arm();
        }else {
            disarm();
        }
    }

    private void arm() {
    }

    private void disarm() {
    }
}