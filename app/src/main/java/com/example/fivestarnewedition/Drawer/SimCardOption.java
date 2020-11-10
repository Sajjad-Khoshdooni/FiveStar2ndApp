package com.example.fivestarnewedition.Drawer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.MQTT.MQTT;
import com.example.fivestarnewedition.R;


public class SimCardOption extends AppCompatActivity {
    private TextView anten;
    private EditText charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_card_option);

        anten = findViewById(R.id.anten_power);
        charge = findViewById(R.id.charge_code);
        MQTT.getInstance(SimCardOption.this).subscribe(Constant.getMainAccount(SimCardOption.this).getIMEI() + "/Ant1");
    }

    public void recharge(View view) {
        if (charge.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Charge code is Empty!!!",Toast.LENGTH_SHORT).show();
            charge.requestFocus();
            return;
        }
        Constant.sendMessage(charge.getText().toString(),getApplicationContext());
    }

    public void balance(View view) {
//        Constant.sendMessage();
    }
}