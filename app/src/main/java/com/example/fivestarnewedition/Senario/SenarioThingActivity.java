package com.example.fivestarnewedition.Senario;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Senario;
import com.example.fivestarnewedition.R;


public class SenarioThingActivity extends AppCompatActivity {
    private Senario senario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senario_thing);

        senario = Constant.getSenarios(SenarioThingActivity.this ,Integer.valueOf(getIntent().getStringExtra("position")));
    }
}