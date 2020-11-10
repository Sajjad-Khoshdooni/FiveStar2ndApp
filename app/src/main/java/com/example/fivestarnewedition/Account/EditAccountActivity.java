package com.example.fivestarnewedition.Account;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Account;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;

public class EditAccountActivity extends AppCompatActivity {
    private EditText name,address,id,imei;
    private ImageButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        name = findViewById(R.id.account_name);
        address = findViewById(R.id.account_address);
        id = findViewById(R.id.account_id);
        imei = findViewById(R.id.account_imei);
        save = findViewById(R.id.account_save_btn);

        if (getIntent().getStringExtra("edit").equals("1")){
            edit(Integer.valueOf(getIntent().getStringExtra("position")));
        }
        else {
            TextView editText = findViewById(R.id.edit_account_tv);
            editText.setText("Add Account");

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (name.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Name is Empty !!!",Toast.LENGTH_SHORT).show();
                        name.requestFocus();
                        return;
                    }
                    else if (address.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Address is Empty !!!",Toast.LENGTH_SHORT).show();
                        address.requestFocus();
                        return;
                    }
                    else if (id.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),"ID is Empty !!!",Toast.LENGTH_SHORT).show();
                        id.requestFocus();
                        return;
                    }
                    else if (imei.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),"IMEI is Empty !!!",Toast.LENGTH_SHORT).show();
                        imei.requestFocus();
                        return;
                    }
                    Constant.addAccount(EditAccountActivity.this ,new Account(name.getText().toString(), address.getText().toString(),
                            id.getText().toString(), imei.getText().toString()));
                    finish();
                }
            });
        }
    }

    private void edit(Integer position) {
        TextView editText = findViewById(R.id.edit_account_tv);
        editText.setText("Edit Account");

        final Account account = Constant.getAccounts(EditAccountActivity.this ).get(position);
        name.setText(account.getName());
        address.setText(account.getAddress());
        id.setText(account.getID());
        imei.setText(account.getIMEI());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.changeAccount(EditAccountActivity.this ,account, new Account(name.getText().toString(), address.getText().toString(),
                        id.getText().toString(), imei.getText().toString()));
                finish();
            }
        });
    }
}