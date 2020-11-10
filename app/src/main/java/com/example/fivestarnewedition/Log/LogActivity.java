package com.example.fivestarnewedition.Log;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;


public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initiale();
    }

    private void initiale() {
        RecyclerView recyclerView = findViewById(R.id.log_recycler);
        LogAdapter adapter = new LogAdapter(Constant.getLogs(LogActivity.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void clearLog(View view) {
        Constant.clearLog(LogActivity.this);
        initiale();
    }
}