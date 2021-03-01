package com.example.fivestarnewedition.Senario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Senario;
import com.example.fivestarnewedition.R;

public class AddSenarioActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button save;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_senario);

        Constant.clearSenarioDevice();

        save = findViewById(R.id.choose_device_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        name = findViewById(R.id.senario_name);
        recyclerView = findViewById(R.id.choose_senario_recycler);
        AddSenarioAdapter myAdapter = new AddSenarioAdapter(AddSenarioActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    public void stop() {

        if (name.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter Name!!!", Toast.LENGTH_SHORT).show();
            name.requestFocus();
            return;
        }else if (Constant.getSenarioDevice().size() == 0){

            Toast.makeText(getApplicationContext(),"Choose Device!!!", Toast.LENGTH_SHORT).show();
        }
        Constant.addSenario(AddSenarioActivity.this, new Senario(name.getText().toString(),R.drawable.l,Constant.getSenarioDevice()));
        AddSenarioActivity.this.finish();
    }
}