package com.example.fivestarnewedition.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;

public class AccountManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {

        RecyclerView recyclerView = findViewById(R.id.account_activity_recycler);
        AccountAdapter adapter = new AccountAdapter(AccountManageActivity.this, Constant.getAccounts(AccountManageActivity.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void addAccount(View view) {
        Intent my = new Intent(AccountManageActivity.this, EditAccountActivity.class);
        my.putExtra("edit","0");
        startActivity(my);
    }
}