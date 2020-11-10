package com.example.fivestarnewedition.ADDSection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;

public class AddDeviceActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.add_device_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddDeviceAdapter adapter = new AddDeviceAdapter(AddDeviceActivity.this, Constant.getDevices(AddDeviceActivity.this));
        recyclerView.setAdapter(adapter);
    }

    public void addDevices(View view) {
        Intent my = new Intent(AddDeviceActivity.this, EditDeviceActivity.class);
        my.putExtra("edit","0");
        startActivity(my);
    }
}