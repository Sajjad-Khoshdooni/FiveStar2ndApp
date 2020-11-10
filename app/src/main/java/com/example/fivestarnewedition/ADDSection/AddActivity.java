package com.example.fivestarnewedition.ADDSection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.R;


public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void addDevice(View view) {
        Intent my = new Intent(AddActivity.this, AddDeviceActivity.class);
        startActivity(my);
    }

    public void addGroup(View view) {
        Intent my = new Intent(AddActivity.this, AddGroupActivity.class);
        startActivity(my);
    }
}